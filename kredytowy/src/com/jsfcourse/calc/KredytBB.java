package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private double kwota;
	private int miesiace;
	private double oprocentowanie;
	private Double result;

	@Inject
	FacesContext ctx;

	public double getKwota() {
		return kwota;
	}

	public void setKwota(double Kwota) {
		kwota = Kwota;
	}

	public int getMiesiace() {
		return miesiace;
	}

	public void setMiesiace(int Miesiace) {
		miesiace = Miesiace;
	}

	public double getOprocentowanie() {
		return oprocentowanie;
	}

	public void setOprocentowanie(double Oprocentowanie) {
		oprocentowanie = Oprocentowanie;
	}

	public boolean doTheMath() {
		try {
			double kwota = this.kwota;
			double miesiace = this.miesiace;
			double oprocentowanie = this.oprocentowanie/100;
			double q = (1+(oprocentowanie/12));
			double rata = kwota*(Math.pow(q, miesiace)) * ((q-1)/(Math.pow(q,miesiace)-1));
			double kosztKredytu = rata*miesiace;
			
			result = kosztKredytu;
			return true;

		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystapil blad", null));
		}
		return false;

	}

	public String calc() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Calkowity koszt kredytu " + result, null));
			
		}
		return null;
	}
	
}

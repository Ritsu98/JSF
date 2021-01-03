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
	private double Kwota;
	private int Miesiace;
	private double Oprocentowanie;
	private Double result;

	@Inject
	FacesContext ctx;

	public double getKwota() {
		return Kwota;
	}

	public void setKwota(double kwota) {
		Kwota = kwota;
	}

	public int getMiesiace() {
		return Miesiace;
	}

	public void setMiesiace(int miesiace) {
		Miesiace = miesiace;
	}

	public double getOprocentowanie() {
		return Oprocentowanie;
	}

	public void setOprocentowanie(double oprocentowanie) {
		Oprocentowanie = oprocentowanie;
	}

	public boolean doTheMath() {
		try {
			double Kwota = this.Kwota;
			double Miesiace = this.Miesiace;
			double Oprocentowanie = this.Oprocentowanie/100;
			double q = (1+(Oprocentowanie/12));
			double Rata = Kwota*(Math.pow(q, Miesiace)) * ((q-1)/(Math.pow(q,Miesiace)-1));
			double Wynik = Rata*Miesiace;
			
			result = Wynik;
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

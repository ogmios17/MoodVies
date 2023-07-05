package model.beans;

import java.util.Date;

public class CartaBean 
{
	private String numero_carta;
	private int cvv;
	private Date scadenza;
	private String utente;	
	
	public CartaBean() 
	{
		numero_carta = "";
		cvv = 0;
		scadenza=null;
		utente = "";		
	}

	public String getNumeroCarta() {
		return numero_carta;
	}

	public void setNumeroCarta(String numero_carta) {
		this.numero_carta = numero_carta;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public Date getScadenza() {
		return scadenza;
	}

	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}		
	
}

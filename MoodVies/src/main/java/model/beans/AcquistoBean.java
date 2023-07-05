package model.beans;

import java.io.Serializable;

public class AcquistoBean implements Serializable 
{
	private static final long serialVersionUID=1L;
    private int id_acquisto;
	private int id_ordine;
	private String id_prodotto;
	private int quantita;
	private double subtotale_iva;
	private double subtotale;
	
	public AcquistoBean()
	 {
		id_acquisto = 0; 
        id_ordine=0;
		id_prodotto="";
		quantita=0;
		subtotale_iva=0;
		subtotale=0; 
	 }

	public int getIdAcquisto() {
		return id_acquisto;
	}

	public void setIdAcquisto(int id_acquisto) {
		this.id_acquisto = id_acquisto;
	}

	public int getIdOrdine() {
		return id_ordine;
	}

	public void setIdOrdine(int id_ordine) {
		this.id_ordine = id_ordine;
	}

	public String getIdProdotto() {
		return id_prodotto;
	}

	public void setIdProdotto(String id_prodotto) {
		this.id_prodotto = id_prodotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getSubtotaleIva() {
		return subtotale_iva;
	}

	public void setSubtotaleIva(double subtotale_iva) {
		this.subtotale_iva = subtotale_iva;
	}

	public double getSubtotale() {
		return subtotale;
	}

	public void setSubtotale(double subtotale) {
		this.subtotale = subtotale;
	}
	
}
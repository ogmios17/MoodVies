package model.beans;

import java.util.Date;

public class OrdineBean {
	
	private int id_ordine;
	private int id_stato;
	private String utente;
    private Date data_ordine;
	private int id_fattura;
	private int id_indirizzo;

	public OrdineBean() {
		id_ordine = 0;
		id_stato = 0;
		utente = "";
        data_ordine = null;
		id_fattura = 0;
		id_indirizzo = 0;		
	}

	public int getIdOrdine() {
		return id_ordine;
	}

	public void setIdOrdine(int id_ordine) {
		this.id_ordine = id_ordine;
	}

	public int getIdStato() {
		return id_stato;
	}

	public void setIdStato(int id_stato) {
		this.id_stato = id_stato;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public Date getDataOrdine() {
		return data_ordine;
	}

	public void setDataOrdine(Date data_ordine) {
		this.data_ordine = data_ordine;
	}

	public void setIdFattura(int id_fattura) {
		this.id_fattura = id_fattura;
	}

	public int getIdFattura() {
		return id_fattura;
	}

	public void setIdIndirizzo(int id_indirizzo) {
		this.id_indirizzo = id_indirizzo;
	}

	public int getIdIndirizzo() {
		return id_indirizzo;
	}	

}
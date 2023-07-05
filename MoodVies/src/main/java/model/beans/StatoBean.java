package model.beans;

import java.io.Serializable;

public class StatoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id_stato;
    private String descrizione;
	
	public StatoBean() {
		id_stato = 0;
		descrizione = "";
	}

	public int getIdStato() {
		return id_stato;
	}

	public void setIdStato(int id_stato) {
		this.id_stato = id_stato;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}    

}
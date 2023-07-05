package model.beans;

import java.io.Serializable;

public class IndirizzoBean implements Serializable
{
	private static final long serialVersionUID=1L;
	private int id_indirizzo;
	private String utente;
	private String via; 
    private int cap;
    private String citta;
	private String provincia;
 
	public IndirizzoBean() {
        id_indirizzo = 0;
		utente = "";
		via = "";
		cap = 0;
        citta = "";
        provincia = "";
	}

	public int getIdIndirizzo() {
		return id_indirizzo;
	}

	public void setIdIndirizzo(int id_indirizzo) {
		this.id_indirizzo = id_indirizzo;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}	

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}  

	public Integer getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

    public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
}

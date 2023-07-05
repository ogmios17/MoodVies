package model.beans;

import java.io.Serializable;

public class RecensioneBean implements Serializable
{
	private static final long serialVersionUID=1L;
	private int id_recensione;
	private String id_prodotto;
	private String utente;
	private String titolo;
	private String descrizione;
    private int stelle;
	 
    public RecensioneBean()
	{
		id_recensione = 0;
		id_prodotto ="";
		utente = "";
		titolo = "";
		descrizione = "";
        stelle = 0;
	}

	public int getIdRecensione() {
		return id_recensione;
	}

	public void setIdRecensione(int id_recensione) {
		this.id_recensione = id_recensione;
	}

	public String getIdProdotto() {
		return id_prodotto;
	}

	public void setIdProdotto(String id_prodotto) {
		this.id_prodotto = id_prodotto;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
  
	 
	public int getStelle() {
		return stelle;
	}

	public void setStelle(int stelle) {
		this.stelle = stelle;
	}  	
}

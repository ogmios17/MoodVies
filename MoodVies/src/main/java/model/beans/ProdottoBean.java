package model.beans;

import java.io.Serializable;
import java.sql.Date;

public class ProdottoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codice;
	private String nome;
	private String descrizione;
	private String dettaglio;
	private int id_genere;
	private String edizione;
	private Date data_uscita;
	private int voto;
	private double prezzo;
	private int quantita;
	private String immagine;
	private boolean cancellato;
	private int qtaCarrello;	
	private double subtotale_iva;
	private double subtotale;	

	public ProdottoBean() {
		codice = "";
		nome = "";
		descrizione = "";
		dettaglio = "";
		id_genere = 0;
		edizione = "";
		voto = 0;
		prezzo = 0;
		quantita = 0;
		immagine = "";
		qtaCarrello = 0;
		cancellato = false;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDettaglio() {
		return dettaglio;
	}

	public void setDettaglio(String dettaglio) {
		this.dettaglio = dettaglio;
	}

	public int getIdGenere() {
		return id_genere;
	}

	public void setIdGenere(int id_genere) {
		this.id_genere = id_genere;
	}

	public String getEdizione() {
		return edizione;
	}

	public void setEdizione(String edizione) {
		this.edizione = edizione;
	}	

	public Date getDataUscita() {
		return data_uscita;
	}

	public void setDataUscita(Date data_uscita) {
		this.data_uscita = data_uscita;
	}
	
	public int getVoto() {
		return voto;
	}		

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public boolean getCancellato() {
		return cancellato;
	}

	public void setCancellato(boolean cancellato) {
		this.cancellato = cancellato;
	}	
	
	public int getQtaCarrello() {
		return qtaCarrello;
	}

	public void setQtaCarrello(int qtaCarrello) {
		this.qtaCarrello = qtaCarrello;
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

	@Override
	public String toString() {
		return "ProdottoBean [codice=" + codice + ", nome=" + nome + ", descrizione=" + descrizione + ", dettaglio=" + dettaglio + 
				", id_genere=" + id_genere + ", edizione=" + edizione + ", data_uscita=" + data_uscita + ", voto=" + voto + ", prezzo=" + prezzo +
				", quantita=" + quantita + ", immagine=" + immagine + ", cancellato= " + cancellato + "]";
	}

}
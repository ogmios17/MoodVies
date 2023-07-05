package model.beans;

import java.io.Serializable;

import java.util.Date;

public class FatturaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id_fattura;
    private Date data_fattura;
	private float totale_iva;
	private float totale;
	
	public FatturaBean() {
		id_fattura = 0;
        data_fattura = null;
		totale_iva = 0;
		totale = 0;
	}

	public int getIdFattura() {
		return id_fattura;
	}

	public void setIdFattura(int id_fattura) {
		this.id_fattura = id_fattura;
	}

	public Date getDataFattura() {
		return data_fattura;
	}

	public void setDataFattura(Date data_fattura) {
		this.data_fattura = data_fattura;
	}

	public float getTotaleIva() {
		return totale_iva;
	}

	public void setTotaleIva(float totale_iva) {
		this.totale_iva = totale_iva;
	}
	
	public float getTotale() {
		return totale;
	}

	public void setTotale(float totale) {
		this.totale = totale;
	}		

}
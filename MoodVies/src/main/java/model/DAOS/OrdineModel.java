package model.DAOS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.beans.OrdineBean;

public interface OrdineModel {

	public void doSave(OrdineBean ordine) throws SQLException;
	
	public void doUpdate(OrdineBean ordine) throws SQLException;
	
	public boolean doDelete(int id_ordine) throws SQLException;

	public OrdineBean doRetrieveByKey(int id_ordine) throws SQLException;
	
	public ArrayList<OrdineBean> doRetrieveAll(String orderby) throws SQLException;

	public ArrayList<OrdineBean> doRetrieveAllByUtente(String utente) throws SQLException;

	public ArrayList<OrdineBean> doRetrieveByStato(int id_stato) throws SQLException; 
	
public ArrayList<OrdineBean> doRetrieveByIndirizzo(int id_indirizzo) throws SQLException; 	

	public ArrayList<OrdineBean> doRetrieveByDate(Date da, Date a) throws SQLException;
	
}
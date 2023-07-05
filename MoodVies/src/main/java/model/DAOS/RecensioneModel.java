package model.DAOS;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.RecensioneBean;

public interface RecensioneModel 
{
	public void doSave(RecensioneBean feedback) throws SQLException;
	
	public void doUpdate(RecensioneBean feedback) throws SQLException;
	
	public boolean doDelete(int id_recensione) throws SQLException;

	public RecensioneBean doRetrieveByKey(int id_recensione) throws SQLException;

	public ArrayList<RecensioneBean> doRetrieveAll(String orderby) throws SQLException;

    public ArrayList<RecensioneBean> doRetrieveByProduct(String codice) throws SQLException;
    
    public ArrayList<RecensioneBean> doRetrieveByUtente(String utente) throws SQLException;
}

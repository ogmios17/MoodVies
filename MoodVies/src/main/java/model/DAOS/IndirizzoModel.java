package model.DAOS;

import java.sql.SQLException;
import java.util.ArrayList;
import model.beans.IndirizzoBean;

public interface IndirizzoModel {

	public void doSave(IndirizzoBean indirizzo) throws SQLException;
	
	public void doUpdate(IndirizzoBean indirizzo) throws SQLException;
	
	public boolean doDelete(int id_indirizzo) throws SQLException;

	public IndirizzoBean doRetrieveByKey(int id_indirizzo) throws SQLException;

	public ArrayList<IndirizzoBean> doRetrieveByUtente(String utente) throws SQLException;	
	
	public ArrayList<IndirizzoBean> doRetrieveAll(String orderby) throws SQLException;
    
}

package model.DAOS;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.FatturaBean;

public interface FatturaModel 
{
	public void doSave(FatturaBean fattura) throws SQLException;
	
	public void doUpdate(FatturaBean id_fattura) throws SQLException;
	
	public boolean doDelete(int id_fattura) throws SQLException;

	public FatturaBean doRetrieveByKey(int id_fattura) throws SQLException;
	
	public ArrayList<FatturaBean> doRetrieveAll(String orderby) throws SQLException;

}

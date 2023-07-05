package model.DAOS;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.StatoBean;

public interface StatoModel 
{
	public void doSave(StatoBean stato) throws SQLException;
	
	public void doUpdate(StatoBean stato) throws SQLException;
	
	public boolean doDelete(int id_stato) throws SQLException;

	public StatoBean doRetrieveByKey(int id_stato) throws SQLException;
	
	public ArrayList<StatoBean> doRetrieveAll(String orderby) throws SQLException;

}

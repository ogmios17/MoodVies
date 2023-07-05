package model.DAOS;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.GenereBean;

public interface GenereModel 
{
	public void doSave(GenereBean genere) throws SQLException;
	
	public void doUpdate(GenereBean genere) throws SQLException;
	
	public boolean doDelete(int id_genere) throws SQLException;

	public GenereBean doRetrieveByKey(int id_genere) throws SQLException;
	
	public ArrayList<GenereBean> doRetrieveAll(String orderby) throws SQLException;

}

package model.DAOS;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.CartaBean;

public interface CartaModel 
{
	public void doSave(CartaBean carta) throws SQLException;
	
	public void doUpdate(CartaBean carta) throws SQLException;
	
	public boolean doDelete(String numero_carta) throws SQLException;

	public CartaBean doRetrieveByKey(String numero_carta) throws SQLException;
	
	public ArrayList<CartaBean> doRetrieveAll(String orderby) throws SQLException;

	public ArrayList<CartaBean> doRetrieveByUtente(String utente) throws SQLException;
}


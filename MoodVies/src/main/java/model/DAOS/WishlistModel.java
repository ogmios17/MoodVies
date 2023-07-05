package model.DAOS;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.WishlistBean;

public interface WishlistModel 
{
	public void doSave(WishlistBean wish) throws SQLException;
	
	public void doUpdate(WishlistBean wish) throws SQLException;
	
	public boolean doDelete(int id_wishlist) throws SQLException;

	public WishlistBean doRetrieveByKey(int id_wishlist) throws SQLException;
	
	public ArrayList<WishlistBean> doRetrieveAll(String orderby) throws SQLException;

	public ArrayList<WishlistBean> doRetrieveByProdotto(String utente) throws SQLException;

    public ArrayList<WishlistBean> doRetrieveByUtente(String utente) throws SQLException;
}
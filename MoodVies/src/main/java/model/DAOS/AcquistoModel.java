package model.DAOS;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.AcquistoBean;

public interface AcquistoModel 
{
	public void doSave(AcquistoBean acquisto) throws SQLException;
	
	public void doUpdate(AcquistoBean acquisto) throws SQLException;
	
	public boolean doDelete(int id_acquisto) throws SQLException;

	public AcquistoBean doRetrieveByKey(int id_acquisto) throws SQLException;
	
	public ArrayList<AcquistoBean> doRetrieveAll(String orderby) throws SQLException;

    public ArrayList<AcquistoBean> doRetrieveByProdotto(String id_prodotto) throws SQLException;

    public ArrayList<AcquistoBean> doRetrieveByOrdine(int id_ordine) throws SQLException;	
}

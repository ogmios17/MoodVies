package model.DAOS;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.ProdottoBean;

public interface ProdottoModel {
	public void doSave(ProdottoBean product) throws SQLException;
	
	public void doUpdate(ProdottoBean product) throws SQLException;
	
	public boolean doDelete(String codice) throws SQLException;

	public ProdottoBean doRetrieveByKey(String codice) throws SQLException;
	
	public ArrayList<ProdottoBean> doRetrieveAll(String orderby) throws SQLException;

	public ArrayList<ProdottoBean> doRetrieveByGenere(int id_genere) throws SQLException;
}
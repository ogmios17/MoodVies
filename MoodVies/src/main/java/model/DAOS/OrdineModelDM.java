package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import database.DriverManagerConnectionPool;
import model.beans.OrdineBean;

public class OrdineModelDM implements OrdineModel {

	private static final String TABLE_NAME = "Ordine";
	
	@Override
	public synchronized void doSave(OrdineBean ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + OrdineModelDM.TABLE_NAME
						 + " (id_ordine, id_stato, utente, data_ordine, id_fattura, id_indirizzo) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1,ordine.getIdOrdine());
			preparedStatement.setInt(2,ordine.getIdStato());
			preparedStatement.setString(3,ordine.getUtente());            
			preparedStatement.setDate(4, new java.sql.Date(ordine.getDataOrdine().getTime()));
			preparedStatement.setInt(5,ordine.getIdFattura());
			preparedStatement.setInt(6,ordine.getIdIndirizzo());			
			
			preparedStatement.executeUpdate();
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}		
	}

	@Override
	public synchronized void doUpdate(OrdineBean ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + OrdineModelDM.TABLE_NAME
						 + " SET id_stato = ?, utente= ?, data_ordine= ?, id_fattura=?, id_indirizzo=?"
						 + " WHERE id_ordine = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1,ordine.getIdStato());
			preparedStatement.setString(2,ordine.getUtente());            
			preparedStatement.setDate(3, new java.sql.Date(ordine.getDataOrdine().getTime()));
			preparedStatement.setInt(4,ordine.getIdFattura());	
			preparedStatement.setInt(5,ordine.getIdIndirizzo());				
			preparedStatement.setInt(6,ordine.getIdOrdine());
			
			preparedStatement.executeUpdate();
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}		
	}

	@Override
	public synchronized boolean doDelete(int id_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrdineModelDM.TABLE_NAME + " WHERE id_ordine = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id_ordine);

			result = preparedStatement.executeUpdate();
			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized OrdineBean doRetrieveByKey(int id_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdineBean bean = new OrdineBean();

		String selectSQL = "SELECT * FROM " + OrdineModelDM.TABLE_NAME + " WHERE id_ordine = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_ordine);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdOrdine(id_ordine);
				bean.setIdStato(rs.getInt("id_stato"));
				bean.setUtente(rs.getString("utente"));                
				bean.setDataOrdine(new java.util.Date(rs.getDate("data_ordine").getTime()));
				bean.setIdFattura(rs.getInt("id_fattura"));
				bean.setIdIndirizzo(rs.getInt("id_indirizzo"));				
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}
	
	@Override
	public synchronized ArrayList<OrdineBean> doRetrieveByDate(Date da, Date a) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();

		java.sql.Date daSQL = new java.sql.Date(da.getTime());
		java.sql.Date aSQL = new java.sql.Date(a.getTime());
		String selectSQL = "SELECT * FROM Ordine WHERE data_ordine >= ? and data_ordine <= ?;";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setDate(1, daSQL);
			preparedStatement.setDate(2, aSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setIdStato(rs.getInt("id_stato"));
				bean.setUtente(rs.getString("utente"));                
				bean.setDataOrdine(new java.util.Date(rs.getDate("data_ordine").getTime()));
				bean.setIdFattura(rs.getInt("id_fattura"));
				bean.setIdIndirizzo(rs.getInt("id_indirizzo"));				
				ordini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
	}
	
	@Override
	public synchronized ArrayList<OrdineBean> doRetrieveAllByUtente(String utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineModelDM.TABLE_NAME + " WHERE utente = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, utente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setIdStato(rs.getInt("id_stato"));
				bean.setUtente(rs.getString("utente"));                
				bean.setDataOrdine(new java.util.Date(rs.getDate("data_ordine").getTime()));
				bean.setIdFattura(rs.getInt("id_fattura"));
				bean.setIdIndirizzo(rs.getInt("id_indirizzo"));					
				ordini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
	}

	@Override
	public synchronized ArrayList<OrdineBean> doRetrieveByStato(int id_stato) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineModelDM.TABLE_NAME + " WHERE id_stato = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_stato);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setIdStato(rs.getInt("id_stato"));
				bean.setUtente(rs.getString("utente"));                
				bean.setDataOrdine(new java.util.Date(rs.getDate("data_ordine").getTime()));
				bean.setIdFattura(rs.getInt("id_fattura"));
				bean.setIdIndirizzo(rs.getInt("id_indirizzo"));					
				ordini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
	}    

	@Override
	public synchronized ArrayList<OrdineBean> doRetrieveByIndirizzo(int id_indirizzo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineModelDM.TABLE_NAME + " WHERE id_indirizzo = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_indirizzo);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setIdStato(rs.getInt("id_stato"));
				bean.setUtente(rs.getString("utente"));                
				bean.setDataOrdine(new java.util.Date(rs.getDate("data_ordine").getTime()));
				bean.setIdFattura(rs.getInt("id_fattura"));
				bean.setIdIndirizzo(rs.getInt("id_indirizzo"));					
				ordini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
	}    	

	@Override
	public synchronized ArrayList<OrdineBean> doRetrieveAll(String orderby) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineModelDM.TABLE_NAME;

		if (orderby != null && !orderby.equals("")) {
			selectSQL += " ORDER BY " + orderby;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setIdStato(rs.getInt("id_stato"));
				bean.setUtente(rs.getString("utente"));                
				bean.setDataOrdine(new java.util.Date(rs.getDate("data_ordine").getTime()));
				bean.setIdFattura(rs.getInt("id_fattura"));
				bean.setIdIndirizzo(rs.getInt("id_indirizzo"));					
				ordini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
	}
	
	

}
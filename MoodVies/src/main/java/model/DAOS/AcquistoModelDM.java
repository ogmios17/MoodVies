package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DriverManagerConnectionPool;
import model.beans.AcquistoBean;

public class AcquistoModelDM implements AcquistoModel
{
	private static final String TABLE_NAME = "Acquisto";
	
	public synchronized void doSave(AcquistoBean acquisto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + AcquistoModelDM.TABLE_NAME
						 + " (id_ordine, id_prodotto, quantita, subtotale_iva, subtotale) VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, acquisto.getIdOrdine());
			preparedStatement.setString(2, acquisto.getIdProdotto() );
			preparedStatement.setInt(3, acquisto.getQuantita());
			preparedStatement.setDouble(4, acquisto.getSubtotaleIva() );
			preparedStatement.setDouble(5, acquisto.getSubtotale());
			
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
	public synchronized void doUpdate(AcquistoBean acquisto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + AcquistoModelDM.TABLE_NAME
						 + " SET id_prodotto=?, quantita= ?, subtotale_iva= ?, subtotale= ?"
						 + " WHERE id_ordine= ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		    preparedStatement.setString(1, acquisto.getIdProdotto());            
			preparedStatement.setInt(2, acquisto.getQuantita());
			preparedStatement.setDouble(3, acquisto.getSubtotaleIva());
			preparedStatement.setDouble(4, acquisto.getSubtotale());
			preparedStatement.setInt(5, acquisto.getIdOrdine());
			
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

		String deleteSQL = "DELETE FROM " + AcquistoModelDM.TABLE_NAME + " WHERE id_ordine= ?";

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
	public synchronized AcquistoBean doRetrieveByKey(int id_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		AcquistoBean bean = new AcquistoBean();

		String selectSQL = "SELECT * FROM " + AcquistoModelDM.TABLE_NAME + " WHERE id_ordine= ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_ordine);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setIdProdotto(rs.getString("id_prodotto"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setSubtotaleIva(rs.getDouble("subtotale_iva"));
				bean.setSubtotale(rs.getDouble("subtotale"));
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
	public synchronized ArrayList<AcquistoBean> doRetrieveAll(String orderby) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<AcquistoBean> acquisto = new ArrayList<AcquistoBean>();

		String selectSQL = "SELECT * FROM " + AcquistoModelDM.TABLE_NAME;

		if (orderby != null && !orderby.equals("")) {
			selectSQL += " ORDER BY " + orderby;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				AcquistoBean bean = new AcquistoBean();
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setIdProdotto(rs.getString("id_prodotto"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setSubtotaleIva(rs.getDouble("subtotale_iva"));
				bean.setSubtotale(rs.getDouble("subtotale"));
				acquisto.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return acquisto;
	}
	
	public synchronized ArrayList<AcquistoBean> doRetrieveByProdotto(String codice) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<AcquistoBean> acquisto = new ArrayList<AcquistoBean>();

		String selectSQL = "SELECT * FROM " + AcquistoModelDM.TABLE_NAME
						  +" WHERE id_prodotto = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				AcquistoBean bean = new AcquistoBean();
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setIdProdotto(rs.getString("id_prodotto"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setSubtotaleIva(rs.getDouble("subtotale_iva"));
				bean.setSubtotale(rs.getDouble("subtotale"));
				acquisto.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return acquisto;
	}

	public synchronized ArrayList<AcquistoBean> doRetrieveByOrdine(int id_ordine) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<AcquistoBean> acquisto = new ArrayList<AcquistoBean>();

		String selectSQL = "SELECT * FROM " + AcquistoModelDM.TABLE_NAME
						  +" WHERE id_prodotto = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_ordine);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				AcquistoBean bean = new AcquistoBean();
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setIdProdotto(rs.getString("id_prodotto"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setSubtotaleIva(rs.getDouble("subtotale_iva"));
				bean.setSubtotale(rs.getDouble("subtotale"));
				acquisto.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return acquisto;
	}	
}
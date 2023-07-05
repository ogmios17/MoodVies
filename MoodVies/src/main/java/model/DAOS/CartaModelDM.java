package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DriverManagerConnectionPool;
import model.beans.CartaBean;

public class CartaModelDM implements CartaModel
{
	private static final String TABLE_NAME = "carta";
	
	@Override
	public synchronized void doSave(CartaBean carta) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + CartaModelDM.TABLE_NAME
						 + " (numero_carta, cvv, scadenza, utente) VALUES (?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1,carta.getNumeroCarta());
			preparedStatement.setInt(2,carta.getCvv());
			preparedStatement.setDate(3, new java.sql.Date(carta.getScadenza().getTime()));
			preparedStatement.setString(4,carta.getUtente());
			
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
	public synchronized void doUpdate(CartaBean dati) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + CartaModelDM.TABLE_NAME
						 + " SET cvv = ?, scadenza= ?, utente= ?"
						 + " WHERE numero_carta = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1,dati.getCvv());
			preparedStatement.setDate(2, new java.sql.Date(dati.getScadenza().getTime()));
			preparedStatement.setString(3,dati.getNumeroCarta());
			preparedStatement.setString(4,dati.getUtente());			
			
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
	public synchronized boolean doDelete(String numero_carta) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + CartaModelDM.TABLE_NAME + " WHERE numero_carta = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, numero_carta);

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
	public synchronized CartaBean doRetrieveByKey(String numero_carta) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		CartaBean bean = new CartaBean();

		String selectSQL = "SELECT * FROM " + CartaModelDM.TABLE_NAME + " WHERE numero_carta = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, numero_carta);
			

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNumeroCarta(rs.getString("numero_carta"));
				bean.setCvv(rs.getInt("cvv"));
				bean.setScadenza(new java.util.Date(rs.getDate("scadenza").getTime()));
				bean.setUtente(rs.getString("utente"));				
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
	public synchronized ArrayList<CartaBean> doRetrieveByUtente(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        
		ArrayList<CartaBean> carta = new ArrayList<CartaBean>();

		String selectSQL = "SELECT * FROM " + CartaModelDM.TABLE_NAME
						 + " WHERE utente = ?";

		try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CartaBean bean = new CartaBean();
				bean.setCvv(rs.getInt("cvv"));
				bean.setScadenza(new java.util.Date(rs.getDate("scadenza").getTime()));
				bean.setNumeroCarta(rs.getString("numero_carta"));
				bean.setUtente(rs.getString("utente"));				
				carta.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return carta;
	}

	@Override
	public synchronized ArrayList<CartaBean> doRetrieveAll(String orderby) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<CartaBean> carta = new ArrayList<CartaBean>();

		String selectSQL = "SELECT * FROM " + CartaModelDM.TABLE_NAME;

		if (orderby != null && !orderby.equals("")) {
			selectSQL += " ORDER BY " + orderby;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CartaBean bean = new CartaBean();
				bean.setNumeroCarta(rs.getString("numero_carta"));
				bean.setCvv(rs.getInt("cvv"));
				bean.setScadenza(new java.util.Date(rs.getDate("scadenza").getTime()));
				bean.setUtente(rs.getString("utente"));					
				carta.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return carta;
	}
	
}

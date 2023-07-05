package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DriverManagerConnectionPool;
import model.beans.RecensioneBean;

public class RecensioneModelDM implements RecensioneModel
{
	private static final String TABLE_NAME = "recensione";
	
	@Override
	public synchronized void doSave(RecensioneBean recensione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + RecensioneModelDM.TABLE_NAME
						 + " (id_prodotto, utente, titolo, descrizione, stelle) VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, recensione.getIdProdotto());
			preparedStatement.setString(2, recensione.getUtente());
			preparedStatement.setString(3, recensione.getTitolo());
			preparedStatement.setString(4, recensione.getDescrizione());
            preparedStatement.setInt(6, recensione.getStelle());
			
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
	public synchronized void doUpdate(RecensioneBean recensione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + RecensioneModelDM.TABLE_NAME
						 + " SET id_prodotto= ?, utente= ?, titolo= ?, descrizione= ?, stelle=?"
						 + " WHERE id_recensione = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, recensione.getIdProdotto());
			preparedStatement.setString(2, recensione.getUtente());
			preparedStatement.setString(3, recensione.getTitolo());
			preparedStatement.setString(4, recensione.getDescrizione());
            preparedStatement.setInt(6, recensione.getStelle());
			preparedStatement.setInt(7, recensione.getIdRecensione());
			
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
	public synchronized boolean doDelete(int id_recensione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + RecensioneModelDM.TABLE_NAME + " WHERE id_recensione = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id_recensione);

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
	public synchronized RecensioneBean doRetrieveByKey(int id_recensione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		RecensioneBean bean = new RecensioneBean();

		String selectSQL = "SELECT * FROM " + RecensioneModelDM.TABLE_NAME + " WHERE id_recensione = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_recensione);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdProdotto(rs.getString("id_prodotto"));
				bean.setUtente(rs.getString("utente"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setStelle(rs.getInt("stelle"));                                
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
	public synchronized ArrayList<RecensioneBean> doRetrieveAll(String orderby) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<RecensioneBean> recensione = new ArrayList<RecensioneBean>();

		String selectSQL = "SELECT * FROM " + RecensioneModelDM.TABLE_NAME;

		if (orderby != null && !orderby.equals("")) {
			selectSQL += " ORDER BY " + orderby;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();
				bean.setIdRecensione(rs.getInt("id_recensione"));
				bean.setIdProdotto(rs.getString("id_prodotto"));
				bean.setUtente(rs.getString("utente"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setStelle(rs.getInt("stelle")); 
				recensione.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return recensione;
	}
	
	public synchronized ArrayList<RecensioneBean> doRetrieveByProduct(String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<RecensioneBean> feedback = new ArrayList<RecensioneBean>();

		String selectSQL = "SELECT * FROM " + RecensioneModelDM.TABLE_NAME
						  +" WHERE id_prodotto = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, codice);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();
				bean.setIdRecensione(rs.getInt("id_recensione"));
				bean.setIdProdotto(rs.getString("id_prodotto"));
				bean.setUtente(rs.getString("utente"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setStelle(rs.getInt("stelle")); 
				feedback.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return feedback;
	}
	
	public synchronized ArrayList<RecensioneBean> doRetrieveByUtente(String utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<RecensioneBean> feedback = new ArrayList<RecensioneBean>();

		String selectSQL = "SELECT * FROM " + RecensioneModelDM.TABLE_NAME
						 + " WHERE utente = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, utente);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();
				bean.setIdRecensione(rs.getInt("id_recensione"));
				bean.setIdProdotto(rs.getString("id_prodotto"));
				bean.setUtente(rs.getString("utente"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setStelle(rs.getInt("stelle")); 
				feedback.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return feedback;
	}
}
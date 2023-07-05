package model.DAOS;
import model.beans.*;
import database.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IndirizzoModelDM implements IndirizzoModel {
    
	private static final String TABLE_NAME = "indirizzo";
	
	@Override
	public synchronized void doSave(IndirizzoBean indirizzo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + IndirizzoModelDM.TABLE_NAME
						 + " (utente, via, cap, citta, provincia) VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, indirizzo.getUtente());
			preparedStatement.setString(2, indirizzo.getVia());
			preparedStatement.setInt(3, indirizzo.getCap());
			preparedStatement.setString(4, indirizzo.getCitta());
			preparedStatement.setString(5, indirizzo.getProvincia());
			
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
	public synchronized void doUpdate(IndirizzoBean indirizzo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + IndirizzoModelDM.TABLE_NAME
						 + " SET utente= ?, via = ?, cap= ?, citta= ?, provincia= ? WHERE id_indirizzo = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, indirizzo.getUtente());
			preparedStatement.setString(2, indirizzo.getVia());						
			preparedStatement.setInt(3, indirizzo.getCap());
			preparedStatement.setString(4, indirizzo.getCitta());
			preparedStatement.setString(5, indirizzo.getProvincia());
			preparedStatement.setInt(6, indirizzo.getIdIndirizzo());
			
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
	public synchronized boolean doDelete(int ID) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + IndirizzoModelDM.TABLE_NAME + " WHERE id_indirizzo = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, ID);

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
	public synchronized IndirizzoBean doRetrieveByKey(int id_indirizzo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		IndirizzoBean bean = new IndirizzoBean();

		String selectSQL = "SELECT * FROM " + IndirizzoModelDM.TABLE_NAME + " WHERE id_indirizzo = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_indirizzo);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdIndirizzo(rs.getInt("id_indirizzo"));
				bean.setUtente(rs.getString("utente"));
				bean.setVia(rs.getString("via"));								
				bean.setCap(rs.getInt("cap"));
				bean.setCitta(rs.getString("citta"));
				bean.setProvincia(rs.getString("provincia"));
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
	public synchronized ArrayList<IndirizzoBean> doRetrieveByUtente(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<IndirizzoBean> indirizzo = new ArrayList<IndirizzoBean>();

		String selectSQL = "SELECT * FROM " + IndirizzoModelDM.TABLE_NAME + " WHERE utente = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				IndirizzoBean bean = new IndirizzoBean();
				bean.setIdIndirizzo(rs.getInt("id_indirizzo"));
				bean.setUtente(rs.getString("utente"));
				bean.setVia(rs.getString("via"));								
				bean.setCap(rs.getInt("cap"));
				bean.setCitta(rs.getString("citta"));
				bean.setProvincia(rs.getString("provincia"));
				indirizzo.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return indirizzo;
	}	
	
	@Override
	public synchronized ArrayList<IndirizzoBean> doRetrieveAll(String orderby) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<IndirizzoBean> indirizzo = new ArrayList<IndirizzoBean>();

		String selectSQL = "SELECT * FROM " + IndirizzoModelDM.TABLE_NAME;

		if (orderby != null && !orderby.equals("")) {
			selectSQL += " ORDER BY " + orderby;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				IndirizzoBean bean = new IndirizzoBean();
				bean.setIdIndirizzo(rs.getInt("id_indirizzo"));				
				bean.setUtente(rs.getString("utente"));
				bean.setVia(rs.getString("via"));								
				bean.setCap(rs.getInt("cap"));
				bean.setCitta(rs.getString("citta"));
				bean.setProvincia(rs.getString("provincia"));
				indirizzo.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return indirizzo;
	}
}

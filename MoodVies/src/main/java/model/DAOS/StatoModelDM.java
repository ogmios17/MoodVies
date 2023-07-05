package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DriverManagerConnectionPool;
import model.beans.StatoBean;

public class StatoModelDM implements StatoModel
{
	private static final String TABLE_NAME = "stato";
	
	@Override
	public synchronized void doSave(StatoBean stato) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + StatoModelDM.TABLE_NAME
						 + " (descrizione) VALUES (?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1,stato.getDescrizione() );
			
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
	public synchronized void doUpdate(StatoBean stato) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + StatoModelDM.TABLE_NAME
						 + " SET descrizione = ?"
						 + " WHERE id_stato = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1,stato.getDescrizione());
			preparedStatement.setInt(2,stato.getIdStato());
			
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
	public synchronized boolean doDelete(int id_stato) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + StatoModelDM.TABLE_NAME + " WHERE id_stato = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id_stato);

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
	public synchronized StatoBean doRetrieveByKey(int id_stato) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		StatoBean bean = new StatoBean();

		String selectSQL = "SELECT * FROM " + StatoModelDM.TABLE_NAME + " WHERE id_stato = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_stato);
			

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdStato(rs.getInt("id_stato"));
				bean.setDescrizione(rs.getString("descrizione"));
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
	public synchronized ArrayList<StatoBean> doRetrieveAll(String orderby) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<StatoBean> stato = new ArrayList<StatoBean>();

		String selectSQL = "SELECT * FROM " + StatoModelDM.TABLE_NAME;

		if (orderby != null && !orderby.equals("")) {
			selectSQL += " ORDER BY " + orderby;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				StatoBean bean = new StatoBean();
				bean.setIdStato(rs.getInt("id_stato"));
				bean.setDescrizione(rs.getString("descrizione"));
				stato.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return stato;
	}   
    
}

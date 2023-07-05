package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DriverManagerConnectionPool;
import model.beans.FatturaBean;

public class FatturaModelDM implements FatturaModel
{
	private static final String TABLE_NAME = "fattura";
	
	@Override
	public synchronized void doSave(FatturaBean fattura) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + FatturaModelDM.TABLE_NAME
						 + " (data_fattura, totale_iva, totale) VALUES (?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setDate(1,new java.sql.Date(fattura.getDataFattura().getTime()));
			preparedStatement.setFloat(1,fattura.getTotaleIva());
			preparedStatement.setFloat(1,fattura.getTotale());						
			
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
	public synchronized void doUpdate(FatturaBean fattura) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + FatturaModelDM.TABLE_NAME
						 + " SET data_fattura = ?, totale_iva = ?, totale = ?"
						 + " WHERE id_fattura = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setDate(1, new java.sql.Date(fattura.getDataFattura().getTime()));
			preparedStatement.setFloat(2,fattura.getTotaleIva());
			preparedStatement.setFloat(3,fattura.getTotale());	
			preparedStatement.setInt(4,fattura.getIdFattura());					
			
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
	public synchronized boolean doDelete(int id_fattura) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + FatturaModelDM.TABLE_NAME + " WHERE id_fattura = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id_fattura);

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
	public synchronized FatturaBean doRetrieveByKey(int id_fattura) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		FatturaBean bean = new FatturaBean();

		String selectSQL = "SELECT * FROM " + FatturaModelDM.TABLE_NAME + " WHERE id_fattura = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_fattura);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdFattura(rs.getInt("id_stato"));
				bean.setDataFattura(new java.util.Date(rs.getDate("data_fattura").getTime()));
				bean.setTotaleIva(rs.getFloat("totale_iva"));
				bean.setTotale(rs.getFloat("totale"));				
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
	public synchronized ArrayList<FatturaBean> doRetrieveAll(String orderby) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<FatturaBean> fattura = new ArrayList<FatturaBean>();

		String selectSQL = "SELECT * FROM " + FatturaModelDM.TABLE_NAME;

		if (orderby != null && !orderby.equals("")) {
			selectSQL += " ORDER BY " + orderby;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				FatturaBean bean = new FatturaBean();
				bean.setIdFattura(rs.getInt("id_stato"));
				bean.setDataFattura(new java.util.Date(rs.getDate("data_fattura").getTime()));
				bean.setTotaleIva(rs.getFloat("totale_iva"));
				bean.setTotale(rs.getFloat("totale"));				
				fattura.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return fattura;
	}   
    
}

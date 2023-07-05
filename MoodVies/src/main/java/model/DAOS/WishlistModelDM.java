package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DriverManagerConnectionPool;
import model.beans.WishlistBean;

public class WishlistModelDM implements WishlistModel
{
	private static final String TABLE_NAME = "wishlist";
	
	@Override
	public synchronized void doSave(WishlistBean wish) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + WishlistModelDM.TABLE_NAME
						 + " (id_prodotto, utente) VALUES (?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1,wish.getIdProdotto() );
			preparedStatement.setString(2,wish.getUtente());
			
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
	public synchronized void doUpdate(WishlistBean wish) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + WishlistModelDM.TABLE_NAME
						 + " SET id_prodotto = ?, utente= ?"
						 + " WHERE id_wishlist = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1,wish.getIdProdotto());
			preparedStatement.setString(2,wish.getUtente());
			preparedStatement.setInt(3,wish.getIdWishlist());
			
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
	public synchronized boolean doDelete(int id_wishlist) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + WishlistModelDM.TABLE_NAME + " WHERE id_wishlist = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id_wishlist);

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
	public synchronized WishlistBean doRetrieveByKey(int id_wishlist) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		WishlistBean bean = new WishlistBean();

		String selectSQL = "SELECT * FROM " + WishlistModelDM.TABLE_NAME + " WHERE id_wishlist = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_wishlist);
			

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdWishlist(rs.getInt("id_wishlist"));
				bean.setIdProdotto(rs.getString("id_prodotto"));
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
	public synchronized ArrayList<WishlistBean> doRetrieveAll(String orderby) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<WishlistBean> carta = new ArrayList<WishlistBean>();

		String selectSQL = "SELECT * FROM " + WishlistModelDM.TABLE_NAME;

		if (orderby != null && !orderby.equals("")) {
			selectSQL += " ORDER BY " + orderby;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				WishlistBean bean = new WishlistBean();
				bean.setIdWishlist(rs.getInt("id_wishlist"));
				bean.setIdProdotto(rs.getString("id_prodotto"));
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
	public synchronized ArrayList<WishlistBean> doRetrieveByProdotto(String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<WishlistBean> wish = new ArrayList<WishlistBean>();

		String selectSQL = "SELECT * FROM " + WishlistModelDM.TABLE_NAME
						 + " WHERE id_prodotto = ?";

		try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, codice);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				WishlistBean bean = new WishlistBean();
				bean.setIdWishlist(rs.getInt("id_wishlist"));
				bean.setIdProdotto(rs.getString("id_prodotto"));
				bean.setUtente(rs.getString("utente"));
				
				wish.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return wish;
	}    
	
	@Override
	public synchronized ArrayList<WishlistBean> doRetrieveByUtente(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<WishlistBean> wish = new ArrayList<WishlistBean>();

		String selectSQL = "SELECT * FROM " + WishlistModelDM.TABLE_NAME
						 + " WHERE utente = ?";

		try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				WishlistBean bean = new WishlistBean();
				bean.setIdWishlist(rs.getInt("id_wishlist"));
				bean.setIdProdotto(rs.getString("id_prodotto"));
				bean.setUtente(rs.getString("utente"));
				
				wish.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return wish;
	}

}

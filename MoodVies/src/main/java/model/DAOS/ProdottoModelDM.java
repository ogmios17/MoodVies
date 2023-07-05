package model.DAOS;
import model.beans.*;
import database.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoModelDM implements ProdottoModel {

	private static final String TABLE_NAME = "prodotto";

	@Override
	public synchronized void doSave(ProdottoBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProdottoModelDM.TABLE_NAME
				+ " (codice, nome, descrizione, dettaglio, id_genere, edizione, data_uscita, voto, prezzo, quantita, immagine, cancellato) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getCodice());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setString(3, product.getDescrizione());
			preparedStatement.setString(4, product.getDettaglio());
			preparedStatement.setInt(5, product.getIdGenere());
			preparedStatement.setString(6, product.getEdizione());
			preparedStatement.setDate(7, product.getDataUscita());
			preparedStatement.setInt(8, product.getVoto());
			preparedStatement.setDouble(9, product.getPrezzo());
			preparedStatement.setInt(10, product.getQuantita());
			preparedStatement.setString(11, product.getImmagine());
			preparedStatement.setBoolean(12, product.getCancellato());		
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
	public synchronized void doUpdate(ProdottoBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE " + ProdottoModelDM.TABLE_NAME
				+ " SET nome = ?, descrizione = ?, dettaglio = ?, id_genere = ?, edizione = ?, data_uscita = ?, voto = ?, prezzo = ?, quantita = ?, immagine = ?, cancellato = ?"
				+ " WHERE codice = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setString(2, product.getDescrizione());
			preparedStatement.setString(3, product.getDettaglio());
			preparedStatement.setInt(4, product.getIdGenere());
			preparedStatement.setString(5, product.getEdizione());
			preparedStatement.setDate(6, product.getDataUscita());
			preparedStatement.setInt(7, product.getVoto());
			preparedStatement.setDouble(8, product.getPrezzo());
			preparedStatement.setInt(9, product.getQuantita());
			preparedStatement.setString(10, product.getImmagine());
			preparedStatement.setBoolean(11, product.getCancellato());			
			preparedStatement.setString(12, product.getCodice());
			
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
	public synchronized ProdottoBean doRetrieveByKey(String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProdottoBean bean = new ProdottoBean();

		String selectSQL = "SELECT * FROM " + ProdottoModelDM.TABLE_NAME + " WHERE codice = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getString("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setDettaglio(rs.getString("dettaglio"));
				bean.setIdGenere(rs.getInt("id_genere"));
				bean.setEdizione(rs.getString("edizione"));				
				bean.setDataUscita(rs.getDate("data_uscita"));
				bean.setVoto(rs.getInt("voto"));				
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setImmagine(rs.getString("immagine"));
				bean.setCancellato(rs.getBoolean("cancellato"));
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
	public synchronized boolean doDelete(String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		// VERIFICARE PRIMA SE CI SONO ORDINI COLLEGATI: in tal caso non eliminare ma porre solo cancellato = true (1 nel db) 
		ArrayList<AcquistoBean> acquisto = new ArrayList<AcquistoBean>();
		AcquistoModelDM aDAO = new AcquistoModelDM();
		acquisto = aDAO.doRetrieveByProdotto(codice); 
		if(acquisto!=null) {
			ProdottoBean product = doRetrieveByKey(codice);
			product.setCancellato(true);
			doUpdate(product);
		} else {
			String deleteSQL = "DELETE FROM " + ProdottoModelDM.TABLE_NAME + " WHERE codice = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setString(1, codice);

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
		}
		return (result != 0);
	}

	@Override
	public synchronized ArrayList<ProdottoBean> doRetrieveAll(String orderby) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProdottoBean> products = new ArrayList<ProdottoBean>();

		String selectSQL = "SELECT * FROM " + ProdottoModelDM.TABLE_NAME + " WHERE cancellato = 0";  //visualizza solo i prodotto non cancellati

		if (orderby != null && !orderby.equals("")) {
			selectSQL += " ORDER BY " + orderby;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProdottoBean bean = new ProdottoBean();
				bean.setCodice(rs.getString("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setDettaglio(rs.getString("dettaglio"));
				bean.setIdGenere(rs.getInt("id_genere"));
				bean.setEdizione(rs.getString("edizione"));				
				bean.setDataUscita(rs.getDate("data_uscita"));
				bean.setVoto(rs.getInt("voto"));				
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setImmagine(rs.getString("immagine"));
				bean.setCancellato(rs.getBoolean("cancellato"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	@Override
	public synchronized ArrayList<ProdottoBean> doRetrieveByGenere(int id_genere) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProdottoBean> products = new ArrayList<ProdottoBean>();

		String selectSQL = "SELECT * FROM " + ProdottoModelDM.TABLE_NAME
						 + " WHERE id_genere = ? AND cancellato = 0";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_genere);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProdottoBean bean = new ProdottoBean();
				bean.setCodice(rs.getString("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setDettaglio(rs.getString("dettaglio"));
				bean.setEdizione(rs.getString("edizione"));				
				bean.setDataUscita(rs.getDate("data_uscita"));
				bean.setVoto(rs.getInt("voto"));				
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setImmagine(rs.getString("immagine"));
				bean.setCancellato(rs.getBoolean("cancellato"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	/* Prodotti acquistati da un utente */
	public ArrayList<ProdottoBean> doRetrieveProductBuy (String utente) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<ProdottoBean> products = new ArrayList<ProdottoBean>();
		ArrayList<String> idProducts = new ArrayList<String>();
		
		/* *** DA VERIFICARE *** */
		String selectSQL = "SELECT DISTINCT ACQUISTO.ID_ORDINE FROM ORDINE, ACQUISTO WHERE ORDINE.UTENTE = ? "
						 + " AND ORDINE.ID_ORDINE = ACQUISTO.ID_ORDINE";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, utente);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				idProducts.add(rs.getString(1));
			}
			
			for(String s : idProducts) {
				products.add(this.doRetrieveByKey(s));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}

}
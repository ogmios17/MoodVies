package model.beans;

import java.sql.SQLException;
import java.util.ArrayList;

import model.DAOS.ProdottoModelDM;

public class Carrello {
	
	private ArrayList<ProdottoBean> products;
	
	public Carrello() {
		products = new ArrayList<ProdottoBean>();
	}
	
	public ArrayList<ProdottoBean> getProducts() {
		return products;
	}

	public synchronized void addProduct(String codice) throws SQLException {
		int i = 0;
		boolean flag = false;
		for(ProdottoBean product : products) {
			if(codice.equals(product.getCodice())) {
				flag = true;
				if(product.getQuantita() >= 1) {
					product.setQtaCarrello(product.getQtaCarrello()+1);
					product.setQuantita(product.getQuantita() - 1);
				}
				products.set(i, product);
			}
			i++;
		}
		if(!flag) {
			ProdottoModelDM prodottoDAO = new ProdottoModelDM();
			ProdottoBean product = prodottoDAO.doRetrieveByKey(codice);
			product.setQtaCarrello(1);
			product.setQuantita(product.getQuantita() - 1);
			products.add(product);
		}
	}
	
	//diminuisce di uno la quantità nel carrello e aumentà di 1 la disponibilità del prodotto
	public synchronized void removeProduct(String codice) {
		for(int i = 0; i < products.size(); i++) {
			ProdottoBean product = products.get(i);
			if(codice.equals(product.getCodice())) {
				product.setQuantita(product.getQuantita() + 1);
				int newQtaCarrello = product.getQtaCarrello() - 1;
				if(newQtaCarrello <= 0)
					products.remove(i);
				else
					product.setQtaCarrello(newQtaCarrello);
			}
		}
	}
	
	//rimuove il prodotto dal carrello
	public synchronized void deleteProduct(String codice) {
		for(int i = 0; i < products.size(); i++) {
			ProdottoBean product = products.get(i);
			if(product.getCodice().equals(codice))
				products.remove(i);
		}
	}
	
	public synchronized double getTotale() {
		double totale = 0;
		for(ProdottoBean product : products) {
			totale = totale + (product.getPrezzo()*product.getQtaCarrello());
		}
		return totale;
	}
	
	public synchronized int getQtaProducts() {
		int qta = 0;
		for(int i = 0; i < products.size(); i++) {
			ProdottoBean p = products.get(i);
			qta += p.getQtaCarrello();
		}
		return qta;
	}
}
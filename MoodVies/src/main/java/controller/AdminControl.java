package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAOS.*;
import model.beans.*;

@WebServlet("/AdminControl")
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("Utente");
		if(utente == null && !utente.getRuolo().equalsIgnoreCase("admin")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
		
		String action = request.getParameter("action");
		
		if(action.equals("gestioneClienti")) {
			UtenteModelDM search = new UtenteModelDM();
			ArrayList <UtenteBean> result	= new ArrayList <UtenteBean>();
			try {
				result = search.doRetrieveAll(null);
			} catch (SQLException e1) {
				e1.printStackTrace();
				e1.getMessage();
			}
			request.setAttribute("listaUtenti", result);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/AdClienti.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equals("gestioneCatalogo")) {
			ProdottoModelDM search = new ProdottoModelDM();
			ArrayList <ProdottoBean> result	= new ArrayList <ProdottoBean>();
			try {
				result = search.doRetrieveAll(null);
			} catch (SQLException e) {
				e.printStackTrace();
				e.getMessage();
			}
			
			request.setAttribute("listaProdotti", result);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/AdCatalogo.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equals("gestioneOrdini")) {
			OrdineModelDM search = new OrdineModelDM();
			ArrayList<OrdineBean> listor=new ArrayList<OrdineBean>();
			try {
				listor  = search.doRetrieveAll(null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("ordini", listor);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/AdOrdini.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equals("viewOrdinePerData")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date da = null;
			Date a = null;
			try {
				da = dateFormat.parse(request.getParameter("dataDa"));
				a = dateFormat.parse(request.getParameter("dataA"));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			OrdineModelDM search = new OrdineModelDM();
			ArrayList<OrdineBean> listor = new ArrayList<OrdineBean>();
			try {
				listor  = search.doRetrieveByDate(da, a);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("ordini", listor);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/AdOrdini.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equals("viewOrdine")) {
			AcquistoModelDM model = new AcquistoModelDM();
			IndirizzoModelDM indirizzoDAO = new IndirizzoModelDM();
			OrdineModelDM ordineDAO = new OrdineModelDM();
			
			int id = Integer.parseInt(request.getParameter("id_ordine"));
			ArrayList<ProdottoBean> products = new ArrayList<ProdottoBean>();
			IndirizzoBean indirizzo = null;
			
			try {
				ArrayList<AcquistoBean> acquisti = model.doRetrieveByOrdine(id);
				
				ProdottoModelDM productDAO = new ProdottoModelDM();
				for(AcquistoBean acquisto : acquisti) {
					ProdottoBean product = productDAO.doRetrieveByKey(acquisto.getIdProdotto());
					product.setQtaCarrello(acquisto.getQuantita());
					products.add(product);
				}
				OrdineBean o = ordineDAO.doRetrieveByKey(id);
				indirizzo = indirizzoDAO.doRetrieveByKey(o.getIdIndirizzo());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			String indCompleto = indirizzo.getVia() + " Città " + indirizzo.getCitta() + " CAP " + indirizzo.getCap();
			request.setAttribute("dettagliOrdine", products);
			request.setAttribute("indirizzo", indCompleto);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/dettagliOrdine.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equals("ordiniUtente")) {
			OrdineModelDM model = new OrdineModelDM();
			String utente1 = request.getParameter("utente");
			try {
				ArrayList<OrdineBean> ordini = model.doRetrieveAllByUtente(utente1);
				request.setAttribute("ordini", ordini);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/ordiniUtente.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equals("aggiungiProdotto")) {
			ProdottoBean product = new ProdottoBean();
			product.setCodice(request.getParameter("codice"));
			product.setNome(request.getParameter("nome"));
			product.setDescrizione(request.getParameter("descrizione"));
			product.setDettaglio(request.getParameter("dettaglio"));
			product.setIdGenere(Integer.parseInt(request.getParameter("id_genere")));  
 			product.setEdizione(request.getParameter("edizione")); 
			product.setDataUscita(java.sql.Date.valueOf(request.getParameter("data_uscita")));   
			product.setVoto(Integer.parseInt(request.getParameter("voto")));                                         
			product.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
			product.setQuantita(Integer.parseInt(request.getParameter("qtaMagazzino")));
			product.setImmagine("foto/"+request.getParameter("immagine"));
			product.setCancellato(false);			
			ProdottoModelDM model = new ProdottoModelDM();
			try {
				model.doSave(product);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("operazione", "L'aggiunta del prodotto al catalogo è avvenuta con successo");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equals("modificaProdotto")) {
			ProdottoBean product = new ProdottoBean();
			product.setCodice(request.getParameter("codice"));
			product.setNome(request.getParameter("nome"));
			product.setDescrizione(request.getParameter("descrizione"));
			product.setDettaglio(request.getParameter("dettaglio"));
			product.setIdGenere(Integer.parseInt(request.getParameter("id_genere")));  
 			product.setEdizione(request.getParameter("edizione")); 
			product.setDataUscita(java.sql.Date.valueOf(request.getParameter("data_uscita")));   
			product.setVoto(Integer.parseInt(request.getParameter("voto")));                                         
			product.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
			product.setQuantita(Integer.parseInt(request.getParameter("qtaMagazzino")));
			product.setImmagine("foto/"+request.getParameter("immagine"));
			product.setCancellato(false);			
			
			ProdottoModelDM model = new ProdottoModelDM();
			try {
				model.doUpdate(product);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("operazione", "La modifica del prodotto al catalogo è avvenuta con successo");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
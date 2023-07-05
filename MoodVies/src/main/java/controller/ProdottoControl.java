package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAOS.RecensioneModelDM;
import model.DAOS.GenereModelDM;
import model.DAOS.ProdottoModelDM;
import model.beans.Carrello;
import model.beans.GenereBean;
import model.beans.RecensioneBean;
import model.beans.ProdottoBean;

@WebServlet("/ProdottoControl")
public class ProdottoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ProdottoModelDM model = new ProdottoModelDM();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CARRELLO
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		if(carrello == null) {
			carrello = new Carrello();
			request.getSession().setAttribute("carrello", carrello);
		}
		//FINE CARRELLO
				
		String action = request.getParameter("action");
		
		if(action != null) {

			//Visualizza catalogo 
			if(action.equals("ViewProdotti")) {
				ArrayList<ProdottoBean> prodotti = null;
				String gen = request.getParameter("gen");
				int genere = 0;
				if(gen != null) {
					genere = Integer.parseInt(gen);
				}
				try {
					if(genere == 0) {
						prodotti = model.doRetrieveAll("");	//visualizza tutti i prodotti
					} else {
						prodotti = model.doRetrieveByGenere(genere);	//visualizza solo i prodotti di un genere
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("prodotti", prodotti);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/viewProdotti.jsp");
				dispatcher.forward(request, response);
			}

			//Visualizza dettaglio prodotto con genere e recensioni
			if(action.equals("ViewProdotto")) {
				ProdottoBean prodotto = null;
				ArrayList<RecensioneBean> f = null;
				RecensioneModelDM fDAO = new RecensioneModelDM();

				GenereBean genere = null;
				GenereModelDM gDAO = new GenereModelDM();

				String cod = request.getParameter("codice");
				try {
					prodotto = model.doRetrieveByKey(cod);
					f = fDAO.doRetrieveByProduct(cod); 	//recupera le recensioni
					genere = gDAO.doRetrieveByKey(prodotto.getIdGenere());		//recupera il genere
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("recensioni", f);
				request.setAttribute("prodotto", prodotto);
				request.setAttribute("genere", genere);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/prodotto.jsp");
				dispatcher.forward(request, response);
			}
			
			if(action.equals("AddToCarrello")) {
				try {
					carrello.addProduct(request.getParameter("codice"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.getSession().setAttribute("carrello", carrello);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/carrello.jsp");
				dispatcher.forward(request, response);
			}
			
			//rimuove 1 quantità dal carrello
			if(action.equals("RemoveToCarrello")) {
				carrello.removeProduct(request.getParameter("codice"));
				request.getSession().setAttribute("carrello", carrello);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/carrello.jsp");
				dispatcher.forward(request, response);
			}
			
			//rimuove prodotto dal carrello
			if(action.equals("DeleteToCarrello")) {
				carrello.deleteProduct(request.getParameter("codice"));
				request.getSession().setAttribute("carrello", carrello);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/carrello.jsp");
				dispatcher.forward(request, response);
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
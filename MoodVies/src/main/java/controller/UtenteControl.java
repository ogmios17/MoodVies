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

@WebServlet("/UtenteControl")
public class UtenteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IndirizzoModelDM indirizzoDAO= new IndirizzoModelDM();
	private CartaModelDM cartaDAO= new CartaModelDM();
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session != null) {
			UtenteBean utente = (UtenteBean) session.getAttribute("Utente");
			if(utente == null) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/login.jsp");
				dispatcher.forward(request, response);
			}

			String action = request.getParameter("action");
			
			if(action.equals("viewUtente")) {
				try {
					ArrayList<CartaBean> dati = cartaDAO.doRetrieveByUtente(utente.getEmail());
					request.setAttribute("carta", dati);
					ArrayList<IndirizzoBean> indirizzo = indirizzoDAO.doRetrieveByUtente(utente.getEmail());
					request.setAttribute("indirizzo", indirizzo);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/utente.jsp");
				dispatcher.forward(request, response);
			}
			
			if(action.equals("addIndirizzo")) {
				String via=request.getParameter("via");
				int cap = Integer.parseInt(request.getParameter("cap"));
				String citta=request.getParameter("citta");
                String provincia=request.getParameter("provincia");
				IndirizzoBean indirizzo=new IndirizzoBean();
				indirizzo.setUtente(utente.getEmail());
				indirizzo.setVia(via);				
				indirizzo.setCap(cap);
				indirizzo.setCitta(citta);
                indirizzo.setProvincia(provincia);
				try {
					indirizzoDAO.doSave(indirizzo);
 				} catch (SQLException e) {
					e.printStackTrace();
					request.setAttribute("operazione", "Si è verificato un errore. Ci scusiamo per il disagio.");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
					dispatcher.forward(request, response);
				}               
				request.setAttribute("operazione", "L'aggiunta dell'indirizzo di spedizione è avvenuta correttamente");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
				dispatcher.forward(request, response);
			}
			
			if(action.equals("addCarta")) {
				String numero_carta=request.getParameter("carta");
				int cvv=Integer.parseInt(request.getParameter("cvv"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date scadenza=null;
				try {
					scadenza=dateFormat.parse(request.getParameter("scadenza"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				CartaBean carta=new CartaBean();
				carta.setNumeroCarta(numero_carta);;
				carta.setCvv(cvv);
				carta.setScadenza(scadenza);
				carta.setUtente(utente.getEmail());
				try {
					cartaDAO.doSave(carta);
				} catch (SQLException e) {
					e.printStackTrace();
					request.setAttribute("operazione", "Si è verificato un errore. Ci scusiamo per il disagio.");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
					dispatcher.forward(request, response);
				}
				request.setAttribute("operazione", "L'aggiunta dei Dati di Pagamento è avvenuta correttamente");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
				dispatcher.forward(request, response);
			}
			
    		if(action.equals("addRecensione")) {
				RecensioneBean rec = new RecensioneBean();
                rec.setIdProdotto(request.getParameter("id_prodotto"));
				rec.setUtente(request.getParameter("utente"));                
				rec.setTitolo(request.getParameter("titolo"));
				rec.setDescrizione(request.getParameter("descrizione"));
                rec.setStelle(Integer.parseInt(request.getParameter("stelle")));
				
				RecensioneModelDM recensioneDAO = new RecensioneModelDM();
				try {
					recensioneDAO.doSave(rec);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("operazione", "Grazie per la tua recensione. Il tuo parere per noi è molto importante.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
				dispatcher.forward(request, response);
			}

    		if(action.equals("addWishlist")) {
				WishlistBean w = new WishlistBean();
                w.setIdProdotto(request.getParameter("id_prodotto"));
				w.setUtente(request.getParameter("utente"));                
				
				WishlistModelDM wishlistDAO = new WishlistModelDM();
				try {
					wishlistDAO.doSave(w);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("operazione", "Grazie per la tua recensione. Il tuo parere per noi è molto importante.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
				dispatcher.forward(request, response);
			}            
			

		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

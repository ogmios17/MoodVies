package controller;

import java.io.IOException;
import java.sql.SQLException;
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

@WebServlet("/OrdineControl")
public class OrdineControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrdineModelDM ordineDAO = new OrdineModelDM();
	private AcquistoModelDM acquistoDAO = new AcquistoModelDM();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			UtenteBean utente = (UtenteBean) session.getAttribute("Utente");
			if(utente == null) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/login.jsp");
				dispatcher.forward(request, response);
			} else {
			
				String action = request.getParameter("action");
				
				if(action.equals("datiOrdineUtente")) {
					IndirizzoModelDM indirizzoDAO=new IndirizzoModelDM();
					CartaModelDM cartaDAO=new CartaModelDM();
					try {
						ArrayList<IndirizzoBean> indirizzi= indirizzoDAO.doRetrieveByUtente(utente.getEmail());
						ArrayList<CartaBean> carta= cartaDAO.doRetrieveByUtente(utente.getEmail());
						request.setAttribute("indirizzi", indirizzi);
						request.setAttribute("carta", carta);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/checkoutOrdine.jsp");
						dispatcher.forward(request, response);	
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
						
				if(action.equals("checkout")) {
					Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
					ArrayList<ProdottoBean> prodotti = carrello.getProducts();
					if(prodotti.size() == 0) {
						request.setAttribute("operazione", "Il carrello è vuoto, visita il nostro catalogo");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
						dispatcher.forward(request, response);
					} else {
  						OrdineBean ordine = new OrdineBean();
                        ordine.setIdStato(Integer.parseInt(request.getParameter("id_stato")));
						ordine.setUtente(utente.getEmail());
                        ordine.setDataOrdine(new Date());
						ordine.setIdIndirizzo(Integer.parseInt(request.getParameter("id_indirizzo")));
						try {
							ordineDAO.doSave(ordine);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						for(ProdottoBean product : prodotti) {
							AcquistoBean acquisto = new AcquistoBean();
                            Double iva =  product.getQtaCarrello() * product.getPrezzo() * 0.22;
                            Double subtot =  (product.getQtaCarrello() * product.getPrezzo()) + iva;
							acquisto.setIdOrdine(ordine.getIdOrdine());
							acquisto.setIdProdotto(product.getCodice());
							acquisto.setQuantita(product.getQtaCarrello());
							acquisto.setSubtotaleIva(iva);
							acquisto.setSubtotale(subtot);
							try {
								acquistoDAO.doSave(acquisto);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						
						request.getSession().removeAttribute("carrello");
						carrello = new Carrello();
						request.getSession().setAttribute("carrello", carrello);
						request.setAttribute("operazione", "Il tuo ordine è stato preso in carico");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
						dispatcher.forward(request, response);
					}
				}
				
				if(action.equals("dettagliOrdine")) {
					int id = Integer.parseInt(request.getParameter("id"));
					ArrayList<ProdottoBean> products = new ArrayList<ProdottoBean>();
					IndirizzoModelDM indirizzoDAO = new IndirizzoModelDM();
					OrdineModelDM ordineDAO = new OrdineModelDM();
					IndirizzoBean indirizzo = null;
					
					try {
						ArrayList<AcquistoBean> acquisti = acquistoDAO.doRetrieveByOrdine(id);
						
						ProdottoModelDM productDAO = new ProdottoModelDM();
						for(AcquistoBean acquisto : acquisti) {
							ProdottoBean product = productDAO.doRetrieveByKey(acquisto.getIdProdotto());
							product.setQtaCarrello(acquisto.getQuantita());
                            product.setSubtotaleIva(acquisto.getSubtotaleIva());
                            product.setSubtotale(acquisto.getSubtotale());
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
		
				if(action.equals("viewOrdini")) {
					try {
						ArrayList<OrdineBean> ordini = ordineDAO.doRetrieveAllByUtente(utente.getEmail());
						request.setAttribute("ordini", ordini);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/ordiniUtente.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
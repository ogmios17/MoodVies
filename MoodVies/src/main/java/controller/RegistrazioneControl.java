package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAOS.UtenteModelDM;
import model.beans.UtenteBean;

@WebServlet("/RegistrazioneControl")
public class RegistrazioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteModelDM utenteDAO = new UtenteModelDM();
		UtenteBean utente = null;
		try {
			utente = utenteDAO.doRetrieveByKey(request.getParameter("email"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(utente == null) {
			utente = new UtenteBean();
			utente.setEmail(request.getParameter("email"));
            utente.setNome(request.getParameter("nome"));
			utente.setCognome(request.getParameter("cognome"));            
			utente.setPassword(request.getParameter("password"));
			utente.setRuolo(request.getParameter("ruolo"));
			try {
				utenteDAO.doSave(utente);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/utente.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("operazione", "Utente già registrato");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
			dispatcher.forward(request, response);
		}
	}
}

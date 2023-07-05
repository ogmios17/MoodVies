package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAOS.UtenteModelDM;
import model.beans.UtenteBean;

@WebServlet("/Login")
public class LoginControl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static UtenteModelDM model = new UtenteModelDM();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			UtenteBean utente = model.doRetrieve(email, password);
			if(utente == null) {
                request.setAttribute("operazione", "Login o password errati o utente non registrato");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
                dispatcher.forward(request, response);
			}
			else {
				HttpSession session = request.getSession(true);
				if(utente.getRuolo().equalsIgnoreCase("admin")) {
					session.setMaxInactiveInterval(60*35);
					session.setAttribute("Utente", utente);
				}else {
					session.setMaxInactiveInterval(60*20);
					session.setAttribute("Utente", utente);
				}
				response.getWriter().write("1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
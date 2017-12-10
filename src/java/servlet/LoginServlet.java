package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cliente;
import dao.ClienteDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String dni = req.getParameter("dni");
		String pass = req.getParameter("pass");
		
		Cliente c =  ClienteDao.loginValid(dni, pass);

		String encodeURL = resp.encodeRedirectURL("PerfilServlet");
		if (c.isValid()) {
			
			HttpSession session = req.getSession();
			
			session.setMaxInactiveInterval(60);
			session.setAttribute("clientSession", c);
			req.setAttribute("clientSession", c);
//			req.getRequestDispatcher("profile.jsp").forward(req, resp);
			
			
			
			
		}else{
			encodeURL =  resp.encodeRedirectURL("LoginServlet");
//			req.getRequestDispatcher("loginok.jsp").include(req, resp);
		}
		resp.sendRedirect(encodeURL);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           
		req.getRequestDispatcher("login.jsp").include(req, resp);
	
	}
	
}

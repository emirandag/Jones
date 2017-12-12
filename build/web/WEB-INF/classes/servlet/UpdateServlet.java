/**
 * 
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Dispatch;

import beans.Cliente;
import dao.ClienteDao;

@WebServlet("/UpdateServlet/*")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dni = req.getParameter("dni");
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String fechanac = req.getParameter("fecha");
		String sexo = req.getParameter("sexo");
		String direccion = req.getParameter("direccion");
		String telefono = req.getParameter("telefono");
		Cliente c = ClienteDao.updateUser(dni, nombre, apellidos, fechanac, sexo, direccion, telefono);

		if (c.isValid()) {

			HttpSession session = req.getSession();

			session.setMaxInactiveInterval(60);
			session.setAttribute("clientSession", c);
			req.setAttribute("clientSession", c);
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("loginok.jsp").include(req, resp);
		}
	}

}
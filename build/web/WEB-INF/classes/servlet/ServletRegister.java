/**
 *
 */
package servlet;

import beans.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.ClienteDao;

import javax.servlet.http.HttpSession;

@WebServlet("/ServletRegister")
public class ServletRegister extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String fechanac = request.getParameter("fecha");
        String sexo = request.getParameter("sexo");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String pass = request.getParameter("pass");

        if (!nombre.equalsIgnoreCase("") && !apellidos.equalsIgnoreCase("") && !fechanac.equalsIgnoreCase("")
                && !sexo.equalsIgnoreCase("") && !direccion.equalsIgnoreCase("") && !telefono.equalsIgnoreCase("") && !pass.equalsIgnoreCase("")) {
            Cliente c = ClienteDao.loginReg(dni, nombre, apellidos, fechanac, sexo, direccion, telefono, pass);
            if (c.isValid()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("clientSession", c);
                request.setAttribute("clientSession", c);
                response.sendRedirect("PerfilServlet");
            }
        } else {
            PrintWriter out = response.getWriter();
            out.println("Si estas viendo este mensaje es por que algo salio mal, no se pudo completar tu solicitud.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registro.jsp").include(req, resp);
    }
    
    

}

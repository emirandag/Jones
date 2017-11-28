/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Account;
import beans.Transaccion;
import dao.AccountsDAO;
import dao.TransaccionesDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author iaw26540084
 */
@WebServlet("/AccountDetailsServlet")
public class AccountDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dni = req.getParameter("dni");
        String iban = req.getParameter("iban");

        Account account = AccountsDAO.getAccount(iban);
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(60);
        session.setAttribute("cuenta", account);
        req.setAttribute("cuenta", account);

        Object tmp = TransaccionesDAO.listaTransacciones(iban);
        session.setAttribute("listaTransacciones", tmp);
        req.setAttribute("listaTransacciones", tmp);
        req.getRequestDispatcher("detalleCuenta.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}

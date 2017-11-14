/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Account;
import dao.AccountsDAO;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author iaw26540084
 */
@WebServlet(name = "ListAccountServlet", urlPatterns = {"/ListAccountServlet/*"})
public class ListAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        String dni = req.getRequestURI().split("ListAccounts/")[1];

        List<Account> account = AccountsDAO.getAccounts(dni);

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(60);
        session.setAttribute("listaCuentas", account);
        req.setAttribute("listaCuentas", account);
        String jsp = "listaCuentas.jsp";
        resp.sendRedirect(jsp);

//        for (Account account1 : account) {
//            
//        }
    }

}

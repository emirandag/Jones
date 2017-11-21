/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Account;
import dao.AccountsDAO;
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
@WebServlet("/ServletListAccount")
public class ServletListAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String dni = req.getParameter("dni");
        List<Account> account = AccountsDAO.getAccounts(dni);
        System.out.println(account.size()+"**********************************");

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(60);
        session.setAttribute("listaCuentas", account);
        req.setAttribute("listaCuentas", account);

        String jsp = "listaCuentas.jsp";
        req.getRequestDispatcher(jsp).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String iban = req.getParameter("iban");
        if(AccountsDAO.deleteAccount(new Account(iban, 0, null))){
            doGet(req, resp);
        }else{
            
        }
    }

}

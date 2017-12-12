/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Account;
import dao.AccountsDAO;
import java.io.IOException;
import java.math.BigInteger;

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
        String dni = req.getParameter("dni");
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("1")) {
            String saldoString = req.getParameter("saldo");
            if (!saldoString.isEmpty()) {
                Long saldo = Long.parseLong(saldoString);
                //insertar aqui
                BigInteger numTmp = new BigInteger(AccountsDAO.getLastIbanNumber()).add(new BigInteger("1"));
                String iban = "ES" + numTmp.toString();
                System.out.println(iban + "************************************");
                AccountsDAO.insertAccount(new Account(iban, saldo, dni));
            }

        }
        if (action.equals("2")) {
            ///borrar aqui
            String iban = req.getParameter("iban");
            AccountsDAO.deleteAccount(new Account(iban, 0, null));

        }
        doGet(req, resp);

    }

}

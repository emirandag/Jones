<%-- 
    Document   : listaCuentas
    Created on : Nov 14, 2017, 5:54:54 PM
    Author     : iaw26540084
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="beans.Cliente"%>
<%@ page import="beans.Account"%>
<%
    Account account = null;
    String iban = null;
    long saldo = 0;
    Cliente c = null;
    String user = null;
    String surname = null;
    String dni = null;
    if (session.getAttribute("clientSession") == null) {


    } else {

        c = (Cliente) session.getAttribute("clientSession");
        user = c.getNombre();
        surname = c.getApellidos();
        dni = c.getDni();
    }

    if (session.getAttribute("listaCuentas") == null) {

    } else {

        account = (Account) session.getAttribute("listaCuentas");
        iban = account.getIban();
        saldo = account.getSaldo();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cuentas</title>
    </head>
    <body>
        <h1>Las cuentas del cliente <%=user%></h1>    
    </body>
</html>

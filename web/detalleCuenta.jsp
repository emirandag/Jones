<%-- 
    Document   : detalleCuenta
    Created on : Nov 23, 2017, 8:33:56 PM
    Author     : iaw26540084
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="beans.Cliente"%>
<%@ page import="beans.Account"%>
<%Account account = new Account();
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

    if (session.getAttribute("cuenta") == null) {

    } else {

        account = (Account) session.getAttribute("cuenta");

    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <h1>Hola <%=user%> <%=surname%> </h1>

        <h2>Las transacciones de la cuenta lista</h2>

        <h3>IBAN: <%=account.getIban()%> </h3>



    </body>
</html>

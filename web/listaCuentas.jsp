<%-- 
    Document   : listaCuentas
    Created on : Nov 14, 2017, 5:54:54 PM
    Author     : iaw26540084
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="beans.Cliente"%>
<%@ page import="beans.Account"%>
<%ArrayList accounts = new ArrayList();
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

        accounts = (ArrayList) session.getAttribute("listaCuentas");

    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cuentas</title>
    </head>
    <body>
        <h1>Las cuentas del cliente <%=user%> <%=surname%> </h1>    
        <%for (Object cuenta : accounts) {
                Account cuentaObj = (Account) cuenta;
        %>
        <div id="contenido">
            <table width=40%"" border="2">
                <tr>
                    <th>IBAN</th>
                    <th>SALDO</th>
                </tr>
                <tr>
                    <td><%=cuentaObj.getIban()%></td>
                    <td><%=cuentaObj.getSaldo()%> €</td>
                <!--<p>IBAN: <%=cuentaObj.getIban()%></p>
                <p>SALDO: <%=cuentaObj.getSaldo()%> €</p>-->
                    <td width="5%">
                        <form method="POST" action="ServletListAccount">
                            <input type="hidden" name="iban" value="<%=cuentaObj.getIban()%>">
                            <input type="hidden" name="dni" value="<%=dni%>">
                            <input type="submit" value="Eliminar" name="accion">
                        </form>
                    </td>
                    <td width="5%">
                        <form method="POST" action="AccountDetailsServlet">
                            <input type="hidden" name="iban" value="<%=cuentaObj.getIban()%>">
                            <input type="hidden" name="dni" value="<%=dni%>">
                            <input type="submit" value="Detalles" name="accion">
                        </form>
                    </td>
                </tr>
            </table>
            <%}%>
            <h2>Tiene <%=accounts.size()%> cuentas</h2>
            
            


    </body>
</html>

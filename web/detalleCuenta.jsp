<%-- 
    Document   : detalleCuenta
    Created on : Nov 23, 2017, 8:33:56 PM
    Author     : iaw26540084
--%>

<%@page import="java.lang.String"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="beans.Cliente"%>
<%@ page import="beans.Account"%>
<%@ page import="beans.Transaccion"%>
<%Account account = new Account();
    HashMap<String, List<Transaccion>> listaTransacciones
            = session.getAttribute("listaTransacciones") == null ? new HashMap()
            : (HashMap<String, List<Transaccion>>) session.getAttribute("listaTransacciones");
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

        <h2>Las transacciones de la cuenta</h2>

        <p>IBAN: <%=account.getIban()%> </p>
        <p>SALDO: <%=account.getSaldo()%> €</p>
        <table width="100%" border="2">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Fecha</th>
                    <th>Cantidad</th>
                    <th>Destino</th>
                </tr>
            </thead>
            <tbody>
                <%for (Transaccion destino : listaTransacciones.get("envios")) {
                %>
                <tr>
                    <td><%=destino.getId()%></td>
                    <td><%=destino.getFecha()%></td>
                    <td><%=destino.getCantidad()%></td>
                    <td><%=destino.getDestino()%></td>

                </tr>
                <%}%>        
            </tbody>
        </table>



        <table width="100%" border="2">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Fecha</th>
                    <th>Cantidad</th>
                    <th>Origen</th>
                </tr>
            </thead>
            <tbody>
                <%if (listaTransacciones.get("recibidos").size() > 0) {

                        for (Transaccion origen : listaTransacciones.get("recibidos")) {
                %>
                <tr>
                    <td><%=origen.getId()%></td>
                    <td><%=origen.getFecha()%></td>
                    <td><%=origen.getCantidad()%></td>
                    <td><%=origen.getOrigen()%></td>
                </tr>
                <%}
                } else {%>        
                <tr>
                    <td colspan="4">
                        NO HAY REGISTROS
                    </td>
                </tr>
                <%}%>        
            </tbody>
        </table>






        <form method="POST" action="AccountDetailsServlet">
            <input type="hidden" name="iban" value="<%=account.getIban()%>"/>
            <input type="number" name="pasta" />€
            <input type="text" name="destino"/>
            <input type="submit" value="Realizar transaccion" name="accion">
        </form>




    </body>
</html>

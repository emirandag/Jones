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

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'es' }"
       scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources/language" />
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
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dropotron.min.js"></script>
        <script src="js/jquery.scrollgress.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-layers.min.js"></script>
        <script src="js/init.js"></script>

        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-wide.css" />

        <!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
    </head>
    <body>
        <!-------------------------------------- Header ----------------------------------------->
        <header id="header" class="skel-layers-fixed">
            <h1>
                ¡Hola!
                <%=user%>
                <%=surname%>
            </h1>
            <nav id="nav">
                <ul>
                    <li><fmt:message key="session" />: <%=dni%></li>
                    <li><a href="LogoutServlet" class="button"><fmt:message key="end.session" /></a></li>
                    <li><form>
                            <select id="language" name="language" class="button"
                                    onchange="submit()">
                                <option value="ca" ${language == 'ca' ? 'selected': '' }>
                                    Català</option>
                                <option value="es" ${language == 'es' ? 'selected': '' }>
                                    Castellano</option>
                            </select>
                        </form></li>
                </ul>
            </nav> </header>
        <section id="main" class="container 75%">   <header>
                <h2>
                    <fmt:message key="transaction.account" />
                </h2>
                <table width="80%" border="2">
                    <tr>
                        <th><strong>IBAN:</strong></th><td><%=account.getIban()%></td>
                        <th><strong>SALDO:</strong></th><td><%=account.getSaldo()%> €</td>
                    </tr>
                </table>
            </header>
      
          <section id="main" class="container 50%"> 
              <div class="box">
                <p><fmt:message key="transaction.made" /></p>
                <table width="100%" border="2" >
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th><fmt:message key="date" /></th>
                            <th><fmt:message key="quantity" /></th>
                            <th><fmt:message key="destination" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%if (listaTransacciones.get("envios").size() > 0) {
                                for (Transaccion destino : listaTransacciones.get("envios")) {
                        %>
                        <tr>
                            <td><%=destino.getId()%></td>
                            <td><%=destino.getFecha()%></td>
                            <td><%=destino.getCantidad()%> €</td>
                            <td><%=destino.getDestino()%></td>
                        </tr>
                        <%}
                        } else {%>        
                        <tr>
                            <td colspan="4">
                                <fmt:message key="not.records" />
                            </td>
                        </tr>
                        <%}%>        
                    </tbody>
                </table>
            </div>
            <div class="box">
                <p><fmt:message key="transaction.received" /></p>
                <table width="100%" border="2">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th><fmt:message key="date" /></th>
                            <th><fmt:message key="quantity" /></th>
                            <th><fmt:message key="origin" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%if (listaTransacciones.get("recibidos").size() > 0) {
                                for (Transaccion origen : listaTransacciones.get("recibidos")) {
                        %>
                        <tr>
                            <td><%=origen.getId()%></td>
                            <td><%=origen.getFecha()%></td>
                            <td><%=origen.getCantidad()%> €</td>
                            <td><%=origen.getOrigen()%></td>
                        </tr>
                        <%}
                        } else {%>        
                        <tr>
                            <td colspan="4">
                                <fmt:message key="not.records" />
                            </td>
                        </tr>
                        <%}%>        
                    </tbody>
                </table>
            </div>
        </section>     
                    
                    
        
            <div class="box2">
                <h3>¿<fmt:message key="textmake.transaction" />?</h3>
                <form method="POST" action="AccountDetailsServlet">
                    <div class="row uniform 50%">
                        <input type="hidden" name="iban" value="<%=account.getIban()%>"/>
                        <div class="6u 12u(3)">
                            <fmt:message key="quantity.one" />: <input type="text" name="pasta" placeholder="€" />
                        </div>
                        <div class="6u 12u(3)">
                            <fmt:message key="account.received" />: <input type="text" name="destino" placeholder="IBAN"/>
                        </div>
                    </div>
                    <div class="row uniform">
                        <div class="12u">
                            <input type="submit" value="<fmt:message key="make.transaction" />" name="accion">
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </body>
</html>
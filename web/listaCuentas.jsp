<%-- 
    Document   : listaCuentas
    Created on : Nov 14, 2017, 5:54:54 PM
    Author     : iaw26540084
--%>

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


        <section id="main" class="container 75%"> <header>
                <h2>
                    <%=user%> <%=surname%>, <fmt:message key="have" /> <%=accounts.size()%> <fmt:message key="account" />
                </h2>
                <%for (Object cuenta : accounts) {
                        Account cuentaObj = (Account) cuenta;
                %>
            </header>
            <div class="box">
                <table width=40%"" border="2">
                    <tr>
                        <th>IBAN</th>
                        <th>SALDO</th>

                    </tr>
                    <tr>
                        <td><%=cuentaObj.getIban()%></td>
                        <td><%=cuentaObj.getSaldo()%> €</td>
                    <tr/>
                    <tr>
                        <td width="2%">
                            <form method="POST" action="ServletListAccount">
                                <input type="hidden" name="action" value="2"/>
                                <input type="hidden" name="iban" value="<%=cuentaObj.getIban()%>">
                                <input type="hidden" name="dni" value="<%=dni%>">
                                <input type="submit" value="<fmt:message key="remove" />" name="accion">
                            </form>
                        </td>
                        <td width="2%">
                            <form method="GET" action="AccountDetailsServlet">
                                <input type="hidden" name="iban" value="<%=cuentaObj.getIban()%>">
                                <input type="hidden" name="dni" value="<%=dni%>">
                                <input type="submit" value="<fmt:message key="details" />" name="accion">
                            </form>
                        </td>
                    </tr>
                </table>
            </div>

            <%}%>
            
            <div class="box2">
                <h3>¿<fmt:message key="textadd.account" />?</h3>
                <form method="POST" action="ServletListAccount">
                    <input type="hidden" name="action" value="1"/>
                    <input type="hidden" name="dni" value="<%=dni%>">
                    <div class="row uniform 50%">
                        <div class="6u 12u(3)">
                    SALDO: <input type="text" name="saldo"/>
                    </div>
                         </div>
                     <div class="row uniform">
                        <div class="12u">
                    <input type="submit" value="<fmt:message key="add.account" />" name="accion">
                    </div>
                    </div>
                       
                </form>
            </div>
        </section>




    </body>
</html>

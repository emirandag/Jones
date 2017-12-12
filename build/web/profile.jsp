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

<%
    Cliente c = null;
    String user = null;
    String surname = null;
    String dni = null;
    if (session.getAttribute("clientSession") == null) {
        response.sendRedirect("login.jsp");
    } else {
        c = (Cliente) session.getAttribute("clientSession");
        user = c.getNombre();
        surname = c.getApellidos();
        dni = c.getDni();
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    <section id="main" class="container 75%"> <header>
            <h2>
                <fmt:message key="information.user" />
            </h2>
        </header>
        <div class="box2" align="center">
            <form action="UpdateServlet" method="post">
                <input name="dni" type="hidden" value="<%=c.getDni()%>" />
                <div class="6u 12u(3)" align="left">
                    <fmt:message key="name" />: <input type="text" value="<%=c.getNombre()%>" name="nombre"></input>
                    <fmt:message key="surname" />: <input type="text" value="<%=c.getApellidos()%>"
                             name="apellidos"></input> 
                    <fmt:message key="birthdate" />: <input type="text"
                             value="<%=c.getFechanac()%>" name="fecha"></input> 
                    <fmt:message key="sex" />: <input
                        type="text" value="<%=c.getSexo()%>" name="sexo"></input>
                    <fmt:message key="address" />: <input type="text" value="<%=c.getDireccion()%>"
                             name="direccion"></input> 
                    <fmt:message key="phone" />: <input type="text"
                             value="<%=c.getTelefono()%>" name="telefono"></input>
                </div>
                <div class="6u 12u(3)">
                    <input type="submit" value="<fmt:message key="update.profile" />">
                    <a href="ServletListAccount?dni=<%=dni%>" class="button"><fmt:message key="go.accounts" /></a>
                </div>
            </form>
        </div>
    </section>
</body>
</html>
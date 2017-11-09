<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : 'es' }"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources/language" />
<!DOCTYPE HTML>
<html>
<head>
<title>Registro - Banco Jones</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
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

	<!-- Header -->
	<header id="header" class="skel-layers-fixed">
		<h1>
			<a href="index.html"><fmt:message key="bank" /> Jones</a>
		</h1>

		<nav id="nav">
			<ul>
				<li><a href="index.html">Home</a></li>
				<li><a href="#"><fmt:message key="rates" /></a></li>
				<li><a href="#"><fmt:message key="secure" /></a></li>
				<li><a href="#">Jones</a></li>
				<li><a href="registro.jsp"><fmt:message key="registry" /></a></li>
				<li><a href="login.jsp" class="button"><fmt:message
							key="init.session" /></a></li>
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
		</nav>
	</header>

	<!-- Main -->
	<section id="main" class="container 75%">
		<header>
			<h2><fmt:message key="check.in" /></h2>
		</header>
		<div class="box">
			<form action="ServletRegister" method="post">
				<div class="row uniform 50%">
					<div class="6u 12u(3)">
						<fmt:message key="name" />: <input type="text" name="nombre" id="nombre" value=""
							placeholder="<fmt:message key="name" />" />
					</div>
					<div class="6u 12u(3)">
						<fmt:message key="surname" />: <input type="text" name="apellidos" id="apellidos"
							value="" placeholder="<fmt:message key="surname" />" />
					</div>
					<div class="6u 12u(3)">
						<fmt:message key="birthdate" />: <input type="text" name="fecha" id="fecha"
							value="" placeholder="<fmt:message key="birthdate" />" />
					</div>
					<div class="6u 12u(3)">
						DNI: <input type="text" name="dni" id="dni" value=""
							placeholder="DNI" />
					</div>
					<div class="6u 12u(3)">
						<fmt:message key="sex" />: <input type="text" name="sexo" id="sexo" value=""
							placeholder="<fmt:message key="sex" />" />
					</div>
					<div class="6u 12u(3)">
						<fmt:message key="address" />: <input type="text" name="direccion" id="direccion"
							value="" placeholder="<fmt:message key="address" />" />
					</div>

					<div class="6u 12u(3)">
						<fmt:message key="phone" />: <input type="text" name="telefono" id="telefono"
							value="" placeholder="<fmt:message key="phone" />" />
					</div>

					<div class="6u 12u(3)">
						<fmt:message key="password" />: <input type="password" name="pass" id="contrasenya"
							value="" placeholder="<fmt:message key="password" />" />
					</div>

				</div>
				<div class="row uniform">
					<div class="12u">
						<ul class="actions align-center">
							<li><input type="submit" value="<fmt:message key="check.in" />" /></li>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</section>

</body>
</html>
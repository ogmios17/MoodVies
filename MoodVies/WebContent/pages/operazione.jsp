<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="icon" href="foto/logofavicon.png" sizes="9x9">
		<link rel="stylesheet" href="<%=request.getContextPath() + "/css/style.css"%>">	
		<meta charset="ISO-8859-1">
		<title>Messaggi</title>
	</head>
	<body>
		<%@ include file="header.jsp" %>
		
		<%String operazione = (String) request.getAttribute("operazione"); %>	
		<h3 style="text-align: center;color: #FA9600;font-family:Verdana;padding-top:30px;"><%=operazione %></h3>
		
		<%@ include file="footer.jsp" %>
	</body>
</html>
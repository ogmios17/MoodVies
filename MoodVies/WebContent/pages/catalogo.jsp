<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.DAOS.*, model.beans.*" %>

<%
	ArrayList<ProdottoBean> products = (ArrayList<ProdottoBean>)request.getAttribute("prodotti");
%>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" href="foto/logofavicon.png" sizes="9x9">
	<link rel="stylesheet" href="<%=request.getContextPath() + "/css/style.css"%>">

	<meta charset="ISO-8859-1">
	<title>MoodVies - Catalogo</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<br>
	<div class="container bootstrap snipets">
	   	<div class="row flow-offset-1">
		    <%if(products != null && products.size() != 0){
				for(ProdottoBean product : products){%>
			<div class="col-xs-6 col-md-4">
				<div class="product tumbnail thumbnail-3"><a href="<%=request.getContextPath()%>/ProductControl?action=ViewProdotto&codice=<%=product.getCodice()%>"><img src="<%=request.getContextPath() + "/" + product.getImmagine()%>"></a>
			    	<div class="caption">
				       	<h6><%=product.getNome()%></h6>
					<h2><%=product.getDescrizione()%></h2>
				        <p><span class="price"><%=product.getPrezzo()%>&euro;</span></p>
				        <% if(product.getQuantita() == 0) {%>
							<p class="float-right">Non Disponibile</p>
						<%}else { %>
							<a href="<%=request.getContextPath()%>/ProductControl?action=AddToCarrello&codice=<%=product.getCodice()%>" class="btn btn-danger float-right btn-mio">Aggiungi al carello</a>
						<%} %>
					</div>
				</div>
			</div>
				<%}%>
			<%}%>
		</div>
	</div>
		
	<%@ include file="footer.jsp" %>
</body>
</html>
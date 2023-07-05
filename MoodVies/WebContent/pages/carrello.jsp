<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.beans.*" %>
<%@ page import="java.util.ArrayList" %>

<%
	Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
	ArrayList<ProdottoBean> products = null;
	if(carrello != null)	
		products = carrello.getProducts();
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>MoodVies - Carrello</title>
		<link rel="icon" href="foto/logofavicon.png" sizes="9x9">
		<link rel="stylesheet" href="<%=request.getContextPath() + "/css/style.css"%>">
	</head>
	<body>
		<%@ include file="header.jsp" %>

		<div class="card">
			<div class="row">
	   			<%if(products != null && products.size() != 0){%>
				<div class="col-md-8 cart">
					<div class="title">
	                	<div class="row">
	                    	<div class="col">
	                        	<h4><b>Il tuo Carrello</b></h4>
	                    	</div>
	                    	<div class="col align-self-center text-right text-muted"><%=carrello.getQtaProducts()%> prodotti</div>
	                	</div>
	            	</div>
					<%for(ProdottoBean product : products){%>
						<div class="row border-top border-bottom">
							<div class="row main align-items-center">
								<div class="col-2"><img class="img-cart" src="<%=request.getContextPath()+ "/" + product.getImmagine()%>"></div>
								<div class="col"><%=product.getNome()%></div>
								<div class="col"> <a href="<%=request.getContextPath()%>/ProductControl?action=RemoveToCarrello&codice=<%=product.getCodice()%>">-</a><%=product.getQtaCarrello()%><a href="<%=request.getContextPath()%>/ProductControl?action=AddToCarrello&codice=<%=product.getCodice()%>">+</a></div>
								<div class="col">&euro; <%=product.getPrezzo()%><span class="close"><a href="<%=request.getContextPath()%>/ProductControl?action=DeleteToCarrello&codice=<%=product.getCodice()%>">&#10005;</a></span></div>
							</div>
						</div>
					<%}%>
				</div>	
				<%}else{%>
					<div>
		       			<h6 id="not-product">Nessun elemento aggiunto al carrello fino ad ora.</h6>
					</div>
				<%}%>
	
			<%if(carrello != null && carrello.getTotale()!=0){ %>
				<div class="col-md-4 summary">
            		<div class="row" style="border-top: 1px solid rgba(0,0,0,.1); padding: 2vh 0;">
		                <div class="col">TOTALE</div>
		                <div class="col text-right">&euro; <%=carrello.getTotale() %></div>
		            </div> <a href="<%=request.getContextPath()%>/OrdineControl?action=datiOrdineUtente"><button class="btn btn-mio btn-lg btn-primary btn-block btn-dark">Acquista</button></a>
				</div>
			<%}%>
		</div>
	</div>

		<%@ include file="footer.jsp" %>	
	</body>
</html>
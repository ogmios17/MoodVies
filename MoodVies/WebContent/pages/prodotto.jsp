<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.beans.*, model.DAOS.*" %>

<%
	ProdottoBean prodotto = (ProdottoBean) request.getAttribute("prodotto");
	ArrayList<RecensioneBean> recensione = (ArrayList<RecensioneBean>) request.getAttribute("recensioni");
	GenereBean genere = (GenereBean) request.getAttribute("genere");

%>

<!DOCTYPE html>
<html>
<head>
	<head>
		<link rel="icon" href="foto/logofavicon.png" sizes="9x9">
		<link rel="stylesheet" href="<%=request.getContextPath() + "/css/style.css"%>">

		
		<meta charset="ISO-8859-1">
		<title>MoodVies - <%=prodotto.getNome()%></title>
	</head>
	<body>
		<%@ include file="header.jsp" %>
		
		
		<img class="img-product" src="<%=prodotto.getImmagine()%>">	
		<h1 style="padding-top:30px;"><%=prodotto.getNome()%></h1>
		<p style="padding-top:20px;"><%=prodotto.getDettaglio()%></p>
		<p style="padding-top:20px;">Genere: <%=genere.getNome()%></p>
		<p style="padding-top:20px;">Edizione: <%=prodotto.getEdizione()%></p>
		<p style="padding-top:20px;">Data uscita: <%=prodotto.getDataUscita()%></p>
		<h1 id="grassetto"><%=prodotto.getPrezzo()%>&euro;</h1>
		
		<%if(prodotto.getQuantita() > 0) {%>
			<a href="<%=request.getContextPath()%>/ProductControl?action=AddToCarrello&codice=<%=prodotto.getCodice()%>" class="btn btn-danger btn-lg float-right btn-mio">Aggiungi al carrello</a>
		<%} else { %>
			Non disponibile
		<%} %>
		
		
		<div style="clear:left;" class="feedback">
		<%if(recensione != null && recensione.size() != 0) {%>
		<div class="container py-5">
			<div class="card rounded-0 CDtot" >
            <div class="card-header carta1">
                 <h3 class="mb-0">Recensioni</h3>
             </div>
             <div class="card-body">
        
  
			<table>
				<thead>
					<tr>
					<th>Titolo</th>
					<th>Commento</th>
					</tr>
				</thead>
				<tbody>
				<%for(RecensioneBean f: recensione){ %>
					<tr>
					<td><%=f.getTitolo() %></td>
					<td><%=f.getDescrizione() %></td>
					</tr>
				<%} %>
				</tbody>
			</table>
			
			</div>
			</div>
			</div>
		
		<br>
		<br>
		<br>
		<%} else {%>
			<h6 class="testo">Nessuna recensione per questo prodotto.</h6>
		<%} %>
		<br>
		<br>
		<br>
		
		<%@ include file="footer.jsp" %>	
	</body>
</html>
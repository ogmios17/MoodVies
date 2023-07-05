<%@page import="model.DAOS.*"%>
<%@page import="model.beans.*"%>
<%@page import="controller.*"%>

<nav id="navbar1" class="navbar navbar-expand-lg">
  <div class="d-flex flex-grow-1">
      <span class="w-100 d-lg-none d-block"><!-- hidden spacer to center brand on mobile --></span>
      <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">
         <img src="foto/logo.jpg"  alt="MoodViev" id="logo">
      </a>
      <div class="w-100 text-right">
          <button id="tastino1" class="navbar-toggler" type="button" data-toggle="collapse" data-target="#myNavbar7">
              <span class="navbar-toggler-icon"><img src="foto/om.png" alt=""></span>
          </button>
      </div>
  </div>
  <div class="collapse navbar-collapse flex-grow-1 text-right" id="myNavbar7">
      <ul class="navbar-nav ml-auto flex-nowrap">
	          <li class="nav-item">
	              <a id="collegamento" href="<%=request.getContextPath()%>/index.jsp" class="nav-link">Home</a>
	          </li>
	          <li class="nav-item">
	              <a id="collegamento" href="<%=request.getContextPath()%>/ProductControl?action=ViewProdotti" class="nav-link">Catalogo</a>
	          </li>
          <%if(session.getAttribute("Utente")==null){ %>
	          <li class="nav-item">
	              <a id="collegamento" href="<%=request.getContextPath()%>/pages/login.jsp" class="nav-link">Accedi</a>
	          </li>
          <%} else{%>
          <li class="nav-item">
              <a id="collegamento" href="<%=request.getContextPath()%>/servletLogout" class="nav-link">Logout</a>
          </li>
          <li class="nav-item">
              <a id="collegamento" href="<%=request.getContextPath()%>/pages/utente.jsp" class="nav-link">Area Utente</a>
          </li>
          <%} %>
          <li class="nav-item">
            <a href="<%=request.getContextPath()%>/pages/carrello.jsp"><button type="button" class="btn btn-secondary bottone" id="carrello"><img src="foto/car.png"></button></a>
          </li>
          
      </ul>
  </div>
</nav>

<nav id="navbar2" class="navbar navbar-expand-lg">

  
  <div class="w-100 d-flex flex-nowrap">
      <div class="w-100">
          <button id="tastino2" class="navbar-toggler" type="button" data-toggle="collapse" data-target="#myNavbar8">
              <span class="navbar-toggler-icon"><img src="foto/linee.png" alt=""></span>
          </button>
      </div>
  
</nav>

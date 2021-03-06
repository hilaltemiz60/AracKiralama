<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html >
<head>
  <title>Araç Kiralama</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
         <a class="navbar-brand" href="<%=request.getContextPath()%>/home">Araç Kiralama Sistemi</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Araç İşlemleri <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<%=request.getContextPath()%>/listArac">Araç Listesi</a></li>
          <li><a href="<%=request.getContextPath()%>/newArac">Araç Ekle</a></li>
        </ul>
      </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Müşteri İşlemleri <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<%=request.getContextPath()%>/listMusteri">Müşteri Listesi</a></li>
          <li><a href="<%=request.getContextPath()%>/newMusteri">Müşteri Ekle</a></li>
        </ul>
      </li>
                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Araç Kiralama İşlemleri <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<%=request.getContextPath()%>/listKiralama">Kiralanan Araçlar Listesi</a></li>
          <li><a href="<%=request.getContextPath()%>/newKiralama">Araç Kirala</a></li>
          <li><a href="<%=request.getContextPath()%>/listAracTeslim">Araç Teslim Listesi</a></li>
        </ul>
      </li>
    </ul>
        <ul class="nav navbar-nav  navbar-right" >
            <li class="dropdown"><a class="dropdown-toggle text-uppercase" data-toggle="dropdown" href="#">${loggedUser.firstName} ${loggedUser.lastName} <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<%=request.getContextPath()%>/profilim">Profilim</a></li>
          <li><a href="<%=request.getContextPath()%>/cikisYap">Çıkış Yap</a></li>
        </ul>
      </li>
      
    </ul>
  </div>
</nav>
  
<div class="container">
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Araç Teslim Listesi</h3>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Araç Plaka</th>
						<th>Araç Marka</th>
						<th>Araç Model</th>
						<th>Araç Renk</th>
						<th>Teslim Alan Ad Soyad</th>
						<th>Teslim Tarihi</th>
						
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="aracTeslim" items="${listAracTeslim}">
						<tr>
							<td><c:out value="${aracTeslim.id}" /></td>
							<td><c:out value="${aracTeslim.arac.plakaNo}" /></td>
							<td><c:out value="${aracTeslim.arac.marka}" /></td>
							<td><c:out value="${aracTeslim.arac.model}" /></td>
							<td><c:out value="${aracTeslim.arac.renk}" /></td>
							<td><c:out value="${aracTeslim.user.firstName} ${aracTeslim.user.lastName}" /></td>		
							<td><c:out value="${aracTeslim.teslimTarihi}" /></td>	
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</div>

</body>
</html>

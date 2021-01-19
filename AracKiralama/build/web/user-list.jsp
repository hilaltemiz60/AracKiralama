    <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
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
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Kullanıcı Listesi</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Yeni Kullanıcı Ekle</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Password</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="user" items="${listUser}">

						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.name}" /></td>
							<td><c:out value="${user.password}" /></td>				
							<td><a href="<%=request.getContextPath()%>/edit?id=<c:out value='${user.id}' />">Düzenle</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="<%=request.getContextPath()%>/delete?id=<c:out value='${user.id}' />">Sil</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Araç Kiralama Sistemi</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />


</head>
<body>

	<header>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
     <a class="navbar-brand" href="#">Araç Kiralama Sistemi</a>
    </div>
  </div>
</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
							<form action="loginUser" method="post" id="loginForm">
					
					<caption>
						<h2><span id="accountTypeName"></span> Giriş</h2>
					</caption>
					<fieldset class="form-group">
						<label>Kullanıcı Adı</label> <input type="text" class="form-control"
							name="name" required="required">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Şifre</label> <input type="password" class="form-control "
							name="password" required="required">
					</fieldset>
	
					<button type="submit" class="btn btn-success">Giriş Yap</button>
					<c:if test="${error != null}">
						<label class="text-danger">${error}</label>
					</c:if>
				</form>
<a class="text-secondary" href="<%=request.getContextPath()%>/uyeOl" style="cursor:pointer"><i class="fa fa-arrow-left"> </i>Üye Ol</a>
			</div>
		</div>
	</div>
</body>
</html>


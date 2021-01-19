<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Araç Takip Sistemi</title>
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
          <a class="navbar-brand" href="<%=request.getContextPath()%>/home">Araç Kiralama Sistemi</a>
    </div>
  </div>
</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
						<form action="insertUyeOl" method="post">

				<caption>
					<h2>
						<c:if test="${user == null}">
            			Üye Ol
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>
								<fieldset class="form-group">
					<label>Adı</label> <input type="text"
						value="<c:out value='${user.firstName}' />" class="form-control"
						name="firstName">
				</fieldset>
						<fieldset class="form-group">
					<label>Soyadı</label> <input type="text"
						value="<c:out value='${user.lastName}' />" class="form-control"
						name="lastName">
				</fieldset>
				<fieldset class="form-group">
					<label>Kullanıcı Adı</label> <input type="text"
						value="<c:out value='${user.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Şifre</label> <input type="text"
						value="<c:out value='${user.password}' />" class="form-control"
						name="password">
				</fieldset>
					<button type="submit" class="btn btn-success">Üye Ol</button>
					<c:if test="${error != null}">
						<label class="text-danger">${error}</label>
					</c:if>
				</form>
<a class="text-secondary" href="<%=request.getContextPath()%>/login" style="cursor:pointer"><i class="fa fa-arrow-left"> </i>Giriş Yap</a>

			</div>
		</div>
	</div>
</body>
</html>

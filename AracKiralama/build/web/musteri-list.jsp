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
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${musteri != null}">
					<form action="updateMusteri" method="post">
				</c:if>
				<c:if test="${musteri == null}">
					<form action="insertMusteri" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${musteri != null}">
            			Müşteri Güncelle
            		</c:if>
						<c:if test="${musteri == null}">
            			Müşteri Ekle
            		</c:if>
					</h2>
				</caption>

				<c:if test="${musteri != null}">
					<input type="hidden" name="id" value="<c:out value='${musteri.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Ad</label> <input type="text"
						value="<c:out value='${musteri.ad}' />" class="form-control"
						name="ad" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Soyad</label> <input type="text"
						value="<c:out value='${musteri.soyad}' />" class="form-control"
						name="soyad">
				</fieldset>
				<fieldset class="form-group">
					<label>Telefon</label> <input type="text"
						value="<c:out value='${musteri.telefon}' />" class="form-control"
						name="telefon">
				</fieldset>
						<fieldset class="form-group">
					<label>Adres</label> <input type="text"
						value="<c:out value='${musteri.adres}' />" class="form-control"
						name="adres">
				</fieldset>
				<button type="submit" class="btn btn-success">Kaydet</button>
				</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>

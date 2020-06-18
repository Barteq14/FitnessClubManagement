<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Szczegóły sal</title>
    <link rel="stylesheet" type="text/css"
          href="webjars/bootstrap-material-design/4.1.1/assets/css/docs.min.css" />
    <link rel="stylesheet" type="text/css"
          href="statics/css/main.css" />
</head>

<body class="card">
<c:import url="shared/header.jsp">
    <c:param name="pageName" value="trainingRoomList"/>
</c:import>
<div id="main" class="card-body container">
    <H1>Dane sali</H1>
    Id: <b>${trainingRoom.id}</b><br/>
    Czas utworzenia wpisu: <b><fmt:formatDate value="${trainingRoom.createdDate}" type="both" timeStyle="medium"/></b><br/>
    Nazwa: <b>${empty trainingRoom.name?'Brak danych': trainingRoom.name}</b><br/>
    Model: <b>${empty trainingRoom.model?'Brak danych': trainingRoom.model}</b><br/>
    Cena: <b><fmt:formatNumber type="CURRENCY" value="${vehicle.price}" currencySymbol="PLN"/></b><br/>
    Typ sali: <b>${trainingRoom.trainingRoomType.name}</b><br/>

    <a class="btn btn-success" href="/trainingRoomList.html">Wstecz</a>
</div>
</body>
<jsp:include page="shared/footer.jsp"/>
</html>


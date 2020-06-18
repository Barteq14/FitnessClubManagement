<%--
  Created by IntelliJ IDEA.
  User: barteq
  Date: 08.01.2019
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista sal treningowych</title>
        <link rel="stylesheet" type="text/css"
              href="webjars/bootstrap-material-design/4.1.1/assets/css/docs.min.css" />
        <link rel="stylesheet" type="text/css"
              href="statics/css/main.css" />
    </head>
</head>
<body class="card">
<jsp:include page="shared/header.jsp">
    <jsp:param name="pageName" value="vehicleList"/>
</jsp:include>
<div id="main" class="card-body">
    <H1>LISTA POJAZDÓW</H1>
    <H3>Implementacja widoku z tagów JSTL(JSP Standard Tags Library)</H3>


    <c:if test="${empty vehicleListPage.content}">
        ${searchCommand.isEmpty() ? 'Lista pojazdów jest pusta':'Brak wyników wyszukiwania'}
    </c:if>

    <c:if test="${not empty vehicleListPage.content}">

        <c:choose>
            <c:when test="${searchCommand.isEmpty()}">
                Lista zawiera ${vehicleListPage.totalElements} pojazdów
            </c:when>
            <c:otherwise>
                Wynik wyszukiwania: ${vehicleListPage.totalElements} pojazdów
            </c:otherwise>
        </c:choose>

        <c:set var="boundMin" value="${20000}"/>
        <c:set var="boundMax" value="${40000}"/>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>Nazwa</th>
                <th>Typ sali</th>
                <th>Ilość miejsc</th>


            </tr>
            </thead>
            <tbody>

                        <td>
                            <c:url var="deleteUrl" value="/trainingRoomList.html?did=${vehicle.id}&page=${vehicleListPage.number}&size=${vehicleListPage.size}" />
                            <c:url var="editUrl" value="/vehicleForm.html?id=${vehicle.id}" />
                            <a class="btn btn-danger" href="${deleteUrl}">Usuń</a>
                            <a class="btn btn-success" href="${editUrl}">Edytuj</a>
                        </td>

                </tr>

            </tbody>
        </table>

        <c:set var="page" value="${vehicleListPage}" scope="request"/>
        <c:set var="mainUrl" value="vehicleList.html" scope="request"/>
        <jsp:include page="shared/pagination.jsp"/>

    </c:if>

</div>
<security:authorize access="hasRole('ADMIN')">
<div>
    <a class="btn btn-success" href="vehicleForm.html">Dodaj Nowy</a>
</div>
</security:authorize>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>

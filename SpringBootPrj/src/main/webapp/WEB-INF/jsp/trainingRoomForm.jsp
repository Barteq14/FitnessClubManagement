<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Formularz sal treningowych</title>
    <link rel="stylesheet" type="text/css"
          href="webjars/bootstrap-material-design/4.1.1/assets/css/docs.min.css" />
    <link rel="stylesheet" type="text/css"
          href="statics/css/main.css" />
</head>
<body class="card">
<c:import url="shared/header.jsp">
    <c:param name="pageName" value="trainingRoomForm"></c:param>
</c:import>
<div id="main" class="card-body container">

    <form:form modelAttribute="trainingRoom">
        <div class="form-group">
            <label for="name" class="bmd-label-floating">Nazwa:</label>
            <form:input path="name" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
            <form:errors path="name" cssClass="error text-danger" element="div"/>
        </div>
        <div class="form-group">
            <label for="trainingRoomType.id" class="bmd-label-floating">Typ Sali:</label>
            <form:select path="trainingRoomType.id" cssClass="form-control" cssErrorClass="form-control is-invalid">
                <form:option value="-1">--wybierz typ sali--</form:option>
                <form:options items="${trainingRoomTypes}" itemLabel="name" itemValue="id"/>
            </form:select>
            <form:errors path="trainingRoomType.id" cssClass="error text-danger" element="div"/>
        </div>

        <div class="form-group">
            <label for="number_of_places" class="bmd-label-floating">Liczba miejsc:</label>
            <form:input path="number_of_places" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
            <form:errors path="number_of_places" cssClass="error text-danger" element="div"/>
        </div>


        <div class="form-group">
            <label class="bmd-label-floating">Wyposażenie sali:</label>
            <form:checkboxes path="accessories" element="div class='checkbox' style='left:25px;'" items="${accessoryList}" itemLabel="name" itemValue="id"/>
            <form:errors path="accessories" cssClass="error text-danger" element="div"></form:errors>
        </div>

        <button type="submit" class="btn btn-primary btn-raised">Zapisz</button>
    </form:form>

</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>





<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Suitable flights</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Suitable flights</h3>

<p style="color: red;">${errorString}</p>
<form method="POST" action="${pageContext.request.contextPath}/chooseFlight">
<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Choose</th>
        <th>Id</th>
        <th>Departure</th>
        <th>Arrival</th>
        <th>Start Airport</th>
        <th>Final Airport</th>
        <th>Plane</th>
        <th>Price</th>
    </tr>
    <c:forEach items="${flights}" var="flight">
        <tr>
            <td><input type="radio" name="flightId" value=${flight.id}></td>
            <td>${flight.id}</td>
            <td>${flight.departure}</td>
            <td>${flight.arrival}</td>
            <td>${flight.startAirport}</td>
            <td>${flight.finalAirport}</td>
            <td>${flight.plane.model}</td>
            <td>${flight.price}</td>
        </tr>
    </c:forEach>
</table>
    <input type="number" name="numberOfSeats" value= ${numberOfSeats}>

    <p><input type="submit" value="Отправить">
        <a href="${pageContext.request.contextPath}/">Cancel</a>
<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.10.2021
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Tickets</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<form method="POST" action="${pageContext.request.contextPath}/chooseFlight">
    <table border="1" cellpadding="5" cellspacing="1" >
        <tr>
            <th>Flight Id</th>
            <th>Departure</th>
            <th>Arrival</th>
            <th>Start Airport</th>
            <th>Final Airport</th>
            <th>Price</th>
            <th>Plane</th>
            <th>Show plane</th>
            <th>Number Of Seats</th>
            <th>Show Seats</th>
            <td>Active</td>
            <th>Deactivate ticket</th>
        </tr>


        <c:forEach items="${tickets}" var="ticket">
            <tr>
                <td>${ticket.flight.id}</td>
                <td>${ticket.flight.departure}</td>
                <td>${ticket.flight.arrival}</td>
                <td>${ticket.flight.startAirport}</td>
                <td>${ticket.flight.finalAirport}</td>
                <td>300</td>
                <td>${ticket.flight.plane.model}</td>
                <td>
                    <a href="showPlane?planeId=${ticket.flight.plane.id}">show</a>
                </td>
                <td>${ticket.numberOfSeats}</td>
                <td>
                    <a href="showSeats?ticketId=${ticket.id}">show</a>
                </td>
                <td>${ticket.active}</td>
                <td>
                    <a href="deactivate?ticketId=${ticket.id}">deactivate</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <var type="number" name="numberOfSeats" value= ${numberOfSeats}>

        <a href="${pageContext.request.contextPath}/">Cancel</a>
        <jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>

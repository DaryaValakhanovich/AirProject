<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.10.2021
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Suitable flights</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>


<h3>There are no suitable flights for you!!!</h3>
<h3>How about a transfer option?</h3>
<p style="color: red;">${errorString}</p>
<form method="POST" action="${pageContext.request.contextPath}/chooseDifficultWay">
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
        <c:forEach items="${listsOfFlights}" var="listOfFlights">
            <%--      <tr>
                      <td><input type="radio" name="listOfFlights" value=${listOfFlights}></td>
                  </tr>
                  --%>
                      <c:forEach items="${listOfFlights}" var="flight">
                          <tr>
                           <td> <input type="checkbox" name="listOfFlightsIds" value=${flight.id}></td>
                          <td>${flight.id}</td>
                          <td>${flight.departure}</td>
                          <td>${flight.arrival}</td>
                          <td>${flight.startAirport}</td>
                          <td>${flight.finalAirport}</td>
                          <td>${flight.plane.model}</td>
                          <td>${flight.price}</td>
                          </tr>
                      </c:forEach>
            <tr><td>/////////////////////////////////////////////////////////////////////////////</td></tr>
              </c:forEach>
          </table>
          <input type="number" name="numberOfSeats" value= ${numberOfSeats}>

          <p><input type="submit" value="Отправить">
          </p>
</form>
<a href="${pageContext.request.contextPath}/">Cancel</a>
              <jsp:include page="_footer.jsp"></jsp:include>
      </body>
      </html>
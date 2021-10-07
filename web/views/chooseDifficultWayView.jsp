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
<c:if test="${loginedUser==null}">
    <jsp:include page="_menu.jsp"></jsp:include>
</c:if>
<c:if test="${loginedUser.role=='USER'}">
    <jsp:include page="_user_menu.jsp"></jsp:include>
</c:if>
<c:if test="${loginedUser.role=='ADMIN'}">
    <jsp:include page="_admin_menu.jsp"></jsp:include>
</c:if>
<script>
    function Validate(){
        if(!validateForm()){
            alert("You must check at least one of the flights");
            return false;
        }
        return true
    }
    function validateForm()
    {
        var c=document.getElementsByTagName('input');
        for (var i = 0; i<c.length; i++){
            if (c[i].type=='checkbox')
            {
                if (c[i].checked){return true}
            }
        }
        return false;
    }
</script>

<h3>There are no suitable flights for you!!!</h3>
<h3>How about a transfer option?</h3>
<p style="color: red;">${errorString}</p>
<form method="POST" action="${pageContext.request.contextPath}/chooseDifficultWay" onsubmit="return Validate()">
    <style>
        th {
            font-weight: normal;
            color: #039;
            padding: 10px 15px;
        }
        td {
            color: #669;
            border-top: 1px solid #e8edff;
            padding: 10px 15px;
        }
        tr:hover td {
            background: #e8edff;
        }
    </style>
    <table>
        <tr>
            <th></th>
            <th>Id</th>
            <th>Departure</th>
            <th>Arrival</th>
            <th>Start Airport</th>
            <th>Final Airport</th>
            <th>Plane</th>
            <th>Price</th>
        </tr>
        <c:forEach items="${listsOfFlights}" var="listOfFlights">

                      <c:forEach items="${listOfFlights}" var="flight">
                          <tr>
                           <td><input type="checkbox" name="listOfFlightsIds" value=${flight.id}></td>
                          <td>${flight.id}</td>
                          <td>${flight.departure}</td>
                          <td>${flight.arrival}</td>
                          <td>${flight.startAirport}</td>
                          <td>${flight.finalAirport}</td>
                          <td>${flight.plane.model}</td>
                          <td>${flight.price}</td>
                          </tr>
                      </c:forEach>
            <tr>
                <td colspan="8"></td>
            </tr>
              </c:forEach>
          </table>

    <a href="${pageContext.request.contextPath}/">Cancel      </a>
    <p><input type="submit" value="Отправить"></p>
          <input style="display: none" type="number" name="numberOfSeats" value= ${numberOfSeats}>
    <input  style="display: none" name="listsOfFlights" value= ${listsOfFlights}>

</form>
              <jsp:include page="_footer.jsp"></jsp:include>
      </body>
      </html>
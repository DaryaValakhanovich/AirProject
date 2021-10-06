<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.10.2021
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Seats</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Seats</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Number Of Seat</th>
        <th>Ticket Id</th>
    </tr>
    <c:forEach items="${seats}" var="s" >
        <tr>
            <td>${s.seat}</td>
            <td>${s.ticketId}</td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/">Cancel</a>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.10.2021
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Flight</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Create Flight</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/createFlight">
    <table border="0">
        <tr>
            <td>Departure date</td>
            <td><input type="datetime-local" name="departure" value="${departure}" /></td>
        </tr>
        <tr>
            <td>Arrival date</td>
            <td><input type="datetime-local" name="arrival" value="${arrival}" /></td>
        </tr>
        <tr>
            <td>Start airport</td>
            <td><input type="text" name="startAirport" value="${startAirport}" /></td>
        </tr>
        <tr>
            <td>Final airport</td>
            <td><input type="text" name="finalAirport" value="${finalAirport}" /></td>
        </tr>
        Plane
        <c:forEach items="${planes}" var="plane">
            <tr>
                <td><input type="radio" name="planeId" value=${plane.id}></td>
                <td>${plane.id}</td>
                <td>${plane.model}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Find Flight</title>
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

<h3>Find Flight</h3>

<p style="color: red;">${errorString}</p>

<form method="GET" action="${pageContext.request.contextPath}/chooseFlight">
    <table border="0">
        <tr>
            <td>Departure date</td>
            <td><input type="date" name="departure" value="${flight.departure}" /></td>
        </tr>
        <tr>
            <td>Number of seats</td>
            <td><input type="number" name="numberOfSeats" value="${flight.numberOfSeats}" /></td>
        </tr>
        <tr>
            <td>Start airport</td>
            <td><input type="text" name="startAirport" value="${flight.startAirport}" /></td>
        </tr>
        <tr>
            <td>Final airport</td>
            <td><input type="text" name="finalAirport" value="${flight.finalAirport}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <a href="${pageContext.request.contextPath}/">Cancel</a>
                <input type="submit" value="Submit" />
            </td>
        </tr>
    </table>
</form>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
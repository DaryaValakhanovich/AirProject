<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Product</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Create Product</h3>

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
                <input type="submit" value="Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
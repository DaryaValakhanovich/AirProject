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
            alert("You must check one of the planes");
            return false;
        }
        return true
    }
    function validateForm()
    {
        var c=document.getElementsByTagName('input');
        for (var i = 0; i<c.length; i++){
            if (c[i].type=='radio')
            {
                if (c[i].checked){return true}
            }
        }
        return false;
    }
</script>

<h3>Create Flight</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/createFlight" onsubmit="return Validate()">
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
                <a href="${pageContext.request.contextPath}/">Cancel</a>
                <input type="submit" value="Submit" />
            </td>
        </tr>
    </table>
</form>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
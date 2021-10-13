<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.10.2021
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
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
<h3>Are you absolutely sure that you want to deactivate ticket?</h3>
<form method="POST" action="${pageContext.request.contextPath}/deactivate">
    <table border="0">
        <tr>
            <td><a href="${pageContext.request.contextPath}/showMyTickets">Back</a></td>
            <td></td>
            <td><input type="submit" value="Deactivate!"></td>
        </tr>
    </table>
    <input  style="display: none" name="ticketId" value= ${ticketId}>
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<h3>Create User</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/createUser">
    <table border="0">
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="${user.email}" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password" value="${user.password}" /></td>
        </tr>
        <tr>
            <td>Number</td>
            <td><input type="text" name="number" value="${user.number}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <a href="${pageContext.request.contextPath}/">Cancel</a>
                <input type="submit" value="Submit" />
            </td>
        </tr>
    </table>
</form>
<p style="color:blue;">Пароль должен быть не меньше 8 символов, содержать как минимум 1 большую букву, одну маленькую букву и ЛИБО спец. символ ЛИБО цифру.</p>
<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
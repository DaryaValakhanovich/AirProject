<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Create User</h3>

<p style="color: red;">${errorString}</p>

<form method="GET" action="${pageContext.request.contextPath}/createUser">
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
                <input type="submit" value="Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
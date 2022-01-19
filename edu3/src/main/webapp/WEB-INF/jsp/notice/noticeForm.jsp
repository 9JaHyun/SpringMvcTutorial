<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022-01-19
  Time: 오후 2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script></script>
<body>
<form action="${pageContext.request.contextPath}/noticeAdd.do" method="post">
    <table>
        <tbody>
        <tr>
            <th>제목</th>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <th>내용</th>
            <td><textarea name="content"></textarea></td>
        </tr>
        </tbody>
    </table>
    <input type="submit" value="입력">
</form>
</body>
</html>

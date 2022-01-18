<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022-01-18
  Time: 오후 5:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>글번호</th>
        <th>글제목</th>
        <th>작성자</th>
        <th>작성일시</th>
        <th>조회수<th>
    </tr>
    </thead>
    <c:set var="bulletin" value="${bulletin}">
    <tbody>
        <tr>
            <td>${bulletin.bbsId}</td>
            <td>${bulletin.bbsTitle}</td>
            <td>${bulletin.bbsWriter}</td>
            <td>${bulletin.bbsCreateDate}</td>
            <td>${bulletin.bbsHit}</td>
        </tr>
    </tbody>
    </c:set>
</table>
</body>
</html>

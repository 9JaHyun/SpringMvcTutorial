<%@ page import="java.util.List" %>
<%@ page import="comments.domain.comment.Comments" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--JSTL 확장 라이브러리--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/edu2_war_exploded">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <c:forEach var="members" items="${requestScope.commentList}">
        <tr>
            <td>${members.id}</td>
            <td>${members.name}</td>
            <td>${members.content}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
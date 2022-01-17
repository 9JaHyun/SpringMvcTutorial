<%@ page import="member.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--JSTL 확장 라이브러리--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        td {
            padding: 1rem 3rem;
        }
    </style>
</head>
<body>
<a href="/edu2_war_exploded">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>password</th>
    <th>name</th>
    <th>email</th>
    </thead>
    <tbody>
    <%
        List<Member> memberList = (List<Member>) request.getAttribute("memberList");
        for (Member member : memberList) {
            out.println("<tr>");
            out.println("<td><a href=./update/" + member.getId() + ">" + member.getId() + "</td>");
            out.println("<td>" + member.getPassword() + "</td>");
            out.println("<td>" + member.getName() + "</td>");
            out.println("<td>" + member.getPassword() + "</td>");
            out.println("</tr>");
        }
    %>
    <c:forEach var="members" items="${memberList}">
        <tr>
            <td>${members.id}</td>
            <td>${members.password}</td>
            <td>${members.name}</td>
            <td>${members.email}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
<%@ page import="comments.domain.comment.CommentsDao" %>
<%@ page import="comments.domain.comment.Comments" %>
<%@ page import="comments.domain.comment.dto.CommentSaveRequestDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // request, response 사용 가능
    CommentsDao commentsDao = new CommentsDao();
    System.out.println("save.jsp");
    String name = request.getParameter("name");
    String content = request.getParameter("content");
    CommentSaveRequestDto dto = new CommentSaveRequestDto(name, content);
    Comments comments = commentsDao.save(dto);
%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
성공
<ul>
    <li>id=<%=comments.getId()%></li>
    <li>username=<%=comments.getName()%></li>
    <li>age=<%=comments.getContent()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
<%@ page import="member.domain.Member" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022-01-17
  Time: 오후 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
  <%
    Member member = (Member) request.getAttribute("member");
  %>
  <form>
    <div class="form-group">
      <label for="id">아이디</label>
      <input type="text" class="form-control"
             id = "id" value=<%=member.getId()%> readonly>
    </div>
    <div class="form-group">
      <label for="password">비밀번호</label>
      <input type="text" class="form-control"
             id = "password" value=<%=member.getPassword()%>>
    </div>
    <div class="form-group">
      <label for="name">이름</label>
      <input type="text" class="form-control"
             id = "name" value=<%=member.getName()%>>
    </div>
    <div class="form-group">
      <label for="email">이메일</label>
      <input type="text" class="form-control"
             id = "email" value=<%=member.getEmail()%>>
    </div>
  </form>
  <a href="/" role="button" class="btn btn-secondary">취소</a>
  <button type="button" class="btn btn-primary" id="btn-update">수정 완료</button>
  <button type="button" class="btn btn-primary" id="btn-delete">삭제</button>
</div>
</body>
</html>

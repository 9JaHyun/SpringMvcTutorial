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
    <title>게시글</title>
</head>
<body>
<table border="1">
    <form name="delFrm" action="${pageContext.request.contextPath}/bulletinDelete.do"
          method="post"></form>
    <form name="updFrm" action="${pageContext.request.contextPath}/bulletinUpdate.do"
          method="post"></form>
    <thead>
    <tr>
        <th>글번호</th>
        <th>글제목</th>
        <th>작성자</th>
        <th>작성일시</th>
        <th>조회수
        <th>
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
    <div class="reply">

    </div>
</table>
</body>
</html>
<script>
  let bbsId = $('#detailFrm>input[name="id"]').val();
  $.ajax({
    url: 'replySelect.do',
    data: 'id=1',
  }).done(function (result) {
    result.forEach((reply) => {
      let div = $('<div>').attr('class', 'row');
      div.append(
          $('<span>').text(replyWriter),
          $('<span>').text(replyContent),
          $('<span>').text(replyDate)
      )
    });
  })
  .fail({})
</script>

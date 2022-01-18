<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022-01-18
  Time: 오후 3:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<script>
  let input = document.getElementById("bulletinBtn");
  input.addEventListener("click", search);
    function search() {
      let request = new XMLHttpRequest();
      request.open("get", "${pageContext.request.contextPath}/bulletinSelect.do")
      request.send()
      let input = document.getElementById("bulletinSearch");

      input.innerText
    }
</script>
<body>
<input type="text" id="bulletinSearch" placeholder="검색">
<input type="button" id="bulletinBtn" value="검색" >
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
    <tbody>
        <c:forEach var="bulletin" items="${bulletinList}">
            <tr>
                <td>${bulletin.bbsId}</td>
                <td>${bulletin.bbsTitle}</td>
                <td>${bulletin.bbsWriter}</td>
                <td>${bulletin.bbsCreateDate}</td>
                <td>${bulletin.bbsHit}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<li><a href="${pageContext.request.contextPath}/bulletinForm.do">입력</a></li>
</body>
</html>

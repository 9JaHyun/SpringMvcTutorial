<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022-01-18
  Time: 오전 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <title>template.jsp</title>
    <style>
      .menu ul {
        list-style: none;
      }
      .menu li {
        display: inline-block;
        width: 100px;
        margin: 10px;
      }
    </style>
</head>
<body>
<div class="head">
    <tiles:insertAttribute name="head"></tiles:insertAttribute>
</div>
<div class="menu">
    <tiles:insertAttribute name="menu"></tiles:insertAttribute>
</div>
<div class="body">
    <tiles:insertAttribute name="body"></tiles:insertAttribute>
</div>
<div class="foot">
    <tiles:insertAttribute name="foot"></tiles:insertAttribute>
</div>
</body>
</html>

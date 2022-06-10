<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/design.css">
<%@include file="header.html" %>

	<form method="post" action="regist.jsp" >
	<input class="menu" type="submit" value="会員情報登録" name="KK01"></form>

	<form method="post" action="update.jsp" >
	<input class="menu"type="submit" value="会員情報更新" name="KK02"></form>

	<form method="post" action="delete.jsp" >
	<input  class="menu"type="submit" value="会員情報削除" name="KK03"></form>

<%@include file="footer.html" %>
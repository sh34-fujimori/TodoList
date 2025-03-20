<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>

<style>
	h1{
		color: red;
	 	font-family:"Yu Mincho";
	 	font-weight: 400;
	}
	
	p{
		font-family:"Yu Mincho";
		font-size: 20px;
	}
</style>
<%@ include file="menubar.jsp" %>
<meta charset="UTF-8">

<title>ToDoタスク管理</title>

</head>
<body style="background-color: #e9eaec;">

<p>【変更・削除】</p>
<table border="1">
<tr>
	<td style="text-align: center;"><b>タスクID</b></td>
	<td style="width: 450px;" >${ incompleteTodoList.id }</td>
</tr>
<form action="UpdateServlet" method="post">
<tr>
	<td style="text-align: center;"><b>タスク名称</b></td>
	<td ><input type="text" name="name"  value="${ incompleteTodoList.name }" style="width: 450px;">
</tr>
<tr>
	<td style="text-align: center;"><b>タスク内容</b></td>
	<td><input type="text" name="contents" value="${ incompleteTodoList.contents }" style="width: 450px;">
</tr>
<tr>
	<td style="text-align: center;"><b>タスク期限</b></td>
	<td><input type="text" style="width: 100px;" name="limitdate" value="${ incompleteTodoList.limitdate }">
</tr>
<tr>
	<td style="text-align: center;"><b>タスク担当者</b></td>
	<td><input type="text" style="width: 160px;" name="user" value="${ incompleteTodoList.user }" style="width: 450px;">
</tr>
<tr>
	<td style="text-align: center;" value="${ incompleteTodoList.status }"><b>タスク状況</b></td>
<td>
        <select id="task_status" name="status">
            <option value="0" ${incompleteTodoList.status == 0 ? 'selected' : ''}>未着手</option>
            <option value="1" ${incompleteTodoList.status == 1 ? 'selected' : ''}>着手</option>
            <option value="2" ${incompleteTodoList.status == 2 ? 'selected' : ''}>完了</option>
            <option value="3" ${incompleteTodoList.status == 3 ? 'selected' : ''}>凍結</option>
        </select>
        
    </td>
</td>
</tr>
</table>
<br>

<input type="hidden" name="id" value="${ todoList.id }">
<input type="hidden" name="currentName" value="${ todoList.name }">
<input type="hidden" name="currentContents" value="${ todoList.contents }">
<input type="hidden" name="currentLimitdate" value="${ todoList.limitdate}">
<input type="hidden" name="currentUser" value="${ todoList.user }">
<input type="hidden" name="currentStatus" value="${ todoList.status }">
<input type="submit" value="変更する">
</form>
<br>
<br>
<form action="DeleteServlet" method="post">
<input type="hidden" name="id" value="${ todoList.id }">
<input type="submit" value="削除する">
</form>
<c:if test="${not empty errorMsg}">
<p style="color:red;"><c:out value="${errorMsg}" /></p>
</c:if>
<br>
 <li><a href="TodolistServlet">タスク一覧へ</a></li>

</body>
</html>
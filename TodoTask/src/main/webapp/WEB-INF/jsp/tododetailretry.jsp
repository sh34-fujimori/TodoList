<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>

<style>
	
	p{
		font-family:"Yu Gothic";
		font-size: 20px;
		}
	.set-center{
    	    margin: 0 auto;
    		text-align: center;
    }
</style>
<%@ include file="menubar.jsp" %>
<meta charset="UTF-8">

<title>ToDoタスク管理</title>
</head>
<body style="background-color: #e9eaec;">
<div class="container mt-5" style="width: 30vw;">
<div class="set-center">
<h1>-新規登録-</h1>


<br>
 <table class="table table-striped text-center">
<tr>
	<td><b>タスクID</b></td>
	<td>(新規)</td>
</tr>
<form action="InsertServlet" method="post">
<tr>
	<td><b>タスク名</b></td>
	<td ><input type="text" name="name" value="${ incompleteTodoList.name }">
</tr>
<tr>
	<td><b>内容</b></td>
	<td><input type="text" name="contents" value="${ incompleteTodoList.contents }">
</tr>
<tr>
	<td><b>期限</b></td>
	<td> <input type="text" id="date" name="limitdate" placeholder="YYYY-MM-DD" value="${ incompleteTodoList.limitdateString }">
</tr>
<tr>
	<td><b>担当者</b></td>
	<td><input type="text" name="user" value="${ incompleteTodoList.user }">
</tr>
<tr>
	<td><b>進捗</b></td>
	<td><select id="task_status" name="status" value="${ incompleteTodoList.status }">
  <option value="0">未着手</option>
  <option value="1">着手</option>
  <option value="2">完了</option>
  <option value="3">凍結</option>
		</select>
</td>
</tr>
</table>
<br>
        <button type="submit" class="btn btn-outline-info">登録する</button>
</form>
<br>
<br>
 <button type="button" class="btn btn-outline-secondary" onclick="location.href='TodolistServlet';">タスク一覧へ</button>
 <c:if test="${not empty errorMsg}">
<p style="color:red;"><c:out value="${errorMsg}" /></p>
</c:if>
</div>
</div>
</body>
</html>
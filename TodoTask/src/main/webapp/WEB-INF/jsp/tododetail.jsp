<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>

<%@ include file="menubar.jsp" %>
<style>
	
	p{
		font-family:"Yu Mincho";
		font-size: 20px;
		}
	    .set-center{
    		text-align: center;
    }
</style>
<meta charset="UTF-8">

<title>ToDoタスク管理</title>
</head>
<body style="background-color: #e9eaec;">
    <div class="set-center">
    <h1>- Task登録 -</h1>
    </div>
<div class="container mt-5">
    <div class="d-flex justify-content-center">

    <form action="InsertServlet" method="post">
    <table class="table table-striped text-center">

<tr>
	<td><b>タスクID</b></td>
	<td>(新規)</td>
</tr>
<tr>
	<td><b>タスク名称</b></td>
	<td ><input type="text" name="name">
</tr>
<tr>
	<td><b>タスク内容</b></td>
	<td><input type="text" name="contents">
</tr>
<tr>
	<td><b>タスク期限</b></td>
	<td> <input type="text" id="date" name="limitdate" placeholder="YYYY-MM-DD">
</tr>
<tr>
	<td><b>タスク担当者</b></td>
	<td><input type="text" name="user">
</tr>
<tr>
	<td><b>タスク状況</b></td>
	<td><select id="task_status" name="status">
  <option value="0">未着手</option>
  <option value="1">着手</option>
  <option value="2">完了</option>
  <option value="3">凍結</option>
		</select>
</td>
</tr>
</table>
</div>
</div>
<br>
  <div class="set-center">
        <button type="submit" class="btn btn-outline-info">登録する</button>
    </div>
</div>
</form>
<c:if test="${not empty errorMsg}">
<p style="color:red;"><c:out value="${errorMsg}" /></p>
</c:if>
<br>
  <div class="set-center">
        <button type="button" class="btn btn-outline-secondary" onclick="location.href='TodolistServlet';">タスク一覧へ</button>
    </div>
</body>
</html>
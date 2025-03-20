<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>

<style>
	
	p{
		font-family:"Yu Mincho";
		font-size: 20px;
	}
	    .set-center{
    	    margin: 0 auto;
    		text-align: center;
    }
</style>
<%@ include file="menubar.jsp" %>
<meta charset="UTF-8">

<title>To Do List</title>

</head>
<body style="background-color: #e9eaec;">

<div class="container mt-5" style="width: 30vw;">
<div class="set-center">
<h1>- 変更・削除 -</h1>
<br>
    <table class="table table-striped text-center">
<tr>
	<td><b>タスクID</b></td>
	<td>${ todoList.id }</td>
</tr>

<form action="UpdateServlet" method="post">
<tr>
	<td><b>タスク名</b></td>
	<td ><input type="text" name="name"  value="${ todoList.name }">
</tr>
<tr>
	<td><b>内容</b></td>
	<td><input type="text" name="contents" value="${ todoList.contents }">
</tr>
<tr>
	<td><b>期限</b></td>
	<td><input type="text" name="limitdate" value="${ todoList.limitdate }">
</tr>
<tr>
	<td><b>担当者</b></td>
	<td><input type="text" name="user" value="${ todoList.user }">
</tr>
<tr>
	<td><b>進捗</b></td>
<td>
        <select id="task_status" name="status" value="${ todoList.status }">
            <option value="0" ${todoList.status == 0 ? 'selected' : ''}>未着手</option>
            <option value="1" ${todoList.status == 1 ? 'selected' : ''}>着手</option>
            <option value="2" ${todoList.status == 2 ? 'selected' : ''}>完了</option>
            <option value="3" ${todoList.status == 3 ? 'selected' : ''}>凍結</option>
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
<button type="submit" class="btn btn-outline-info">変更する</button>
</form>
<br>
<br>
<form action="DeleteServlet" method="post">
<input type="hidden" name="id" value="${ todoList.id }">

 <button type="submit" class="btn btn-dark">削除する</button>
</form>

<c:if test="${not empty errorMsg}">
<p style="color:red;"><c:out value="${errorMsg}" /></p>
</c:if>
<br>
 <button type="button" class="btn btn-outline-secondary" onclick="location.href='TodolistServlet';">タスク一覧へ</button>
</div>
</div>
</body>
</html>
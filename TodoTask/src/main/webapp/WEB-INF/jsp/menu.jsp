<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>

    .set-center{
    	    margin: 0 auto;
    		text-align: center;
    }
</style>
<%@ include file="menubar.jsp" %>
<meta charset="UTF-8">
<title>ToDoタスク</title>
</head>
<body style="background-color: #e9eaec;">
<div class="container mt-5">
<div class="set-center">
	    <br>
<h1>- MENU -</h1>
</div>
    <div class="row align-items-center" >
        <!-- 画像部分 -->
        <div class="col-md-6">
            <img src="img/todolist.png" class="img-fluid" alt="背景画像">
        </div>
        <!-- ボタン部分 -->
        <div class="col-md-6 text-start">
            <button type="button" class="btn btn-outline-info mb-4" onclick="location.href='InsertServlet';">ToDoタスク登録</button>
            <br>
            <button type="button" class="btn btn-outline-secondary mb-4" onclick="location.href='TodolistServlet';">ToDoタスク一覧</button>
            <br>
            <button type="button" class="btn btn-dark" onclick="location.href='LogoutServlet';">ログアウト</button>
        </div>
        </div>
</div>
</body>
</html>
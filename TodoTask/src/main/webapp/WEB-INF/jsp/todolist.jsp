<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<style>
	h1{
	 	color: #91b3c6;
	 	font-size: 40px;
		}
	
	th{
		text-align: center;
	
		}
	p{
		font-family:"Yu Mincho";
		font-size: 20px;
		}
		
    footer {
    		border-top: 1px solid #cccccc;
    		font-family:"Yu Mincho";
            text-align: right;
            padding: 20px;
       	 }
         .set-center{
    	    margin: 0 auto;
    		text-align: center;
    }

</style>
<%@ include file="menubar.jsp" %>
<title>To Do List</title>
</head>
<body style="background-color: #e9eaec;">
<div class="set-center">
<br>
<h1>- Task一覧 -</h1>
<br>
<div class="d-flex justify-content-center">
    <table class="table table-striped text-center" style="width: 60vw;">
        <thead>
            <tr>
                <th>　　</th><th>Task</th><th>内容</th><th>期限</th>
                <th>最終更新日</th><th>担当者</th><th>進捗</th><th>　　</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" begin="0" end="${ listLength }" step="1">
                <tr>
                    <td><c:out value="${todoTaskList.get(i).id }"/></td>
                    <td><c:out value="${todoTaskList.get(i).name }"/></td>
                    <td><c:out value="${todoTaskList.get(i).contents }"/></td>
                    <td><c:out value="${todoTaskList.get(i).limitdate }"/></td>
                    <td><c:out value="${todoTaskList.get(i).updateString }"/></td>
                    <td><c:out value="${todoTaskList.get(i).user }"/></td>
                    <td><c:out value="${todoTaskList.get(i).statusLabel }"/></td>
                    <td>
                       
                        <button type="button" class="btn btn-outline-info" onclick="location.href='UpdateServlet?id=${todoTaskList.get(i).id}';">変更・削除へ</button>
                        
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<br>
<button type="button" class="btn btn-outline-info" onclick="location.href='InsertServlet';">新規登録へ</button>
 <button type="button" class="btn btn-outline-secondary" onclick="location.href='MenuServlet';">メニューへ</button>
 <br>
 </div>
 <br>
 <br>
 <footer>ログインユーザー名：<c:out value="${ userId }"/></footer>
</body>

</html>
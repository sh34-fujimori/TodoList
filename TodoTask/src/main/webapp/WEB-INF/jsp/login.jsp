<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<style>
	     .navbar-brand.text-light{
	     				font-size: 2rem;
    					font-weight: bold;
					}
		.img-fluid{
					height: auto;
					}
</style>

<html>
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  
  <nav class="navbar navbar-expand-sm sticky-top navbar-secondary bg-secondary mt-3 mb-3">

        <a class="navbar-brand text-light">To Do List</a>
   </nav>

<meta charset="UTF-8">
<title>To Do List</title>
</head>
<body style="background-color: #e9eaec;">
    <div class="container mt-5">
        <div class="row align-items-center justify-content-center text-center">
        <br>
        <br>
            <!-- 画像部分 -->
            <div class="col">
                <img src="img/todolist.png" class="img-fluid" alt="背景画像">
            </div>

            <!-- フォーム部分 -->
            <div class="col">
                <form action="LoginServlet" method="post" class="p-4 bg-light rounded shadow" style="width: 100%; max-width: 300px;">
                    <div class="form-group mb-3">
                        <label for="userId" class="form-label">ユーザーID</label>
                        <input type="text" id="userId" name="userId" class="form-control" placeholder="ユーザーIDを入力">
                    </div>
                    <div class="form-group mb-3">
                        <label for="password" class="form-label">パスワード</label>
                        <input type="password" id="password" name="pass" class="form-control" placeholder="パスワードを入力">
                    </div>
                    <button type="submit" class="btn btn-outline-info btn-block w-100">ログイン</button>
                </form>
            </div>
        </div>
    </div>
</body>
        <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>  
</body>
</html>
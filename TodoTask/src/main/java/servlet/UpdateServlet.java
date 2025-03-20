package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import dao.TodoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TodoList;
import model.TodoLogic;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
	    int id = Integer.parseInt(request.getParameter("id"));
	    
		TodoDAO dao = new TodoDAO();
	    TodoList todoList = dao.findTask(id);
	    
		// リクエストスコープに保存
		request.setAttribute("todoList", todoList);
	    
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/todoupdate.jsp");
		dispatcher.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String contents = request.getParameter("contents");
        String limitdateString = request.getParameter("limitdate");
        String user = request.getParameter("user");
        String statusString = request.getParameter("status");
        int status;
        Date limitdate;
        
        // 変更前のデータのリクエストパラメータの取得
        String currentName = request.getParameter("currentName");
        String currentContents = request.getParameter("currentContents");
        String currentLimitdateString = request.getParameter("currentLimitdate");
        String currentUser = request.getParameter("currentUser");
        String currentStatus = request.getParameter("currentStatus");

        // エラーチェック
        TodoLogic tl = new TodoLogic();
		String nameCheck = tl.errorCheckName(name);
		String contentsCheck = tl.errorCheckContents(contents);
		String limitdateCheck = tl.errorCheckLimit(limitdateString, statusString);
		String userCheck = tl.errorCheckUser(user);
		
        // 更新日の設定
        LocalDateTime now = LocalDateTime.now();
        Timestamp update = Timestamp.valueOf(now);
		
        // タスク状況のデータ設定
        if (statusString == null || statusString.isEmpty()) {
            status = Integer.parseInt(currentStatus);
        } else {
            status = Integer.parseInt(statusString);
        }
        
        TodoDAO dao = new TodoDAO();
        String statusLabel = dao.findStatus(status);
		
		if(nameCheck.equals("OK") && contentsCheck.equals("OK")
				&& limitdateCheck .equals("OK") && userCheck.equals("OK")) {
			
	        // タスク名称が変更なしの場合のデータ設定
	        if (name == null || name.isEmpty()) {
	        	name = currentName;
	        }

	        // タスク内容が変更なしの場合のデータ設定
	        if (contents == null || contents.isEmpty()) {
	        	contents = currentContents;
	        }
	        
	        // タスク期限のデータ設定        
	        if (limitdateString == null || limitdateString.isEmpty()) {
	            limitdateString = currentLimitdateString;
	        }
	 
	        // タスク担当者のデータ設定
	        if (user == null || user .isEmpty()) {
	        	user  = currentUser;
	        }
	        
	        
	        // タスク期限のデータ設定        
	        if (limitdateString == null || limitdateString.isEmpty()) {
	            limitdate = Date.valueOf(currentLimitdateString);
	        } else {
	        	limitdate = Date.valueOf(limitdateString);
	        }


	        // TodoListオブジェクトの作成
	        TodoList todoList = new TodoList(id, name, contents, limitdate, update, user, status, statusLabel);

	        // タスクを更新
	        boolean result = dao.updateTask(todoList);

	        if (result) {
	            // 更新成功時
	        	response.sendRedirect("TodolistServlet");
	  
	        } else {
	            // 更新失敗時
	            response.sendRedirect("UpdateServlet");
	        }
			
		} else {	// 入力でエラー発生時
			
			// 記入途中のタスク情報のインスタンスを生成し、リクエストスコープに保存
			TodoList incompleteTodoList = new TodoList(id, name, contents, limitdateString, update, user, status, statusLabel);
			request.setAttribute("incompleteTodoList", incompleteTodoList);
			
			
			if(nameCheck.equals("OK") == false) {
				request.setAttribute("errorMsg", nameCheck);
			}
			if (contentsCheck.equals("OK") == false) {
				request.setAttribute("errorMsg", contentsCheck);
			}
			if (limitdateCheck.equals("OK") == false) {
				request.setAttribute("errorMsg", limitdateCheck);
			}
			if (userCheck.equals("OK") == false) {
				request.setAttribute("errorMsg", userCheck);
			}
			
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/todoupdateretry.jsp");
			dispatcher.forward(request, response);
		}
        
	}

}

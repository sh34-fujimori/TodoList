package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import dao.TodoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TodoList;
import model.TodoLogic;


@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/tododetail.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String contents = request.getParameter("contents");
		String limitdateString = request.getParameter("limitdate");
		String user = request.getParameter("user");
		String status = request.getParameter("status");
		
		TodoLogic tl = new TodoLogic();
		String nameCheck = tl.errorCheckName(name);
		String contentsCheck = tl.errorCheckContents(contents);
		String limitdateCheck = tl.errorCheckLimit(limitdateString, status);
		String userCheck = tl.errorCheckUser(user);
		
		// エラーチェック
		if(nameCheck.equals("OK") && contentsCheck.equals("OK")
				&& limitdateCheck .equals("OK") && userCheck.equals("OK")) {
		
		    // Stringをjava.util.Dateに変換
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Date utilDate = null;
	        try {
	            utilDate = dateFormat.parse(limitdateString);
	        } catch (ParseException e) {
	            e.printStackTrace();
	            response.sendRedirect("InsertServlet");
	        }
	        // java.util.Dateをjava.sql.Dateに変換
	        java.sql.Date limitdate = new java.sql.Date(utilDate.getTime());
			
			// 日時を取得
			LocalDateTime now = LocalDateTime.now();
			Timestamp update = Timestamp.valueOf(now);
			
			TodoDAO dao = new TodoDAO();
			String statusLabel = dao.findStatus(Integer.parseInt(status));
			TodoList todoList = new TodoList(0, name, contents, limitdate, update, user, Integer.parseInt(status), statusLabel);
			boolean result = dao.create(todoList);
			
			if (result) {	// タスク登録成功時
				// デバッグログ
				// System.out.println("登録できました");
				
				// リダイレクト
				response.sendRedirect("TodolistServlet");

			
			} else {	// タスク登録失敗時
				
				// デバッグログ
				// System.out.println("登録できませんでした");
				
				// リダイレクト
				response.sendRedirect("InsertServlet");
			}
			
		} else {	// 入力でエラー発生時
			
			// 日時を取得
			LocalDateTime now = LocalDateTime.now();
			Timestamp update = Timestamp.valueOf(now);
			
			TodoDAO dao = new TodoDAO();
			String statusLabel = dao.findStatus(Integer.parseInt(status));
			
			// 記入途中のタスク情報のインスタンスを生成し、リクエストスコープに保存
			TodoList incompleteTodoList = new TodoList(0, name, contents, limitdateString, update, user, Integer.parseInt(status), statusLabel);
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
			// デバッグログ
			// System.out.println("入力時エラーです");
			
			
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/tododetailretry.jsp");
			dispatcher.forward(request, response);
		}
 
	}

}

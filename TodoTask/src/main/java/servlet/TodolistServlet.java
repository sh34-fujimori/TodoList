package servlet;

import java.io.IOException;
import java.util.List;

import dao.TodoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TodoList;

/**
 * Servlet implementation class TodolistServlet
 */
@WebServlet("/TodolistServlet")
public class TodolistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TodoDAO dao = new TodoDAO();
		List<TodoList> todoTaskList = dao.getTodoList();
		
		// リクエストスコープに保存
		request.setAttribute("todoTaskList", todoTaskList);
		
		// todoTaskListの長さから１を引いた数をリクエストスコープに保存
		int listLength = todoTaskList.size() - 1;
		request.setAttribute("listLength", listLength);
		
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/todolist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

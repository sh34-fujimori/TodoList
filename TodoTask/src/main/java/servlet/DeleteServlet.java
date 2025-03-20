package servlet;

import java.io.IOException;

import dao.TodoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
	    int id = Integer.parseInt(request.getParameter("id"));
		
		TodoDAO dao = new TodoDAO();
		boolean result = dao.deleteTask(id);
		
		if (result) {
			// タスク削除成功時
	        // 更新成功時
	        response.sendRedirect("TodolistServlet");

		}
	}

}

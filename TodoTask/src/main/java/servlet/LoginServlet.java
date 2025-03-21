package servlet;

import java.io.IOException;

import dao.LoginDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Login;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");

		// ログイン処理の実行
		Login login = new Login(userId, pass);
		LoginDAO dao = new LoginDAO();
		boolean result = dao.findByLogin(login);
		
		// ログイン処理の成否によって処理を分岐
		if (result) {	// ログイン成功時
			// セッションスコープにユーザーIDを保持
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
		
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/menu.jsp");
			dispatcher.forward(request, response);
		} else {	// ログイン失敗時
			// リダイレクト
			response.sendRedirect("LoginServlet");
		}
	}

}

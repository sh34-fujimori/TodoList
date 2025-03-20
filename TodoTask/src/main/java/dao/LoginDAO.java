package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Login;

public class LoginDAO {
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/todolist";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "5356";
	
	public boolean findByLogin(Login login) {
		// JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try(Connection conn = DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){
			
		// SELECT文を準備
		String sql = "SELECT USERS_ID, USERS_PASS FROM USERS WHERE USERS_ID = ? AND USERS_PASS = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, login.getUserId());
		pStmt.setString(2, login.getPass());
		
		// SELECT文を実行し、結果表を取得
		ResultSet rs = pStmt.executeQuery();
		if(rs.next()) {
			return true;
		}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
}
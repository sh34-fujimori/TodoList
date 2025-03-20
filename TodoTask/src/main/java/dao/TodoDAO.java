package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.TodoList;

public class TodoDAO {
		private final String JDBC_URL = "jdbc:postgresql://localhost:5432/todolist";
		private final String DB_USER = "postgres";
		private final String DB_PASS = "5356";

	public List<TodoList> getTodoList() {
		List<TodoList> todoTaskList = new ArrayList<>();
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
			String sql = "SELECT TASK_ID, TASK_NAME, TASK_CONTENTS, TASK_LIMITDATE, TASK_UPDATE, TASK_USER, TASK_STATUS FROM TODO";
			PreparedStatement pStmt = conn.prepareStatement(sql);
		
			// SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
		
			while (rs.next()) {
				// タスクデータが存在したらデータを取得
				// そのタスクデータのTodoListインスタンスを生成
				int id = rs.getInt("TASK_ID");
				String name = rs.getString("TASK_NAME");
				String contents = rs.getString("TASK_CONTENTS");
				Date limitdate = rs.getDate("TASK_LIMITDATE");
				Timestamp update = rs.getTimestamp("TASK_UPDATE");
				String user = rs.getString("TASK_USER");
				int status = rs.getInt("TASK_STATUS");
				String statusLabel = findStatus(status);
				TodoList todoList = new TodoList(id, name, contents, limitdate, update, user, status, statusLabel);
				todoTaskList.add(todoList);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return todoTaskList;
	}
	
	public boolean create(TodoList todoList) {
		// JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}
		// データベースに接続
		try(Connection conn = DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){	

				
		// INSERT文の準備（idは自動連番なので指定しなくてよい）
		String sql = "INSERT INTO TODO(TASK_NAME, TASK_CONTENTS, TASK_LIMITDATE, TASK_UPDATE, TASK_USER, TASK_STATUS) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		// INSERT文の「？」に使用する値を設定してSQL文を完成
		
		pStmt.setString(1, todoList.getName());
		pStmt.setString(2, todoList.getContents());
		pStmt.setDate(3, todoList.getLimitdate() );
		pStmt.setTimestamp(4, todoList.getUpdate());
		pStmt.setString(5, todoList.getUser());
		pStmt.setInt(6, todoList.getStatus());
		
		
		// INSERT文を実行（resultには追加された行数が代入される）
		int result = pStmt.executeUpdate();
		if (result != 1) {
			return false;
		}
	} catch(SQLException e) {
		e.printStackTrace();
		return false;
	}
		
		return true;
	}

	
	public String findStatus(int status) {
		// JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException(
				"JDBCドライバを読み込めませんでした");
		}
		// データベースに接続
		try(Connection conn = DriverManager.getConnection(
			JDBC_URL, DB_USER, DB_PASS)){
	
			// statuslistのSELECT文を準備
			String sql = "SELECT TASK_LABEL FROM STATUSLIST WHERE TASK_STATUS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
	
			// INSERT文の「？」に使用する値を設定してSQL文を完成
			pStmt.setInt(1, status);
			// SELECT文を実行し、結果を取得
			ResultSet rs = pStmt.executeQuery();
			
            if(rs.next()) {
                String statusLabel = rs.getString("TASK_LABEL");
                return statusLabel.toString();
               
            }
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		 return null;
	}
	
	public boolean deleteTask(int taskId) {
		// JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException(
				"JDBCドライバを読み込めませんでした");
		}
		// データベースに接続
		try(Connection conn = DriverManager.getConnection(
			JDBC_URL, DB_USER, DB_PASS)){
	
			// statuslistのSELECT文を準備
			String sql = "DELETE FROM TODO WHERE TASK_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文の「？」に使用する値を設定してSQL文を完成
			pStmt.setInt(1, taskId);
			// SELECT文を実行し、結果を取得
			int result = pStmt.executeUpdate();
				 return result == 1;
			 
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public TodoList findTask(int taskId) {
		TodoList todoList = new TodoList();
		// JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException(
				"JDBCドライバを読み込めませんでした");
		}
		// データベースに接続
		try(Connection conn = DriverManager.getConnection(
			JDBC_URL, DB_USER, DB_PASS)){
	
			// statuslistのSELECT文を準備
			String sql = "SELECT * FROM TODO WHERE TASK_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文の「？」に使用する値を設定してSQL文を完成
			pStmt.setInt(1, taskId);
			// SELECT文を実行し、結果を取得
			ResultSet rs = pStmt.executeQuery();
            if(rs.next()) {
            	
				// タスクデータが存在したらデータを取得
				// そのタスクデータのTodoListインスタンスを生成
				String name = rs.getString("TASK_NAME");
				String contents = rs.getString("TASK_CONTENTS");
				Date limitdate = rs.getDate("TASK_LIMITDATE");
				Timestamp update = rs.getTimestamp("TASK_UPDATE");
				String user = rs.getString("TASK_USER");
				int status = rs.getInt("TASK_STATUS");
				String statusLabel = findStatus(status);
				todoList = new TodoList(taskId, name, contents, limitdate, update, user, status, statusLabel);
			}
			 return todoList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean updateTask(TodoList todoList) {
		
		// JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException(
				"JDBCドライバを読み込めませんでした");
		}
		// データベースに接続
		try(Connection conn = DriverManager.getConnection(
			JDBC_URL, DB_USER, DB_PASS)){
	
			// statuslistのSELECT文を準備
			String sql = "UPDATE TODO SET TASK_NAME = ?, TASK_CONTENTS = ?, TASK_LIMITDATE = ?, TASK_UPDATE = ?, TASK_USER = ?, TASK_STATUS = ?  WHERE TASK_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文の「？」に使用する値を設定してSQL文を完成
			pStmt.setString(1, todoList.getName());
			pStmt.setString(2, todoList.getContents());
			pStmt.setDate(3, todoList.getLimitdate());
			pStmt.setTimestamp(4, todoList.getUpdate());
			pStmt.setString(5, todoList.getUser());
			pStmt.setInt(6, todoList.getStatus());
			pStmt.setInt(7, todoList.getId());
			// SELECT文を実行し、結果を取得
			int result = pStmt.executeUpdate();
			return result == 1;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
}

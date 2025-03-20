package model;

import java.io.Serializable;

public class Login implements Serializable{
	private String userId;	// ユーザー名
	private String pass;	// パスワード
	
	public Login() {}
	public Login(String userId, String pass) {
		this.userId = userId;
		this.pass = pass;
	}
	public String getUserId() { return userId; }
	public String getPass() { return pass; }

}
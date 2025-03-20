package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TodoList implements Serializable {
	private int id;		// タスクID
	private String name;		// タスク名称
	private String contents;	// タスク内容
	private Date limitdate;		// タスク期限
	private String limitdateString;	// 文字列に変換したタスク期限
	private Timestamp update;	// 最終更新日時
	private String updateString;// 最終更新日
	private String user;		// タスク担当者
	private int status;			// タスク状況
	private String statusLabel;	// タスク状況ラベル
	
	public TodoList() {}
	public TodoList(int id, String name, String contents, Date limitdate, Timestamp update, String user, int status, String statusLabel){
		this.id = id;
		this.name = name;
		this.contents = contents;
		this.limitdate = limitdate;
	    this.update = update != null ? update : Timestamp.valueOf(LocalDateTime.now());;
	    this.updateString = updateString != null ? updateString : this.update.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    this.user = user;
	    this.status = status;
	    this.statusLabel = statusLabel;
		
	}
	
	public TodoList(int id, String name, String contents, String limitdateString, Timestamp update, String user, int status, String statusLabel){
		this.id = id;
		this.name = name;
		this.contents = contents;
		this.limitdateString = limitdateString;
	    this.update = update != null ? update : Timestamp.valueOf(LocalDateTime.now());;
	    this.updateString = updateString != null ? updateString : this.update.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    this.user = user;
	    this.status = status;
	    this.statusLabel = statusLabel;
		
	}
//  LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
	
	public int getId() { return id; }
	public String getName() { return name; }
	public String getContents() { return contents; }
	public Date getLimitdate() { return limitdate; }
	public String getLimitdateString() { return limitdateString; }
	public Timestamp getUpdate() { return update;}
	public String getUpdateString() { return updateString; }
	public String getUser() { return user; }
	public int getStatus() { return status; }
	public String getStatusLabel() { return statusLabel; }

	
	public void setId(int id) { this.id = id;}
	public void setName(String name) { this.name = name;}
	public void setContents(String contents) { this.contents = contents;}
	public void setLimitdate(Date limitdate) { this.limitdate = limitdate;}
	public void setLimitdateString(String limitdateString) { this.limitdateString = limitdateString;}
	public void setUpdate(Timestamp update) { this.update = update;}
	public void setUpdateString(String updateString) { this.updateString = updateString; }
	public void setUser(String user) { this.user = user;}
	public void setStatus(int status) { this.status = status;}
	public void setStatusLabel(String statusLabel) { this.statusLabel = statusLabel; }

}
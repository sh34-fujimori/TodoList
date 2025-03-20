package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TodoLogic {

	// タスク名称のエラーチェック
	public String errorCheckName(String name) {
		if  (name == null || name.isEmpty()) {
			return "タスク名称が空白です";
		}
		if (name.length() >= 50) {
				return "タスク名称が長すぎます";
		}
		return "OK";
	}
	
	// タスク内容のエラーチェック
	public String errorCheckContents(String contents) {
		if (contents == null || contents.isEmpty()) {
			return "タスク内容が空白です";
		}
		if (contents.length() >= 100) {
				return "タスク内容が長すぎます";
		}
		return "OK";
		
	}
	
	// タスク期限のエラーチェック
	public String errorCheckLimit(String limitdate, String status) {
		 
		
		// 空白でないかのチェック
		if (limitdate == null || limitdate.isEmpty()) {
			return "タスク期限が空白です";
		}
		
        // YYYY-MM-DD形式であるかどうかのチェック 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            formatter.parse(limitdate);
            
        } catch (DateTimeParseException e) {
            return "タスク期限の入力形式が違います";
        }
		
        // 存在しない日付でないかのチェック
		LocalDate date = null;
        try {
            date = LocalDate.parse(limitdate);
            
        } catch (DateTimeParseException e) {
            return "タスク期限が誤りです";
        }
        
        LocalDate today = LocalDate.now();
		// タスク状況が"未着手"または"着手"の時に今日より前の日付でないかのチェック
	    if ((status.equals("0") || status.equals("1")) && date.isBefore(today)) {
	        return "タスク期限が過去日付です";
		}

		return "OK";
	}
	
	// タスク内容のエラーチェック
	public String errorCheckUser(String user) {
		if (user == null || user.isEmpty()) {
			return "タスク担当者が空白です";
		}
		if (user.length() >= 20) {
				return "タスク担当者が長すぎます";
		}
			return "OK";
	}
}

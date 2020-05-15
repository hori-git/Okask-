package service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import exception.falseLogionException;
import exception.falseLogoutException;
import exception.notExistException;
import obj.Task;
import obj.UserContext;

//サービスの仕様（本来jarファイルごと分けとくべき）
public interface Service {

	//ログイン
	void login(UserContext usercontext) throws falseLogionException,notExistException ;

	//ログアウト
	void logout(HttpSession session) throws falseLogoutException;

	//ここから下は、本来基盤サービスに分けたほうが良さげ
	//Task番号採番
	String tasknum();

	//Task格納機能
	void taskstorage(Task task);

	//一覧取得
	ArrayList<Task> getList();

	//Task削除機能
	void deleteTask(String[] taskNumList);
}

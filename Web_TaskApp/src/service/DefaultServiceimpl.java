package service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import dao.DAOManager;
import exception.falseLogionException;
import exception.falseLogoutException;
import exception.notExistException;
import obj.Task;
import obj.UserContext;

public class DefaultServiceimpl extends AbstructService implements Service {

	//DAOサービスの取得
	//このクラスには、なるべく基盤機能以外は記述したくないので、インスタンスの取得メソッドは親クラスに記述
	DAOManager dao = super.getDAOManager();

	@Override
	//ログイン処理の実装(ログイン失敗、該当のユーザIDが存在しない場合は例外処理発生)
	public void login(UserContext usercontext) throws falseLogionException, notExistException {

		//確認用
		System.out.println("ログイン機能スタート");

		//パスワードチェックDAOを呼び出す
		final String dbpass = dao.passwordCheckDAO(usercontext);
		//入力されたパスワード
		final String sysinpass = usercontext.getPassword();

		//確認用
		//System.out.println(usercontext.getUserId());
		//System.out.println(usercontext.getPassword());

		//パスワードが正しいかチェック
		if((sysinpass.equals(dbpass))) {
			System.out.println("パスワード合致");
		}else {
			//正しくなければ例外処理
			throw new falseLogionException();
		}
	}

	@Override
	//ログアウト処理の実装
	public void logout(HttpSession session) throws falseLogoutException {

		//セッションの破棄
		session.invalidate();
		System.out.println("セッションの破棄を完了しました");
		System.out.println("ログアウト処理の完了");

	}

	@Override
	//タスク番号採番の実装
	public String tasknum() {

		//確認用
		System.out.println("タスク番号採番機能スタート");

		//ユニークな値を返してくれるクラス、UUIDを使用
		UUID uuid = UUID.randomUUID();
		String num = uuid.toString();

		//テスト用
		System.out.print(num);

		return num;
	}

	@Override
	//タスクの格納機能
	public void taskstorage(Task task) {

		//確認用
		System.out.println("タスク格納機能スタート");

		//タスクの要素をとりだし、DAOに渡す。
		String num = task.getTaskNum();
		Date deadline = task.getDeadline();
		String taskname = task.getTaskName();
		String content = task.getContent();
		String client = task.getClient();

		//テスト用
		//System.out.println(num);
		//System.out.println(deadline.toString());
		//System.out.println(taskname);
		//System.out.println(content);
		//System.out.println(client);

		//受け取ったタスクをDAOに渡し格納する
		dao.taskstorageDAO(num, deadline, taskname, content, client);
	}

	@Override
	//task一覧取得
	public ArrayList<Task> getList() {
		//メンバ
		ArrayList<Task> tasklist = new ArrayList<Task>();

		//一覧取得DAOを呼び出す
		ResultSet result_set = dao.getListDAO();

		//取得したResultSetから、task配列オブジェクトを生成する。（num以外）
		try {
			//タスクをリストに格納
			while(result_set.next()) {
				Task task = new Task();
					task.setTaskNum(result_set.getNString("num"));
					task.setDeadline(result_set.getDate("deadline"));
					task.setTaskname(result_set.getNString("name"));
					task.setContent(result_set.getNString("content"));
					task.setClient(result_set.getNString("client"));
				tasklist.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasklist;
	}

	@Override
	//Task削除機能
	public void deleteTask(String[] taskNumList) {
		//DAO呼び出し
		dao.deleteTaskDAO(taskNumList);

	}
}


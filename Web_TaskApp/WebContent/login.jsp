<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="shortcut icon" href="resources/image/favicon.ico">
<title>Okask!-ログイン画面-</title>
<link rel="stylesheet" href="resources/css/default.css">
<link rel="stylesheet" href="resources/css/login.css">
</head>
<body>
<header>
	<div class="header-container">
		<h1>Okask!</h1>
	</div>
</header>

<div class="contents">
	<div class="contents-container">
		<span class="label label-danger">${message}</span>
		<!-- フォーム -->
		<form action="/Web_TaskApp/LoginServlet" method="POST" id="login_form">
			<h1 class="login_title">ログインしてください</h1>
			<p class="login_line">
				<label class="textfield_label">ユーザーID</label>
				<input type="text" name="user_id" required autofocus />
			</p>
			<p class="login_line">
				<label class="textfield_label">パスワード</label>
				<input type="password" name="password" required />
			</p>
			<p class="login_line">
				<input type="submit" value="Sign Up" />
			</p>
				<input type="hidden" name="functionStr" value="login" />
		</form>
	</div>
</div>
<footer>
	<div class="footer-container">
		<p><small>©2020 Okask! Corporation(仮)</small></p>
	</div>
</footer>
</body>
</html>
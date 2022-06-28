<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>BookMall(LOGIN)</title>
<style>
	body {
	background-color: rgba(194, 222, 209);
	width:100%;
	margin-top: 250px;
	text-align: center;
	}
	#logo {
 	text-decoration: none;
 	color: black;
	font-size:28px;
}
</style>
</head>
<body>
		<h1><a id="logo" href="/">책방</a></h1>
		<br>
		<h2 style="font-size: 20">간편 로그인</h2>
		<div id="naver_id_login">
			<a href="${url}">
			<img width="300" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/>
			</a>
		</div>
</body>
</html>

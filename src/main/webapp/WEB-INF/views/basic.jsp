<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/basic.css" />
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="js/categoryList.js"></script>
</head>
<body>
	<div id="container">
		<div id="sidenav">
			<div id="side_top">
				<button>로그인</button>
				<button>장바구니</button>
				<button>주문내역</button>
				</div>
			<div id="side_bottom">
				<ul id="sideList"></ul>
			</div>
		</div>
		<div id="top">
			<h1><a href="/">책방</a></h1>
			<div id="top_bottom">
				<select name="searchOption" id="searchOption" >
				  <option value="all">통합검색</option>
				  <option value="title">제목검색</option>
				  <option value="auth">저자검색</option>
			  </select>
			  <input type="text" id="keyword" autocomplete='off' placeholder="검색" />
			  <input type="button" class="btnOk" value="검색" />
			</div>
		</div>
		<div id="main">
			<h2>main입니다</h2>
		</div>
	</div>
</body>
</html>
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
<style>
	#main {
		margin: 0 auto;
		width: 1200px;
	}
	
	#div_submit {
	margin: 20px auto;
	text-align:right;
    white-space: nowrap;
	}
	
	#tbl_submit {
		width: 100%;
	}
	
	#main
	#tbl_list {
		border: 3px solid #CCCCCC;
		border-collapse : collapse;
		width: 100%;
	}
	#tbl_list td, th {
		text-align: center;
		border: 1px solid #CCCCCC;
		border-collapse : collapse;
		padding : 8px;
	}
	#tbl_list th { 
		background-color: #EAEAEA;
	}
	#period a {
		text-decoration: none;
		color: black;
		border: 1px solid black;
		background-color: #CCCCCC;
		padding-left: 4px;
		padding-right: 4px;
	}
	#period {
		margin-left: 10px;
		margin-right: 10px;
	}
</style>
<script>

		// 이전 기간 날짜 구하는 함수
		function search_order_history(date) {
			let forms      = document.getElementById('listForm');
			let startel    = document.querySelector('[name=start_date]');
			let endel      = document.querySelector('[name=end_date]');
			let now        = new Date();
			let year       = now.getFullYear();
			let month      = now.getMonth() + 1;
			let day        = now.getDate();
			let lastweek   = new Date(+new Date(year, month-1, day-7) + 3240 * 10000).toISOString().split("T")[0];
			let lastmonth  = new Date(+new Date(year, month-2, day) + 3240 * 10000).toISOString().split("T")[0];
			let last3month = new Date(+new Date(year, month-4, day) + 3240 * 10000).toISOString().split("T")[0];
			day = day + 1;
			
			if (day < 10) {
				day = '0' + day;				
			}
			if (month < 10) {
				month = '0' + month;
			} 
			
			let today    = year + '-' + month + '-' + day;
			endel.value   = today;
			
			switch (date) {
			case 'one_week'   : startel.value = lastweek;   break;
			case 'one_month'  : startel.value = lastmonth;  break;
			case 'three_month': startel.value = last3month; break;
			}
			
			forms.action = "/Mypage/OrderList";
			forms.submit();
		};
		
		$(function() {   
	         $('#listForm').on('submit', function(){   
	               
	            let startel    = document.querySelector('[name=start_date]').value;   
	            let endel      = document.querySelector('[name=end_date]').value;   
	               
	            if ( startel == '' || endel == '') {   
	               alert('기간을 모두 선택해주세요');   
	               return false;   
	            }   
	         })   
	            
	      })

</script>
</head>
<body>
	<div id="container">
		<div id="sidenav">
			<div id="side_top"><%@ include file="/WEB-INF/include/sideTop.jsp" %></div>
			<div id="side_bottom">
				<ul id="sideList">
					<li><a href="/Mypage/Cart">장바구니</a></li>
					<li><a href="/Mypage/Orderlist" class="active">주문내역</a></li>
				</ul>
			</div>
		</div>
		<div id="top">
			<h1><a href="/">책방</a></h1>
			<div id="top_bottom"><%@ include file="/WEB-INF/include/topBottom.jsp" %></div>
		</div>
		<div id="main">
			<h2 class="h3">${username} 주문 배송 조회</h2><hr />
			<div id="div_submit">
				<form action="/Mypage/OrderList" method="post" id="listForm">
					<input type="date" size="4" name="start_date" value="" /> ~ 
					<input type="date" size="4" name="end_date" value="" style="display:inline"/>
					<input type="submit" value="검색" />
					<a href="javascript:search_order_history('one_week')">1주일</a>
					<a href="javascript:search_order_history('one_month')">1개월</a>
					<a href="javascript:search_order_history('three_month')">3개월</a>
					<a href="/Mypage/OrderList">전체</a>
				</form>
			</div>
			<!-- end "div_submit" -->
			<div  id="div_list">
				<c:choose>
					<c:when test="${list != null}">
						<table id="tbl_list">
							<tr>
								<th style="width:80px;">주문번호</th>
								<th>주문일자</th>
								<th>상품명</th>
								<th>결제금액</th>
							</tr>
							<c:forEach var="list" items="${ list }">
								<tr>
									<td>${ list.order_Id }</td>
									<td>${ list.order_Date }</td>
									<td>${ list.bookName }</td>
									<td>${ list.payment }</td>
								</tr>
							</c:forEach>
						</table>
					</c:when>
				</c:choose>
			</div>
			<!-- end -->
		</div>
		<!-- end main -->
	</div>
</body>
</html>
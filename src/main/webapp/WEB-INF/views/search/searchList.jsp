<%@page import="com.bookroom.book.vo.BookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/basic.css" />
<link rel="stylesheet" href="/css/searchList.css" />
<link rel="stylesheet" href="/css/pagination.css" />
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/js/categoryList.js"></script>
<script>
	$(function(){
		// 선택된 카테고리 찾기
		$("#sideList li a").each(function(index, item){
			if(("${keyword}" == item.id) && ("${searchOption}" == "catg")){
				$("#searchKeyword").html('"' + item.innerHTML + '"');
				item.className = "active";
			}
		});
		
		// option 선택 반영
		$("#displayOption").val("${display}").prop("selected", true);
		$("#sortOption").val("${sortOption}").prop("selected", true);
		
		// pagingBar
		var pageNum = ${pageNum};
		var total = ${total};
		var display = ${display};
		var totalPage = Math.ceil(total / display);
		var pagingHTML = '<button class="btn pagingBtn" onclick="pagingBtn(this)"><<</button>';
		for(var i = parseInt((pageNum - 1) / 10) * 10 + 1; i <= (parseInt((pageNum - 1) / 10) + 1) * 10; i++){
			if(i > totalPage || display * i > 1000){
				break;
			}
			if(pageNum == i){
				pagingHTML += '<button class="btn pagingBtn active" onclick="pagingBtn(this)">' + i + '</button>';
			}else{
				pagingHTML += '<button class="btn pagingBtn" onclick="pagingBtn(this)">' + i + '</button>';
			}
		}
		pagingHTML += '<button class="btn pagingBtn" onclick="pagingBtn(this)">>></button>';
		$("#pagingBar").append(pagingHTML);
	});
	
	// pagingBar Btn onclick()
	function pagingBtn(btn) {
		$("#optionForm input").remove();
		var pageGo = btn.innerHTML;
		if(pageGo == "&lt;&lt;"){
			pageGo = parseInt((${pageNum} - 11) / 10) * 10 + 1;
			if(pageGo < 1){
				pageGo = 1;
			}
		}else if(pageGo == "&gt;&gt;"){
			pageGo = parseInt((${pageNum} + 9) / 10) * 10 + 1;
			if(pageGo > ${total} || ${display} * pageGo > 1000){
				alert('마지막 페이지 입니다.');
				return;
			}
		}
		var optionForm = $("#optionForm");
		var html = $("<input type='hidden' name='pageNum' value=" + pageGo + " />");
		optionForm.append(html);
		optionFormSubmit();
	}
	
	// sort, display 옵션 선택 시 동작
	function optionFormSubmit() {
		var optionForm = $("#optionForm");
		var html = $("<input type='hidden' name='searchOption' value=${searchOption} />")
		optionForm.append(html);			
		html = $("<input type='hidden' name='keyword' value=${keyword} />")
		optionForm.append(html);			
		
		optionForm.submit();
	}
	
	// 해당 책 정보(BookVo) 전달
	function viewSubmit() {
		$("#prodform").submit();
	}
</script>
</head>
<body>
	<div id="container">
		<div id="sidenav">
			<div id="side_top"><%@ include file="/WEB-INF/include/sideTop.jsp" %></div>
			<div id="side_bottom">
				<ul id="sideList"></ul>
			</div>
		</div>
		<div id="top">
			<h1><a href="/">책방</a></h1>
			<div id="top_bottom"><%@ include file="/WEB-INF/include/topBottom.jsp" %></div>
		</div>
		<div id="main">
			<!-- mainHeader -->
			<div id="mainHeader">
			<h2 id="searchKeyword" style="display: inline">"${keyword}"</h2>
			<p style="display: inline">검색내용에 대한 검색결과</p>
			<!-- -    정렬기능 : 정확도순, 신상품순, 최저가순 클릭시 정렬 -->
			<form action="/Search" id="optionForm" style="display: inline;">
				<select name="sortOption" id="sortOption" onchange="optionFormSubmit()">
					<option value="sim">유사도순</option>
					<option value="date">출간일순</option>
					<option value="count">판매량순</option>
				</select>
				<select name="display" id="displayOption" onchange="optionFormSubmit()">
				 <option value="10">10개</option>
				 <option value="20">20개</option>
				 <option value="30">30개</option>
				 <option value="40">40개</option>
				</select>
			</form>
			</div>
			<!-- end mainHeader -->
			<!-- bookList  -->
			<div id="bookListBody">
				<ul id="bookList">
				<c:forEach var="book" items="${bookList}">
					<li>
						<div class="book">
								<p class="book_img"><a href="/Search/Prod?isbn=${book.isbn}"><img src="${ book.image }" alt="book_img" class="btn"/></a></p>
								<div class="book_info">
									<p class="book_name"><a href="/Search/Prod?isbn=${book.isbn}" class="btn">${book.title}</a></p>
									<p class="book_auth">${book.author }</p>
									<p class="book_pub">${book.publisher }</p>
									<p class="book_price">${book.price } 원</p>
								</div>
						</div>
					</li>
				</c:forEach>
				</ul>
			</div>
			<!-- end bookList  -->
			<div id="pagingBar" ></div>
		</div>
	</div>
</body>
</html>
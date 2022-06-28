<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<script>
$(function(){
	// 로그인버튼 관련
	if("${username}" != "") {
		$("#login").attr("href", "/Login/Logout");
		$("#login button").html("로그아웃");
	}
});
</script>
<a href="/Login" id="login"><button>로그인</button></a>
<a href="/Mypage/Cart"><button>장바구니</button></a>
<a href="/Mypage/OrderList"><button>주문내역</button></a>
<c:choose>
	<c:when test="${username != null}">
		<div id="printname"  style="text-align:center; color: white;">${username}님</div>
   </c:when>
</c:choose>
<%@page import="com.bookroom.book.vo.BookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/basic.css" />
<title>책 상세보기</title>
<style>
   #main {
      margin: 0 auto;
      width: 1000px;
   }
   /* 책 이미지 박스 */
   .picturebox {
      width: 300px;
   }
   
   .picture{
      width:80%;
   }
   td, th {
      border-bottom: 2px dotted #DDD;
   }
   
   tr, td{
      font-size: 21px;
      padding: 6px;
      text-align: left;
   }
   
   /* 수량 표시 텍스트 */
   #amount {
      width : 50px;
      height : 30px;
      margin : 0px 10px 0px 10px;
      text-align: center;
   }
   
   /* 장바구니, 구매 버튼 */
   #bookinfo {
      display:flex;
      flex-wrap: nowrap;
      justify-content: center;
      margin-bottom: 60px;
   }
   
   #bookinfo * {
      margin: 20px;
   }
   
   #buttons {
      text-align: center;
      margin-bottom: 40px;
   }
   
   .book_a {
        text-align: center;
       color: black;
       background-color: #C2DED1;
       width : 300px;
       padding: 10px 150px;
       margin: 10px 30px;
       text-decoration: none;
       transition: background-color .3s;
       border: 1px solid #ffffff;
       border-radius: 5px;
       font-size: 23px;
       font-weight: bold;
   }
   
/*popup*/
.popup_layer {position:fixed;top:0;left:0;z-index: 10000; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.4); }
.popup_box{position: relative;top:50%;left:50%; overflow: auto; height: 375px; width:600px;transform:translate(-50%, -50%);z-index:1002;box-sizing:border-box;background:#fff;box-shadow: 2px 5px 10px 0px rgba(0,0,0,0.35);-webkit-box-shadow: 2px 5px 10px 0px rgba(0,0,0,0.35);-moz-box-shadow: 2px 5px 10px 0px rgba(0,0,0,0.35);}
.popup_box .popup_cont {padding:50px;line-height:1.4rem;font-size:30px; text-align: center; }
.popup_btn { position:relative; height:70px; margin-top:80px;}
.popup_btn a {position:absolute; left:33px; width:250px; background:#3498DB; text-align: center;  padding-top: 24px; padding-bottom:24px; border-radius: 10px; font-size:20px; color:white;}
/*popup end*/
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/js/categoryList.js"></script>
<script>
   // 책 상세 페이지 수량 조절 함수
   function count(type)  {

     const resultElement = document.getElementById('amount');
     let   num           = resultElement.value;
     
     if(type == 1) {
       num = parseInt(num) + 1;
     }else if(type == -1)  {
       num = parseInt(num) - 1;
          if(num <=0) {
            num = 0;             
          }
     } 

     resultElement.value = num;
   }
   
   function move(String) {
      
      const resultElement = document.getElementById('amount');
      let   num           = resultElement.value;
      
      switch (String) {
      case 'cart'    : 
            if ('${username}'== null || '${username}' == "" ) {
               location.href = "/Login";  break;
            } else { 
                   let params = '${book.isbn}';
                   $.ajax({
                       url: '/Mypage/InsertCart',
                       type: 'POST',
                       data: {isbn : params, quantity : num},
                       contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                       dataType: 'json',
                       success: function (res) {
                       }
                   });
               document.getElementById("popup_layer").style.display = "block";
               break;
            } 
         case 'buy'     : location.href = "/Mypage/Pay?isbn=${book.isbn}&quantity=" + num; break;
         case 'confirm' : location.href = "/Mypage/Cart";   break;
      }
   }

   //팝업 닫기
   function closePop() {
       document.getElementById("popup_layer").style.display = "none";
   }
   
</script>
<style>
   .active {background-color: yellow;}
</style>
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
      <div id="bookinfo">
            <div class="picturebox">
                  <img class="picture" src="${book.image}">
            </div>
            <div class="itembox">
               <table>
                  <tr>
                     <td colspan="2" style="font-weight: bold;">${book.title}</td>
                  </tr>
                  <tr>
                     <td>저자</td>
                     <td>${book.author}</td>
                  </tr>
                  <tr>
                     <td>출판사</td>
                     <td>${book.publisher}</td>
                  </tr>
                  <tr>
                     <td>금액</td>
                     <td>${book.price}</td>
                  </tr>
                  <tr>
                     <td>수량</td>
                     <td><input type="button" onclick="count(-1)" value="-" />                  
                         <input type="number" name="amount" id="amount" value="1" />
                         <input type="button" onclick="count(1)" value="+" />
                     </td>
                  </tr>
               </table>
            </div>
            </div>
            <div id="buttons">
               <a class="book_a" href="javascript:void(0)" onclick="move('cart')">장바구니</a>
               <a class="book_a" href="javascript:void(0)" onclick="move('buy')">바로구매</a>
            </div>
      </div>
   </div>
   
   <!-- 장바구니 팝업-->
   <div class="popup">
      <div class="popup_layer" id="popup_layer" style="display: none;">
         <div class="popup_box">
              <div class="popup_cont">
               <h2>상품이 잘 담겼어요</h2>
            </div>
   
            <div class="popup_btn">
               <a onclick="move('confirm')">장바구니로 이동</a>
               <a style="left:317px;" href="javascript:closePop();">닫기</a>
            </div>
         </div>
      </div>
   </div>
</body>
</html>
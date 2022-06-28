<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
$(function(){
		// 검색창 submit 제한
		$("#searchForm").submit(function(event){
			var keyword = $("#keyword").val();
			if(keyword == ""){
				alert("검색어를 입력하세요.");
				event.preventDefault();
				return;
			}
		});
	});
</script>
<form action="/Search" id="searchForm">
	<select name="searchOption" >
	  <option value="all">통합검색</option>
	  <option value="title">제목검색</option>
	  <option value="auth">저자검색</option>
  </select>
  <input type="text" name="keyword" id="keyword" autocomplete='off' placeholder="검색" />
  <input type="submit" class="btnOk" value="검색" />
</form>
<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet"
				href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
		<title>Error Page</title>
	</head>
	
	<body style="padding : 15px">
		<form class="pure-form" method="get" action="${location }">
				 <fieldset>
				 	<legend>錯誤頁面</legend>
				 	返回頁面位置 : ${location }<p />
				 	錯誤訊息內容 : ${ex }<p />
				 </fieldset>
				 <button type="submit" class="pure-button pure-button-primary">返回</button>
		</form>
	</body>
	
</html>
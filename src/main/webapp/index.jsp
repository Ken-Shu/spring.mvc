<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	Index Page <%=new Date() %>
	<h2>Session 08</h2>
	<ol>
		<li><a href="./mvc/hello/welcome">welcome</a></li>
		<li><a href="./mvc/hello/sayhi?name=John&age=18">sayhi</a></li>
		<li><a href="./mvc/hello/bmi?height=170&weight=60">getbmi</a></li>
		<li><a href="./mvc/hello/age?age=17&age=90&age=60">age</a></li>
		<li><a href="./mvc/hello/symbol?symbol=2330.TW&symbol=2317.TW">symbol</a></li>
	</ol>
</body>
</html>
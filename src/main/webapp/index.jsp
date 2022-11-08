<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body bgcolor="#FFAAD5">
	Index Page <%=new Date() %>
	<h2>Session 08</h2>
	<ol>
		<li><a href="./mvc/hello/welcome">welcome</a></li>
		<li><a href="./mvc/hello/sayhi?name=John&age=18">sayhi</a></li>
		<li><a href="./mvc/hello/bmi?height=170&weight=60">getbmi</a></li>
		<li><a href="./mvc/hello/age?age=17&age=90&age=60">age</a></li>
		<li><a href="./mvc/hello/symbol?symbol=2330.TW&symbol=2317.TW">symbol</a></li>
		<li><a href="./mvc/hello/person?name=Tom&score=90.5&age=18&pass=true">person</a></li>
		<li><a href="./mvc/data/case1">data case1</a></li>
		<li><a href="./mvc/model/case1">model case1</a></li>
		<li><a href="./mvc/model/case3">redirect(重定向): case3</a></li>
		<li><a href="./mvc/lotto/">lotto</a></li>
		<li><a href="./mvc/user/">User</a></li>
		<li><a href="./mvc/person/">Person Form</a></li>
		<li><a href="./mvc/mystock/">MyStock</a></li>
		<li><a href="./mvc/jdbc/employee/">Employee And Job</a></li>
		<li><a href="./mvc/session13/div/">Div</a></li>
		<li><a href="./mvc/session13/book_author/">book_author</a></li>
	</ol>
</body>
</html>
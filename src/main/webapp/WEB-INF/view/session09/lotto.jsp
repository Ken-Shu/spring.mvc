<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>

<html>

	<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@2.1.0/build/pure-min.css">
		<meta charset="UTF-8">
		<title>Lotto Page</title>
		
		<style type="text/css">
		<!-- table 表內的文字內容置中 -->
			td , th {
				text-align: center;
			}
		</style>
		
	</head>
	
	<body style="padding: 15px">
		<button type="button" 
				onclick="window.location.href='${ pageContext.request.contextPath }/mvc/lotto/get';"
				class="pure-button pure-button-primary">
				取得 Lotto 539 電腦選號
				</button>
				<p />
				最新 Lotto 資料 : ${ lotto }
				<p />
				
				統計每一個號碼出現的次數:
		<button type="button" 
				onclick="window.location.href='${ pageContext.request.contextPath }/mvc/lotto/stat';"
				class="pure-button pure-button-primary">
				統計運算
				</button>
				<p />
				
				<table class="pure-table pure-table-bordered">
					<thead>
						<tr>
							<th style="background-color:blue"><span style="color:white;">號碼</span></th>
							<c:forEach var="s" items="${stat}">
								<th style="background-color:blue"><span style="color:white;">${s.key }</span></th>
							</c:forEach>
						</tr>	
					</thead>
					
					<tbody>
						<tr>
							<td>次數</td>
							<c:forEach var="s" items="${stat}">
								<th>${s.value }</th>
							</c:forEach>
						</tr>
					</tbody>
				</table>
				<p />
		Lotto 歷史紀錄 : <p />
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr>
					<th>#</th><th>號碼 1</th><th>號碼 2</th><th>號碼 3</th><th>號碼 4</th><th>號碼 5</th><th>更新 (Update)</th><th>刪除 (Delete)</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach varStatus="status" var="lotto" items="${ lottos }">
				<tr>
					<td style="background-color:red">${ status.index }</td>
					<c:forEach varStatus="item" var="num" items="${ lotto }">
						<td onclick="window.location.href='${ pageContext.request.contextPath }/mvc/lotto/change/${status.index }/${item.index }';" style="cursor: pointer;" title="按我一下可以修改">${ num }</td>
					</c:forEach>
					<td><button type="button" 
						onclick="window.location.href='${ pageContext.request.contextPath }/mvc/lotto/update/${status.index }';"
						class="pure-button pure-button-primary">
						更新 (Update)
						</button></td>
					<td><button type="button" 
						onclick="window.location.href='${ pageContext.request.contextPath }/mvc/lotto/delete/${status.index }';"
						class="pure-button pure-button-primary">
						刪除 (Delete)
						</button></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
	
</html>
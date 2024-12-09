<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="history.*, java.util.*" %>
<% 
	HistoryService historyService = new HistoryService();
	List<History> historyList = historyService.readAllHistories();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<style>
        table {
            border-collapse: collapse;
            width: 100%;
            text-align: center;
        }

        table td,
        table th {
            border: 1px solid #ddd;
            padding: 15px;
        }

        table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        table tr:hover {
            background-color: #ddd;
        }

        table th {
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: #04AA6D;
            color: white;
        }

        div {
            margin-bottom: 15px;
            position: relative;
        }

        div>a:not(:last-child) {
            margin-right: 14px;
        }

        div>a:not(:last-child)::after {
            content: "";
            position: absolute;
            top: 2px;
            bottom: 2px;
            width: 2px;
            margin-left: 7px;
            background-color: grey;
        }

        form {
            margin-bottom: 10px;
        }
</style>
</head>
<body>

	<h1>와이파이 정보 구하기</h1>
    <div>
        <a href="../index.jsp">홈</a>
        <a href="history.jsp">위치 히스토리 목록</a>
        <a href="../load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
        <a href="../bookmark/bookmark.jsp">즐겨 찾기 보기</a>
        <a href="../bookmarkGroup/bookmarkGroup.jsp">즐겨 찾기 그룹 관리</a>
    </div>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
		<% for (History history : historyList) { %>
			<tr>
				<td><%= history.getHistoryId() %></td>
				<td><%= history.getMyLnt() %></td>
				<td><%= history.getMyLat() %></td>
				<td><%= history.getSearchDate() %></td>
				<td><button onclick="location.href='deleteHistory.jsp?historyId=<%= history.getHistoryId() %>'">삭제</button></td>
			</tr>
		<% } %>
		</tbody>
	</table>
</body>
</html>
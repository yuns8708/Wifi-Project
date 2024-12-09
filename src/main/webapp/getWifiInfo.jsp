<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="history.*, java.util.*" %>
<%
	HistoryService historyService = new HistoryService();
	String myLnt = request.getParameter("LNT");
	String myLat = request.getParameter("LAT");
	
	History history = new History(Double.parseDouble(myLnt), Double.parseDouble(myLat));
	historyService.createHistory(history);
	response.sendRedirect("index.jsp?lnt=" + myLnt + "&lat=" + myLat);
%>
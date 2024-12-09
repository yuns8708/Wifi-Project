<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="history.*, java.util.*" %>
<%
	HistoryService historyService = new HistoryService();
	String historyId = request.getParameter("historyId");
	
	historyService.deleteHistory(Integer.parseInt(historyId));
	response.sendRedirect("history.jsp");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bookmark.*, java.util.*, wifi.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	BookmarkService bookmarkService = new BookmarkService();

	int bookmarkGroupId = Integer.parseInt(request.getParameter("bookmarkGroupId"));
	String mgrNo = request.getParameter("mgrNo");

	bookmarkService.createBookmark(bookmarkGroupId, mgrNo);
	response.sendRedirect("bookmark.jsp");
%>
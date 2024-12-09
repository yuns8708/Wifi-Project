<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bookmark.*, java.util.*" %>
<%
	BookmarkService bookmarkService = new BookmarkService();
	int bookmarkId = Integer.parseInt(request.getParameter("bookmarkId"));
	
	bookmarkService.deleteBookmark(bookmarkId);
	response.sendRedirect("bookmark.jsp");
%>
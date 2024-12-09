<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bookmark.*, java.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
	String bookmarkName = request.getParameter("bookmark-name");
	String bookmarkOrder = request.getParameter("bookmark-order");
	BookmarkGroup bookmarkGroup = new BookmarkGroup(bookmarkName, Integer.parseInt(bookmarkOrder));
	
	// bookmarkGroupService.createBookmarkGroupTable();
	bookmarkGroupService.createBookmarkGroup(bookmarkGroup);
	response.sendRedirect("bookmarkGroup.jsp");
%>
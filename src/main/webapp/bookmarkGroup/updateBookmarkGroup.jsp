<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bookmark.*, java.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
	int bookmarkGroupId = Integer.parseInt(request.getParameter("bookmarkGroupId"));
	String bookmarkGroupName = request.getParameter("bookmark-name");
	String bookmarkGroupOrder = request.getParameter("bookmark-order");
	
	bookmarkGroupService.updateBookmarkGroup(bookmarkGroupId, bookmarkGroupName, Integer.parseInt(bookmarkGroupOrder));
	bookmarkGroupService.updateBookmark(bookmarkGroupId, bookmarkGroupName);

	response.sendRedirect("bookmarkGroup.jsp");
%>
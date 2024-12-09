<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bookmark.*, java.util.*" %>
<%
	BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
	BookmarkService bookmarkService = new BookmarkService();
	
	int bookmarkGroupId = Integer.parseInt(request.getParameter("bookmarkGroupId"));
	
	bookmarkGroupService.deleteBookmarkGroup(bookmarkGroupId);
	bookmarkService.deleteBookmarkByBookmarkGroupId(bookmarkGroupId);
	response.sendRedirect("bookmarkGroup.jsp");
%>
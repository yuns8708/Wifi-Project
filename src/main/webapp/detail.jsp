<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, wifi.*, bookmark.*" %>
<% 
	String mgrNo = request.getParameter("mgrNo");
	WifiInfoService wifiInfoService = new WifiInfoService();
	WifiInfo wifiInfo = wifiInfoService.readWifiInfoByMgrNo(mgrNo);
		
	BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
	List<BookmarkGroup> bookmarkGroupList = bookmarkGroupService.readAllBookmarkGroups();
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
        <a href="index.jsp">홈</a>
        <a href="history/history.jsp">위치 히스토리 목록</a>
        <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
        <a href="bookmark/bookmark.jsp">즐겨 찾기 보기</a>
        <a href="bookmarkGroup/bookmarkGroup.jsp">즐겨 찾기 그룹 관리</a>
    </div>

    <form action="bookmark/createBookmark.jsp?mgrNo=<%= wifiInfo.getX_SWIFI_MGR_NO() %>" method="post">
    	<select name="bookmarkGroupId">
   		<% for (BookmarkGroup bookmarkGroup : bookmarkGroupList) { %>
    		<option value="<%= bookmarkGroup.getBookmarkGroupId() %>"><%= bookmarkGroup.getBookmarkGroupName() %></option>
    	<% } %>
        </select>
        <button type="submit">북마크 추가하기</button>
    </form>

    <table>
        <tbody>
            <tr>
                <th>거리(Km)</th>
                <td><%= wifiInfo.getDistance() %></td>
            </tr>
            <tr>
                <th>관리번호</th>
                <td><%= wifiInfo.getX_SWIFI_MGR_NO() %></td>
            </tr>
            <tr>
                <th>자치구</th>
                <td><%= wifiInfo.getX_SWIFI_WRDOFC() %></td>
            </tr>
            <tr>
                <th>와이파이명</th>
                <td><%= wifiInfo.getX_SWIFI_MAIN_NM() %></td>
            </tr>
            <tr>
                <th>도로명주소</th>
                <td><%= wifiInfo.getX_SWIFI_ADRES1() %></td>
            </tr>
            <tr>
                <th>상세주소</th>
                <td><%= wifiInfo.getX_SWIFI_ADRES2() %></td>
            </tr>
            <tr>
                <th>설치위치(층)</th>
                <td><%= wifiInfo.getX_SWIFI_INSTL_FLOOR() %></td>
            </tr>
            <tr>
                <th>설치유형</th>
                <td><%= wifiInfo.getX_SWIFI_INSTL_TY() %></td>
            </tr>
            <tr>
                <th>설치기관</th>
                <td><%= wifiInfo.getX_SWIFI_INSTL_MBY() %></td>
            </tr>
            <tr>
                <th>서비스구분</th>
                <td><%= wifiInfo.getX_SWIFI_SVC_SE() %></td>
            </tr>
            <tr>
                <th>망종류</th>
                <td><%= wifiInfo.getX_SWIFI_CMCWR() %></td>
            </tr>
            <tr>
                <th>설치년도</th>
                <td><%= wifiInfo.getX_SWIFI_CNSTC_YEAR() %></td>
            </tr>
            <tr>
                <th>실내외구분</th>
                <td><%= wifiInfo.getX_SWIFI_INOUT_DOOR() %></td>
            </tr>
            <tr>
                <th>WIFI접속환경</th>
                <td><%= wifiInfo.getX_SWIFI_REMARS3() %></td>
            </tr>
            <tr>
                <th>X좌표</th>
                <td><%= wifiInfo.getLAT() %></td>
            </tr>
            <tr>
                <th>Y좌표</th>
                <td><%= wifiInfo.getLNT() %></td>
            </tr>
            <tr>
                <th>작업일자</th>
                <td><%= wifiInfo.getWORK_DTTM() %></td>
            </tr>
        </tbody>
    </table>

</body>
</html>
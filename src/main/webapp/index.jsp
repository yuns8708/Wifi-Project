<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="history.*, bookmark.*, java.util.*, wifi.*, data.*" %>
<% 
	HistoryService historyService = new HistoryService();
	String myLnt = null;
	String myLat = null;
	WifiInfoService wifiInfoService = new WifiInfoService();
	List<WifiInfo> wifiInfoList = null;
	
	if (request.getParameter("lnt") != null) {
		myLnt = request.getParameter("lnt");
		myLat = request.getParameter("lat");

		wifiInfoList = wifiInfoService.readAllWifiInfos();
		for (WifiInfo wifiInfo : wifiInfoList) {
			double distance = wifiInfoService.getDistance(Double.parseDouble(myLnt), Double.parseDouble(myLat), Double.parseDouble(wifiInfo.getLAT()), Double.parseDouble(wifiInfo.getLNT()));
			wifiInfoService.updateWifiDistance(distance, wifiInfo.getX_SWIFI_MGR_NO());
		}
		wifiInfoList = wifiInfoService.readCloseWifiInfos();
	}	
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <script>
        window.onload = function () {
            const getLocationButton = document.getElementById("location-btn");
            getLocationButton.addEventListener('click', () => {
                navigator.geolocation.getCurrentPosition(function (position) {
                    const latitude = position.coords.latitude;
                    const longitude = position.coords.longitude;

                    document.querySelector('input[name="LNT"]').value = latitude;
                    document.querySelector('input[name="LAT"]').value = longitude;
                });
            });
        };
    </script>
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

    <form action="getWifiInfo.jsp" method="post">
    <% if (request.getParameter("lnt") != null) { %>
    	LNT: <input type="text" name="LNT" value="<%= myLnt %>"> ,
        LAT: <input type="text" name="LAT" value="<%= myLat %>">
    <% } else { %>
    	LNT: <input type="text" name="LNT"> ,
        LAT: <input type="text" name="LAT">
    <% } %>
        <button type="button" id="location-btn">내 위치 가져오기</button>
        <button type="submit">근처 WIFI 정보 보기</button>
    </form>

    <table>
        <thead>
            <tr>
                <th>거리(Km)</th>
                <th>관리번호</th>
                <th>자치구</th>
                <th>와이파이명</th>
                <th>도로명주소</th>
                <th>상세주소</th>
                <th>설치위치(층)</th>
                <th>설치유형</th>
                <th>설치기관</th>
                <th>서비스구분</th>
                <th>망종류</th>
                <th>설치년도</th>
                <th>실내외구분</th>
                <th>WIFI접속환경</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>작업일자</th>
            </tr>
        </thead>
        <tbody>
        <% if (myLnt == null || myLat == null) { %>
        	<tr>
                <td colspan="17">위치정보를 입력한 후에 조회해주세요.</td>
            </tr>
        <% } else { %>
        	<% for (WifiInfo wifiInfo : wifiInfoList) { %>
        		<tr>
                <td><%= wifiInfo.getDistance() %></td>
                <td><%= wifiInfo.getX_SWIFI_MGR_NO() %></td>
                <td><%= wifiInfo.getX_SWIFI_WRDOFC() %></td>
                <td><a href="detail.jsp?mgrNo=<%= wifiInfo.getX_SWIFI_MGR_NO() %>"><%= wifiInfo.getX_SWIFI_MAIN_NM() %></a></td>
                <td><%= wifiInfo.getX_SWIFI_ADRES1() %></td>
                <td><%= wifiInfo.getX_SWIFI_ADRES2() %></td>
                <td><%= wifiInfo.getX_SWIFI_INSTL_FLOOR() %></td>
                <td><%= wifiInfo.getX_SWIFI_INSTL_TY() %></td>
                <td><%= wifiInfo.getX_SWIFI_INSTL_MBY() %></td>
                <td><%= wifiInfo.getX_SWIFI_SVC_SE() %></td>
                <td><%= wifiInfo.getX_SWIFI_CMCWR() %></td>
                <td><%= wifiInfo.getX_SWIFI_CNSTC_YEAR() %></td>
                <td><%= wifiInfo.getX_SWIFI_INOUT_DOOR() %></td>
                <td><%= wifiInfo.getX_SWIFI_REMARS3() %></td>
                <td><%= wifiInfo.getLAT() %></td>
                <td><%= wifiInfo.getLNT() %></td>
                <td><%= wifiInfo.getWORK_DTTM() %></td>
            </tr>
        	<% } %>
        <% } %>
        </tbody>
    </table>
</body>

</html>
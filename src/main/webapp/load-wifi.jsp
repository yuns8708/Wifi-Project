<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="wifi.*, java.util.*, data.*, com.google.gson.Gson, com.google.gson.JsonArray, com.google.gson.JsonElement, com.google.gson.JsonObject, com.google.gson.JsonParser" %>
<%
	WifiInfoService wifiInfoService = new WifiInfoService();

	wifiInfoService.dropWifiInfoTable();
	wifiInfoService.createWifiInfoTable();
	
	ApiExplorer api = new ApiExplorer();
	String info = api.getWifiInfo();
	Gson gson = new Gson();
	
	JsonObject obj = JsonParser.parseString(info).getAsJsonObject();

	JsonObject tbPublicWifiInfo = obj.get("TbPublicWifiInfo").getAsJsonObject();

	JsonArray row = tbPublicWifiInfo.get("row").getAsJsonArray();

	for (JsonElement jsonObj : row) {
		String rowObject = jsonObj.toString();
		WifiInfo wifiInfo = gson.fromJson(rowObject, WifiInfo.class);
		wifiInfoService.insertWifiInfo(wifiInfo);
	}
	
	List<WifiInfo> wifiInfoList = wifiInfoService.readAllWifiInfos();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2><%= wifiInfoList.size() %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h2>
	<a href="index.jsp">홈 으로 가기</a>

</body>
</html>
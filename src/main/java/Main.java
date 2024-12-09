import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import data.ApiExplorer;
import wifi.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	// 와이파이 정보 파싱해서 집어넣기
    	WifiInfoService wifiInfoService = new WifiInfoService();

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
		
		// wifiInfoService.readAllWifiInfos();
    }

}

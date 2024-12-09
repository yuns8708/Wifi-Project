package wifi;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import data.ApiExplorer;
import db.DbConnection;
import history.History;

public class WifiInfoService {
	DbConnection dbConnection = new DbConnection();
	private PreparedStatement st = null;
	private ResultSet rs = null;
	
	// 와이파이 정보 테이블 생성
	public void createWifiInfoTable() {
		dbConnection.getConnection();
		try {
			String sql = "create table if not exists wifi_info (\n"
					+ "	X_SWIFI_MGR_NO varchar(100) primary key,\n"
					+ "	distance real,\n"
					+ "	X_SWIFI_WRDOFC varchar(100),\n"
					+ "	X_SWIFI_MAIN_NM varchar(100),\n"
					+ "	X_SWIFI_ADRES1 varchar(100),\n"
					+ "	X_SWIFI_ADRES2 varchar(100),\n"
					+ "	X_SWIFI_INSTL_FLOOR varchar(100),\n"
					+ "	X_SWIFI_INSTL_TY varchar(100),\n"
					+ "	X_SWIFI_INSTL_MBY varchar(100),\n"
					+ "	X_SWIFI_SVC_SE varchar(100),\n"
					+ "	X_SWIFI_CMCWR varchar(100),\n"
					+ "	X_SWIFI_CNSTC_YEAR varchar(100),\n"
					+ "	X_SWIFI_INOUT_DOOR varchar(100),\n"
					+ "	X_SWIFI_REMARS3 varchar(100),\n"
					+ "	LAT varchar(100),\n"
					+ "	LNT varchar(100),\n"
					+ "	WORK_DTTM varchar(100)\n"
					+ ")";
			st = dbConnection.getConn().prepareStatement(sql);
			st.executeUpdate();
			System.out.print("insert success");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 와이파이 정보 테이블 삭제
	public void dropWifiInfoTable() {
		dbConnection.getConnection();
		try {
			String sql = "drop table wifi_info";
			st = dbConnection.getConn().prepareStatement(sql);
			st.executeUpdate();
			System.out.print("delete success");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 와이파이 정보 db에 삽입
	public void insertWifiInfo(WifiInfo wifiInfo) {
		dbConnection.getConnection();
		
		try {
			String sql = "insert into wifi_info (X_SWIFI_MGR_NO, distance, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, \n"
					+ "	X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, \n"
					+ "	X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, \n"
					+ "	X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) \n"
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setString(1, wifiInfo.getX_SWIFI_MGR_NO());
			st.setDouble(2, 0.0);
			st.setString(3, wifiInfo.getX_SWIFI_WRDOFC());
			st.setString(4, wifiInfo.getX_SWIFI_MAIN_NM());
			st.setString(5, wifiInfo.getX_SWIFI_ADRES1());
			st.setString(6, wifiInfo.getX_SWIFI_ADRES2());
			st.setString(7, wifiInfo.getX_SWIFI_INSTL_FLOOR());
			st.setString(8, wifiInfo.getX_SWIFI_INSTL_TY());
			st.setString(9, wifiInfo.getX_SWIFI_INSTL_MBY());
			st.setString(10, wifiInfo.getX_SWIFI_SVC_SE());
			st.setString(11, wifiInfo.getX_SWIFI_CMCWR());
			st.setString(12, wifiInfo.getX_SWIFI_CNSTC_YEAR());
			st.setString(13, wifiInfo.getX_SWIFI_INOUT_DOOR());
			st.setString(14, wifiInfo.getX_SWIFI_REMARS3());
			st.setString(15, wifiInfo.getLAT());
			st.setString(16, wifiInfo.getLNT());
			st.setString(17, wifiInfo.getWORK_DTTM());
			int result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 모든 와이파이 정보 가져오기 
	public List<WifiInfo> readAllWifiInfos() {
		List<WifiInfo> wifiInfoList = new ArrayList<>();
		dbConnection.getConnection();
		
		try {
			String sql = "select * from wifi_info";
			st = dbConnection.getConn().prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				WifiInfo wifiInfo = new WifiInfo(
						rs.getString("X_SWIFI_MGR_NO"),
						rs.getDouble("distance"),
						rs.getString("X_SWIFI_WRDOFC"),
						rs.getString("X_SWIFI_MAIN_NM"),
						rs.getString("X_SWIFI_ADRES1"),
						rs.getString("X_SWIFI_ADRES2"),
						rs.getString("X_SWIFI_INSTL_FLOOR"),
						rs.getString("X_SWIFI_INSTL_TY"),
						rs.getString("X_SWIFI_INSTL_MBY"),
						rs.getString("X_SWIFI_SVC_SE"),
						rs.getString("X_SWIFI_CMCWR"),
						rs.getString("X_SWIFI_CNSTC_YEAR"),
						rs.getString("X_SWIFI_INOUT_DOOR"),
						rs.getString("X_SWIFI_REMARS3"),
						rs.getString("LAT"),
						rs.getString("LNT"),
						rs.getString("WORK_DTTM")
						);
				wifiInfoList.add(wifiInfo);
			}
			return wifiInfoList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}	
		return wifiInfoList;
	}
	
	// 와이파이 정보 거리 컬럼 수정
	public void updateWifiDistance(double distance, String mgrNo) {
		dbConnection.getConnection();
		
		try {
			String sql = "update wifi_info set distance=? where X_SWIFI_MGR_NO =?";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setDouble(1, distance);
			st.setString(2, mgrNo);
			int result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 내 거리와 가까운 와이파이 정보 20개 가져오기 
	public List<WifiInfo> readCloseWifiInfos() {
		List<WifiInfo> wifiInfoList = new ArrayList<>();
		dbConnection.getConnection();
		
		try {
			String sql = "select * from wifi_info order by distance asc";
			st = dbConnection.getConn().prepareStatement(sql);
			rs = st.executeQuery();
			for (int i = 0; i < 20; i++) {
				WifiInfo wifiInfo = new WifiInfo(
						rs.getString("X_SWIFI_MGR_NO"),
						rs.getDouble("distance"),
						rs.getString("X_SWIFI_WRDOFC"),
						rs.getString("X_SWIFI_MAIN_NM"),
						rs.getString("X_SWIFI_ADRES1"),
						rs.getString("X_SWIFI_ADRES2"),
						rs.getString("X_SWIFI_INSTL_FLOOR"),
						rs.getString("X_SWIFI_INSTL_TY"),
						rs.getString("X_SWIFI_INSTL_MBY"),
						rs.getString("X_SWIFI_SVC_SE"),
						rs.getString("X_SWIFI_CMCWR"),
						rs.getString("X_SWIFI_CNSTC_YEAR"),
						rs.getString("X_SWIFI_INOUT_DOOR"),
						rs.getString("X_SWIFI_REMARS3"),
						rs.getString("LAT"),
						rs.getString("LNT"),
						rs.getString("WORK_DTTM")
						);
				wifiInfoList.add(wifiInfo);
				rs.next();
			}
			return wifiInfoList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
		
		return wifiInfoList;
	}
	
	// 특정 와이파이 정보 하나 가져오기
	public WifiInfo readWifiInfoByMgrNo(String mgrNo) {
		WifiInfo wifiInfo = new WifiInfo();
		dbConnection.getConnection();
		
		try {
			String sql = "select * from wifi_info where X_SWIFI_MGR_NO=?";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setString(1, mgrNo);
			rs = st.executeQuery();
			if (rs.next()) {
				wifiInfo = new WifiInfo(
						rs.getString("X_SWIFI_MGR_NO"),
						rs.getDouble("distance"),
						rs.getString("X_SWIFI_WRDOFC"),
						rs.getString("X_SWIFI_MAIN_NM"),
						rs.getString("X_SWIFI_ADRES1"),
						rs.getString("X_SWIFI_ADRES2"),
						rs.getString("X_SWIFI_INSTL_FLOOR"),
						rs.getString("X_SWIFI_INSTL_TY"),
						rs.getString("X_SWIFI_INSTL_MBY"),
						rs.getString("X_SWIFI_SVC_SE"),
						rs.getString("X_SWIFI_CMCWR"),
						rs.getString("X_SWIFI_CNSTC_YEAR"),
						rs.getString("X_SWIFI_INOUT_DOOR"),
						rs.getString("X_SWIFI_REMARS3"),
						rs.getString("LAT"),
						rs.getString("LNT"),
						rs.getString("WORK_DTTM")
						);
				return wifiInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
		return wifiInfo;
	}
	
	public double getDistance(double p1x, double p1y, double p2x, double p2y) {
		Point2D my = new Point2D.Double(p1x, p1y);
		Point2D target = new Point2D.Double(p2x, p2y);
		double result = my.distance(target);
		return Math.round(result * 10000) / 10000.0;
	}
	
//	public void insertWifiInfo() throws IOException {
//		ApiExplorer api = new ApiExplorer();
//		String info = api.getWifiInfo();
//		Gson gson = new Gson();
//		
//		JsonObject obj = JsonParser.parseString(info).getAsJsonObject();
////		System.out.println(obj);
//		
//		JsonObject tbPublicWifiInfo = obj.get("TbPublicWifiInfo").getAsJsonObject();
////		System.out.println(tbPublicWifiInfo);
//		
//		JsonArray row = tbPublicWifiInfo.get("row").getAsJsonArray();
////		System.out.println(row);
//		
//		String rowObject = row.get(0).toString();
//		
//		WifiInfo wifiInfo = gson.fromJson(rowObject, WifiInfo.class);
////		System.out.println(wifiInfo.getX_SWIFI_ADRES1());
//		
//		
//		getConnection();
//		
//		closeConnection();
//	}


}

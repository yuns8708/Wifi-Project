package bookmark;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import wifi.WifiInfo;
import wifi.WifiInfoService;

public class BookmarkService {
	DbConnection dbConnection = new DbConnection();
	private PreparedStatement st = null;
	private ResultSet rs = null;
	
	// 북마크 생성
	public void createBookmark(int bookmarkGroupId, String mgrNo) {
		dbConnection.getConnection();
		
		try {
			BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
			BookmarkGroup bookmarkGroup = bookmarkGroupService.readBookmarkGroupById(bookmarkGroupId);
			WifiInfoService wifiInfoService = new WifiInfoService();
			WifiInfo wifiInfo = wifiInfoService.readWifiInfoByMgrNo(mgrNo);
			String sql = "insert into bookmark (bookmark_group_name,\n"
					+ "MAIN_NM, bookmark_date, MGR_NO, bookmark_group_id) \n"
					+ "values (?, ?, datetime('now'), ?, ?)";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setString(1, bookmarkGroup.getBookmarkGroupName());
			st.setString(2, wifiInfo.getX_SWIFI_MAIN_NM());
			st.setString(3, mgrNo);
			st.setInt(4, bookmarkGroupId);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 모든 북마크 가져오기
	public List<Bookmark> readAllBookmarks() {
		List<Bookmark> bookmarkList = new ArrayList<>();
		dbConnection.getConnection();
		
		try {
			String sql = "select * from bookmark";
			st = dbConnection.getConn().prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				Bookmark bookmark = new Bookmark(
						rs.getInt("bookmark_id"),
						rs.getString("bookmark_group_name"),
						rs.getString("MAIN_NM"),
						rs.getString("bookmark_date"),
						rs.getString("MGR_NO"),
						rs.getInt("bookmark_group_id")
						);
				bookmarkList.add(bookmark);
			}
			return bookmarkList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
		return bookmarkList;
	}
	
	// 특정 북마크 하나 가져오기
	public Bookmark readBookmarkById(int bookmarkId) {
		Bookmark bookmark = new Bookmark();
		dbConnection.getConnection();
		
		try {
			String sql = "select * from bookmark where bookmark_id=?";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setInt(1, bookmarkId);
			rs = st.executeQuery();
			if (rs.next()) {
				bookmark = new Bookmark(
						rs.getInt("bookmark_id"),
						rs.getString("bookmark_group_name"),
						rs.getString("MAIN_NM"),
						rs.getString("bookmark_date"),
						rs.getString("MGR_NO"),
						rs.getInt("bookmark_group_id")
						);
				return bookmark;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
		return bookmark;
	}
	
	// 북마크 삭제
	public void deleteBookmark(int bookmarkId) {
		dbConnection.getConnection();
		
		try {
			String sql = "delete from bookmark where bookmark_id=?";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setInt(1, bookmarkId);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 북마크 그룹 삭제되면 북마크도 삭제
	public void deleteBookmarkByBookmarkGroupId(int bookmarkGroupId) {
		dbConnection.getConnection();
		
		try {
			String sql = "delete from bookmark where bookmark_group_id=?";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setInt(1, bookmarkGroupId);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 북마크 테이블 생성
	public void createBookmarkTable() {
		dbConnection.getConnection();
		
		try {
			String sql = "create table if not exists bookmark (\n"
					+ "	bookmark_id integer primary key autoincrement,\n"
					+ "	bookmark_group_name varchar(30),\n"
					+ "	MAIN_NM varchar(100),\n"
					+ "	bookmark_date varchar(30),\n"
					+ "	MGR_NO varchar(100),\n"
					+ "	bookmark_group_id int\n"
					+ ")";
			st = dbConnection.getConn().prepareStatement(sql);
			st.executeUpdate();
			System.out.println("success : create bookmark table");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 북마크 테이블 삭제
	public void dropBookmarkTable() {
		dbConnection.getConnection();
		
		try {
			String sql = "drop table bookmark";
			st = dbConnection.getConn().prepareStatement(sql);
			st.executeUpdate();
			System.out.println("success : drop bookmark table");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
}

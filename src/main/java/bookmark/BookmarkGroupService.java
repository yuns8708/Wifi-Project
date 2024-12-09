package bookmark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import wifi.WifiInfo;

public class BookmarkGroupService {
	DbConnection dbConnection = new DbConnection();
	private PreparedStatement st = null;
	private ResultSet rs = null;
	
	// 북마크 그룹 생성
	public void createBookmarkGroup(BookmarkGroup bookmarkGroup) {
		dbConnection.getConnection();
		
		try {
			String sql = "insert into bookmark_group (bookmark_group_name, bookmark_group_order,\n"
					+ "bookmark_group_date, bookmark_group_modifydate) \n"
					+ "values (?, ?, datetime('now'), '')";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setString(1, bookmarkGroup.getBookmarkGroupName());
			st.setInt(2, bookmarkGroup.getBookmarkGroupOrder());
			int result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 북마크 그룹 수정
	public void updateBookmarkGroup(int bookmarkGroupId, String bookmarkGroupName, int bookmarkGroupOrder) {
		dbConnection.getConnection();
		
		try {
			String sql = "update bookmark_group \n"
					+ "set bookmark_group_name=?, \n"
					+ "bookmark_group_order=?,\n"
					+ "bookmark_group_modifydate=datetime('now')\n"
					+ "where bookmark_group_id=?";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setString(1, bookmarkGroupName);
			st.setInt(2, bookmarkGroupOrder);
			st.setInt(3, bookmarkGroupId);
			int result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 북마크 그룹 수정시 북마크의 북마크 그룹 이름도 수정
	public void updateBookmark(int bookmarkGroupId, String bookmarkGroupName) {
		dbConnection.getConnection();
		try {
			String sql = "update bookmark set bookmark_group_name=? where bookmark_group_id=?";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setString(1, bookmarkGroupName);
			st.setInt(2, bookmarkGroupId);
			int result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 북마크 그룹 삭제
	public void deleteBookmarkGroup(int bookmarkGroupId) {
		dbConnection.getConnection();
		
		try {
			String sql = "delete from bookmark_group where bookmark_group_id=?";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setInt(1, bookmarkGroupId);
			int result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 모든 북마크 그룹 순서 오름차순으로 가져오기
	public List<BookmarkGroup> readAllBookmarkGroups() {
		List<BookmarkGroup> bookmarkGroupList = new ArrayList<>();
		dbConnection.getConnection();
		
		try {
			String sql = "select * from bookmark_group order by bookmark_group_order asc";
			st = dbConnection.getConn().prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				BookmarkGroup bookmarkGroup = new BookmarkGroup(
						rs.getInt("bookmark_group_id"),
						rs.getString("bookmark_group_name"),
						rs.getInt("bookmark_group_order"),
						rs.getString("bookmark_group_date"),
						rs.getString("bookmark_group_modifydate")
						);
				bookmarkGroupList.add(bookmarkGroup);
			}
			return bookmarkGroupList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
		return bookmarkGroupList;
	}
	
	// 특정 북마크 그룹 하나 가져오기
	public BookmarkGroup readBookmarkGroupById(int bookmarkGroupId) {
		BookmarkGroup bookmarkGroup = new BookmarkGroup();
		dbConnection.getConnection();
		
		try {
			String sql = "select * from bookmark_group where bookmark_group_id=?";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setInt(1, bookmarkGroupId);
			rs = st.executeQuery();
			if (rs.next()) {
				bookmarkGroup = new BookmarkGroup(
						rs.getInt("bookmark_group_id"),
						rs.getString("bookmark_group_name")
						);
				return bookmarkGroup;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
		return bookmarkGroup;
	}
	
	// 북마크 그룹 테이블 생성
	public void createBookmarkGroupTable() {
		dbConnection.getConnection();
		
		try {
			String sql = "create table if not exists bookmark_group (\n"
					+ "	bookmark_group_id integer primary key autoincrement,\n"
					+ "	bookmark_group_name varchar(30),\n"
					+ "	bookmark_group_order int,\n"
					+ "	bookmark_group_date varchar(30),\n"
					+ "	bookmark_group_modifydate varchar(30)\n"
					+ ")";
			st = dbConnection.getConn().prepareStatement(sql);
			st.executeUpdate();
			System.out.println("success : create bookmark group table");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 북마크 그룹 테이블 삭제
	public void dropBookmarkGroupTable() {
		dbConnection.getConnection();
		
		try {
			String sql = "drop table bookmark_group";
			st = dbConnection.getConn().prepareStatement(sql);
			st.executeUpdate();
			System.out.println("success : drop bookmark group table");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}

}

package history;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DbConnection;

public class HistoryService {
	DbConnection dbConnection = new DbConnection();
	private PreparedStatement st = null;
	private ResultSet rs = null;
	
	// 모든 히스토리 가져오기
	public List<History> readAllHistories() {
		List<History> historyList = new ArrayList<>();
		dbConnection.getConnection();
		
		try {
			String sql = "select * from history order by history_id desc";
			st = dbConnection.getConn().prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				int historyId = rs.getInt("history_id");
				double myLnt = rs.getDouble("my_lnt");
				double myLat = rs.getDouble("my_lat");
				Date searchDate = rs.getDate("search_date");
				History history = new History(historyId, myLnt, myLat, searchDate);
				historyList.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
		return historyList;
	}
	
	// 히스토리 생성
	public void createHistory(History history) {
		dbConnection.getConnection();
		
		try {
			String sql = "insert into history (my_lnt, my_lat, search_date) values (?, ?, datetime('now'))";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setDouble(1, history.getMyLnt());
			st.setDouble(2, history.getMyLat());
			int result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 히스토리 삭제
	public void deleteHistory(int historyId) {
		dbConnection.getConnection();
		
		try {
			String sql = "delete from history where history_id=?";
			st = dbConnection.getConn().prepareStatement(sql);
			st.setInt(1, historyId);
			int result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 히스토리 테이블 생성
	public void createHistoryTable() {
		dbConnection.getConnection();
		try {
			String sql = "create table if not exists history (\n"
					+ "	history_id integer primary key autoincrement,\n"
					+ "	my_lnt real,\n"
					+ "	my_lat real,\n"
					+ "	search_date varchar(30)\n"
					+ ");";
			st = dbConnection.getConn().prepareStatement(sql);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}
	
	// 히스토리 테이블 삭제
	public void dropHistoryTable() {
		dbConnection.getConnection();
		try {
			String sql = "create table if not exists history (\n"
					+ "	history_id integer primary key autoincrement,\n"
					+ "	my_lnt real,\n"
					+ "	my_lat real,\n"
					+ "	search_date varchar(30)\n"
					+ ");";
			st = dbConnection.getConn().prepareStatement(sql);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(st, rs);
		}
	}

}

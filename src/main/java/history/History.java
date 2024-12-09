package history;

import java.util.Date;

public class History {
	private int historyId;
	private double myLnt;
	private double myLat;
	private Date searchDate;
	
	public History() {}
	
	public History(int historyId, double myLnt, double myLat, Date searchDate) {
		this.historyId = historyId;
		this.myLnt = myLnt;
		this.myLat = myLat;
		this.searchDate = searchDate;
	}
	
	public History(double myLnt, double myLat) {
		this.myLnt = myLnt;
		this.myLat = myLat;
	}
	
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	public double getMyLnt() {
		return myLnt;
	}
	public void setMyLnt(double myLnt) {
		this.myLnt = myLnt;
	}
	public double getMyLat() {
		return myLat;
	}
	public void setMyLat(double myLat) {
		this.myLat = myLat;
	}
	public Date getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

}

package bookmark;

public class Bookmark {
	private int bookmarkId;
	private String bookmarkGroupName;
	private String MAIN_NM;
	private String bookmarkDate;
	private String MGR_NO;
	private int bookmarkGroupId;
	
	public Bookmark() {}
	
	public Bookmark(String bookmarkGroupName, String mAIN_NM, String mGR_NO,
			int bookmarkGroupId) {
		super();
		this.bookmarkGroupName = bookmarkGroupName;
		MAIN_NM = mAIN_NM;
		MGR_NO = mGR_NO;
		this.bookmarkGroupId = bookmarkGroupId;
	}
	
	public Bookmark(int bookmarkId, String bookmarkGroupName, String mAIN_NM, String bookmarkDate, String mGR_NO,
			int bookmarkGroupId) {
		super();
		this.bookmarkId = bookmarkId;
		this.bookmarkGroupName = bookmarkGroupName;
		MAIN_NM = mAIN_NM;
		this.bookmarkDate = bookmarkDate;
		MGR_NO = mGR_NO;
		this.bookmarkGroupId = bookmarkGroupId;
	}

	public int getBookmarkId() {
		return bookmarkId;
	}
	public void setBookmarkId(int bookmarkId) {
		this.bookmarkId = bookmarkId;
	}
	public String getBookmarkGroupName() {
		return bookmarkGroupName;
	}
	public void setBookmarkGroupName(String bookmarkGroupName) {
		this.bookmarkGroupName = bookmarkGroupName;
	}
	public String getMAIN_NM() {
		return MAIN_NM;
	}
	public void setMAIN_NM(String mAIN_NM) {
		MAIN_NM = mAIN_NM;
	}
	public String getBookmarkDate() {
		return bookmarkDate;
	}
	public void setBookmarkDate(String bookmarkDate) {
		this.bookmarkDate = bookmarkDate;
	}
	public String getMGR_NO() {
		return MGR_NO;
	}
	public void setMGR_NO(String mGR_NO) {
		MGR_NO = mGR_NO;
	}
	public int getBookmarkGroupId() {
		return bookmarkGroupId;
	}
	public void setBookmarkGroupId(int bookmarkGroupId) {
		this.bookmarkGroupId = bookmarkGroupId;
	}
	
	
}

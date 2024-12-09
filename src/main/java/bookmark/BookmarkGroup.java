package bookmark;

public class BookmarkGroup {
	private int bookmarkGroupId;
	private String bookmarkGroupName;
	private int bookmarkGroupOrder;
	private String bookmarkGroupDate;
	private String bookmarkGroupModifyDate;
	
	public BookmarkGroup() {}

	public BookmarkGroup(int bookmarkGroupId, String bookmarkGroupName, int bookmarkGroupOrder, String bookmarkGroupDate,
			String bookmarkGroupModifyDate) {
		super();
		this.bookmarkGroupId = bookmarkGroupId;
		this.bookmarkGroupName = bookmarkGroupName;
		this.bookmarkGroupOrder = bookmarkGroupOrder;
		this.bookmarkGroupDate = bookmarkGroupDate;
		this.bookmarkGroupModifyDate = bookmarkGroupModifyDate;
	}

	public BookmarkGroup(String bookmarkGroupName, int bookmarkGroupOrder) {
		super();
		this.bookmarkGroupName = bookmarkGroupName;
		this.bookmarkGroupOrder = bookmarkGroupOrder;
	}
	
	public BookmarkGroup(int bookmarkGroupId, String bookmarkGroupName) {
		super();
		this.bookmarkGroupId = bookmarkGroupId;
		this.bookmarkGroupName = bookmarkGroupName;
	}

	public int getBookmarkGroupId() {
		return bookmarkGroupId;
	}

	public void setBookmarkGroupId(int bookmarkGroupId) {
		this.bookmarkGroupId = bookmarkGroupId;
	}

	public String getBookmarkGroupName() {
		return bookmarkGroupName;
	}

	public void setBookmarkGroupName(String bookmarkGroupName) {
		this.bookmarkGroupName = bookmarkGroupName;
	}

	public int getBookmarkGroupOrder() {
		return bookmarkGroupOrder;
	}

	public void setBookmarkGroupOrder(int bookmarkGroupOrder) {
		this.bookmarkGroupOrder = bookmarkGroupOrder;
	}

	public String getBookmarkGroupDate() {
		return bookmarkGroupDate;
	}

	public void setBookmarkGroupDate(String bookmarkGroupDate) {
		this.bookmarkGroupDate = bookmarkGroupDate;
	}

	public String getBookmarkGroupModifyDate() {
		return bookmarkGroupModifyDate;
	}

	public void setBookmarkGroupModifyDate(String bookmarkGroupModifyDate) {
		this.bookmarkGroupModifyDate = bookmarkGroupModifyDate;
	}

}

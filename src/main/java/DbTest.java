public class DbTest {
	public static void main(String[] args) {
		SqlManager sq = new SqlManager();
		
		sq.createConnection();
		sq.closeConnection();
		sq.ensureConnection();
	}

}

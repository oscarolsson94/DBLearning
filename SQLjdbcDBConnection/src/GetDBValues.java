import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetDBValues {

	public static void main(String[] args) {
		GetDBValues pro = new GetDBValues();
		pro.createConnection();

	}

	public void createConnection() {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			String url = "jdbc:mysql://localhost:3306/StudentDB?useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "root";
			String pw = "!Hedehage8"; // bör hämtas från fil och inte visas direkt i programmet
			String query = "SELECT * FROM Student";

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pw);
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				String firstname = rs.getString(1); // 1 för första kolumnen, kan även skriva namn på col
				String lastname = rs.getString(2);
				String domain = firstname + lastname + rs.getString(3);
				System.out.println(domain);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
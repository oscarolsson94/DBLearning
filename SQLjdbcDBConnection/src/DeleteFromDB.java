import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteFromDB {

	public static void main(String[] args) {
		DeleteFromDB pro = new DeleteFromDB();
		pro.createConnection("DELETE FROM Student WHERE förnamn = 'Oscar'");

	}

	public void createConnection(String sqlstatement) {

		Connection con = null;
		PreparedStatement st = null;

		try {
			String url = "jdbc:mysql://localhost:3306/StudentDB?useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "root";
			String pw = "!test"; // bör hämtas från fil och inte visas direkt i programmet
			String query = sqlstatement;

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pw);
			st = con.prepareStatement(query);
			
			int count = st.executeUpdate(); //returnerar en int för hur många rader som berörs
			
			System.out.println(count + " rows affected");

		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}

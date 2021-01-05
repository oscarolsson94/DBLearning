import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertIntoDB {

	public static void main(String[] args) {
		InsertIntoDB pro = new InsertIntoDB();
		pro.createConnection("Oscar", "Olsson", "hotmail.com");

	}

	public void createConnection(String förnamn, String efternamn, String domän) {

		Connection con = null;
		PreparedStatement st = null;

		try {
			String url = "jdbc:mysql://localhost:3306/StudentDB?useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "root";
			String pw = "!test"; // bör hämtas från fil och inte visas direkt i programmet
			String query = "INSERT INTO Student values (?,?,?)";

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pw);
			st = con.prepareStatement(query);
			
			st.setString(1, förnamn); //index för frågetecken
			st.setString(2, efternamn);
			st.setString(3, domän);
			
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

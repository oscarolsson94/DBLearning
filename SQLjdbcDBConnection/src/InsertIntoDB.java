import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertIntoDB {

	public static void main(String[] args) {
		InsertIntoDB pro = new InsertIntoDB();
		pro.createConnection("Oscar", "Olsson", "hotmail.com");

	}

	public void createConnection(String f�rnamn, String efternamn, String dom�n) {

		Connection con = null;
		PreparedStatement st = null;

		try {
			String url = "jdbc:mysql://localhost:3306/StudentDB?useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "root";
			String pw = "!Hedehage8"; // b�r h�mtas fr�n fil och inte visas direkt i programmet
			String query = "INSERT INTO Student values (?,?,?)";

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pw);
			st = con.prepareStatement(query);
			
			st.setString(1, f�rnamn); //index f�r fr�getecken
			st.setString(2, efternamn);
			st.setString(3, dom�n);
			
			int count = st.executeUpdate(); //returnerar en int f�r hur m�nga rader som ber�rs
			
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
import java.sql.*;

public class DbTest2 {
    public static void main(String[] args) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://10.0.4.62:3306/sihom_db", "root", "Ldb1234567890@");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW CREATE VIEW v_tb_machine_tool")) {
            if (rs.next()) {
                System.out.println(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

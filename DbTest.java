import java.sql.*;

public class DbTest {
    public static void main(String[] args) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        try(Connection conn = DriverManager.getConnection("jdbc:mariadb://10.0.4.62:3306/sihomdb", "sihomdb", "ojsGnsIL48bV2wEF");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT a.remark as a_remark, a.status as a_status, c.status as c_status FROM V_OFFIE_CAR_STATUS a JOIN LOGIN c ON a.userId = c.KEY_ID LIMIT 1")) {
            if(rs.next()) {
                System.out.println("a.remark: " + rs.getString("a_remark") + ", a.status: " + rs.getString("a_status") + ", c.status: " + rs.getString("c_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

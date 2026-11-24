import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/bank";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "SaiKiran#work24";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void main(String[] args) {

        String sql = """
                INSERT INTO accounts
                (account_number, customer_name, password, balance, is_active)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, 100001);
            statement.setString(2, "Test User");
            statement.setString(3, "test123");
            statement.setDouble(4, 5000.00);
            statement.setBoolean(5, true);

            int rows = statement.executeUpdate();

            System.out.println(rows + " account inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

class AccountDAO
{
    public boolean createAccount(Account account)
    {
        String sql = """
            INSERT INTO accounts
                (customer_name, password, balance, is_active)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, account.getName());
            ps.setString(2, account.getPasswordHash());
            ps.setBigDecimal(3, BigDecimal.ZERO);
            ps.setBoolean(4, account.isActive());

            if (ps.executeUpdate() != 1)
                return false;

            try (ResultSet keys = ps.getGeneratedKeys())
            {
                if (keys.next())
                {
                    account.setAccountNumber(keys.getInt(1));
                    return true;
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Database error: " + e.getMessage());
        }

        return false;
    }

    public List<Account> getAllAccounts()
    {
        List<Account> accounts = new ArrayList<>();

        String sql = """
            SELECT account_number, customer_name, password,
                   balance, is_active
            FROM accounts
            """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
            {
                int accountNumber = rs.getInt("account_number");
                String name = rs.getString("customer_name");
                String passwordHash = rs.getString("password");
                BigDecimal balance = rs.getBigDecimal("balance");
                boolean active = rs.getBoolean("is_active");

                Account account = new Account(
                    accountNumber, name, passwordHash
                );

                // The database already contains a BCrypt hash.
                // Replace the temporary hash created by the constructor.
                account.setPasswordHash(passwordHash);
                account.setBalance(balance.floatValue());
                account.setActive(active);

                accounts.add(account);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Database error while loading accounts: "
                               + e.getMessage());
        }

        return accounts;
    }
}

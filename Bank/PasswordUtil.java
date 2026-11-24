import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean verifyPassword(
            String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }


public static void main(String[] args) {
    String password = "Test@123";

    String hashedPassword = hashPassword(password);

    System.out.println("Original password: " + password);
    System.out.println("Hashed password: " + hashedPassword);
    System.out.println(
        "Correct password: " +
        verifyPassword(password, hashedPassword)
    );
    System.out.println(
        "Wrong password: " +
        verifyPassword("Wrong@123", hashedPassword)
    );
}

}
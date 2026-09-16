import java.util.Scanner;

class InvalidUsernameException extends Exception {
    InvalidUsernameException(String msg) {
        super(msg);
    }
}
class InvalidPasswordException extends Exception {
    InvalidPasswordException(String msg) {
        super(msg);
    }
}
class AccountLockedException extends Exception {
    AccountLockedException(String msg) {
        super(msg);
    }
}
public class Q6 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String correctUser = "admin";
        String correctPass = "1234";

        int attempts = 0;

        try {
            while (attempts < 3) {

                try {
                    System.out.print("Username: ");
                    String user = sc.nextLine();

                    System.out.print("Password: ");
                    String pass = sc.nextLine();

                    if (!user.equals(correctUser))
                        throw new InvalidUsernameException("Wrong username");

                    if (!pass.equals(correctPass))
                        throw new InvalidPasswordException("Wrong password");

                    System.out.println("Login Successful!");
                    break;
                }

                catch (InvalidUsernameException e) {
                    System.out.println(e.getMessage());
                }

                catch (InvalidPasswordException e) {
                    System.out.println(e.getMessage());
                }

                attempts++;

                if (attempts == 3)
                    throw new AccountLockedException("Account Locked!");
            }
        }
        catch (AccountLockedException e) {
            System.out.println(e.getMessage());
        }

        finally {
            System.out.println("Login process ended.");
            sc.close();
        }
    }
}
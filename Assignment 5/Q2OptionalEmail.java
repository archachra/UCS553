import java.util.Optional;

public class Q2OptionalEmail {
    public static void main(String[] args) {
        Optional<String> email = Optional.of("student@example.com");
        Optional<String> emptyEmail = Optional.empty();

        email.ifPresent(value -> System.out.println("Email: " + value));

        String emailOrDefault = emptyEmail.orElse("not-provided@example.com");
        System.out.println("Email with default: " + emailOrDefault);

        try {
            String requiredEmail = emptyEmail.orElseThrow(
                () -> new IllegalStateException("Email address is missing")
            );
            System.out.println("Required email: " + requiredEmail);
        } catch (IllegalStateException exception) {
            System.out.println("Exception: " + exception.getMessage());
        }
    }
}

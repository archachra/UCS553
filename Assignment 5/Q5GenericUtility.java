import java.util.Objects;
import java.util.Optional;

public class Q5GenericUtility {
    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    public static <T> T getDefault(Optional<T> value, T defaultValue) {
        Objects.requireNonNull(value, "Optional argument cannot be null");
        return value.orElse(defaultValue);
    }

    public static <T extends Comparable<? super T>> T max(T a, T b) {
        Objects.requireNonNull(a, "First value cannot be null");
        Objects.requireNonNull(b, "Second value cannot be null");
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }

    public static void main(String[] args) {
        System.out.println("Strings equal: " + isEqual("Java", "Java"));
        System.out.println("Integers equal: " + isEqual(10, 20));

        Optional<String> present = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        System.out.println("Present value: " + getDefault(present, "Default"));
        System.out.println("Default value: " + getDefault(empty, "Default"));

        System.out.println("Larger integer: " + max(12, 25));
        System.out.println("Larger string: " + max("Apple", "Banana"));

        String value = null;
        System.out.println("Is value null? " + isNull(value));
        System.out.println("Is text null? " + isNull("text"));
    }
}

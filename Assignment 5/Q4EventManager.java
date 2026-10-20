import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Scanner;

public class Q4EventManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();

        System.out.print("Enter event date (yyyy-MM-dd): ");
        LocalDate eventDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter start time (HH:mm): ");
        LocalTime startTime = LocalTime.parse(scanner.nextLine());

        System.out.print("Enter venue (leave blank if not specified): ");
        String venueInput = scanner.nextLine().trim();
        Optional<String> venue = venueInput.isEmpty()
            ? Optional.empty()
            : Optional.of(venueInput);

        LocalDate today = LocalDate.now();
        LocalTime endTime = startTime.plusHours(2);
        long daysRemaining = ChronoUnit.DAYS.between(today, eventDate);

        System.out.println("\n--- Event Details ---");
        System.out.println("Name: " + eventName);
        System.out.println("Date: " + eventDate);
        System.out.println("Start time: " + startTime);
        System.out.println("End time: " + endTime);
        System.out.println("Venue: " + venue.orElse("Venue not specified"));

        if (eventDate.isAfter(today)) {
            System.out.println("Status: Future event");
            System.out.println("Days remaining: " + daysRemaining);
        } else if (eventDate.isEqual(today)) {
            System.out.println("Status: Event is today");
            System.out.println("Days remaining: 0");
        } else {
            System.out.println("Status: Past event");
            System.out.println("Days since event: " + Math.abs(daysRemaining));
        }

        scanner.close();
    }
}

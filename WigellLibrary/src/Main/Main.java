package Main;

import com.Skoglund.Book.Book;
import com.Skoglund.Book.BookInventory;
import com.Skoglund.Book.FictionBook;
import com.Skoglund.Book.NonFictionBook;
import com.Skoglund.Price.PremiumPrice;
import com.Skoglund.Price.StandardPrice;
import com.Skoglund.Price.StudentPrice;
import com.Skoglund.Price.PricePolicy;
import com.Skoglund.Rental.Rental;
import com.Skoglund.Rental.RentalService;
import Member.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookInventory inventory = new BookInventory();
        MemberRegistry registry = new MemberRegistry();
        RentalService rentalService = new RentalService();

        inventory.addBook(new FictionBook(1, "Nano Machine", "Han Joong Wueol Ya", 10, "Fantasy"));
        inventory.addBook(new FictionBook(2, "Martial Peak", "Momo", 18, "Fantasy"));
        inventory.addBook(new FictionBook(3, "Shadow Slave", "Guiltythree", 15, "Fantasy"));
        inventory.addBook(new NonFictionBook(4, "Factfulness", "Hans Rosling", 12, "Facts"));
        inventory.addBook(new NonFictionBook(5, "Java Programing 11th Edition", "Herbert Schildt", 10, "Learning"));

        registry.addMember(new Member(1, "Chris", "Standard"));
        registry.addMember(new Member(2, "Kayn", "Student"));
        registry.addMember(new Member(3, "Vayne", "student"));
        registry.addMember(new Member(4, "Caitlyn", "Premium"));

        boolean running = true;
        while (running) {
            System.out.println("\n--- Bibliotek ---");
            System.out.println("\n1. Lista medlemmar");
            System.out.println("2. Ändra medlemmar status");
            System.out.println("3. Lista böcker");
            System.out.println("4. Lista av typ av böcker");
            System.out.println("5. Boka uthyrning");
            System.out.println("6. Avsluta hyrning");
            System.out.println("7. Visa intäkter");
            System.out.println("8. Avsluta");
            System.out.print("\nVal: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> registry.getAllMembers().forEach(System.out::println);
                case "2" -> {
                    System.out.print("Ange medlems-ID: ");
                    int memberId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Ange ny status (standard, student, premium): ");
                    String newStatus = scanner.nextLine().toLowerCase();

                    if (newStatus.equals("standard") || newStatus.equals("student") || newStatus.equals("premium")) {
                        registry.updateMemberStatus(memberId, newStatus);
                    } else {
                        System.out.println("Fel: Ogiltig status. Välj mellan 'standard', 'student' eller 'premium'.");
                    }
                }
                case "3" -> inventory.getBooks().forEach(Book::displayInfo);
                case "4" -> {
                    System.out.print("Vill du se [fiction] eller [nonfiction] böcker?\nVall: ");
                    String type = scanner.nextLine().trim().toLowerCase();

                    if (type.equals("fiction") || type.equals("nonfiction")) {
                        inventory.listBooksByType(type);
                    } else {
                        System.out.println("Fel: Ogiltigt val. Skriv 'fiction' eller 'nonfiction'.");
                    }
                }
                case "5" -> {
                    try {
                        System.out.print("Ange medlems-ID: ");
                        int memberId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Ange bok-ID: ");
                        int bookId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Ange startdatum (YYYY-MM-DD): ");
                        LocalDate start = LocalDate.parse(scanner.nextLine());
                        System.out.print("Ange slutdatum (YYYY-MM-DD): ");
                        LocalDate end = LocalDate.parse(scanner.nextLine());

                        Member member = registry.getMember(memberId);
                        Book book = inventory.findBookById(bookId);

                        if (member == null) {
                            System.out.println("\nFel: Medlem finns inte.");
                            break;
                        }
                        if (book == null) {
                            System.out.println("\nFel: Bok finns inte.");
                            break;
                        }
                        if (end.isBefore(start)) {
                            System.out.println("\nFel: Slutdatum kan inte vara före startdatum.");
                            break;
                        }

                        Rental rental = new Rental(member, book, start, end);
                        rentalService.rentItem(member, rental);
                        System.out.println("Bokning klar: " + rental);

                    } catch (NumberFormatException | DateTimeParseException e) {
                        System.out.println("Felaktig inmatning, försök igen.");
                    }
                }
                case "6" -> {
                    try {
                        System.out.print("Ange bok-ID att avsluta hyra: ");
                        int bookId = Integer.parseInt(scanner.nextLine());
                        rentalService.endRental(bookId);
                    } catch (NumberFormatException e) {
                        System.out.println("Felaktig inmatning, försök igen.");
                    }
                }
                case "7" -> {
                    double totalRevenue = 0;
                    for (Member member : registry.getAllMembers()) {
                        PricePolicy policy;
                        switch (member.getStatus()) {
                            case "student" -> policy = new StudentPrice();
                            case "premium" -> policy = new PremiumPrice();
                            default -> policy = new StandardPrice();
                        }
                        for (Rental r : member.getHistory()) {
                            totalRevenue += r.calculatePrice(policy);
                        }
                    }
                    System.out.println("Totala intäkter: " + totalRevenue + " kr");
                }
                case "8" -> running = false;
                default -> System.out.println("Ogiltigt val.");
            }
            System.out.println("\nProgram avslutas.");
        }
    }
}

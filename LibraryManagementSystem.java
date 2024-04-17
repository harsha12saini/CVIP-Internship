import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

class User {
    private String name;
    private String userId;
    private List<Book> checkedOutBooks;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.checkedOutBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public void checkoutBook(Book book) {
        checkedOutBooks.add(book);
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append(", User ID: ").append(userId);
        if (!checkedOutBooks.isEmpty()) {
            sb.append(", Books Checked Out: ");
            for (Book book : checkedOutBooks) {
                sb.append(book.getTitle()).append(" (ISBN: ").append(book.getIsbn()).append("), ");
            }
            sb.setLength(sb.length() - 2);  // Remove last comma and space
        } else {
            sb.append(", No books checked out");
        }
        return sb.toString();
    }
}

class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        initializeBooks();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    public void initializeBooks() {
        for (int i = 1; i <= 30; i++) {
            addBook(new Book("Book Title " + i, "Author " + i, String.format("%010d", i)));
        }
    }

    public void checkoutBook(String isbn, User user) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && book.isAvailable()) {
                book.setAvailable(false);
                user.checkoutBook(book);
                System.out.println("Book checked out successfully to " + user.getName());
                return;
            }
        }
        System.out.println("Book not available or ISBN not found.");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Library Management System");
            System.out.println("1. List all books");
            System.out.println("2. Checkout a book");
            System.out.println("3. List all users and their books");
            System.out.println("4. Exit");
            System.out.print("Enter an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    System.out.println("Listing all books:");
                    for (Book book : library.getBooks()) {
                        System.out.println(book);
                    }
                    break;
                case 2:
                    System.out.print("Enter ISBN of the book to checkout: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your User ID: ");
                    String userId = scanner.nextLine();

                    User user = new User(name, userId);
                    library.addUser(user);
                    library.checkoutBook(isbn, user);
                    break;
                case 3:
                    System.out.println("Listing all users and their books:");
                    for (User u : library.getUsers()) {
                        System.out.println(u);
                    }
                    break;
                case 4:
                    System.out.println("Exiting the program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
    }
}

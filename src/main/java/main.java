import com.fasterxml.jackson.databind.ObjectMapper;
import service.BookService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

    private static void printChoices(){

    }

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get("src/main/resources/books.json");
        List<Book> booksFromJson = List.of(mapper.readValue(path.toFile(), Book[].class));
        Scanner scanner = new Scanner(System.in);
        BookService bookService = new BookService();

        System.out.println("""
                Meny
                1 skapa ny
                2 visa alla böcker
                3 avsluta program
                """);
        int choice = Integer.parseInt(scanner.nextLine());

        while(choice !=3) {
            switch (choice) {
                case 1:
                   List<Book> books = new ArrayList<>(booksFromJson);

                    System.out.println("Titel");
                    String titel = scanner.nextLine();
                    System.out.println("författare");
                    String author = scanner.nextLine();
                    System.out.println("sidor");
                    int pages = Integer.parseInt(scanner.nextLine());
                    String date = LocalDate.now().toString();

                    Book newBook = new Book(pages,titel,author,date);

                    books.add(newBook);
                    mapper.writeValue(Paths.get("src/main/resources/book.json").toFile(), books);
                    break;
                case 2:
                    List<Book> booksFromJsonUpdt = List.of(mapper.readValue(path.toFile(), Book[].class));

                    for (Book book : booksFromJsonUpdt) {
                        System.out.println("\n");
                        System.out.println(book.getAuthor());
                        System.out.println(book.getTitle());
                        System.out.println(book.getDate());
                        System.out.println(book.getNumberOfPages());
                        System.out.println("\n");

                    }
            }







        }










    }
}

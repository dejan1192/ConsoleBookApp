import java.util.ArrayList;

public interface LibaryInterface {

    void addBook(Book b) throws Exception;
    void addBooks(ArrayList<Book> b);
    ArrayList<Book> searchBooks(String title);
    void removeBook(int id) throws Exception;
    ArrayList<Book> getBooks();
}

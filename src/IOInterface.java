import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IOInterface {

    void printBooksToFile(List<Book> books, String filename) throws IOException;
    ArrayList<Book> loadBooksFromFile(String filename) throws FileNotFoundException;
}

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOBookService implements  IOInterface {

    public void printBooksToFile(List<Book> books, String filename) throws IOException {

        if(!filename.endsWith(".txt")){
            filename = filename.concat(".txt");
        }

        FileWriter fw = new FileWriter(filename);

        for (int i = 0; i < books.size(); i++) {

            fw.write(books.get(i).getTitle() + " - " + books.get(i).getAuthor() + "\n");
        }
        fw.close();

    }

    public ArrayList<Book> loadBooksFromFile(String filename) throws FileNotFoundException {
        if(!filename.endsWith(".txt"))
        {
            filename = filename.concat(".txt");
        }
        File f = new File(filename);
        ArrayList<Book> books = new ArrayList<>();
        Scanner read = new Scanner(f);

      try{
          System.out.println("\n----- BOOKS LOADED ------\n");
          while (read.hasNext()){

              String book = read.nextLine();
              System.out.println(book);
              String[] bookSplit = book.split("-");
              String title = bookSplit[0].trim();
              String author = bookSplit[1].trim();

              Book b = new Book(author, title);
              books.add(b);
          }
          System.out.println("\n------------------------\n");
      }catch (IndexOutOfBoundsException e){
          System.out.println("Bad file format.");
      }

        return books;
    }
}

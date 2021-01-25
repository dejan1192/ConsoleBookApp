import java.util.ArrayList;

public class Libary implements LibaryInterface {

    private ArrayList<Book> books = new ArrayList<>();

    @Override
    public void addBook(Book b) throws Exception {
        if(b.getTitle().isEmpty() || b.getAuthor().isEmpty()){
            throw new Exception("Title/ Author cant be empty");

        }
        books.add(b);
        System.out.println("Book added to libary");
    }

    @Override
    public void addBooks(ArrayList<Book> b){

        for (int i = 0; i < b.size(); i++) {
            books.add(b.get(i));
        }

    }
    @Override
    public ArrayList<Book> getBooks(){
        return books;
    }
    @Override
    public  ArrayList<Book> searchBooks(String b){
        ArrayList<Book> found = new ArrayList<>();
         b = b.toLowerCase();
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getTitle().toLowerCase().contains(b)){
                found.add(books.get(i));
            }

        }

        return found;
    }

    @Override
    public void removeBook(int isbn) throws Exception {
       Book bookFound = books.stream().filter(b -> b.getIsbn() == isbn).findAny().orElse(null);
        if(bookFound != null){
            books.remove(bookFound);
        }else{
            throw new Exception("Book with " + isbn + " not found.");
        }

    }
}

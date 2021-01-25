import java.util.Random;

public class Book {

    private String author;
    private String title;
    private int isbn;

    public Book(){}
    public Book(String author, String title) {
        this.author = author;
        this.title = title;
        Random r = new Random();
        this.isbn = r.nextInt();
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", isbn=" + isbn +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
}

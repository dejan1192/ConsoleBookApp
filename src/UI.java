import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    private final Scanner sc = new Scanner(System.in);
    private final LibaryInterface lib;
    private final IOInterface IOBookService;

    public UI(LibaryInterface libary, IOInterface service){
        lib = libary;
        IOBookService = service;
    }


    public void mainMenu(){

        printBookAppLogo();
        printNavigation();
        try {
            navigationControls();
        }catch (InterruptedException | IOException e){
            System.out.println(e.getMessage());
        }

    }


    private int clearConsole() throws IOException, InterruptedException {
       return new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();


    }

    public void printBookAppLogo(){
        System.out.println("\t\t\t===============================");
        System.out.println("\t\t\t Welcome to Book Console App   ");
        System.out.println("\t\t\t                               ");
        System.out.println("\t\t\t===============================");
    }

    public void printNavigation(){
        System.out.printf("%-15s %-15s %15s %20s %20s %20s\n", "[1]AddBook", "[2]ListAllBooks", "[3]PrintToFile", "[4]LoadFromFile", "[5]SearchByTitle", "[6] SearchAndDelete");




    }

    public void navigationControls() throws IOException, InterruptedException {
        String command = sc.nextLine();
        try{

            if(Integer.parseInt(command) == 1){
                addBook();
            }else if(Integer.parseInt(command) == 2){
                showBooks();
            }else if(Integer.parseInt(command) == 3){
                printToFile();
            }else if(Integer.parseInt(command) == 4) {
                loadFromFile();
            }else if(Integer.parseInt(command) == 5){
                searchBook();
            }else if(Integer.parseInt(command) == 6){
                removeBook();
            }else{
                clearConsole();
                mainMenu();
            }

        }catch (NumberFormatException e){
            clearConsole();
            mainMenu();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            clearConsole();
            System.out.println(e.getMessage());
            mainMenu();
        }
    }

    public void cancel(String command){
        if(command.equalsIgnoreCase("c")){
            try {
                clearConsole();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            mainMenu();
        }
    }



    public void showBooks() throws IOException, InterruptedException {
        clearConsole();
        System.out.println("========= BOOKS =============");
        System.out.printf("%-25s %25s\n","Author", "Title" );
        System.out.println("----------------------------------");

        for (int i = 0; i < lib.getBooks().size(); i++) {
           String title =  lib.getBooks().get(i).getTitle();
           String author =  lib.getBooks().get(i).getAuthor();
            System.out.printf("%-25s %25s\n",author, title );
        }

        System.out.println("\n\n\nPress Any key to go back..");
        sc.nextLine();
        clearConsole();
        mainMenu();
    }

    public void addBook() throws Exception {
        clearConsole();
        System.out.println("------ ADD NEW BOOK ------");
        System.out.println("Press c to cancel\n");
        System.out.print("Enter book tite: ");
        String title = sc.nextLine();
        cancel(title);
        System.out.print("Enter book author: ");
        String author = sc.nextLine();
        cancel(author);

        System.out.println("\n\t\t\t\t====== NEW BOOK ========");
        System.out.println("\t\t\t\t Title: " + title);
        System.out.println("\t\t\t\t Title: " + author);
        System.out.println("\t\t\t\t=========================\n");

        System.out.println("To confirm press ENTER ..");
        System.out.println("Press c to cancel.");


        String command = sc.nextLine();
        cancel(command);
        Book b = new Book(author, title);
        lib.addBook(b);
        clearConsole();
        System.out.println("\u001B[45m" + "**** Book added ****" + "\u001B[0m");
        mainMenu();


    }

    public void printToFile() throws IOException, InterruptedException {
        clearConsole();
        System.out.println("===== PRINT BOOK LIST ========");
        System.out.println("Press c to cancel\n");
        System.out.println("Enter filename: ");
        String filename = sc.nextLine();
        cancel(filename);
        try {
            IOBookService.printBooksToFile(lib.getBooks(), filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clearConsole();
            System.out.println("Print successfull!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }finally{

            mainMenu();
        }


    }

    public void loadFromFile() throws IOException, InterruptedException {
        clearConsole();
        System.out.println("======== LOAD FROM FILE =======");
        System.out.println("Press c to cancel\n");
        System.out.println("Enter filename: ");
        String filename = sc.nextLine();
        cancel(filename);
        try {
            ArrayList<Book> bookList = IOBookService.loadBooksFromFile(filename);

            lib.addBooks(bookList);
        } catch (FileNotFoundException e) {
            clearConsole();
            System.out.println("File not found!");
            mainMenu();
        }
        System.out.println("Books added to libary!");
        System.out.println("Press any key to continue...");
        sc.nextLine();
        clearConsole();
        mainMenu();
    }

    public void searchBook() throws IOException, InterruptedException {
        clearConsole();
        System.out.println("========= SEARCH BOOK =========");
        System.out.println("Press c to cancel\n");
        System.out.println("Press ENTER to list all ");
        System.out.println("or search book's by title: ");
        String title = sc.nextLine();
        cancel(title);

        ArrayList<Book> books = lib.searchBooks(title);
        if(books != null){
            System.out.println("\n--- Book's Found ----\n");
            for (int i = 0; i < books.size(); i++) {
                System.out.printf("%-20d %-20s%20s\n",books.get(i).getIsbn() ,books.get(i).getTitle(), books.get(i).getAuthor() );
            }

        }
        System.out.println("\n Press Any key to continue");
        sc.nextLine();
        clearConsole();
        mainMenu();
    }

    public void removeBook() throws Exception {
        clearConsole();
        System.out.println("=== DELETE BOOKS ====");
        System.out.println("Press c to cancel");
        System.out.println("Press ENTER to list ALL");
        System.out.println("Or search by TITLE: ");
        String title = sc.nextLine();
        cancel(title);

        ArrayList<Book> books = lib.searchBooks(title);
        if(books != null){
            System.out.println("\n--- Book's Found ----\n");
            for (int i = 0; i < books.size(); i++) {
                System.out.printf("%-20d %-20s%20s\n",books.get(i).getIsbn() ,books.get(i).getTitle(), books.get(i).getAuthor() );
            }

        }
        System.out.println("To delete enter book's isbn");
        int isbn = sc.nextInt();
        lib.removeBook(isbn);

        clearConsole();
        mainMenu();


    }


}

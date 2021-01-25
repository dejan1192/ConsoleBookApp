public class Main {


    public static void main(String[] args) {
        UI ui = new UI(new Libary(), new IOBookService());
        ui.mainMenu();
    }
}

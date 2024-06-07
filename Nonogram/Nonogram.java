public class Nonogram {

    public static int size;
    public static void main(String[] args) {

        Menu menu = new Menu();
        size = menu.selectDifficulty();
        new Game();
    
    }
}
package ir.ac.kntu;

public class Main {
    public static void main(String[] args) {
        Bank myBank = new Bank();
        MainMenu.getInstance().implementMenu(myBank);

    }
}

package ir.ac.kntu.main;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        Bank myBank = new Bank();
        MainMenu.getInstance().implementMenu(myBank);
    }
}

package ir.ac.kntu;

public interface MenuProperty <T>{
    void implementMenu(Bank myBank);
    void printTheMenu();
    T getOption();
}

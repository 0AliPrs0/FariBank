package ir.ac.kntu;

public class Main {
    public static void main(String[] args) {
        Bank myBank = new Bank();
        myBank.setInterests(new InterestRatesAndFees("2%", "300", "2%", "2000", "0"));
        MainMenu.getInstance().implementMenu(myBank);
    }
}

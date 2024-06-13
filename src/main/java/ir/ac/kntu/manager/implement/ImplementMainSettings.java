package ir.ac.kntu.manager.implement;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.baseclass.InterestRatesAndFees;
import ir.ac.kntu.main.help.ScannerWrapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImplementMainSettings {
    public void handleMainSettings(Bank myBank){
        String option;

        do {
            printTheMenu();
            option = ScannerWrapper.getInstance().nextLine();
            handleTheMenu(option, myBank);
        } while (!"6".equals(option));
    }

    public void printTheMenu(){
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Fund interest rate");
        System.out.println(Color.BLUE + "2- Wadge card by card");
        System.out.println(Color.BLUE + "3- Wadge inter bank bridge");
        System.out.println(Color.BLUE + "4- Wadge inter bank paya");
        System.out.println(Color.BLUE + "5- Wadge fari by fari");
        System.out.println(Color.BLUE + "6- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.print(Color.PURPLE + "Select (1 - 6): ");
    }

    public void handleTheMenu(String option, Bank myBank){
        String input = "";
        try {
            if (Integer.parseInt(option) >= 1 && Integer.parseInt(option) <= 5) {
                System.out.print("Enter the amount: ");
                input = ScannerWrapper.getInstance().nextLine();
                boolean isTrueForm = checkFormInterest(input);
                if(!isTrueForm){
                    System.out.println(Color.RED + "Enter the correct form!");
                    return;
                }
            }
        } catch (Exception e){
            System.out.println(Color.RED + "Invalid input!");
            return;
        }

        InterestRatesAndFees interest = myBank.getInterests();
        switch (option) {
            case "1" -> interest.setFundInterestRate(input);
            case "2" -> interest.setCardByCard(input);
            case "3" -> interest.setInterBankBridge(input);
            case "4" -> interest.setInterBankPaya(input);
            case "5" -> interest.setFariByFari(input);
            case "6" -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
        myBank.setInterests(interest);
    }

    public boolean checkFormInterest(String input){
        String patternStr = "\\d+.?\\d*%?";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}

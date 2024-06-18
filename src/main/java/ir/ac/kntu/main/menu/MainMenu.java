package ir.ac.kntu.main.menu;

import ir.ac.kntu.main.baseclass.InterestRatesAndFees;
//import ir.ac.kntu.main.baseclass.InterestThread;
import ir.ac.kntu.main.baseclass.InterestThread;
import ir.ac.kntu.main.baseclass.MockAccount;
//import ir.ac.kntu.main.baseclass.TransferThread;
import ir.ac.kntu.main.baseclass.TransferThread;
import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.enums.MenuProperty;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.help.ScannerWrapper;
//import ir.ac.kntu.manager.implement.ImplementAutoTransaction;
import ir.ac.kntu.manager.info.Manager;
import ir.ac.kntu.manager.login.ManagerHandler;
import ir.ac.kntu.support.login.SupportHandler;
import ir.ac.kntu.user.info.UserAccount;
import ir.ac.kntu.user.login.UserLoginMenu;

import java.util.ArrayList;
import java.util.List;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;

public class MainMenu implements MenuProperty {
    private static MainMenu instance = new MainMenu();

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        return instance;
    }

    public enum MenuMainField {
        USER,
        SUPPORT,
        MANAGER,
        EXIT,
        UNDEFINED
    }

    public void implementMenu(Bank myBank) {
        addInformation(myBank);
//        ImplementAutoTransaction autoTransaction = new ImplementAutoTransaction();
//        ScheduledExecutorService scheduler1 = Executors.newScheduledThreadPool(1);
//
//        Runnable task1 = () -> autoTransaction.handleInterest(myBank, true);
//        scheduler1.scheduleAtFixedRate(task1, 0, 1, TimeUnit.MINUTES);
//
//        ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(1);
//
//        Runnable task2 = () -> autoTransaction.handleTransfers(myBank, true);
//        scheduler2.scheduleAtFixedRate(task2, 0, 2, TimeUnit.MINUTES);

        InterestThread thread = new InterestThread(myBank);
        thread.start();
        TransferThread thread2 = new TransferThread(myBank);
        thread2.start();
        MenuMainField option;
        do {
            printTheMenu();
            option = getOption();
            handleTheMenu(option, myBank);
        } while (option != MenuMainField.EXIT);

//        scheduler1.shutdownNow();
//        scheduler2.shutdownNow();
        ScannerWrapper.getInstance().close();
    }

    public void addInformation(Bank myBank){
        MockAccount mockAccount1 = new MockAccount("nima", "09032969853", 1234567);
        myBank.addMockAccounts(mockAccount1);

        MockAccount mockAccount2 = new MockAccount("mahdi", "09147458951", 7654321);
        myBank.addMockAccounts(mockAccount2);

        myBank.setInterests(new InterestRatesAndFees("2%", "300", "2%", "2000", "0"));

        myBank.setDepositPeriod(2);
        List<String> fathers = new ArrayList<>();
        fathers.add("1");
        myBank.addManager(new Manager("AliPrs", "@Ap84", fathers));

        myBank.getUserAccounts().add(new UserAccount("ali", "prs", "09032948208", "1452006601", "@Ap84", 0, 12345678, 0));
    }

    public void useThread(Bank myBank){

    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.CYAN + "Main menu");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- User");
        System.out.println(Color.BLUE + "2- Support");
        System.out.println(Color.BLUE + "3- Manager");
        System.out.println(Color.BLUE + "4- Exit");
        System.out.println(Color.YELLOW + "***********************");
        System.out.print(Color.PURPLE + "Select your options: ");
    }

    @Override
    public MenuMainField getOption() {
        MenuMainField[] options = MenuMainField.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return MenuMainField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return MenuMainField.UNDEFINED;
    }

    public void handleTheMenu(MenuMainField option, Bank myBank) {
        UserLoginMenu userLoginMenu = new UserLoginMenu();
        SupportHandler supportHandler = new SupportHandler();
        ManagerHandler managerHandler = new ManagerHandler();
        switch (option) {
            case USER -> userLoginMenu.implementMenu(myBank);
            case SUPPORT -> supportHandler.implementTheSupportMenu(myBank);
            case MANAGER -> managerHandler.implementTheManagerMenu(myBank);
            case EXIT -> System.out.println(Color.RED + "Exiting the program ...");
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

}

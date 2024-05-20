package ir.ac.kntu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {


    public static void main(String[] args) {
        Date date = new Date();
        date.getTime();
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        String formattedDate = sdf.format(currentDate);

        System.out.println(formattedDate);
        Bank myBank = new Bank();

        Menus.MenuMain role;

        do {
            Menus.getInstance().printTheMainMenu();
            role = Menus.getInstance().getOptionMainMenu();
            handleTheMainMenu(role, myBank);
        } while (role != Menus.MenuMain.EXIT);

        ScannerWrapper.getInstance().close();
    }

    public static void handleTheMainMenu(Menus.MenuMain roles, Bank myBank){
        switch (roles) {
            case USER -> UserHandler.implementTheUserMenu(myBank);
            case SUPPORT -> SupportHandler.implementTheSupportMenu(myBank);
            case EXIT -> System.out.println("Exiting the program ...");
            default -> System.out.println("Invalid Input!");
        }
    }
}

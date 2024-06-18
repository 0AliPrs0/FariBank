package ir.ac.kntu.support.login;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.support.info.Support;
import ir.ac.kntu.support.menus.SupportMenu;

public class SupportHandler {
    public void implementTheSupportMenu(Bank myBank) {
        addSupports(myBank);
        Support support = logInSupport(myBank);
        if (support != null) {
            SupportMenu supportMenu = new SupportMenu();
            if (!support.getIsBlocked()) {
                supportMenu.handleSupportOptions(myBank, support);
            } else {
                System.out.println(Color.RED + "You are blocked!!!!");
            }
        }
    }

    public void addSupports(Bank myBank) {
//        myBank.getSupports().add(new Support("ali", "aliprs", "@Ap84"));
//        myBank.getSupports().add(new Support("nima", "nimadan", "#Nd83"));
//        myBank.getSupports().add(new Support("amir", "amirmkh", "&Am85"));
    }

    public Support logInSupport(Bank myBank) {
        System.out.print(Color.YELLOW + "Enter your user name: ");
        String userName = ScannerWrapper.getInstance().nextLine();

        System.out.print(Color.YELLOW + "Enter your password: ");
        String password = ScannerWrapper.getInstance().nextLine();

        return checkInformation(myBank, userName, password);
    }

    public Support checkInformation(Bank myBank, String userName, String password) {
        for (Support entry : myBank.getSupports()) {
            if (entry.getUserName().equals(userName) && entry.getPassword().equals(password)) {
                return entry;
            }
        }
        System.out.println(Color.RED + "There is not Admin with this information!");
        return null;

    }
}

package ir.ac.kntu.manager.login;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.manager.menus.ManagerMenu;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.manager.info.Manager;

public class ManagerHandler {
    public void implementTheManagerMenu(Bank myBank) {
        Manager manager = logInManager(myBank);
        if (manager != null) {
            ManagerMenu managerMenu = new ManagerMenu();
            if (!manager.getIsBlocked()) {
                managerMenu.implementManagerOption(myBank, manager);
            } else {
                System.out.println(Color.RED + "You are blocked!!!");
            }
        }
    }

    public Manager logInManager(Bank myBank) {
        System.out.print(Color.YELLOW + "Enter your user name: ");
        String userName = ScannerWrapper.getInstance().nextLine();

        System.out.print(Color.YELLOW + "Enter your password: ");
        String password = ScannerWrapper.getInstance().nextLine();

        return checkInformation(myBank, userName, password);
    }

    public Manager checkInformation(Bank myBank, String userName, String password) {
        for (Manager entry : myBank.getManagers()) {
            if (entry.getUserName().equals(userName) && entry.getPassword().equals(password)) {
                return entry;
            }
        }
        System.out.println(Color.RED + "There is not manager with this information!");
        return null;

    }

}

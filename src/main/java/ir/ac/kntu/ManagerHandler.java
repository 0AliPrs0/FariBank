package ir.ac.kntu;

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
        Manager manager = new Manager();

        System.out.print(Color.YELLOW + "Enter your user name: ");
        String userName = ScannerWrapper.getInstance().nextLine();

        System.out.print(Color.YELLOW + "Enter your password: ");
        String password = ScannerWrapper.getInstance().nextLine();

        boolean isTrueInformation = checkInformation(myBank, userName, password);
        if (isTrueInformation) {
            manager.setUserName(userName);
            manager.setPassword(password);
            return manager;
        }
        System.out.println(Color.RED + "There is not Manager with this information!");
        return null;
    }

    public boolean checkInformation(Bank myBank, String userName, String password) {
        for (Manager entry : myBank.getManagers()) {
            if (entry.getUserName().equals(userName) && entry.getPassword().equals(password)) {
                return true;
            }
        }
        return false;

    }

}

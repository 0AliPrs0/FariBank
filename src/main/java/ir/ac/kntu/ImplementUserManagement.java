package ir.ac.kntu;


import java.util.ArrayList;
import java.util.List;

public class ImplementUserManagement {

    public void handleShowUsers(Bank myBank) {
        String input;
        do {
            System.out.println(Color.BLUE + "1- Show users");
            System.out.println(Color.BLUE + "2- Show supports");
            System.out.println(Color.BLUE + "3- Show managers");
            System.out.println(Color.BLUE + "4- return");
            System.out.print(Color.CYAN + "Chose (1 - 4)");
            input = ScannerWrapper.getInstance().nextLine();

            switch (input) {
                case "1" -> showUsers(myBank);
                case "2" -> showSupports(myBank);
                case "3" -> showManagers(myBank);
                case "4" -> System.out.println();
                default -> System.out.println(Color.RED + "Invalid input!");
            }
        } while (!"4".equals(input));
    }

    public void showUsers(Bank myBank) {
        if (myBank.getUserAccounts().isEmpty()) {
            System.out.println(Color.RED + "The list is empty!");
            return;
        }

        System.out.println(Color.BLUE + "1- Show all users");
        System.out.println(Color.BLUE + "2- Filter users");
        System.out.print(Color.CYAN + "Chose (1 - 2)");
        String input = ScannerWrapper.getInstance().nextLine();

        switch (input) {
            case "1" -> printUsers(myBank.getUserAccounts());
            case "2" -> filterUser(myBank);
            default -> System.out.println(Color.RED + "Invalid input!");
        }
    }

    public void printUsers(List<UserAccount> userAccounts) {
        int index = 1;
        for (UserAccount user : userAccounts) {
            System.out.print(Color.PURPLE + index + "- ");
            printTheInformationUser(user);
            index++;
        }
    }

    public void filterUser(Bank myBank) {
        System.out.println(Color.PURPLE + "Enter the following information. If you want the field not to be filtered, enter 0");
        System.out.print(Color.YELLOW + "Enter the first name of user: ");
        String fName = ScannerWrapper.getInstance().nextLine();
        System.out.print(Color.YELLOW + "Enter the last name of user: ");
        String lName = ScannerWrapper.getInstance().nextLine();
        System.out.print(Color.YELLOW + "Enter the phone number of user: ");
        String phoneNumber = ScannerWrapper.getInstance().nextLine();

        List<UserAccount> userAccounts = new ArrayList<>();
        for (UserAccount user : myBank.getUserAccounts()) {
            if (user.getFirstName().equals(fName) || "0".equals(fName)) {
                if (user.getLastName().equals(lName) || "0".equals(lName)) {
                    if (user.getPhoneNumber().equals(phoneNumber) || "0".equals(phoneNumber)) {
                        userAccounts.add(user);
                    }
                }
            }
        }
        printUsers(userAccounts);
    }

    public void printTheInformationUser(UserAccount user) {
        System.out.print(Color.BLUE + "name: " + Color.GREEN + user.getFirstName() + " " + user.getLastName() + " ");
        System.out.print(Color.BLUE + "phone number: " + Color.GREEN + user.getPhoneNumber() + " ");
        System.out.println(Color.BLUE + "account number: " + Color.GREEN + user.getAccountNumber());
    }

    public void showSupports(Bank myBank) {
        if (myBank.getSupports().isEmpty()) {
            System.out.println(Color.RED + "The list is empty!");
            return;
        }

        System.out.println(Color.BLUE + "1- Show all supports");
        System.out.println(Color.BLUE + "2- Filter supports");
        System.out.print(Color.CYAN + "Chose (1 - 2)");
        String input = ScannerWrapper.getInstance().nextLine();

        switch (input) {
            case "1" -> printSupports(myBank.getSupports());
            case "2" -> filterSupport(myBank);
            default -> System.out.println(Color.RED + "Invalid input!");
        }
    }

    public void printSupports(List<Support> supports) {
        int index = 1;
        for (Support support : supports) {
            System.out.print(Color.PURPLE + index + "- ");
            printTheInformationSupport(support);
            index++;
        }
    }

    public void filterSupport(Bank myBank) {
        System.out.println(Color.PURPLE + "Enter the following information. If you want the field not to be filtered, enter 0");
        System.out.print(Color.YELLOW + "Enter the name of support: ");
        String name = ScannerWrapper.getInstance().nextLine();
        System.out.print(Color.YELLOW + "Enter the user name of user: ");
        String userName = ScannerWrapper.getInstance().nextLine();

        List<Support> supports = new ArrayList<>();
        for (Support support : myBank.getSupports()) {
            if (support.getName().equals(name) || "0".equals(name)) {
                if (support.getUserName().equals(userName) || "0".equals(userName)) {
                    supports.add(support);
                }
            }
        }
        printSupports(supports);
    }

    public void printTheInformationSupport(Support support) {
        System.out.print(Color.BLUE + "name: " + Color.GREEN + support.getName() + " ");
        System.out.println(Color.BLUE + "user name: " + Color.GREEN + support.getUserName());
    }

    public void showManagers(Bank myBank) {
        if (myBank.getSupports().isEmpty()) {
            System.out.println(Color.RED + "The list is empty!");
            return;
        }

        System.out.println(Color.BLUE + "1- Show all managers");
        System.out.println(Color.BLUE + "2- Filter managers");
        System.out.print(Color.CYAN + "Chose (1 - 2)");
        String input = ScannerWrapper.getInstance().nextLine();

        switch (input) {
            case "1" -> printManagers(myBank.getManagers());
            case "2" -> filterManagers(myBank);
            default -> System.out.println(Color.RED + "Invalid input!");
        }
    }

    public void printManagers(List<Manager> managers) {
        int index = 1;
        for (Manager manager : managers) {
            System.out.print(Color.PURPLE + index + "- ");
            System.out.println(Color.BLUE + "user name: " + Color.GREEN + manager.getUserName());
            index++;
        }
    }

    public void filterManagers(Bank myBank) {
        System.out.println(Color.PURPLE + "Enter the following information. If you want the field not to be filtered, enter 0");
        System.out.print(Color.YELLOW + "Enter the user name of manager: ");
        String userName = ScannerWrapper.getInstance().nextLine();

        List<Manager> managers = new ArrayList<>();
        for (Manager manager : myBank.getManagers()) {
            if (manager.getUserName().equals(userName) || "0".equals(userName)) {
                managers.add(manager);
            }
        }
        printManagers(managers);
    }


    public void handleAddUsers(Bank myBank, Manager manager) {
        System.out.println(Color.BLUE + "1- Manager");
        System.out.println(Color.BLUE + "2- Support");
        System.out.print(Color.CYAN + "Which one do you want add? ");
        String input = ScannerWrapper.getInstance().nextLine();

        switch (input) {
            case "1" -> addManager(myBank, manager);
            case "2" -> addSupport(myBank);
            default -> System.out.println(Color.RED + "Invalid input!");
        }
    }

    public void addManager(Bank myBank, Manager manager) {
        System.out.print(Color.YELLOW + "Enter the user name of manager: ");
        String userName = ScannerWrapper.getInstance().nextLine();
        System.out.print(Color.YELLOW + "Enter the password of manager: ");
        String password = ScannerWrapper.getInstance().nextLine();

        UserHandler userHandler = new UserHandler();
        if (userHandler.checkPassword(password)) {
            List<Manager> fathersManager = manager.getFathersManager();
            fathersManager.add(manager);
            myBank.addManager(new Manager(userName, password, fathersManager));
        } else {
            System.out.println(Color.RED + "Password is unsafe");
        }
    }


    public void addSupport(Bank myBank) {
        System.out.print(Color.YELLOW + "Enter the name of support: ");
        String name = ScannerWrapper.getInstance().nextLine();
        System.out.print(Color.YELLOW + "Enter the user name of support: ");
        String userName = ScannerWrapper.getInstance().nextLine();
        System.out.print(Color.YELLOW + "Enter the password of support: ");
        String password = ScannerWrapper.getInstance().nextLine();

        UserHandler userHandler = new UserHandler();
        if (userHandler.checkPassword(password)) {
            myBank.addSupport(new Support(name, userName, password));
        } else {
            System.out.println(Color.RED + "Password is unsafe");
        }
    }


    public void handleBlockingUsers(Bank myBank, Manager manager) {
        int index = 1;
        for (UserAccount entry : myBank.getUserAccounts()) {
            System.out.print(Color.PURPLE + index + "- " + Color.BLUE + entry.getFirstName() + " " + entry.getLastName() + " ");
            if (entry.getIsBlocked()) {
                System.out.println(Color.RED + "blocked");
            } else {
                System.out.println(Color.GREEN + "unblocked");
            }
            index++;
        }

        for (Support entry : myBank.getSupports()) {
            System.out.print(Color.PURPLE + index + "- " + Color.BLUE + entry.getName() + " ");
            if (entry.getIsBlocked()) {
                System.out.println(Color.RED + "blocked");
            } else {
                System.out.println(Color.GREEN + "unblocked");
            }
            index++;
        }

        for (Manager entry : myBank.getManagers()) {
            System.out.print(Color.PURPLE + index + "- " + Color.BLUE + entry.getUserName() + " ");
            if (entry.getIsBlocked()) {
                System.out.println(Color.RED + "blocked");
            } else {
                System.out.println(Color.GREEN + "unblocked");
            }
            index++;
        }
        inputIndex(myBank, manager);
    }

    public void inputIndex(Bank myBank, Manager manager) {
        System.out.println(Color.YELLOW + "Enter the index of user: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (Exception e) {
            return;
        }

        if (number > myBank.getManagers().size() + myBank.getSupports().size() + myBank.getUserAccounts().size() && number <= 0) {
            System.out.println(Color.RED + "Enter index in the rage!");
            return;
        }

        if (number <= myBank.getUserAccounts().size()) {
            blockUser(myBank.getUserAccounts().get(number - 1));
        } else if (number <= myBank.getUserAccounts().size() + myBank.getSupports().size()) {
            blockSupport(myBank.getSupports().get(number - myBank.getUserAccounts().size() - 1));
        } else {
            blockManager(manager, myBank.getManagers().get(number - myBank.getUserAccounts().size() - myBank.getSupports().size() - 1));
        }
    }

    public void blockUser(UserAccount user) {
        if (user.getIsBlocked()) {
            System.out.println(Color.CYAN + "If you want unblock this user?");
            System.out.println(Color.BLUE + "1- yes");
            System.out.println(Color.BLUE + "2- no");
            String input = ScannerWrapper.getInstance().nextLine();
            if ("1".equals(input)) {
                user.setIsBlocked(false);
            }
        } else {
            System.out.println(Color.CYAN + "If you want block this user?");
            System.out.println(Color.BLUE + "1- yes");
            System.out.println(Color.BLUE + "2- no");
            String input = ScannerWrapper.getInstance().nextLine();
            if ("1".equals(input)) {
                user.setIsBlocked(true);
            }
        }
    }

    public void blockSupport(Support support) {
        if (support.getIsBlocked()) {
            System.out.println(Color.CYAN + "If you want unblock this user?");
            System.out.println(Color.BLUE + "1- yes");
            System.out.println(Color.BLUE + "2- no");
            String input = ScannerWrapper.getInstance().nextLine();
            if ("1".equals(input)) {
                support.setIsBlocked(false);
            }
        } else {
            System.out.println(Color.CYAN + "If you want block this user?");
            System.out.println(Color.BLUE + "1- yes");
            System.out.println(Color.BLUE + "2- no");
            String input = ScannerWrapper.getInstance().nextLine();
            if ("1".equals(input)) {
                support.setIsBlocked(true);
            }
        }
    }

    public void blockManager(Manager manager, Manager blockedManager) {
        if (manager.getFathersManager().contains(blockedManager)) {
            System.out.println(Color.RED + "You can not block your boss!!!!!!");
            return;
        }
        if (blockedManager.getIsBlocked()) {
            System.out.println(Color.CYAN + "If you want unblock this user?");
            System.out.println(Color.BLUE + "1- yes");
            System.out.println(Color.BLUE + "2- no");
            String input = ScannerWrapper.getInstance().nextLine();
            if ("1".equals(input)) {
                blockedManager.setIsBlocked(false);
            }
        } else {
            System.out.println(Color.CYAN + "If you want block this user?");
            System.out.println(Color.BLUE + "1- yes");
            System.out.println(Color.BLUE + "2- no");
            String input = ScannerWrapper.getInstance().nextLine();
            if ("1".equals(input)) {
                blockedManager.setIsBlocked(true);
            }
        }
    }

    public void handleEditUsers(Bank myBank) {

    }
}

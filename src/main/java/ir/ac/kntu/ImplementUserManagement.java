package ir.ac.kntu;


public class ImplementUserManagement {

    public void handleShowUsers(Bank myBank){

    }

    public void handleAddUsers(Bank myBank){
        System.out.println(Color.BLUE + "1- Manager");
        System.out.println(Color.BLUE + "2- Support");
        System.out.print(Color.CYAN + "Which one do you want add? ");
        String input = ScannerWrapper.getInstance().nextLine();

        switch (input){
            case "1" -> addManager(myBank);
            case "2" -> addSupport(myBank);
            default -> System.out.println(Color.RED + "Invalid input!");
        }
    }

    public void addManager(Bank myBank){
        System.out.print(Color.YELLOW + "Enter the user name of manager: ");
        String userName = ScannerWrapper.getInstance().nextLine();
        System.out.print(Color.YELLOW + "Enter the password of manager: ");
        String password = ScannerWrapper.getInstance().nextLine();

        UserHandler userHandler = new UserHandler();
        if (userHandler.checkPassword(password)) {
            myBank.addManager(new Manager(userName, password));
        } else {
            System.out.println(Color.RED + "Password is unsafe");
        }
    }


    public void addSupport(Bank myBank){
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


    public void handleBlockingUsers(Bank myBank){

    }

    public void handleEditUsers(Bank myBank){

    }
}

package ir.ac.kntu;

public class SupportHandler {
    public static void implementTheSupportMenu(Bank myBank) {
        addSupports(myBank);
        Support support = logInSupport(myBank);
        if (support != null) {
            //////menu
        }
    }

    public static void addSupports(Bank myBank) {
        Support newSupport1 = new Support();
        Support newSupport2 = new Support();
        Support newSupport3 = new Support();

        myBank.getSupports().add(newSupport1);
        myBank.getSupports().add(newSupport2);
        myBank.getSupports().add(newSupport3);
    }

    public static Support logInSupport(Bank myBank) {
        Support support = new Support();

        System.out.print("Enter your name: ");
        String name = ScannerWrapper.getInstance().next();

        System.out.print("Enter your user name: ");
        String userName = ScannerWrapper.getInstance().next();

        System.out.print("Enter your password: ");
        String password = ScannerWrapper.getInstance().next();

        boolean isTrueInformation = checkInformation(myBank, name, userName, password);
        if (isTrueInformation) {
            support.setFirstName(name);
            support.setUserName(userName);
            support.setPassword(password);
        }
        return support;
    }

    public static boolean checkInformation(Bank myBank, String name, String userName, String password) {
        for (Support entry : myBank.getSupports()) {
            if (entry.getFirstName().equals(name) && entry.getUserName().equals(userName) && entry.getPassword().equals(password)) {
                return true;
            }
        }
        return false;

    }
}

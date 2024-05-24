package ir.ac.kntu;

public class SupportHandler {
    public static void implementTheSupportMenu(Bank myBank) {
        addSupports(myBank);
        Support support = logInSupport(myBank);
        if (support != null) {
            SupportMenu supportMenu = new SupportMenu();
            supportMenu.handleSupportOptions(myBank);
        }
    }

    public static void addSupports(Bank myBank) {
        myBank.getSupports().add(new Support("ali", "aliprs", "@Ap84"));
        myBank.getSupports().add(new Support("nima", "nimadan", "#Nd83"));
        myBank.getSupports().add(new Support("amir", "amirmkh", "&Am85"));
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
            support.setName(name);
            support.setUserName(userName);
            support.setPassword(password);
        }
        return support;
    }

    public static boolean checkInformation(Bank myBank, String name, String userName, String password) {
        for (Support entry : myBank.getSupports()) {
            if (entry.getName().equals(name) && entry.getUserName().equals(userName) && entry.getPassword().equals(password)) {
                return true;
            }
        }
        return false;

    }
}

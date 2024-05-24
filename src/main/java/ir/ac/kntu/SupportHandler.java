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

        System.out.print(Color.YELLOW + "Enter your user name: ");
        String userName = ScannerWrapper.getInstance().next();

        System.out.print(Color.YELLOW + "Enter your password: ");
        String password = ScannerWrapper.getInstance().next();

        boolean isTrueInformation = checkInformation(myBank, userName, password);
        if (isTrueInformation) {
            support.setUserName(userName);
            support.setPassword(password);
            return support;
        }
        System.out.println(Color.RED + "There is not Admin with this information!");
        return null;
    }

    public static boolean checkInformation(Bank myBank, String userName, String password) {
        for (Support entry : myBank.getSupports()) {
            if (entry.getUserName().equals(userName) && entry.getPassword().equals(password)) {
                return true;
            }
        }
        return false;

    }
}

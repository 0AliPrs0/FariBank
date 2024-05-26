package ir.ac.kntu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImplementSetting {
    public void handleChangePassword(UserAccount myAccount) {
        System.out.println(Color.YELLOW + "Enter new password: ");
        String password = ScannerWrapper.getInstance().nextLine();

        boolean isSafePassword = new UserHandler().checkPassword(password);
        if (isSafePassword) {
            myAccount.setPassword(password);
            System.out.println(Color.GREEN + "Password was changed");
        }
    }

    public void handleRegisterCardPassword(UserAccount myAccount) {
        if (myAccount.getCardPassword() == -1) {
            System.out.println(Color.YELLOW + "Enter your card password: ");
            String inputStr = ScannerWrapper.getInstance().nextLine();
            boolean isValidPassword = checkValidPassword(inputStr);
            if (!isValidPassword) {
                System.out.println(Color.RED + "invalid password!");
                return;
            }
            int cardPassword = convertStrToInt(inputStr);
            if (cardPassword != -1) {
                myAccount.setCardPassword(cardPassword);
                System.out.println(Color.GREEN + "Your card password was registered");
            }
        } else {
            System.out.println(Color.RED + "Your card password is already registered!");
        }
    }

    public int convertStrToInt(String input) {
        int cardPassword;
        try {
            cardPassword = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println(Color.RED + "invalid password!");
            return -1;
        }
        return cardPassword;
    }

    public void handleChangeCardPassword(UserAccount myAccount) {
        if (myAccount.getCardPassword() != -1) {
            System.out.print(Color.YELLOW + "Enter new card password: ");
            String inputStr = ScannerWrapper.getInstance().nextLine();
            boolean isValidPassword = checkValidPassword(inputStr);
            if (!isValidPassword) {
                System.out.println(Color.RED + "invalid password!");
                return;
            }
            int cardPassword = convertStrToInt(inputStr);
            if (cardPassword != -1) {
                myAccount.setCardPassword(cardPassword);
                System.out.println(Color.GREEN + "Your card password was changed");
            }
        } else {
            System.out.println(Color.RED + "Your card password is not registered!");
        }
    }

    public boolean checkValidPassword(String inputStr) {
        String patternStr = "\\d{4}";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }


    public void handleActivationContactKeyword(UserAccount myAccount) {
        if (!myAccount.getIsActingContactKeyword()) {
            myAccount.setIsActingContactKeyword(true);
            System.out.println(Color.GREEN + "Contact keyword has been active");
        } else {
            System.out.println(Color.RED + "Contact keyword already is active!");
        }
    }

    public void handleInactivationContactKeyword(UserAccount myAccount) {
        if (myAccount.getIsActingContactKeyword()) {
            myAccount.setIsActingContactKeyword(false);
            System.out.println(Color.GREEN + "Contact keyword has been inactive");
        } else {
            System.out.println(Color.RED + "Contact keyword already is not active!");
        }
    }

}

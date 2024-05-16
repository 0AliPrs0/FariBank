package ir.ac.kntu;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.*;

public class UserHandler {
    public static void implementTheUserMenu(Bank myBank) {
        FirstUserMenu.UserMenu option;

        do {
            FirstUserMenu.getInstance().printTheMenu();
            option = FirstUserMenu.getInstance().getOption();
            handleTheUserMenu(option, myBank);
        } while (option != FirstUserMenu.UserMenu.RETURN);
    }

    public static void handleTheUserMenu(FirstUserMenu.UserMenu option, Bank myBank) {
        switch (option) {
            case LOG_IN -> handleLogIn(myBank);
            case SIGN_UP -> handleSignUp(myBank);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid input");
        }
    }

    public static void handleLogIn(Bank myBank) {
        System.out.println("Enter your phone number: ");
        String phoneNumber = ScannerWrapper.getInstance().next();

        System.out.println("Enter your password: ");
        String password = ScannerWrapper.getInstance().next();

        UserAccount newUser = lookForUser(myBank, phoneNumber, password);
        if (newUser == null) {
            Authentication newAuthentication = checkInformationAsRequest(phoneNumber, password, myBank);
            firstLogIn(newAuthentication, myBank);
        } else {
            UserOptions.handleUserOptions(myBank, newUser);
        }
    }

    public static UserAccount lookForUser(Bank myBank, String phoneNumber, String password) {
        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getPhoneNumber().equals(phoneNumber) && entry.getPassword().equals(password)) {
                return entry;
            }
        }
        return null;
    }

    public static Authentication checkInformationAsRequest(String phoneNumber, String password, Bank myBank) {
        for (Authentication entry : myBank.getAuthentications()) {
            if (entry.getPhoneNumber().equals(phoneNumber) && entry.getPassword().equals(password)) {
                return entry;
            }
        }
        System.out.println("Invalid information");
        return null;
    }


    public static void firstLogIn(Authentication newAuthentication, Bank myBank) {
        if (newAuthentication == null) {
            return;
        }

        boolean checkedSupport = newAuthentication.isCheckSupport();
        boolean acceptInformation = newAuthentication.isAcceptRequest();

        if (!checkedSupport) {
            System.out.println("The support has not yet checked the information!");
        } else if (!acceptInformation) {
            System.out.println("Support massage: " + newAuthentication.getSupportOpinion());
        } else {
            UserAccount newUser = addUser(myBank, newAuthentication);
            UserOptions.handleUserOptions(myBank, newUser);
        }
    }

    public static UserAccount addUser(Bank myBank, Authentication newAuthentication) {
        String fName = newAuthentication.getFirstName();
        String lName = newAuthentication.getLastName();
        String phoneNumber = newAuthentication.getPhoneNumber();
        String id = newAuthentication.getId();
        String password = newAuthentication.getPassword();

        int min = 10000000;
        int max = 99999999;
        int accountNumber = ThreadLocalRandom.current().nextInt(min, max + 1);

        UserAccount user = new UserAccount(fName, lName, phoneNumber, id, password, accountNumber);
        myBank.getUserAccounts().add(user);
        return user;
    }

    public static void handleSignUp(Bank myBank) {
        System.out.print("Enter your first name: ");
        String fName = ScannerWrapper.getInstance().next();

        System.out.print("Enter your last name: ");
        String lName = ScannerWrapper.getInstance().next();

        System.out.print("Enter your phone number: ");
        String phoneNumber = ScannerWrapper.getInstance().next();

        System.out.print("Enter your id: ");
        String id = ScannerWrapper.getInstance().next();

        System.out.print("Enter your password: ");
        String password = ScannerWrapper.getInstance().next();

        boolean isTrueCondition = checkCondition(myBank, phoneNumber, id, password);
        if (isTrueCondition) {
            Authentication newAuthentication = new Authentication(fName, lName, phoneNumber, id, password, "", false, false);
            myBank.getAuthentications().add(newAuthentication);
        }
    }

    public static boolean checkCondition(Bank myBank, String phoneNumber, String id, String password) {
        boolean isTherePhoneNumber = checkPhoneNumber(myBank, phoneNumber);
        boolean isThereId = checkId(myBank, id);
        boolean isPasswordSafe = checkPassword(password);

        if (!isTherePhoneNumber && !isThereId && isPasswordSafe) {
            System.out.println("Your information has been registered. Please wait until the authentication is confirmed.");
            System.out.println();
            return true;
        }
        return false;
    }

    public static boolean checkPhoneNumber(Bank myBank, String phoneNumber) {
        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("There is a same phone number in system!");
                System.out.println();
                return true;
            }
        }
        return false;
    }

    public static boolean checkId(Bank myBank, String id) {
        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getId().equals(id)) {
                System.out.println("There is a same id in system!");
                System.out.println();
                return true;
            }
        }
        return false;
    }

    public static boolean checkPassword(String password) {
        String patternSmallLetter = "[a-z]+";
        boolean isThereSmallLetter = findRegex(patternSmallLetter, password);

        String patternCapitalLetter = "[A-Z]+";
        boolean isThereCapitalLetter = findRegex(patternCapitalLetter, password);

        String patternDigit = "\\d+";
        boolean isThereDigit = findRegex(patternDigit, password);

        String patternSpecialCharacter = "[!#$%^]+";
        boolean isThereSpecialCharacter = findRegex(patternSpecialCharacter, password);

        if (isThereSmallLetter && isThereCapitalLetter && isThereDigit && isThereSpecialCharacter) {
            return true;
        }
        System.out.println("Incorrect Password!");
        System.out.println();
        return false;
    }

    public static boolean findRegex(String pattern, String password) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        if (m.find()) {
            return true;
        }
        return false;
    }
}

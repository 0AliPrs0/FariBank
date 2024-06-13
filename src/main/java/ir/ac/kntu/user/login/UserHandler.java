package ir.ac.kntu.user.login;

import ir.ac.kntu.main.baseclass.MockAccount;
import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.enums.ApplicationStatus;
import ir.ac.kntu.main.enums.RequestType;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.user.info.Requests;
import ir.ac.kntu.user.info.User;
import ir.ac.kntu.user.info.UserAccount;
import ir.ac.kntu.user.menus.UserMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserHandler {


    public void handleLogin(Bank myBank) {
        System.out.print(Color.YELLOW + "Enter your phone number: ");
        String phoneNumber = ScannerWrapper.getInstance().nextLine();

        System.out.print(Color.YELLOW + "Enter your password: ");
        String password = ScannerWrapper.getInstance().nextLine();

        UserAccount newUser = lookForUser(myBank, phoneNumber, password);
        if (newUser == null) {
            Requests newAuthentication = checkInformationAsRequest(phoneNumber, password, myBank);
            firstLogin(newAuthentication, myBank);
        } else {
            UserMenu userMenu = new UserMenu();
            if (!newUser.getIsBlocked()) {
                userMenu.implementMenu(myBank, newUser);
            } else {
                System.out.println(Color.RED + "You are blocked!!!!");
            }
        }
    }

    public UserAccount lookForUser(Bank myBank, String phoneNumber, String password) {
        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getPhoneNumber().equals(phoneNumber) && entry.getPassword().equals(password)) {
                return entry;
            }
        }
        return null;
    }

    public Requests checkInformationAsRequest(String phoneNumber, String password, Bank myBank) {
        for (Requests entry : myBank.getRequest().get(phoneNumber)) {
            if (entry.getRequestType().equals(RequestType.AUTHENTICATION) && entry.getPassword().equals(password)) {
                return entry;
            }
        }
        System.out.println(Color.RED + "Invalid information");
        return null;
    }


    public void firstLogin(Requests newAuthentication, Bank myBank) {
        if (newAuthentication == null) {
            return;
        }

        if (newAuthentication.getApplicationStatus().equals(ApplicationStatus.IN_PROGRESS)) {
            System.out.println(Color.RED + "The support has not yet checked the information!");
        } else if (newAuthentication.getApplicationStatus().equals(ApplicationStatus.IN_CLOSED)) {
            System.out.println(Color.RED + "The support has not verified your information");
            System.out.println(Color.BLUE + "Support massage: " + Color.GREEN + newAuthentication.getSupportMassage());
        } else {
            UserAccount newUser = addUser(myBank, newAuthentication);
            UserMenu userMenu = new UserMenu();
            if (!newUser.getIsBlocked()) {
                userMenu.implementMenu(myBank, newUser);
            } else {
                System.out.println(Color.RED + "You are blocked!!!!");
            }
        }
    }

    public UserAccount addUser(Bank myBank, Requests newAuthentication) {
        String fName = newAuthentication.getFirstName();
        String lName = newAuthentication.getLastName();
        String phoneNumber = newAuthentication.getPhoneNumber();
        String nationalId = newAuthentication.getNationalId();
        String password = newAuthentication.getPassword();

        int min = 10000000;
        int max = 99999999;
        int accountNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
        int simCardValidity = findMockAccount(myBank, phoneNumber);

        UserAccount user = new UserAccount(fName, lName, phoneNumber, nationalId, password, 0, accountNumber, simCardValidity);
        myBank.getUserAccounts().add(user);
        return user;
    }

    public int findMockAccount(Bank myBank, String phoneNumber) {
        for (MockAccount mockAccount : myBank.getMockAccounts()) {
            if (mockAccount.getPhoneNumber().equals(phoneNumber)) {
                return mockAccount.getSimCardValidity();
            }
        }
        return 0;
    }

    public void handleSignUp(Bank myBank) {
        System.out.print(Color.YELLOW + "Enter your first name: ");
        String fName = ScannerWrapper.getInstance().nextLine();

        System.out.print(Color.YELLOW + "Enter your last name: ");
        String lName = ScannerWrapper.getInstance().nextLine();

        System.out.print(Color.YELLOW + "Enter your phone number: ");
        String phoneNumber = ScannerWrapper.getInstance().nextLine();

        System.out.print(Color.YELLOW + "Enter your id: ");
        String nationalId = ScannerWrapper.getInstance().nextLine();

        System.out.print(Color.YELLOW + "Enter your password: ");
        String password = ScannerWrapper.getInstance().nextLine();

        boolean isTrueCondition = checkCondition(myBank, phoneNumber, nationalId, password);
        if (isTrueCondition) {
            List<Requests> requests = userRequest(myBank.getRequest(), phoneNumber);
            User user = new User(fName, lName, phoneNumber, nationalId, password);
            Requests newAuthentication = new Requests(user, RequestType.AUTHENTICATION, ApplicationStatus.IN_PROGRESS, "", "");
            requests.add(newAuthentication);
            myBank.addRequest(phoneNumber, requests);
        }
    }

    public List<Requests> userRequest(Map<String, List<Requests>> requests, String phoneNumber) {
        List<Requests> requestsList = new ArrayList<>();
        for (Map.Entry<String, List<Requests>> entry : requests.entrySet()) {
            if (entry.getKey().equals(phoneNumber)) {
                requestsList = entry.getValue();
            }
        }
        return requestsList;
    }

    public boolean checkCondition(Bank myBank, String phoneNumber, String nationalId, String password) {
        boolean isPhoneNumber = checkPhoneNumber(myBank, phoneNumber);
        boolean isThereId = checkId(myBank, nationalId);
        boolean isPasswordSafe = checkPassword(password);

        if (!isPhoneNumber && !isThereId && isPasswordSafe) {
            System.out.println();
            System.out.println(Color.GREEN + "Your information has been registered. Please wait until the authentication is confirmed.");
            System.out.println();
            return true;
        }
        return false;
    }

    public boolean checkPhoneNumber(Bank myBank, String phoneNumber) {
        boolean isTruePhone = checkPhoneNumberFormat(phoneNumber);
        if (!isTruePhone) {
            System.out.println(Color.RED + "The format of phone number is not correct");
            return true;
        }
        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getPhoneNumber().equals(phoneNumber)) {
                System.out.println(Color.RED + "There is a same phone number in system!");
                System.out.println();
                return true;
            }
        }
        return false;
    }

    public boolean checkPhoneNumberFormat(String phoneNumber) {
        String patternStr = "\\d{11}";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public boolean checkId(Bank myBank, String nationalId) {
        boolean isTrueId = checkNationalIdFormat(nationalId);
        if (!isTrueId) {
            System.out.println(Color.RED + "The format of id is not correct");
            return true;
        }
        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getNationalId().equals(nationalId)) {
                System.out.println(Color.RED + "There is a same id in system!");
                System.out.println();
                return true;
            }
        }
        return false;
    }

    public boolean checkNationalIdFormat(String nationalId) {
        String patternStr = "\\d{10}";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(nationalId);
        return matcher.matches();
    }

    public boolean checkPassword(String password) {
        String pSmallLetter = "[a-z]+";
        boolean isSmallLetter = findRegex(pSmallLetter, password);

        String pCapitalLetter = "[A-Z]+";
        boolean isCapitalLetter = findRegex(pCapitalLetter, password);

        String pDigit = "\\d+";
        boolean isDigit = findRegex(pDigit, password);

        String pCharacter = "[@!#$%^]+";
        boolean isCharacter = findRegex(pCharacter, password);

        if (isSmallLetter && isCapitalLetter && isDigit && isCharacter) {
            return true;
        }
        System.out.println(Color.RED + "Password is unsafe!");
        System.out.println();
        return false;
    }

    public boolean findRegex(String patternStr, String password) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}

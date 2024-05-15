package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserHandler {
    public static void implementTheUserMenu() {
        UserMenu option;

        do {
            Menus.getInstance().printTheUserMenu();
            option = Menus.getInstance().getOptionUserMenu();
            handleTheUserMenu(option);
        } while (option != UserMenu.RETURN);
    }
    public static void handleTheUserMenu(UserMenu option) {
        switch (option) {
            case LOG_IN -> handleLogIn(myUser, requests);
            case SIGN_UP -> handleSignUp(myUser, requests);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid input");
        }
    }

    public static void handleLogIn(ArrayList<User> myUser, ArrayList<Request> requests) {
        inputForLogIn(scanner,myUser, requests);
    }

    public static void handleSignUp(ArrayList<User> myUser, ArrayList<Request> requests) {
        inputNewPerson();
    }

    public static void inputNewPerson(Scanner scanner, ArrayList<User> myUser, ArrayList<Request> requests) {
        System.out.print("Enter your first name: ");
        String fName = scanner.next();

        System.out.print("Enter your last name: ");
        String lName = scanner.next();

        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.next();

        System.out.print("Enter your id: ");
        String id = scanner.next();

        System.out.print("Enter your password: ");
        String password = scanner.next();

        boolean isTrueCondition = checkCondition(phoneNumber, id, password, myUser);
        if (isTrueCondition) {
            Request newRequest = new Request(fName, lName, phoneNumber, id, password, "", false, false);
            requests.add(newRequest);
        }
    }

    public static boolean checkCondition(String phoneNumber, String id, String password, ArrayList<User> myUser) {
        boolean isTherePhoneNumber = checkPhoneNumber(myUser, phoneNumber);
        boolean isThereId = checkId(myUser, id);
        boolean isPasswordSafe = checkPassword(password);

        if (isTherePhoneNumber && isThereId && !isPasswordSafe) {
            return false;
        }
        return true;
    }

    public static boolean checkPhoneNumber(ArrayList<User> myUser, String phoneNumber) {
        for (User entry : myUser) {
            if (entry.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("There is a same phone number in system!");
                System.out.println();
                return true;
            }
        }
        return false;
    }

    public static boolean checkId(ArrayList<User> myUser, String id) {
        for (User entry : myUser) {
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

    public void inputForLogIn(Scanner scanner,ArrayList<User> myUser, ArrayList<Request> requests){
        System.out.println("Enter your phone number: ");
        String phoneNumber = scanner.next();

        System.out.println("Enter your password: ");
        String password = scanner.next();

        User user = lookForUser(myUser, phoneNumber, password);//////////////new
        if (user == null) {
            Request newRequest = checkInformationAsRequest(phoneNumber, password, requests);/////////////new
            firstLogIn(newRequest, myUser);
        } else {
            ///////////////////////menu
        }
    }

    public User lookForUser(ArrayList<User> myUser, String phoneNumber, String password){
        for (User entry : myUser){
            if (entry.getPhoneNumber().equals(phoneNumber) && entry.getPassword().equals(password)){
                return entry;
            }
        }
        return null;
    }
    public Request checkInformationAsRequest(String phoneNumber, String password, ArrayList<Request> requests){
        for (Request entry : requests){
            if (entry.getPhoneNumber().equals(phoneNumber) && entry.getPassword().equals(password)){
                return entry;
            }
        }
        System.out.println("Invalid information");
        return null;
    }


    public void firstLogIn(Request newRequest, ArrayList<User> myUser){
        if (newRequest == null) {
            return;
        }
        boolean checkedSupport = newRequest.isCheckSupport();
        if (!checkedSupport) {
            System.out.println("The support has not yet checked the information!");
        }

        boolean acceptInformation = newRequest.isAcceptRequest();
        if (!acceptInformation) {
            System.out.println("Support massage: " + newRequest.getSupportOpinion());
        }

        if (checkedSupport && acceptInformation) {
            User user = addUser(myUser, newRequest);///////////////new
            ////////////////////////////////menu
        }
    }
    public User addUser(ArrayList<User> myUser, Request newRequest){
        String fName = newRequest.getFirstName();
        String lName = newRequest.getLastName();
        String phoneNumber = newRequest.getPhoneNumber();
        String id = newRequest.getId();
        String password = newRequest.getPassword();

        int min = 10000000;
        int max = 99999999;
        int accountNumber = ThreadLocalRandom.current().nextInt(min, max + 1);

        User user = new User(fName, lName, phoneNumber, id, password, accountNumber);
        myUser.add(user);
        return user;
    }
}

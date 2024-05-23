package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupportOptions {
    public static void handleSupportOptions(Bank myBank, Support support) {
        Menus.MenuSupport option;

        do {
            Menus.getInstance().printTheSupportMenu();
            option = Menus.getInstance().getOptionSupportMenu();
//            handleTheSupportMenu(option, myBank, support);
        } while (option != Menus.MenuSupport.RETURN);

    }

    public  void handleTheSupportMenu(Menus.MenuSupport options, Bank myBank, Support support) {
        switch (options) {
            case AUTHENTICATION -> authentication(myBank);
            case REQUESTS -> request(myBank);
            case USERS -> usersInformation(myBank);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }

    public static void authentication(Bank myBank) {
        List<Authentication> authentications = new ArrayList<>();
        for (int i = 0; i < myBank.getAuthentications().size(); i++) {
            if (!myBank.getAuthentications().get(0).isCheckSupport()) {
                authentications.add(myBank.getAuthentications().get(i));
            }
        }

        for (int i = 0; i < authentications.size(); i++) {
            String name = authentications.get(i).getFirstName() + authentications.get(i).getLastName();
            System.out.println((i + 1) + "- " + name);
        }

        System.out.println("Enter the number of authentication");
        int numberOfAuthentication = ScannerWrapper.getInstance().nextInt();
        Authentication authentication = authentications.get(numberOfAuthentication - 1);
        showUserForAuthentication(authentication, myBank);
    }

    public static void showUserForAuthentication(Authentication authentication, Bank myBank) {
        int index = 0;
        for (int i = 0; i < myBank.getAuthentications().size(); i++) {
            if (myBank.getAuthentications().get(i).getPhoneNumber().equals(authentication.getPhoneNumber())) {
                index = i;
            }
        }
        myBank.getAuthentications().get(index).setCheckSupport(true);
        System.out.println(authentication.toString());
        System.out.println("Do you confirm this user ?");
        System.out.println("1- Yes");
        System.out.println("2- No");
        int input = ScannerWrapper.getInstance().nextInt();

        if (input == 1) {
            myBank.getAuthentications().get(index).setAcceptRequest(true);
        } else {
            System.out.println("Enter the reason for rejecting this user's authentication");
            String supportOpinion = ScannerWrapper.getInstance().nextLine();
            myBank.getAuthentications().get(index).setSupportOpinion(supportOpinion);
        }

    }

    public static void request(Bank myBank) {
        Menus.RequestSupport option;

        do {
            Menus.getInstance().printTheRequestSupport();
            option = Menus.getInstance().getOptionRequestSupport();
            handleRequestSupport(option, myBank);
        } while (option != Menus.RequestSupport.RETURN);
    }

    public static void handleRequestSupport(Menus.RequestSupport option, Bank myBank) {
        switch (option) {
            case ALL_REQUEST -> showRequest(myBank, myBank.getRequest());
            case REQUEST_ACCORDING_TO_REQUEST_TYPE -> requestAccordingToRequestType(myBank);
            case REQUEST_ACCORDING_TO_APPLICATION_STATUS -> requestAccordingToApplicationStatus(myBank);
            case REQUEST_ACCORDING_TO_USER -> requestAccordingToUser(myBank);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }

    public static void requestAccordingToRequestType(Bank myBank) {
        System.out.print("Enter the request type: ");
        String requestType = ScannerWrapper.getInstance().next();
        Map<String, Requests> requests = new HashMap<>();
        for (Map.Entry<String, Requests> entry : myBank.getRequest().entrySet()) {
            if (entry.getValue().getRequestType().equals(requestType)) {
                requests.put(entry.getKey(), entry.getValue());
            }
        }
        showRequest(myBank, requests);
    }

    public static void requestAccordingToApplicationStatus(Bank myBank) {
        System.out.print("Enter the application status: ");
        String applicationStatus = ScannerWrapper.getInstance().next();
        Map<String, Requests> requests = new HashMap<>();
        for (Map.Entry<String, Requests> entry : myBank.getRequest().entrySet()) {
            if (entry.getValue().getApplicationStatus().equals(applicationStatus)) {
                requests.put(entry.getKey(), entry.getValue());
            }
        }
        showRequest(myBank, requests);
    }

    public static void requestAccordingToUser(Bank myBank) {
        System.out.print("Enter the phoneNumber of user: ");
        String phoneNumberOfUser = ScannerWrapper.getInstance().next();
        Map<String, Requests> requests = new HashMap<>();
        for (Map.Entry<String, Requests> entry : myBank.getRequest().entrySet()) {
            if (entry.getKey().equals(phoneNumberOfUser)) {
                requests.put(entry.getKey(), entry.getValue());
            }
        }
        showRequest(myBank, requests);
    }

    public static void showRequest(Bank myBank, Map<String, Requests> requests) {
        List<String> phoneNumbers = new ArrayList<>();
        Map<Integer, Requests> requestsMap = new HashMap<>();
        int index = 1;
        for (Map.Entry<String, Requests> entry : requests.entrySet()) {
            phoneNumbers.add(entry.getKey());
            requestsMap.put(index, entry.getValue());
            System.out.println(index + "- " + entry.getValue().toString());
            index++;
        }
        System.out.print("Chose number of request: ");
        int indexOfRequest = ScannerWrapper.getInstance().nextInt();

        String phoneNumber = phoneNumbers.get(indexOfRequest - 1);
        Requests request = requestsMap.get(indexOfRequest);
        request.setApplicationStatus(ApplicationStatus.IN_PROGRESS);

        sendSupportMassage(myBank, phoneNumber, request);
    }

    public static void sendSupportMassage(Bank myBank, String phoneNumber, Requests request) {
        System.out.println("If you want fix this problem");
        System.out.println("1- Yes");
        System.out.println("2- No");
        int input = ScannerWrapper.getInstance().nextInt();
        if (input == 2) {
            return;
        }

        System.out.print("Enter your massage to user for this problem: ");
        String massageSupport = ScannerWrapper.getInstance().nextLine();
        String userMassage = request.getUserMassage();
        request.setApplicationStatus(ApplicationStatus.IN_CLOSED);

        for (Map.Entry < String, Requests > entry :myBank.getRequest().entrySet()){
            if (entry.getKey().equals(phoneNumber) && entry.getValue().getUserMassage().equals(userMassage)) {
                entry.getValue().setSupportMassage(massageSupport);
            }
        }
    }

    public static void usersInformation(Bank myBank){
        Menus.UserInformation option;
        do {
            Menus.getInstance().printTheUserInformationMenu();
            option = Menus.getInstance().getOptionUserInformation();
            handleUserInformation(option, myBank);
        } while (option != Menus.UserInformation.RETURN);
    }

    public static void handleUserInformation(Menus.UserInformation option, Bank myBank){
        switch (option) {
            case ALL_USER -> showAllUser(myBank.getUserAccounts());
            case SEARCH_ACCORDING_TO_FIRST_NAME -> searchAccordingToFirstName(myBank);
            case SEARCH_ACCORDING_TO_LAST_NAME -> searchAccordingToLastName(myBank);
            case SEARCH_ACCORDING_TO_PHONE_NUMBER -> searchAccordingToPhoneNumber(myBank);
            case SEARCH_ACCORDING_TO_FIRST_NAME_AND_LAST_NAME -> searchAccordingToFirstNameAndLastName(myBank);
            case SEARCH_ACCORDING_TO_FIRST_NAME_AND_PHONE_NUMBER -> searchAccordingToFirstNameAndPhoneNumber(myBank);
            case SEARCH_ACCORDING_TO_LAST_NAME_AND_PHONE_NUMBER -> searchAccordingToLastNameAndPhoneNumber(myBank);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }

    public static void showAllUser(List<UserAccount> userAccount){
        int index = 1;
        for (UserAccount entry : userAccount){
            System.out.println(index + "- " + entry.getFirstName() + entry.getLastName());
            index++;
        }
        System.out.println("Enter the index of user: ");
        int userIndex = ScannerWrapper.getInstance().nextInt();
        UserAccount user = userAccount.get(userIndex - 1);
        printTheInformationUser(user);
    }

    public static void searchAccordingToFirstName(Bank myBank){
        List<UserAccount> userAccounts = new ArrayList<>();
        System.out.println("Enter the first name of user: ");
        String firstNameOfUser = ScannerWrapper.getInstance().nextLine();

        for (UserAccount entry : myBank.getUserAccounts()){
            if (entry.getFirstName().equals(firstNameOfUser)) {
                userAccounts.add(entry);
            }
        }
        searchResult(userAccounts);
    }

    public static void searchAccordingToLastName(Bank myBank){
        List<UserAccount> userAccounts = new ArrayList<>();
        System.out.println("Enter the last name of user: ");
        String lastNameOfUser = ScannerWrapper.getInstance().nextLine();

        for (UserAccount entry : myBank.getUserAccounts()){
            if (entry.getLastName().equals(lastNameOfUser)) {
                userAccounts.add(entry);
            }
        }
        searchResult(userAccounts);
    }

    public static void searchAccordingToPhoneNumber(Bank myBank){
        List<UserAccount> userAccounts = new ArrayList<>();
        System.out.println("Enter the phone number of user: ");
        String phoneNumberOfUser = ScannerWrapper.getInstance().nextLine();

        for (UserAccount entry : myBank.getUserAccounts()){
            if (entry.getPhoneNumber().equals(phoneNumberOfUser)) {
                userAccounts.add(entry);
            }
        }
        searchResult(userAccounts);
    }

    public static void searchAccordingToFirstNameAndLastName(Bank myBank){
        List<UserAccount> userAccounts = new ArrayList<>();
        System.out.println("Enter the first name of user: ");
        String firstNameOfUser = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the last name of user: ");
        String lastNameOfUser = ScannerWrapper.getInstance().nextLine();

        for (UserAccount entry : myBank.getUserAccounts()){
            if (entry.getFirstName().equals(firstNameOfUser) && entry.getLastName().equals(lastNameOfUser)) {
                userAccounts.add(entry);
            }
        }
        searchResult(userAccounts);
    }

    public static void searchAccordingToFirstNameAndPhoneNumber(Bank myBank){
        List<UserAccount> userAccounts = new ArrayList<>();
        System.out.println("Enter the first name of user: ");
        String firstNameOfUser = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the phone number of user: ");
        String phoneNumberOfUser = ScannerWrapper.getInstance().nextLine();

        for (UserAccount entry : myBank.getUserAccounts()){
            if (entry.getFirstName().equals(firstNameOfUser) && entry.getPhoneNumber().equals(phoneNumberOfUser)) {
                userAccounts.add(entry);
            }
        }
        searchResult(userAccounts);
    }

    public static void searchAccordingToLastNameAndPhoneNumber(Bank myBank){
        List<UserAccount> userAccounts = new ArrayList<>();
        System.out.println("Enter the last name of user: ");
        String lastNameOfUser = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the phone number of user: ");
        String phoneNumberOfUser = ScannerWrapper.getInstance().nextLine();

        for (UserAccount entry : myBank.getUserAccounts()){
            if (entry.getLastName().equals(lastNameOfUser) && entry.getPhoneNumber().equals(phoneNumberOfUser)) {
                userAccounts.add(entry);
            }
        }
        searchResult(userAccounts);
    }

    public static void searchResult(List<UserAccount> userAccounts){
        if (userAccounts.isEmpty()) {
            System.out.println("Not found user!");
        } else if (userAccounts.size() == 1) {
            printTheInformationUser(userAccounts.get(0));
        }else {
            showAllUser(userAccounts);
        }
    }

    public static void printTheInformationUser(UserAccount user){
        System.out.println("name: " + user.getFirstName() + user.getLastName());
        System.out.println("phone number: " + user.getPhoneNumber());
        System.out.println("account number: " + user.getAccountNumber());
        System.out.println("transactions :");
        System.out.println("charge account: " + user.getChargeAccounts());
        System.out.println("transfers: " + user.getTransfers());
    }
}

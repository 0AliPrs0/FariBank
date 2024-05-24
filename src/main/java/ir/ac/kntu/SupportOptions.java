package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupportOptions {


    public void authentication(Bank myBank) {
        List<Authentication> authentications = new ArrayList<>();
        for (int i = 0; i < myBank.getAuthentications().size(); i++) {
            if (!myBank.getAuthentications().get(i).isCheckSupport()) {
                authentications.add(myBank.getAuthentications().get(i));
            }
        }

        for (int i = 0; i < authentications.size(); i++) {
            String name = authentications.get(i).getFirstName() + authentications.get(i).getLastName();
            System.out.println(Color.GREEN + (i + 1) + "- " + Color.BLUE + name);
        }

        System.out.println(Color.YELLOW + "Enter the number of authentication");
        String input = ScannerWrapper.getInstance().nextLine();
        int numberOfAuthentication;
        try{
            numberOfAuthentication = Integer.parseInt(input);
        }catch (Exception e){
            return;
        }
        Authentication authentication = authentications.get(numberOfAuthentication - 1);
        showUserForAuthentication(authentication, myBank);
    }

    public void showUserForAuthentication(Authentication authentication, Bank myBank) {
        int index = 0;
        for (int i = 0; i < myBank.getAuthentications().size(); i++) {
            if (myBank.getAuthentications().get(i).getPhoneNumber().equals(authentication.getPhoneNumber())) {
                index = i;
            }
        }
        myBank.getAuthentications().get(index).setCheckSupport(true);
        System.out.println(authentication.toString());
        System.out.println(Color.YELLOW + "Do you confirm this user ?");
        System.out.println(Color.BLUE + "1- Yes");
        System.out.println(Color.BLUE + "2- No");
        String input = ScannerWrapper.getInstance().nextLine();
        int number;
        try{
            number = Integer.parseInt(input);
        }catch (Exception e){
            return;
        }

        if (number == 1) {
            myBank.getAuthentications().get(index).setAcceptRequest(true);
        } else {
            System.out.println(Color.YELLOW + "Enter the reason for rejecting this user's authentication");
            String supportOpinion = ScannerWrapper.getInstance().nextLine();
            myBank.getAuthentications().get(index).setSupportOpinion(supportOpinion);
        }

    }

    public void requestAccordingToRequestType(Bank myBank) {
        System.out.print(Color.YELLOW + "Enter the request type: ");
        String requestType = ScannerWrapper.getInstance().nextLine();
        Map<String, Requests> requests = new HashMap<>();
        for (Map.Entry<String, Requests> entry : myBank.getRequest().entrySet()) {
            if (entry.getValue().getRequestType().equals(requestType)) {
                requests.put(entry.getKey(), entry.getValue());
            }
        }
        showRequest(myBank, requests);
    }

    public void requestAccordingToApplicationStatus(Bank myBank) {
        System.out.print(Color.YELLOW + "Enter the application status: ");
        String applicationStatus = ScannerWrapper.getInstance().nextLine();
        Map<String, Requests> requests = new HashMap<>();
        for (Map.Entry<String, Requests> entry : myBank.getRequest().entrySet()) {
            if (entry.getValue().getApplicationStatus().equals(applicationStatus)) {
                requests.put(entry.getKey(), entry.getValue());
            }
        }
        showRequest(myBank, requests);
    }

    public void requestAccordingToUser(Bank myBank) {
        System.out.print(Color.YELLOW + "Enter the phoneNumber of user: ");
        String phoneNumberOfUser = ScannerWrapper.getInstance().nextLine();
        Map<String, Requests> requests = new HashMap<>();
        for (Map.Entry<String, Requests> entry : myBank.getRequest().entrySet()) {
            if (entry.getKey().equals(phoneNumberOfUser)) {
                requests.put(entry.getKey(), entry.getValue());
            }
        }
        showRequest(myBank, requests);
    }

    public void showRequest(Bank myBank, Map<String, Requests> requests) {
        if(requests.isEmpty()){
            System.out.println(Color.RED + "The list is empty!");
            return;
        }
        List<String> phoneNumbers = new ArrayList<>();
        Map<Integer, Requests> requestsMap = new HashMap<>();
        int index = 1;
        for (Map.Entry<String, Requests> entry : requests.entrySet()) {
            phoneNumbers.add(entry.getKey());
            requestsMap.put(index, entry.getValue());
            System.out.println(Color.GREEN + index + "- " + Color.BLUE + entry.getValue().toString());
            index++;
        }
        System.out.print(Color.YELLOW + "Chose number of request: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int number;
        try{
            number = Integer.parseInt(input);
        }catch (Exception e){
            return;
        }

        String phoneNumber = phoneNumbers.get(number - 1);
        Requests request = requestsMap.get(number);
        request.setApplicationStatus(ApplicationStatus.IN_PROGRESS);

        sendSupportMassage(myBank, phoneNumber, request);
    }

    public void sendSupportMassage(Bank myBank, String phoneNumber, Requests request) {
        System.out.println(Color.YELLOW + "If you want fix this problem");
        System.out.println(Color.BLUE + "1- Yes");
        System.out.println(Color.BLUE + "2- No");
        String input = ScannerWrapper.getInstance().nextLine();
        int number;
        try{
            number = Integer.parseInt(input);
        }catch (Exception e){
            return;
        }
        if (number == 2) {
            return;
        }

        System.out.print(Color.YELLOW + "Enter your massage to user for this problem: ");
        String massageSupport = ScannerWrapper.getInstance().nextLine();
        String userMassage = request.getUserMassage();
        request.setApplicationStatus(ApplicationStatus.IN_CLOSED);

        for (Map.Entry<String, Requests> entry : myBank.getRequest().entrySet()) {
            if (entry.getKey().equals(phoneNumber) && entry.getValue().getUserMassage().equals(userMassage)) {
                entry.getValue().setSupportMassage(massageSupport);
            }
        }
    }

    public void showAllUser(List<UserAccount> userAccount) {
        if (userAccount.isEmpty()) {
            System.out.println(Color.RED + "The list is empty!");
            return;
        }
        int index = 1;
        for (UserAccount entry : userAccount) {
            System.out.println(Color.GREEN + index + "- " + Color.BLUE + entry.getFirstName() + entry.getLastName());
            index++;
        }
        System.out.println(Color.YELLOW + "Enter the index of user: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int number;
        try{
            number = Integer.parseInt(input);
        }catch (Exception e){
            return;
        }
        UserAccount user = userAccount.get(number - 1);
        printTheInformationUser(user);
    }

    public void searchAccordingToFirstName(Bank myBank) {
        List<UserAccount> userAccounts = new ArrayList<>();
        System.out.println(Color.YELLOW + "Enter the first name of user: ");
        String firstNameOfUser = ScannerWrapper.getInstance().nextLine();

        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getFirstName().equals(firstNameOfUser)) {
                userAccounts.add(entry);
            }
        }
        searchResult(userAccounts);
    }

    public void searchAccordingToLastName(Bank myBank) {
        List<UserAccount> userAccounts = new ArrayList<>();
        System.out.println(Color.YELLOW + "Enter the last name of user: ");
        String lastNameOfUser = ScannerWrapper.getInstance().nextLine();

        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getLastName().equals(lastNameOfUser)) {
                userAccounts.add(entry);
            }
        }
        searchResult(userAccounts);
    }

    public void searchAccordingToPhoneNumber(Bank myBank) {
        List<UserAccount> userAccounts = new ArrayList<>();
        System.out.println(Color.YELLOW + "Enter the phone number of user: ");
        String phoneNumberOfUser = ScannerWrapper.getInstance().nextLine();

        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getPhoneNumber().equals(phoneNumberOfUser)) {
                userAccounts.add(entry);
            }
        }
        searchResult(userAccounts);
    }

    public void searchAccordingToFirstNameAndLastName(Bank myBank) {
        List<UserAccount> userAccounts = new ArrayList<>();
        System.out.println(Color.YELLOW + "Enter the first name of user: ");
        String firstNameOfUser = ScannerWrapper.getInstance().nextLine();
        System.out.println(Color.YELLOW + "Enter the last name of user: ");
        String lastNameOfUser = ScannerWrapper.getInstance().nextLine();

        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getFirstName().equals(firstNameOfUser) && entry.getLastName().equals(lastNameOfUser)) {
                userAccounts.add(entry);
            }
        }
        searchResult(userAccounts);
    }

    public void searchAccordingToFirstNameAndPhoneNumber(Bank myBank) {
        List<UserAccount> userAccounts = new ArrayList<>();
        System.out.println(Color.YELLOW + "Enter the first name of user: ");
        String firstNameOfUser = ScannerWrapper.getInstance().nextLine();
        System.out.println(Color.YELLOW + "Enter the phone number of user: ");
        String phoneNumberOfUser = ScannerWrapper.getInstance().nextLine();

        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getFirstName().equals(firstNameOfUser) && entry.getPhoneNumber().equals(phoneNumberOfUser)) {
                userAccounts.add(entry);
            }
        }
        searchResult(userAccounts);
    }

    public void searchAccordingToLastNameAndPhoneNumber(Bank myBank) {
        List<UserAccount> userAccounts = new ArrayList<>();
        System.out.println(Color.YELLOW + "Enter the last name of user: ");
        String lastNameOfUser = ScannerWrapper.getInstance().nextLine();
        System.out.println(Color.YELLOW + "Enter the phone number of user: ");
        String phoneNumberOfUser = ScannerWrapper.getInstance().nextLine();

        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getLastName().equals(lastNameOfUser) && entry.getPhoneNumber().equals(phoneNumberOfUser)) {
                userAccounts.add(entry);
            }
        }
        searchResult(userAccounts);
    }

    public void searchResult(List<UserAccount> userAccounts) {
        if (userAccounts.isEmpty()) {
            System.out.println(Color.RED + "Not found user!");
        } else if (userAccounts.size() == 1) {
            printTheInformationUser(userAccounts.get(0));
        } else {
            showAllUser(userAccounts);
        }
    }

    public void printTheInformationUser(UserAccount user) {
        System.out.println(Color.BLUE + "name: " + Color.GREEN + user.getFirstName() + " " + user.getLastName());
        System.out.println(Color.BLUE + "phone number: " + Color.GREEN + user.getPhoneNumber());
        System.out.println(Color.BLUE + "account number: " + Color.GREEN + user.getAccountNumber());
        System.out.println(Color.BLUE + "transactions :");
        System.out.println(Color.BLUE + "charge account: " + Color.GREEN + user.getChargeAccounts());
        System.out.println(Color.BLUE + "transfers: " + Color.GREEN + user.getTransfers());
    }
}

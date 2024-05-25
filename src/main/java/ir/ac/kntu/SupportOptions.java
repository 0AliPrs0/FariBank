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
            String name = authentications.get(i).getFirstName() + " " + authentications.get(i).getLastName();
            System.out.println(Color.GREEN + (i + 1) + "- " + Color.BLUE + name);
        }

        System.out.println(Color.YELLOW + "Enter the number of authentication");
        String input = ScannerWrapper.getInstance().nextLine();
        int numberOfAuthentication;
        try {
            numberOfAuthentication = Integer.parseInt(input);
        } catch (Exception e) {
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
        try {
            number = Integer.parseInt(input);
        } catch (Exception e) {
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
        RequestType requestType = findRequestType();
        if (requestType == null) {
            System.out.println(Color.RED + "Invalid input!");
            return;
        }

        Map<String, List<Requests>> requests = new HashMap<>();
        for (Map.Entry<String, List<Requests>> requestsList : myBank.getRequest().entrySet()) {
            List<Requests> list = new ArrayList<>();

            for (Requests entry : requestsList.getValue()) {
                if (entry.getRequestType().equals(requestType)) {
                    list.add(entry);
                }
            }
            requests.put(requestsList.getKey(), list);
        }

        showRequest(myBank, requests);
    }

    public RequestType findRequestType() {
        System.out.println(Color.BLUE + "1- Reports");
        System.out.println(Color.BLUE + "2- Contacts");
        System.out.println(Color.BLUE + "3- Transfer");
        System.out.println(Color.BLUE + "4- Setting");
        System.out.print(Color.YELLOW + "Enter the request type: ");
        String input = ScannerWrapper.getInstance().nextLine();

        switch (input) {
            case "1" -> {
                return RequestType.REPORTS;
            }
            case "2" -> {
                return RequestType.CONTACTS;
            }
            case "3" -> {
                return RequestType.TRANSFER;
            }
            case "4" -> {
                return RequestType.SETTINGS;
            }
        }

        return null;
    }

    public void requestAccordingToApplicationStatus(Bank myBank) {
        ApplicationStatus applicationStatus = findApplicationStatus();
        if (applicationStatus == null) {
            System.out.println(Color.RED + "Invalid input!");
            return;
        }

        Map<String, List<Requests>> requests = new HashMap<>();
        for (Map.Entry<String, List<Requests>> requestsList : myBank.getRequest().entrySet()) {
            List<Requests> list = new ArrayList<>();

            for (Requests entry : requestsList.getValue()) {
                if (entry.getApplicationStatus().equals(applicationStatus)) {
                    list.add(entry);
                }
            }
            requests.put(requestsList.getKey(), list);
        }

        showRequest(myBank, requests);
    }

    public ApplicationStatus findApplicationStatus() {
        System.out.println(Color.BLUE + "1- Registered");
        System.out.println(Color.BLUE + "2- In progress");
        System.out.println(Color.BLUE + "3- In closed");
        System.out.print(Color.YELLOW + "Enter the application status: ");
        String applicationStatus = ScannerWrapper.getInstance().nextLine();

        switch (applicationStatus) {
            case "1" -> {
                return ApplicationStatus.REGISTERED;
            }
            case "2" -> {
                return ApplicationStatus.IN_PROGRESS;
            }
            case "3" -> {
                return ApplicationStatus.IN_CLOSED;
            }
        }

        return null;
    }

    public void requestAccordingToUser(Bank myBank) {
        System.out.print(Color.YELLOW + "Enter the phoneNumber of user: ");
        String phoneNumberOfUser = ScannerWrapper.getInstance().nextLine();
        Map<String, List<Requests>> requests = new HashMap<>();

        for (Map.Entry<String, List<Requests>> entry : myBank.getRequest().entrySet()) {
            if (entry.getKey().equals(phoneNumberOfUser)) {
                requests.put(entry.getKey(), entry.getValue());

            }
        }

        showRequest(myBank, requests);
    }

    public void showRequest(Bank myBank, Map<String, List<Requests>> requests) {
        if (requests.isEmpty()) {
            System.out.println(Color.RED + "The list is empty!");
            return;
        }
        List<String> phoneNumbers = new ArrayList<>();
        Map<Integer, Requests> requestsMap = new HashMap<>();
        int index = 1;
        for (Map.Entry<String, List<Requests>> requestList : requests.entrySet()) {
            for (Requests entry : requestList.getValue()) {
                phoneNumbers.add(requestList.getKey());
                requestsMap.put(index, entry);
                System.out.println(Color.GREEN + index + "- " + Color.BLUE + entry.toString());
                index++;
            }
        }

        System.out.print(Color.YELLOW + "Chose number of request: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (Exception e) {
            return;
        }
        if (number >= index) {
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
        try {
            number = Integer.parseInt(input);
        } catch (Exception e) {
            return;
        }
        if (number != 1) {
            return;
        }

        System.out.print(Color.YELLOW + "Enter your massage to user for this problem: ");
        String massageSupport = ScannerWrapper.getInstance().nextLine();
        String userMassage = request.getUserMassage();
        request.setApplicationStatus(ApplicationStatus.IN_CLOSED);

        for (Map.Entry<String, List<Requests>> requestList : myBank.getRequest().entrySet()) {
            for (Requests entry : requestList.getValue()) {
                if (requestList.getKey().equals(phoneNumber) && entry.getUserMassage().equals(userMassage)) {
                    entry.setSupportMassage(massageSupport);
                }
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
        try {
            number = Integer.parseInt(input);
        } catch (Exception e) {
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

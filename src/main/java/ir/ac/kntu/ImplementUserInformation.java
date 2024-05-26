package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class ImplementUserInformation {

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

    public boolean similaritySearch(String str1, String str2) {
        return SearchSimilarity.similarity(str1, str2) >= 0.5;
    }

    public void searchAccordingToFirstName(Bank myBank) {
        List<UserAccount> userAccounts = new ArrayList<>();
        System.out.println(Color.YELLOW + "Enter the first name of user: ");
        String firstNameOfUser = ScannerWrapper.getInstance().nextLine();

        for (UserAccount entry : myBank.getUserAccounts()) {
            boolean isSameSearch = similaritySearch(entry.getFirstName(), firstNameOfUser);
            if (isSameSearch) {
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
            boolean isSameSearch = similaritySearch(entry.getLastName(), lastNameOfUser);
            if (isSameSearch) {
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
            boolean isSameSearch = similaritySearch(entry.getPhoneNumber(), phoneNumberOfUser);
            if (isSameSearch) {
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
            boolean isSameSearch1 = similaritySearch(entry.getFirstName(), firstNameOfUser);
            boolean isSameSearch2 = similaritySearch(entry.getLastName(), lastNameOfUser);

            if (isSameSearch1 && isSameSearch2) {
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
            boolean isSameSearch1 = similaritySearch(entry.getFirstName(), firstNameOfUser);
            boolean isSameSearch2 = similaritySearch(entry.getPhoneNumber(), phoneNumberOfUser);

            if (isSameSearch1 && isSameSearch2) {
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
            boolean isSameSearch2 = similaritySearch(entry.getLastName(), lastNameOfUser);
            boolean isSameSearch1 = similaritySearch(entry.getPhoneNumber(), phoneNumberOfUser);

            if (isSameSearch1 && isSameSearch2) {
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

package ir.ac.kntu.support.menus;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.enums.MenuProperty;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.support.implement.ImplementUserInformation;
import ir.ac.kntu.support.info.Support;

public class UserInformationMenu implements MenuProperty {
    public enum UserInformationField {
        ALL_USER,
        SEARCH_ACCORDING_TO_FIRST_NAME,
        SEARCH_ACCORDING_TO_LAST_NAME,
        SEARCH_ACCORDING_TO_PHONE_NUMBER,
        SEARCH_ACCORDING_TO_FIRST_NAME_AND_LAST_NAME,
        SEARCH_ACCORDING_TO_FIRST_NAME_AND_PHONE_NUMBER,
        SEARCH_ACCORDING_TO_LAST_NAME_AND_PHONE_NUMBER,
        SEARCH_ACCORDING_TO_FIRST_NAME_AND_LAST_NAME_AND_PHONE_NUMBER,
        RETURN,
        UNDEFINED
    }

    public void implementUsersInformation(Bank myBank, Support support) {
        if (support.getIsLockField()[0]) {
            System.out.println(Color.RED + "This field is inactive for you!!");
            return;
        }
        UserInformationField option;
        do {
            printTheMenu();
            option = getOption();
            handleUserInformation(option, myBank);
        } while (option != UserInformationField.RETURN);
    }

    public void handleUserInformation(UserInformationField option, Bank myBank) {
        ImplementUserInformation userInformation = new ImplementUserInformation();
        switch (option) {
            case ALL_USER -> userInformation.showAllUser(myBank.getUserAccounts());
            case SEARCH_ACCORDING_TO_FIRST_NAME -> userInformation.searchAccordingToFirstName(myBank);
            case SEARCH_ACCORDING_TO_LAST_NAME -> userInformation.searchAccordingToLastName(myBank);
            case SEARCH_ACCORDING_TO_PHONE_NUMBER -> userInformation.searchAccordingToPhoneNumber(myBank);
            case SEARCH_ACCORDING_TO_FIRST_NAME_AND_LAST_NAME ->
                    userInformation.searchAccordingToFirstNameAndLastName(myBank);
            case SEARCH_ACCORDING_TO_FIRST_NAME_AND_PHONE_NUMBER ->
                    userInformation.searchAccordingToFirstNameAndPhoneNumber(myBank);
            case SEARCH_ACCORDING_TO_LAST_NAME_AND_PHONE_NUMBER ->
                    userInformation.searchAccordingToLastNameAndPhoneNumber(myBank);
            case SEARCH_ACCORDING_TO_FIRST_NAME_AND_LAST_NAME_AND_PHONE_NUMBER ->
                    userInformation.searchAccordingToFirstNameAndLastNameAndPhoneNumber(myBank);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.CYAN + "User information menu");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- All User");
        System.out.println(Color.BLUE + "2- Search according to first name");
        System.out.println(Color.BLUE + "3- Search according to last name");
        System.out.println(Color.BLUE + "4- Search according to phone number");
        System.out.println(Color.BLUE + "5- Search according to first name and last name");
        System.out.println(Color.BLUE + "6- Search according to first name and phoneNumber");
        System.out.println(Color.BLUE + "7- Search according to last name and phoneNumber");
        System.out.println(Color.BLUE + "8- Search according to first name and last name and phoneNumber");
        System.out.println(Color.BLUE + "9- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select (1 - 9): ");
    }

    @Override
    public UserInformationField getOption() {
        UserInformationField[] options = UserInformationField.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return UserInformationField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return UserInformationField.UNDEFINED;
    }

}

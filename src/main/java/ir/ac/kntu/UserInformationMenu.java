package ir.ac.kntu;

public class UserInformationMenu implements MenuProperty {


    public void implementUsersInformation(Bank myBank) {
        UserInformationField option;
        do {
            printTheMenu();
            option = getOption();
            handleUserInformation(option, myBank);
        } while (option != UserInformationField.RETURN);
    }

    public void handleUserInformation(UserInformationField option, Bank myBank) {
        SupportOptions supportOptions = new SupportOptions();
        switch (option) {
            case ALL_USER -> supportOptions.showAllUser(myBank.getUserAccounts());
            case SEARCH_ACCORDING_TO_FIRST_NAME -> supportOptions.searchAccordingToFirstName(myBank);
            case SEARCH_ACCORDING_TO_LAST_NAME -> supportOptions.searchAccordingToLastName(myBank);
            case SEARCH_ACCORDING_TO_PHONE_NUMBER -> supportOptions.searchAccordingToPhoneNumber(myBank);
            case SEARCH_ACCORDING_TO_FIRST_NAME_AND_LAST_NAME ->
                    supportOptions.searchAccordingToFirstNameAndLastName(myBank);
            case SEARCH_ACCORDING_TO_FIRST_NAME_AND_PHONE_NUMBER ->
                    supportOptions.searchAccordingToFirstNameAndPhoneNumber(myBank);
            case SEARCH_ACCORDING_TO_LAST_NAME_AND_PHONE_NUMBER ->
                    supportOptions.searchAccordingToLastNameAndPhoneNumber(myBank);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    public enum UserInformationField {
        ALL_USER,
        SEARCH_ACCORDING_TO_FIRST_NAME,
        SEARCH_ACCORDING_TO_LAST_NAME,
        SEARCH_ACCORDING_TO_PHONE_NUMBER,
        SEARCH_ACCORDING_TO_FIRST_NAME_AND_LAST_NAME,
        SEARCH_ACCORDING_TO_FIRST_NAME_AND_PHONE_NUMBER,
        SEARCH_ACCORDING_TO_LAST_NAME_AND_PHONE_NUMBER,
        RETURN,
        UNDEFINED
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- All User");
        System.out.println(Color.BLUE + "2- Search according to first name");
        System.out.println(Color.BLUE + "3- Search according to last name");
        System.out.println(Color.BLUE + "4- Search according to phone number");
        System.out.println(Color.BLUE + "5- Search according to first name and last name");
        System.out.println(Color.BLUE + "6- Search according to first name and phoneNumber");
        System.out.println(Color.BLUE + "7- Search according to last name and phoneNumber");
        System.out.println(Color.BLUE + "8- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select the option: ");
    }

    @Override
    public UserInformationField getOption() {
        UserInformationField[] options = UserInformationField.values();
        String inputStr = ScannerWrapper.getInstance().next();
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

package ir.ac.kntu;

public class Menus {



    public enum UserInformation {
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

    public void printTheUserInformationMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- All User");
        System.out.println("2- Search according to first name");
        System.out.println("3- Search according to last name");
        System.out.println("4- Search according to phone number");
        System.out.println("5- Search according to first name and last name");
        System.out.println("6- Search according to first name and phoneNumber");
        System.out.println("7- Search according to last name and phoneNumber");
        System.out.println("8- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    public Menus.UserInformation getOptionUserInformation() {
        UserInformation[] options = UserInformation.values();
        String inputStr = ScannerWrapper.getInstance().next();
        int input;
        try{
            input = Integer.parseInt(inputStr);
        }catch (Exception e){
            return UserInformation.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return UserInformation.UNDEFINED;
    }
}

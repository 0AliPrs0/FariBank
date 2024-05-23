package ir.ac.kntu;

public class Menus {

    public enum ContactOption {
        EDIT_INFORMATION,
        VIEW_INFORMATION,
        RETURN,
        UNDEFINED
    }

    public void printTheContactOption() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Edit information");
        System.out.println("2- view information");
        System.out.println("3- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    public Menus.ContactOption getOptionContactOption() {
        ContactOption[] options = ContactOption.values();
        String inputStr = ScannerWrapper.getInstance().next();
        int input;
        try{
            input = Integer.parseInt(inputStr);
        }catch (Exception e){
            return ContactOption.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return ContactOption.UNDEFINED;
    }

    public enum SettingOptions {
        CHANGE_PASSWORD,
        REGISTER_CARD_PASSWORD,
        CHANGE_CARD_PASSWORD,
        ACTIVATION_CONTACT_KEYWORD,
        INACTIVATION_CONTACT_KEYWORD,
        RETURN,
        UNDEFINED
    }

    public void printTheSettingOption() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Change password");
        System.out.println("2- Register card password");
        System.out.println("3- change card password");
        System.out.println("4- Activation contact keyword");
        System.out.println("5- Inactivation contact keyword");
        System.out.println("6- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    public Menus.SettingOptions getOptionSettingOption() {
        SettingOptions[] options = SettingOptions.values();
        String inputStr = ScannerWrapper.getInstance().next();
        int input;
        try{
            input = Integer.parseInt(inputStr);
        }catch (Exception e){
            return SettingOptions.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return SettingOptions.UNDEFINED;
    }



    public enum SupportUser {
        REGISTER_MASSAGE,
        SHOW_MASSAGE,
        RETURN,
        UNDEFINED
    }

    public void printTheSupportUser() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Register massage");
        System.out.println("2- Show massage");
        System.out.println("3- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    public Menus.SupportUser getOptionSupportUser() {
        SupportUser[] options = SupportUser.values();
        String inputStr = ScannerWrapper.getInstance().next();
        int input;
        try{
            input = Integer.parseInt(inputStr);
        }catch (Exception e){
            return SupportUser.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return SupportUser.UNDEFINED;
    }

    public enum RegisterSupportUser {
        REPORT,
        CONTACTS,
        TRANSFER,
        SETTING,
        RETURN,
        UNDEFINED
    }

    public void printTheRegisterSupportUser() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Report");
        System.out.println("2- Contacts");
        System.out.println("3- Transfer");
        System.out.println("4- Setting");
        System.out.println("5- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    public Menus.RegisterSupportUser getOptionRegisterSupportUser() {
        RegisterSupportUser[] options = RegisterSupportUser.values();
        String inputStr = ScannerWrapper.getInstance().next();
        int input;
        try{
            input = Integer.parseInt(inputStr);
        }catch (Exception e){
            return RegisterSupportUser.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return RegisterSupportUser.UNDEFINED;
    }

    public enum ChoseAccountsForTransfer {
        SELECT_MANUALLY,
        SELECT_FROM_RESENT_ACCOUNTS,
        SELECT_FROM_CONTACTS,
        UNDEFINED
    }

    public void printTheChoseAccounts() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Select manually");
        System.out.println("2- Select from recent accounts");
        System.out.println("3- Select from contacts");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    public Menus.ChoseAccountsForTransfer getOptionChoseAccounts() {
        ChoseAccountsForTransfer[] options = ChoseAccountsForTransfer.values();
        String inputStr = ScannerWrapper.getInstance().next();
        int input;
        try{
            input = Integer.parseInt(inputStr);
        }catch (Exception e){
            return ChoseAccountsForTransfer.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return ChoseAccountsForTransfer.UNDEFINED;
    }

    public enum RequestSupport {
        ALL_REQUEST,
        REQUEST_ACCORDING_TO_REQUEST_TYPE,
        REQUEST_ACCORDING_TO_APPLICATION_STATUS,
        REQUEST_ACCORDING_TO_USER,
        RETURN,
        UNDEFINED
    }

    public void printTheRequestSupport() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- All request");
        System.out.println("2- Request according to request type");
        System.out.println("3- Request according to application status");
        System.out.println("4- Request according to user");
        System.out.println("5- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    public Menus.RequestSupport getOptionRequestSupport() {
        RequestSupport[] options = RequestSupport.values();
        String inputStr = ScannerWrapper.getInstance().next();
        int input;
        try{
            input = Integer.parseInt(inputStr);
        }catch (Exception e){
            return RequestSupport.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return RequestSupport.UNDEFINED;
    }

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

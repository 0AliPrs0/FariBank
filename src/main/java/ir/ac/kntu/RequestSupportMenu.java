package ir.ac.kntu;

public class RequestSupportMenu implements MenuProperty {
    private static RequestSupportMenu instance = new RequestSupportMenu();

    public static RequestSupportMenu getInstance() {
        return instance;
    }

    public enum RequestSupportField {
        ALL_REQUEST,
        REQUEST_ACCORDING_TO_REQUEST_TYPE,
        REQUEST_ACCORDING_TO_APPLICATION_STATUS,
        REQUEST_ACCORDING_TO_USER,
        RETURN,
        UNDEFINED
    }

    public void implementRequestSupport(Bank myBank) {
        RequestSupportField option;
        do {
            printTheMenu();
            option = getOption();
            handleRequestSupport(option, myBank);
        } while (option != RequestSupportField.RETURN);
    }

    public void handleRequestSupport(RequestSupportField option, Bank myBank) {
        SupportOptions supportOptions = new SupportOptions();
        switch (option) {
            case ALL_REQUEST -> supportOptions.showRequest(myBank, myBank.getRequest());
            case REQUEST_ACCORDING_TO_REQUEST_TYPE -> supportOptions.requestAccordingToRequestType(myBank);
            case REQUEST_ACCORDING_TO_APPLICATION_STATUS -> supportOptions.requestAccordingToApplicationStatus(myBank);
            case REQUEST_ACCORDING_TO_USER -> supportOptions.requestAccordingToUser(myBank);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- All request");
        System.out.println(Color.BLUE + "2- Request according to request type");
        System.out.println(Color.BLUE + "3- Request according to application status");
        System.out.println(Color.BLUE + "4- Request according to user");
        System.out.println(Color.BLUE + "5- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select the option: ");
    }

    @Override
    public RequestSupportField getOption() {
        RequestSupportField[] options = RequestSupportField.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return RequestSupportField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return RequestSupportField.UNDEFINED;
    }
}

package ir.ac.kntu;

public class RequestSupportMenu implements MenuProperty {
    private static RequestSupportMenu instance = new RequestSupportMenu();

    public RequestSupportMenu() {
    }

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
            default -> System.out.println("Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
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

    @Override
    public RequestSupportField getOption() {
        RequestSupportField[] options = RequestSupportField.values();
        String inputStr = ScannerWrapper.getInstance().next();
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

package ir.ac.kntu.support.menus;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.enums.MenuProperty;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.support.implement.ImplementRequest;
import ir.ac.kntu.user.info.Requests;

import java.util.List;
import java.util.Map;

public class RequestSupportMenu implements MenuProperty {
    private static RequestSupportMenu instance = new RequestSupportMenu();

    public static RequestSupportMenu getInstance() {
        return instance;
    }

    public enum RequestSupportField {
        ALL_REQUEST,
        REQUEST_ACCORDING_TO_APPLICATION_STATUS,
        REQUEST_ACCORDING_TO_USER,
        RETURN,
        UNDEFINED
    }

    public void implementRequestSupport(Bank myBank, Map<String, List<Requests>> requests) {
        RequestSupportField option;
        do {
            printTheMenu();
            option = getOption();
            handleRequestSupport(option, myBank, requests);
        } while (option != RequestSupportField.RETURN);
    }

    public void handleRequestSupport(RequestSupportField option, Bank myBank, Map<String, List<Requests>> requests) {
        ImplementRequest implementRequest = new ImplementRequest();
        switch (option) {
            case ALL_REQUEST -> implementRequest.showRequest(myBank, requests);
            case REQUEST_ACCORDING_TO_APPLICATION_STATUS -> implementRequest.requestAccordingToApplicationStatus(myBank, requests);
            case REQUEST_ACCORDING_TO_USER -> implementRequest.requestAccordingToUser(myBank, requests);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.CYAN + "Request lists menu");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- All request");
        System.out.println(Color.BLUE + "2- Request according to application status");
        System.out.println(Color.BLUE + "3- Request according to user");
        System.out.println(Color.BLUE + "4- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.print(Color.PURPLE + "Select (1 - 4): ");
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

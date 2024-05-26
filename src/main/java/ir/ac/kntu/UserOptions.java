package ir.ac.kntu;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserOptions {




















    public void showSupportUser(UserAccount myAccount, Bank myBank) {
        List<Requests> myRequest = new ArrayList<>();
        for (Map.Entry<String, List<Requests>> entry : myBank.getRequest().entrySet()) {
            if (entry.getKey().equals(myAccount.getPhoneNumber())) {
                myRequest = entry.getValue();
            }
        }
        printMassages(myRequest);
    }

    public void printMassages(List<Requests> requests) {
        if (requests.isEmpty()) {
            System.out.println(Color.RED + "The list is empty!");
            return;
        }

        for (int i = 1; i <= requests.size(); i++) {
            System.out.println(Color.GREEN + i + "- " + Color.BLUE + requests.get(i - 1).toString() + " Support massage: " + requests.get(i - 1).getSupportMassage());
        }
    }


    public String inputTheMassage() {
        System.out.print(Color.YELLOW + "Enter your massage: ");
        return ScannerWrapper.getInstance().nextLine();
    }

    public void handleSupportUser(Bank myBank, UserAccount myAccount, RequestType requestType){
        String massageUser = inputTheMassage();
        List<Requests> requests = userRequest(myBank.getRequest(), myAccount.getPhoneNumber());
        requests.add(new Requests(requestType, ApplicationStatus.REGISTERED, massageUser, " "));
        myBank.addRequest(myAccount.getPhoneNumber(), requests);
    }

    public List<Requests> userRequest(Map<String, List<Requests>> requests, String phoneNumber){
        List<Requests> requestsList = new ArrayList<>();
        for (Map.Entry<String, List<Requests>> entry : requests.entrySet()){
            if (entry.getKey().equals(phoneNumber)) {
                requestsList = entry.getValue();
            }
        }
        return requestsList;
    }

    public void handleChangePassword(UserAccount myAccount) {
        System.out.println(Color.YELLOW + "Enter new password: ");
        String password = ScannerWrapper.getInstance().nextLine();

        boolean isSafePassword = new UserHandler().checkPassword(password);
        if (isSafePassword) {
            myAccount.setPassword(password);
            System.out.println(Color.GREEN + "Password was changed");
        }
    }

    public void handleRegisterCardPassword(UserAccount myAccount) {
        if (myAccount.getCardPassword() == -1) {
            System.out.println(Color.YELLOW + "Enter your card password: ");
            String inputStr = ScannerWrapper.getInstance().nextLine();
            boolean isValidPassword = checkValidPassword(inputStr);
            if (!isValidPassword) {
                System.out.println(Color.RED + "invalid password!");
                return;
            }
            int cardPassword = convertStrToInt(inputStr);
            if (cardPassword != -1) {
                myAccount.setCardPassword(cardPassword);
                System.out.println(Color.GREEN + "Your card password was registered");
            }
        } else {
            System.out.println(Color.RED + "Your card password is already registered!");
        }
    }

    public int convertStrToInt(String input){
        int cardPassword;
        try {
            cardPassword = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println(Color.RED + "invalid password!");
            return -1;
        }
        return cardPassword;
    }

    public void handleChangeCardPassword(UserAccount myAccount) {
        if (myAccount.getCardPassword() != -1) {
            System.out.print(Color.YELLOW + "Enter new card password: ");
            String inputStr = ScannerWrapper.getInstance().nextLine();
            boolean isValidPassword = checkValidPassword(inputStr);
            if (!isValidPassword) {
                System.out.println(Color.RED + "invalid password!");
                return;
            }
            int cardPassword = convertStrToInt(inputStr);
            if (cardPassword != -1) {
                myAccount.setCardPassword(cardPassword);
                System.out.println(Color.GREEN + "Your card password was changed");
            }
        } else {
            System.out.println(Color.RED + "Your card password is not registered!");
        }
    }

    public boolean checkValidPassword(String inputStr) {
        String patternStr = "\\d{4}";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }


    public void handleActivationContactKeyword(UserAccount myAccount) {
        if (!myAccount.getIsActingContactKeyword()) {
            myAccount.setIsActingContactKeyword(true);
            System.out.println(Color.GREEN + "Contact keyword has been active");
        } else {
            System.out.println(Color.RED + "Contact keyword already is active!");
        }
    }

    public void handleInactivationContactKeyword(UserAccount myAccount) {
        if (myAccount.getIsActingContactKeyword()) {
            myAccount.setIsActingContactKeyword(false);
            System.out.println(Color.GREEN + "Contact keyword has been inactive");
        } else {
            System.out.println(Color.RED + "Contact keyword already is not active!");
        }
    }


    }
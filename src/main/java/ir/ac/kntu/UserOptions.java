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


    public void handleSelectManually(UserAccount myAccount, Bank myBank) {
        System.out.print(Color.YELLOW + "Enter the account number: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int accountNumber = Integer.parseInt(input);

        UserAccount user = checkAccountNumber(accountNumber, myBank);
        if (user != null) {
            inputTheMoneyForTransaction(myAccount, myBank, accountNumber, user.getFirstName() + user.getLastName());
        }
    }

    public UserAccount checkAccountNumber(int accountNumber, Bank myBank) {
        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getAccountNumber() == accountNumber) {
                return entry;
            }
        }
        System.out.println(Color.GREEN + "Account number is not found!");
        return null;
    }

    public void handleSelectFromResentAccount(UserAccount myAccount, Bank myBank) {
        if (myAccount.getRecentlyAccount().isEmpty()) {
            System.out.println(Color.RED + "The list is empty!");
            return;
        }
        for (int i = 0; i < myAccount.getRecentlyAccount().size(); i++) {
            System.out.println((i + 1) + "- " + myAccount.getRecentlyAccount().get(i).toString());
        }

        System.out.print(Color.YELLOW + "Enter the number of account number: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int index = Integer.parseInt(input);

        int accountNumber = myAccount.getRecentlyAccount().get(index - 1).getAccountNumber();
        String nameOfDestination = myAccount.getRecentlyAccount().get(index - 1).getName();
        inputTheMoneyForTransaction(myAccount, myBank, accountNumber, nameOfDestination);
    }

    public void handleSelectFromContacts(UserAccount myAccount, Bank myBank) {
        if (myAccount.getMyContacts().isEmpty()) {
            System.out.println(Color.RED + "The list is empty!");
            return;
        }
        Map<Integer, Contact> contacts = new TreeMap<>();
        for (int i = 0; i < myAccount.getMyContacts().size(); i++) {
            Contact newContact = myAccount.getMyContacts().get(i);

            for (int j = 0; j < myBank.getUserAccounts().size(); j++) {
                List<Contact> contactsOfContact = myBank.getUserAccounts().get(j).getMyContacts();

                for (Contact contact : contactsOfContact) {
                    String otherPhoneNumber = contact.getPhoneNumber();
                    String myPhoneNumber = newContact.getPhoneNumber();

                    if (newContact.getPhoneNumber().equals(myPhoneNumber) && contact.getPhoneNumber().equals(otherPhoneNumber) && !myPhoneNumber.equals(otherPhoneNumber)) {
                        int accountNumber = myBank.getUserAccounts().get(j).getAccountNumber();
                        contacts.put(accountNumber, newContact);
                    }
                }
            }
        }
        if (contacts.isEmpty()) {
            System.out.println(Color.RED + "The list is empty!");
            return;
        }
        showContactsForTransfer(myAccount, myBank, contacts);
    }

    public void showContactsForTransfer(UserAccount myAccount, Bank myBank, Map<Integer, Contact> contact) {
        Map<Integer, Integer> accountNumbers = new HashMap<>();
        List<Contact> contactsList = new ArrayList<>();

        int index = 1;
        for (Map.Entry<Integer, Contact> entry : contact.entrySet()) {
            contactsList.add(entry.getValue());
            accountNumbers.put(index, entry.getKey());
            index++;
        }

        for (int i = 1; i <= contactsList.size(); i++) {
            System.out.println(Color.GREEN + i + "- " + Color.BLUE + contactsList.get(i - 1).getFirstName() + contactsList.get(i - 1).getLName() + accountNumbers.get(i));
        }

        System.out.print(Color.YELLOW + "Enter the number of account number: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int indexOfContact = Integer.parseInt(input);

        int accountNumber = accountNumbers.get(indexOfContact);
        String nameOfDestination = contact.get(accountNumber).getFirstName() + contact.get(accountNumber).getLName();
        inputTheMoneyForTransaction(myAccount, myBank, accountNumber, nameOfDestination);
    }

    public void inputTheMoneyForTransaction(UserAccount myAccount, Bank myBank, int accountNumber, String nameOfDestination) {
        System.out.print(Color.YELLOW + "Enter the amount money: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int money = Integer.parseInt(input);
        int wadge = 1000;

        boolean isMoneyEnough = checkInventory(myAccount, money + wadge);
        if (isMoneyEnough) {
            UserAccount destinAccount = new UserAccount();
            for (int i = 0; i < myBank.getUserAccounts().size(); i++) {
                if (myBank.getUserAccounts().get(i).getAccountNumber() == accountNumber) {
                    destinAccount = myBank.getUserAccounts().get(i);
                }
            }

            successTransaction(money, myAccount, nameOfDestination, destinAccount);
        }
    }

    public boolean checkInventory(UserAccount myAccount, int money) {
        if (myAccount.getBalanceAccount() > money) {
            return true;
        }
        System.out.println(Color.RED + "Your inventory is not enough!");
        return false;
    }

    public void successTransaction(int money, UserAccount myAccount, String nameOfDestination, UserAccount destinAccount) {
        myAccount.setBalanceAccount(myAccount.getBalanceAccount() - (money + 1000));
        destinAccount.setBalanceAccount(destinAccount.getBalanceAccount() + money);

        printRecite(money, myAccount, destinAccount, nameOfDestination);
        setTransaction(money, myAccount, destinAccount, nameOfDestination);

    }

    public void printRecite(int money, UserAccount myAccount, UserAccount destinAccount, String nameOfDestination) {
        int min = 100000;
        int max = 999999;
        int issueTracking = ThreadLocalRandom.current().nextInt(min, max + 1);

        int originAccount = myAccount.getAccountNumber();
        int destination = destinAccount.getAccountNumber();
        String dateNow = Calendar.getStringNow();

        System.out.println(Color.YELLOW + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(Color.GREEN + "Amount of money: " + Color.BLUE + money);
        System.out.println(Color.GREEN + "Origin accountNumber: " + Color.BLUE + originAccount);
        System.out.println(Color.GREEN + "Destination account number: " + Color.BLUE + destination);
        System.out.println(Color.GREEN + "name of destination account: " + Color.BLUE + nameOfDestination);
        System.out.println(Color.GREEN + "Date of transfer: " + Color.BLUE + dateNow);
        System.out.println(Color.GREEN + "Issue tracking: " + Color.BLUE + issueTracking);
        System.out.println(Color.YELLOW + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public void setTransaction(int money, UserAccount myAccount, UserAccount destinAccount, String nameOfDestination) {
        int accountNumber1 = myAccount.getAccountNumber();
        int accountNumber2 = destinAccount.getAccountNumber();
        String destination = destinAccount.getFirstName() + " " + destinAccount.getLastName();
        String dateNow = Calendar.getStringNow();

        Transfer newTransfer1 = new Transfer(-money, accountNumber1, accountNumber2, nameOfDestination, dateNow);
        myAccount.addTransfer(newTransfer1);

        Transfer newTransfer2 = new Transfer(money, accountNumber1, accountNumber2, destination, dateNow);
        destinAccount.addTransfer(newTransfer2);

        RecentlyAccountForTransfer account = new RecentlyAccountForTransfer(nameOfDestination, accountNumber2);
        myAccount.addRecentlyAccount(account);
    }
}
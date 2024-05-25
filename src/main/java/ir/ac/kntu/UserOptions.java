package ir.ac.kntu;

import ir.ac.kntu.util.Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserOptions {


    public void chargeAccount(UserAccount myAccount) {
        System.out.print(Color.YELLOW + "Enter the amount of money: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int amount = Integer.parseInt(input);

        myAccount.setBalanceAccount(myAccount.getBalanceAccount() + amount);
        System.out.println(Color.YELLOW + "Your account increased by " + amount);

        String dateNow = Calendar.getStringNow();
        ChargeAccount newCharge = new ChargeAccount(amount, dateNow);
        myAccount.addChargeAccount(newCharge);

    }

    public void balance(UserAccount myAccount) {
        System.out.println(Color.BLUE + "Your balance account is: " + Color.GREEN + myAccount.getBalanceAccount());
    }

    public void transaction(List<ChargeAccount> chargeAccounts, List<Transfer> transfers) {
        int index = 1;
        System.out.println(Color.YELLOW + "List of charge account: ");
        for (int i = chargeAccounts.size() - 1; i >= 0; i--) {
            System.out.println(Color.GREEN + index + "- " + Color.BLUE + chargeAccounts.get(i).getChargeAmount());
            index++;
        }

        System.out.println(Color.RED + "-------------------------------------");
        System.out.println(Color.YELLOW + "List of transfer: ");
        for (int i = transfers.size() - 1; i >= 0; i--) {
            int amountTransfer = transfers.get(i).getAmountTransfer();
            System.out.println(Color.GREEN + index + "- " + Color.BLUE + amountTransfer);
            index++;
        }
        showTransactionDetail(chargeAccounts, transfers);
    }

    public void showTransactionDetail(List<ChargeAccount> chargeAccounts, List<Transfer> transfers) {
        if (chargeAccounts.isEmpty() && transfers.isEmpty()) {
            return;
        }
        System.out.println(Color.YELLOW + "Enter the number of transaction: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int index = Integer.parseInt(input);

        if (index <= chargeAccounts.size()) {
            System.out.println(Color.BLUE + chargeAccounts.get(index - 1).toString());
        } else {
            System.out.println(Color.BLUE + transfers.get(index - chargeAccounts.size() - 1).toString());
        }
    }


    public void timeFilterTransaction(UserAccount myAccount) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String pattern = "year.month.day hour:minute:second(yyyy.MM.dd HH:mm:ss)";
        System.out.println(Color.YELLOW + "Enter the start date like this structure " + pattern);
        String startDate = ScannerWrapper.getInstance().nextLine();
        Date date1;
        try {
            date1 = simpleDateFormat.parse(startDate);
        } catch (Exception e) {
            System.out.println(Color.RED + "invalid input");
            return;
        }

        System.out.println(Color.YELLOW + "Enter the end date like this structure " + pattern);
        String endDate = ScannerWrapper.getInstance().nextLine();
        Date date2;
        try {
            date2 = simpleDateFormat.parse(endDate);
        } catch (Exception e) {
            System.out.println(Color.RED + "invalid input");
            return;
        }

        handleTimeFilterTransaction(myAccount, date1, date2);
    }

    public void handleTimeFilterTransaction(UserAccount myAccount, Date startDate, Date endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        List<ChargeAccount> newChargeAccount = new LinkedList<ChargeAccount>();
        List<Transfer> newTransfer = new LinkedList<Transfer>();

        for (int i = myAccount.getChargeAccounts().size() - 1; i >= 0; i--) {
            Date date;
            try {
                date = simpleDateFormat.parse(myAccount.getChargeAccounts().get(i).getDateOfChargeAccount());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (startDate.getTime() <= date.getTime() && endDate.getTime() >= date.getTime()) {
                newChargeAccount.add(myAccount.getChargeAccounts().get(i));
            }
        }

        for (int i = myAccount.getTransfers().size() - 1; i >= 0; i--) {
            Date date;
            try {
                date = simpleDateFormat.parse(myAccount.getTransfers().get(i).getDateOfTransfer());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (startDate.getTime() <= date.getTime() && endDate.getTime() >= date.getTime()) {
                newTransfer.add(myAccount.getTransfers().get(i));
            }
        }
        transaction(newChargeAccount, newTransfer);
    }


    public void addContact(UserAccount myAccount) {
        System.out.print(Color.YELLOW + "Enter the first name of contact: ");
        String fName = ScannerWrapper.getInstance().nextLine();

        System.out.print(Color.YELLOW + "Enter the last name of contact: ");
        String lName = ScannerWrapper.getInstance().nextLine();

        System.out.print(Color.YELLOW + "Enter the phone number name of contact: ");
        String phoneNumber = ScannerWrapper.getInstance().nextLine();

        boolean isTherePhoneNumber = checkPhoneNumber(myAccount, phoneNumber);
        if (!isTherePhoneNumber) {
            Contact newContact = new Contact(fName, lName, phoneNumber);
            myAccount.getMyContacts().add(newContact);
        } else {
            System.out.println(Color.RED + "There is same phone number in your contacts!");
        }
    }

    public void viewInformationContact(UserAccount myAccount) {
        for (int i = 1; i <= myAccount.getMyContacts().size(); i++) {
            Contact contact = myAccount.getMyContacts().get(i - 1);
            System.out.println(Color.GREEN + i + "- " + Color.RED + contact.getFirstName() + " " + contact.getLName());
        }
        System.out.println();
        System.out.println(Color.YELLOW + "Chose the contact (Enter the number of contact) : ");
        String input = ScannerWrapper.getInstance().nextLine();
        int numberOfContact = Integer.parseInt(input);
        numberOfContact--;

        ContactInformationContact contactInformationContact = new ContactInformationContact();
        contactInformationContact.implementInformationContact(myAccount, numberOfContact);
    }


    public boolean checkPhoneNumber(UserAccount myAccount, String phoneNumber) {
        for (Contact entry : myAccount.getMyContacts()) {
            if (entry.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }


    public void editContact(UserAccount myAccount, int numberOfContact) {
        System.out.print(Color.YELLOW + "Enter the new first name of contact: ");
        String newFirstname = ScannerWrapper.getInstance().nextLine();

        System.out.print(Color.YELLOW + "Enter the new last name of contact: ");
        String newLastname = ScannerWrapper.getInstance().nextLine();

        String phoneNumber = myAccount.getMyContacts().get(numberOfContact).getPhoneNumber();
        String oldFirstname = myAccount.getMyContacts().get(numberOfContact).getFirstName();
        String oldLastname = myAccount.getMyContacts().get(numberOfContact).getLName();
        String oldName = oldFirstname + oldLastname;

        Contact editContact = new Contact(newFirstname, newLastname, phoneNumber);

        for (int i = 0; i < myAccount.getTransfers().size(); i++) {
            if (myAccount.getTransfers().get(i).getNameOfDestinationAccountOwner().equals(oldName)) {
                myAccount.getTransfers().get(i).setNameOfDestinationAccountOwner(newFirstname + newLastname);
            }
        }

        myAccount.getMyContacts().set(numberOfContact, editContact);
        System.out.println(Color.GREEN + "The information of contact was changed");
    }

    public void viewInformation(UserAccount myAccount, int numberOfContact) {
        System.out.println(myAccount.getMyContacts().get(numberOfContact).toString());
    }


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

    public void handleReportOfSupportUser(Bank myBank, UserAccount myAccount) {
        String massageUser = inputTheMassage();
        List<Requests> requests = userRequest(myBank.getRequest(), myAccount.getPhoneNumber());
        requests.add(new Requests(RequestType.REPORTS, ApplicationStatus.REGISTERED, massageUser, " "));
        myBank.addRequest(myAccount.getPhoneNumber(), requests);
    }

    public void handleContactOfSupportUser(Bank myBank, UserAccount myAccount) {
        String massageUser = inputTheMassage();
        List<Requests> requests = userRequest(myBank.getRequest(), myAccount.getPhoneNumber());
        requests.add(new Requests(RequestType.CONTACTS, ApplicationStatus.REGISTERED, massageUser, " "));
        myBank.addRequest(myAccount.getPhoneNumber(), requests);
    }

    public void handleTransferOfSupportUser(Bank myBank, UserAccount myAccount) {
        String massageUser = inputTheMassage();
        List<Requests> requests = userRequest(myBank.getRequest(), myAccount.getPhoneNumber());
        requests.add(new Requests(RequestType.TRANSFER, ApplicationStatus.REGISTERED, massageUser, " "));
        myBank.addRequest(myAccount.getPhoneNumber(), requests);
    }

    public void handleSettingOfSupportUser(Bank myBank, UserAccount myAccount) {
        String massageUser = inputTheMassage();
        List<Requests> requests = userRequest(myBank.getRequest(), myAccount.getPhoneNumber());
        requests.add(new Requests(RequestType.SETTINGS, ApplicationStatus.REGISTERED, massageUser, " "));
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

            int cardPassword;
            try {
                cardPassword = Integer.parseInt(inputStr);
            } catch (Exception e) {
                System.out.println(Color.RED + "invalid password!");
                return;
            }
            myAccount.setCardPassword(cardPassword);
            System.out.println(Color.GREEN + "Your card password was registered");
        } else {
            System.out.println(Color.RED + "Your card password is already registered!");
        }
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

            int cardPassword;
            try {
                cardPassword = Integer.parseInt(inputStr);
            } catch (Exception e) {
                System.out.println(Color.RED + "invalid password!");
                return;
            }
            myAccount.setCardPassword(cardPassword);
            System.out.println(Color.GREEN + "Your card password was changed");
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
        if (myAccount.getRecentlyAccountNumberForTransfer().isEmpty()) {
            System.out.println(Color.RED + "The list is empty!");
            return;
        }
        for (int i = 0; i < myAccount.getRecentlyAccountNumberForTransfer().size(); i++) {
            System.out.println((i + 1) + "- " + myAccount.getRecentlyAccountNumberForTransfer().get(i).toString());
        }

        System.out.print(Color.YELLOW + "Enter the number of account number: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int index = Integer.parseInt(input);

        int accountNumber = myAccount.getRecentlyAccountNumberForTransfer().get(index - 1).getAccountNumber();
        String nameOfDestination = myAccount.getRecentlyAccountNumberForTransfer().get(index - 1).getName();

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

                for (int k = 0; k < contactsOfContact.size(); k++) {
                    String otherPhoneNumber = contactsOfContact.get(k).getPhoneNumber();
                    String myPhoneNumber = newContact.getPhoneNumber();

                    if (newContact.getPhoneNumber().equals(myPhoneNumber) && contactsOfContact.get(k).getPhoneNumber().equals(otherPhoneNumber) && !myPhoneNumber.equals(otherPhoneNumber)) {
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
            successTransaction(money, myAccount, myBank, accountNumber, nameOfDestination);
        }
    }

    public boolean checkInventory(UserAccount myAccount, int money) {
        if (myAccount.getBalanceAccount() > money) {
            return true;
        }
        System.out.println(Color.RED + "Your inventory is not enough!");
        return false;
    }

    public void successTransaction(int money, UserAccount myAccount, Bank myBank, int accountNumber2, String nameOfDestination) {
        UserAccount destinationAccount = new UserAccount();
        for (int i = 0; i < myBank.getUserAccounts().size(); i++) {
            if (myBank.getUserAccounts().get(i).getAccountNumber() == accountNumber2) {
                destinationAccount = myBank.getUserAccounts().get(i);
            }
        }

        myAccount.setBalanceAccount(myAccount.getBalanceAccount() - (money + 1000));
        destinationAccount.setBalanceAccount(destinationAccount.getBalanceAccount() + money);

        String dateNow = Calendar.getStringNow();
        printRecite(money, myAccount, destinationAccount, dateNow, nameOfDestination);
        setTransaction(money, myAccount, destinationAccount, dateNow, nameOfDestination);

    }

    public void printRecite(int money, UserAccount myAccount, UserAccount destinationAccount, String dateNow, String nameOfDestination) {
        int min = 100000;
        int max = 999999;
        int issueTracking = ThreadLocalRandom.current().nextInt(min, max + 1);

        int originAccountNumber = myAccount.getAccountNumber();
        int destinationAccountNumber = destinationAccount.getAccountNumber();

        System.out.println(Color.YELLOW + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(Color.GREEN + "Amount of money: " + Color.BLUE + money);
        System.out.println(Color.GREEN + "Origin accountNumber: " + Color.BLUE + originAccountNumber);
        System.out.println(Color.GREEN + "Destination account number: " + Color.BLUE + destinationAccountNumber);
        System.out.println(Color.GREEN + "name of destination account: " + Color.BLUE + nameOfDestination);
        System.out.println(Color.GREEN + "Date of transfer: " + Color.BLUE + dateNow);
        System.out.println(Color.GREEN + "Issue tracking: " + Color.BLUE + issueTracking);
        System.out.println(Color.YELLOW + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public void setTransaction(int money, UserAccount myAccount, UserAccount destinationAccount, String dateNow, String nameOfDestination) {
        int accountNumber1 = myAccount.getAccountNumber();
        int accountNumber2 = destinationAccount.getAccountNumber();
        String nameOfDestinationAccount = destinationAccount.getFirstName() + destinationAccount.getLastName();

        Transfer newTransfer1 = new Transfer(-money, accountNumber1, accountNumber2, nameOfDestination, dateNow);
        myAccount.addTransfer(newTransfer1);

        Transfer newTransfer2 = new Transfer(money, accountNumber1, accountNumber2, nameOfDestinationAccount, dateNow);
        destinationAccount.addTransfer(newTransfer2);

        RecentlyAccountForTransfer account = new RecentlyAccountForTransfer(nameOfDestination, accountNumber2);
        myAccount.addRecentlyAccountNumberForTransfer(account);
    }
}
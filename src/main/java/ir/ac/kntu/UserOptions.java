package ir.ac.kntu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserOptions {



    public void chargeAccount(UserAccount me) {
        System.out.print("Enter the amount of money: ");
        int amount = ScannerWrapper.getInstance().nextInt();

        me.setBalanceAccount(me.getBalanceAccount() + amount);
        System.out.println("Your account increased by " + amount);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        String dateNow = simpleDateFormat.format(date);
        ChargeAccount newCharge = new ChargeAccount(amount, dateNow);
        me.addChargeAccount(newCharge);

    }

    public void balance(UserAccount me) {
        System.out.println("Your balance account is: " + me.getBalanceAccount());
    }

    public void transaction(LinkedList<ChargeAccount> chargeAccounts, LinkedList<Transfer> transfers) {
        int index = 1;
        System.out.println("List of charge account: ");
        for (int i = chargeAccounts.size() - 1; i >= 0; i--) {
            System.out.println(index + "- " + chargeAccounts.get(i).getChargeAmount());
            index++;
        }

        System.out.println("-------------------------------------");
        System.out.println("List of transfer: ");
        for (int i = transfers.size() - 1; i >= 0; i--) {
            System.out.println(index + "- " + transfers.get(i).getAmountTransfer());
            index++;
        }
        showTransactionDetail(chargeAccounts, transfers);
    }

    public void showTransactionDetail(LinkedList<ChargeAccount> chargeAccounts, LinkedList<Transfer> transfers) {
        System.out.println("Enter the number of transaction: ");
        int index = ScannerWrapper.getInstance().nextInt();

        if (index <= chargeAccounts.size()) {
            System.out.println(chargeAccounts.get(index - 1).toString());
        } else {
            System.out.println(transfers.get(index - chargeAccounts.size() - 1).toString());
        }
    }


    public void timeFilterTransaction(UserAccount me) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        System.out.println("Enter the start date like this structure year.month.day hour:minute:second(yyyy.MM.dd HH:mm:ss)");
        String startDate = ScannerWrapper.getInstance().nextLine();
        Date date1;
        try {
            date1 = simpleDateFormat.parse(startDate);
        } catch (Exception e) {
            System.out.println("invalid input");
            return;
        }

        System.out.println("Enter the end date like this structure year.month.day hour:minute:second");
        String endDate = ScannerWrapper.getInstance().nextLine();
        Date date2;
        try {
            date2 = simpleDateFormat.parse(endDate);
        } catch (Exception e) {
            System.out.println("invalid input");
            return;
        }

        handleTimeFilterTransaction(me, date1, date2);
    }

    public void handleTimeFilterTransaction(UserAccount me, Date startDate, Date endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        LinkedList<ChargeAccount> newChargeAccount = new LinkedList<ChargeAccount>();
        LinkedList<Transfer> newTransfer = new LinkedList<Transfer>();

        for (int i = me.getChargeAccounts().size() - 1; i >= 0; i--) {
            Date date;
            try {
                date = simpleDateFormat.parse(me.getChargeAccounts().get(i).getDateOfChargeAccount());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (startDate.getTime() <= date.getTime() && endDate.getTime() >= date.getTime()) {
                newChargeAccount.add(me.getChargeAccounts().get(i));
            }
        }

        for (int i = me.getTransfers().size() - 1; i >= 0; i--) {
            Date date;
            try {
                date = simpleDateFormat.parse(me.getTransfers().get(i).getDateOfTransfer());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (startDate.getTime() <= date.getTime() && endDate.getTime() >= date.getTime()) {
                newTransfer.add(me.getTransfers().get(i));
            }
        }
        transaction(newChargeAccount, newTransfer);
    }



    public void addContact(UserAccount me) {
        System.out.println("Enter the first name of contact: ");
        String fName = ScannerWrapper.getInstance().next();

        System.out.println("Enter the last name of contact: ");
        String lName = ScannerWrapper.getInstance().next();

        System.out.println("Enter the phone number name of contact: ");
        String phoneNumber = ScannerWrapper.getInstance().next();

        boolean isTherePhoneNumber = checkPhoneNumber(me, phoneNumber);
        if (!isTherePhoneNumber) {
            Contact newContact = new Contact(fName, lName, phoneNumber);
            me.getMyContacts().add(newContact);
        } else {
            System.out.println("There is same phone number in your contacts");
        }
    }

    public void viewInformationContact(UserAccount me) {
        for (int i = 1; i <= me.getMyContacts().size(); i++) {
            Contact contact = me.getMyContacts().get(i - 1);
            System.out.println(i + "- " + contact.getFirstName() + " " + contact.getLName());
        }
        System.out.println();
        System.out.println("Chose the contact (Enter the number of contact) : ");
        int numberOfContact = ScannerWrapper.getInstance().nextInt();
        numberOfContact--;

        ContactInformationContact contactInformationContact = new ContactInformationContact();
        contactInformationContact.implementInformationContact(me, numberOfContact);
    }


    public boolean checkPhoneNumber(UserAccount me, String phoneNumber) {
        for (Contact entry : me.getMyContacts()) {
            if (entry.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }




    public void editContact(UserAccount me, int numberOfContact) {
        System.out.println("Enter the first name of contact: ");
        String newFirstName = ScannerWrapper.getInstance().next();

        System.out.println("Enter the last name of contact: ");
        String newLastName = ScannerWrapper.getInstance().next();

        String phoneNumber = me.getMyContacts().get(numberOfContact).getPhoneNumber();
        String oldFirstName = me.getMyContacts().get(numberOfContact).getFirstName();
        String oldLastName = me.getMyContacts().get(numberOfContact).getLName();
        String oldName = oldFirstName + oldLastName;

        Contact editContact = new Contact(newFirstName, newLastName, phoneNumber);

        for (int i = 0; i < me.getTransfers().size(); i++) {
            if (me.getTransfers().get(i).getNameOfDestinationAccountOwner().equals(oldName)) {
                me.getTransfers().get(i).setNameOfDestinationAccountOwner(newFirstName + newLastName);
            }
        }

        me.getMyContacts().set(numberOfContact, editContact);
        System.out.println("The information of contact was changed");
    }

    public void viewInformation(UserAccount me, int numberOfContact) {
        System.out.println(me.getMyContacts().get(numberOfContact).toString());
    }


    public void showSupportUser(UserAccount me, Bank myBank) {
        ArrayList<Requests> myRequest = new ArrayList<>();
        for (Map.Entry<String, Requests> entry : myBank.getRequest().entrySet()) {
            if (entry.getKey().equals(me.getPhoneNumber())) {
                myRequest.add(entry.getValue());
            }
        }
        printMassages(myRequest);
    }

    public void printMassages(ArrayList<Requests> requests) {
        for (int i = 1; i <= requests.size(); i++) {
            String supportMassage = requests.get(i).getSupportMassage();
            System.out.println(i + "- " + '{' + requests.get(i - 1).toString() + " Support massage: " + supportMassage + '}');
        }
    }

    public void registerSupportUser(UserAccount me, Bank myBank) {
        Menus.RegisterSupportUser option;
        do {
            Menus.getInstance().printTheRegisterSupportUser();
            option = Menus.getInstance().getOptionRegisterSupportUser();
            handleRegisterSupportUser(myBank, me, option);
        } while (option != Menus.RegisterSupportUser.RETURN);
    }

    public void handleRegisterSupportUser(Bank myBank, UserAccount me, Menus.RegisterSupportUser option) {
        switch (option) {
            case REPORT -> handleReportOfSupportUser(myBank, me);
            case CONTACTS -> handleContactOfSupportUser(myBank, me);
            case TRANSFER -> handleTransferOfSupportUser(myBank, me);
            case SETTING -> handleActivationContactKeyword(me);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }

    public String inputTheMassage() {
        System.out.println("Enter your massage: ");
        String massage = ScannerWrapper.getInstance().next();
        return massage;
    }

    public void handleReportOfSupportUser(Bank myBank, UserAccount me) {
        String massageUser = inputTheMassage();
        Requests requests = new Requests(RequestType.REPORTS, ApplicationStatus.REGISTERED, massageUser, "");
        myBank.addRequest(me.getPhoneNumber(), requests);
    }

    public void handleContactOfSupportUser(Bank myBank, UserAccount me) {
        String massageUser = inputTheMassage();
        Requests requests = new Requests(RequestType.CONTACTS, ApplicationStatus.REGISTERED, massageUser, "");
        myBank.addRequest(me.getPhoneNumber(), requests);
    }

    public void handleTransferOfSupportUser(Bank myBank, UserAccount me) {
        String massageUser = inputTheMassage();
        Requests requests = new Requests(RequestType.TRANSFER, ApplicationStatus.REGISTERED, massageUser, "");
        myBank.addRequest(me.getPhoneNumber(), requests);
    }

    public void handleSettingOfSupportUser(Bank myBank, UserAccount me) {
        String massageUser = inputTheMassage();
        Requests requests = new Requests(RequestType.SETTINGS, ApplicationStatus.REGISTERED, massageUser, "");
        myBank.addRequest(me.getPhoneNumber(), requests);
    }


    public void handleChangePassword(UserAccount me) {
        System.out.println("Enter new password: ");
        String password = ScannerWrapper.getInstance().next();

        boolean isSafePassword = UserHandler.checkPassword(password);
        if (isSafePassword) {
            me.setPassword(password);
        }
    }

    public void handleRegisterCardPassword(UserAccount me) {
        if (me.getCardPassword() == -1) {
            System.out.println("Enter your card password: ");
            String inputStr = ScannerWrapper.getInstance().next();
            boolean isValidPassword = checkValidPassword(inputStr);
            if (!isValidPassword) {
                System.out.println("invalid password!");
                return;
            }

            int cardPassword;
            try {
                cardPassword = Integer.parseInt(inputStr);
            } catch (Exception e) {
                System.out.println("invalid password!");
                return;
            }
            me.setCardPassword(cardPassword);
            System.out.println("Your card password was registered");
        } else {
            System.out.println("Your card password is already registered!");
        }
    }

    public void handleChangeCardPassword(UserAccount me) {
        if (me.getCardPassword() != -1) {
            System.out.println("Enter new card password: ");
            String inputStr = ScannerWrapper.getInstance().next();
            boolean isValidPassword = checkValidPassword(inputStr);
            if (!isValidPassword) {
                System.out.println("invalid password!");
                return;
            }

            int cardPassword;
            try {
                cardPassword = Integer.parseInt(inputStr);
            } catch (Exception e) {
                System.out.println("invalid password!");
                return;
            }
            me.setCardPassword(cardPassword);
            System.out.println("Your card password was changed");
        } else {
            System.out.println("Your card password is not registered!");
        }
    }

    public boolean checkValidPassword(String inputStr) {
        String patternStr = "\\d{4}";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }


    public void handleActivationContactKeyword(UserAccount me) {
        if (!me.getIsActingContactKeyword()) {
            me.setIsActingContactKeyword(true);
            System.out.println("Contact keyword has been active");
        } else {
            System.out.println("Contact keyword already is active!");
        }
    }

    public void handleInactivationContactKeyword(UserAccount me) {
        if (me.getIsActingContactKeyword()) {
            me.setIsActingContactKeyword(false);
            System.out.println("Contact keyword has been inactive");
        } else {
            System.out.println("Contact keyword already is not active!");
        }
    }



    public void handleSelectManually(UserAccount me, Bank myBank) {
        System.out.print("Enter the account number: ");
        int accountNumber = ScannerWrapper.getInstance().nextInt();

        boolean isThereAccountNumber = checkAccountNumber(accountNumber, myBank);
        if (isThereAccountNumber) {
            inputTheMoneyForTransaction(me, myBank, accountNumber);
        }
    }

    public boolean checkAccountNumber(int accountNumber, Bank myBank) {
        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getAccountNumber() == accountNumber) {
                return true;
            }
        }
        System.out.println("Account number is not found!");
        return false;
    }

    public void handleSelectFromResentAccount(UserAccount me, Bank myBank) {
        for (int i = 0; i < me.getRecentlyAccountNumberForTransfer().size(); i++) {
            System.out.println((i + 1) + "- " + me.getRecentlyAccountNumberForTransfer().get(i).toString());
        }

        System.out.print("Enter the number of account number: ");
        int index = ScannerWrapper.getInstance().nextInt();

        int accountNumber = me.getRecentlyAccountNumberForTransfer().get(index - 1).getAccountNumber();
        inputTheMoneyForTransaction(me, myBank, accountNumber);
    }

    public void handleSelectFromContacts(UserAccount me, Bank myBank) {
        Map<Integer, Contact> contacts = new TreeMap<>();
        for (int i = 0; i < me.getMyContacts().size(); i++) {
            Contact newContact = me.getMyContacts().get(i);

            for (int j = 0; j < myBank.getUserAccounts().size(); j++) {
                ArrayList<Contact> contactsOfContact = myBank.getUserAccounts().get(j).getMyContacts();

                for (int k = 0; k < contactsOfContact.size(); k++) {
                    if (contactsOfContact.get(k).getPhoneNumber().equals(newContact.getPhoneNumber())) {
                        int accountNumber = myBank.getUserAccounts().get(j).getAccountNumber();
                        contacts.put(accountNumber, newContact);
                    }
                }
            }
        }
        showContactsForTransfer(me, myBank, (TreeMap<Integer, Contact>) contacts);
    }

    public void showContactsForTransfer(UserAccount me, Bank myBank, TreeMap<Integer, Contact> contact) {
        Map<Integer, Integer> accountNumbers = new HashMap<>();
        List<Contact> contactsList = new ArrayList<>();

        int index = 1;
        for (Map.Entry<Integer, Contact> entry : contact.entrySet()) {
            contactsList.add(entry.getValue());
            accountNumbers.put(index, entry.getKey());
            index++;
        }

        for (int i = 1; i <= contactsList.size(); i++) {
            System.out.println(i + "- " + contactsList.get(i - 1).getFirstName() + contactsList.get(i - 1).getLName());
        }

        System.out.print("Enter the number of account number: ");
        int indexOfContact = ScannerWrapper.getInstance().nextInt();

        int accountNumber = accountNumbers.get(indexOfContact);
        inputTheMoneyForTransaction(me, myBank, accountNumber);
    }

    public void inputTheMoneyForTransaction(UserAccount me, Bank myBank, int accountNumber) {
        System.out.println("Enter the amount money: ");
        int money = ScannerWrapper.getInstance().nextInt();

        int wadge = 1000;
        boolean isMoneyEnough = checkInventory(me, money + wadge);
        if (isMoneyEnough) {
            successTransaction(money, me, myBank, accountNumber);
        }
    }

    public boolean checkInventory(UserAccount me, int money) {
        if (me.getBalanceAccount() > money) {
            return true;
        }
        System.out.println("Your inventory is not enough");
        return false;
    }

    public void successTransaction(int money, UserAccount me, Bank myBank, int accountNumber2) {
        UserAccount destinationAccount = new UserAccount();
        for (int i = 0; i < myBank.getUserAccounts().size(); i++) {
            if (myBank.getUserAccounts().get(i).getAccountNumber() == accountNumber2) {
                destinationAccount = myBank.getUserAccounts().get(i);
            }
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        String dateNow = simpleDateFormat.format(date);

        printRecite(money, me, destinationAccount, dateNow);
        setTransaction(money, me, destinationAccount, dateNow);

    }

    public void printRecite(int money, UserAccount me, UserAccount destinationAccount, String dateNow) {
        int min = 100000;
        int max = 999999;
        int issueTracking = ThreadLocalRandom.current().nextInt(min, max + 1);

        String nameOfDestinationAccount = destinationAccount.getFirstName() + destinationAccount.getLastName();
        int originAccountNumber = me.getAccountNumber();
        int destinationAccountNumber = destinationAccount.getAccountNumber();

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Amount of money: " + money);
        System.out.println("Origin accountNumber: " + originAccountNumber);
        System.out.println("Destination account number: " + destinationAccountNumber);
        System.out.println("Name of destination account: " + nameOfDestinationAccount);
        System.out.println("Date of transfer: " + dateNow);
        System.out.println("Issue tracking: " + issueTracking);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public void setTransaction(int money, UserAccount me, UserAccount destinationAccount, String dateNow) {
        String nameOfDestinationAccount = destinationAccount.getFirstName() + destinationAccount.getLastName();
        int accountNumber1 = me.getAccountNumber();
        int accountNumber2 = destinationAccount.getAccountNumber();

        Transfer newTransfer1 = new Transfer(-money, accountNumber1, accountNumber2, nameOfDestinationAccount, dateNow);
        me.addTransfer(newTransfer1);

        Transfer newTransfer2 = new Transfer(money, accountNumber1, accountNumber2, nameOfDestinationAccount, dateNow);
        me.addTransfer(newTransfer2);
    }
}
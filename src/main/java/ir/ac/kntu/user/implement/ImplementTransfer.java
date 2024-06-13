package ir.ac.kntu.user.implement;

import ir.ac.kntu.main.baseclass.MockAccount;
import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.user.info.Contact;
import ir.ac.kntu.user.info.RecentlyAccountForTransfer;
import ir.ac.kntu.user.info.Transfer;
import ir.ac.kntu.user.info.UserAccount;
import ir.ac.kntu.util.Calendar;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImplementTransfer {
    public void handleSelectManuallyByAccountNumber(UserAccount myAccount, Bank myBank) {
        System.out.print(Color.YELLOW + "Enter the account number: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int accountNumber = 0;
        try {
            accountNumber = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println(Color.RED + "Invalid input!");
            return;
        }

        UserAccount user = checkAccountNumber(accountNumber, myBank);
        MockAccount mockAccount = checkAccountNumberMockAccounts(accountNumber, myBank);
        if (user != null) {
            handleFariAccount(myAccount, myBank, accountNumber, user.getFirstName() + user.getLastName());
        } else if (mockAccount != null) {
            transferByAccountNumber(myAccount, myBank, mockAccount);
        } else {
            System.out.println(Color.RED + "There is not account with this account number!");
        }
    }

    public UserAccount checkAccountNumber(int accountNumber, Bank myBank) {
        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getAccountNumber() == accountNumber) {
                return entry;
            }
        }
        return null;
    }

    public MockAccount checkAccountNumberMockAccounts(int accountNumber, Bank myBank) {
        for (MockAccount entry : myBank.getMockAccounts()) {
            if (entry.getAccountNumber() == accountNumber) {
                return entry;
            }
        }
        return null;
    }

    public void transferByAccountNumber(UserAccount myAccount, Bank myBank, MockAccount mockAccount) {
        int money = inputTheMoney();
        if (money < 0) {
            return;
        }

        System.out.println(Color.BLUE + "1- interBankBridge");
        System.out.println(Color.BLUE + "2- interBankPaya");
        System.out.print(Color.CYAN + "Enter the transfer method: ");
        String input = ScannerWrapper.getInstance().nextLine();

        switch (input) {
            case "1" -> handleInterBankBridge(myAccount, myBank, mockAccount, money);
            case "2" -> handleInterBankPaya(myAccount, myBank, mockAccount, money);
            default -> System.out.println(Color.RED + "Invalid input!");
        }
    }

    public void handleSelectManuallyByCardNumber(UserAccount myAccount, Bank myBank) {
        System.out.print(Color.YELLOW + "Enter the account number: ");
        String cardNumber = ScannerWrapper.getInstance().nextLine();
        UserAccount user = checkCardNumber(cardNumber, myBank);
        MockAccount mockAccount = checkCardNumberMockAccounts(cardNumber, myBank);

        if (user != null) {
            handleFariAccount(myAccount, myBank, user.getAccountNumber(), user.getFirstName() + user.getLastName());
        } else if (mockAccount != null) {
            transferByCardNumber(myAccount, myBank, mockAccount);
        } else {
            System.out.println(Color.RED + "There is not account with this account number!");
        }
    }

    public UserAccount checkCardNumber(String cardNumber, Bank myBank) {
        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getCardNumber().equals(cardNumber)) {
                return entry;
            }
        }
        return null;
    }

    public MockAccount checkCardNumberMockAccounts(String cardNumber, Bank myBank) {
        for (MockAccount entry : myBank.getMockAccounts()) {
            if (entry.getCardNumber().equals(cardNumber)) {
                return entry;
            }
        }
        return null;
    }

    public void transferByCardNumber(UserAccount myAccount, Bank myBank, MockAccount mockAccount) {
        int money = inputTheMoney();
        if (money < 0) {
            return;
        }

        System.out.println(Color.BLUE + "1- cardByCard");
        System.out.println(Color.BLUE + "2- interBankBridge");
        System.out.println(Color.BLUE + "3- interBankPaya");
        System.out.print(Color.CYAN + "Enter the transfer method: ");
        String input = ScannerWrapper.getInstance().nextLine();

        switch (input) {
            case "1" -> handleCardByCard(myAccount, myBank, mockAccount, money);
            case "2" -> handleInterBankBridge(myAccount, myBank, mockAccount, money);
            case "3" -> handleInterBankPaya(myAccount, myBank, mockAccount, money);
            default -> System.out.println(Color.RED + "Invalid input!");
        }
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

        if (index > myAccount.getRecentlyAccount().size()) {
            System.out.println(Color.RED + "Enter index in the rage!");
            return;
        }

        int accountNumber = myAccount.getRecentlyAccount().get(index - 1).getAccountNumber();
        MockAccount mockAccount = checkAccountNumberMockAccounts(accountNumber, myBank);
        String nameOfDestination = myAccount.getRecentlyAccount().get(index - 1).getName();
        if (mockAccount != null) {
            transferByCardNumber(myAccount, myBank, mockAccount);
        } else {
            handleFariAccount(myAccount, myBank, accountNumber, nameOfDestination);
        }
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
                    String myPhoneNumber = myAccount.getPhoneNumber();

                    if (contact.getPhoneNumber().equals(myPhoneNumber) && newContact.getPhoneNumber().equals(myBank.getUserAccounts().get(j).getPhoneNumber())) {
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
        if (indexOfContact > accountNumbers.size()) {
            System.out.println(Color.RED + "Enter index in the rage!");
            return;
        }

        int accountNumber = accountNumbers.get(indexOfContact);
        String nameOfDestination = contact.get(accountNumber).getFirstName() + contact.get(accountNumber).getLName();
        handleFariAccount(myAccount, myBank, accountNumber, nameOfDestination);
    }

    public void handleFariAccount(UserAccount myAccount, Bank myBank, int accountNumber, String nameOfDestination) {
        int money = inputTheMoney();
        if (money < 0) {
            return;
        }

        int wadge = computingWadge(myBank.getInterests().getFariByFari(), money);
        boolean isMoneyEnough = checkInventory(myAccount, money + wadge);
        if (isMoneyEnough) {
            if (!confirmTransfer()) {
                return;
            }

            UserAccount destinAccount = new UserAccount();
            for (int i = 0; i < myBank.getUserAccounts().size(); i++) {
                if (myBank.getUserAccounts().get(i).getAccountNumber() == accountNumber) {
                    destinAccount = myBank.getUserAccounts().get(i);
                }
            }

            myAccount.setBalanceAccount(myAccount.getBalanceAccount() - remainingFundHandler(money + wadge, myAccount));
            destinAccount.setBalanceAccount(destinAccount.getBalanceAccount() + money);

            printRecite(money, myAccount, destinAccount.getAccountNumber(), nameOfDestination);
            setTransactionForFariByFari(money, myAccount, destinAccount, nameOfDestination);
        }

    }

    public void handleCardByCard(UserAccount myAccount, Bank myBank, MockAccount mockAccount, int money) {
        int wadge = computingWadge(myBank.getInterests().getCardByCard(), money);
        boolean isMoneyEnough = checkInventory(myAccount, money + wadge);
        if (isMoneyEnough) {
            if (!confirmTransfer()) {
                return;
            }

            myAccount.setBalanceAccount(myAccount.getBalanceAccount() - remainingFundHandler(money + wadge, myAccount));
            mockAccount.setBalanceAccount(mockAccount.getBalanceAccount() + money);

            printRecite(money, myAccount, mockAccount.getAccountNumber(), mockAccount.getName());
            setTransactionForCardByCardAndInterestBridgeBAnk(money, myAccount, mockAccount);
        }
    }

    public void handleInterBankBridge(UserAccount myAccount, Bank myBank, MockAccount mockAccount, int money) {
        int wadge = computingWadge(myBank.getInterests().getInterBankBridge(), money);
        boolean isMoneyEnough = checkInventory(myAccount, money + wadge);
        if (isMoneyEnough) {
            if (!confirmTransfer()) {
                return;
            }

            myAccount.setBalanceAccount(myAccount.getBalanceAccount() - remainingFundHandler(money + wadge, myAccount));
            mockAccount.setBalanceAccount(mockAccount.getBalanceAccount() + money);

            printRecite(money, myAccount, mockAccount.getAccountNumber(), mockAccount.getName());
            setTransactionForCardByCardAndInterestBridgeBAnk(money, myAccount, mockAccount);
        }
    }

    public void handleInterBankPaya(UserAccount myAccount, Bank myBank, MockAccount mockAccount, int money) {
        int wadge = computingWadge(myBank.getInterests().getInterBankPaya(), money);
        boolean isMoneyEnough = checkInventory(myAccount, money + wadge);
        if (isMoneyEnough) {
            if (!confirmTransfer()) {
                return;
            }

            myBank.addTransfer(new Transfer(money, myAccount.getAccountNumber(), mockAccount.getAccountNumber(), mockAccount.getName(), Calendar.getStringNow()));
        }
    }

    public int inputTheMoney() {
        System.out.print(Color.YELLOW + "Enter the amount money: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int money = -1;
        try {
            money = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println(Color.RED + "Invalid input!");
        }
        return money;
    }

    public int computingWadge(String wadge, int money) {
        if (isStrHasPercent(wadge)) {
            String[] newStr = wadge.split("%");
            if (isStrHasDot(wadge)) {
                double wadgeAmount = Double.parseDouble(newStr[0]);
                return (int) (wadgeAmount * money / 100);
            } else {
                int wadgeAmount = Integer.parseInt(newStr[0]);
                return wadgeAmount * money / 100;
            }
        } else {
            return Integer.parseInt(wadge);
        }
    }

    public boolean isStrHasPercent(String wadge) {
        Pattern pattern = Pattern.compile("%");
        Matcher matcher = pattern.matcher(wadge);
        return matcher.find();
    }

    public boolean isStrHasDot(String wadge) {
        Pattern pattern = Pattern.compile(".");
        Matcher matcher = pattern.matcher(wadge);
        return matcher.find();
    }

    public boolean checkInventory(UserAccount myAccount, int money) {
        if (myAccount.getBalanceAccount() > money) {
            return true;
        }
        System.out.println(Color.RED + "Your inventory is not enough!");
        return false;
    }

    public boolean confirmTransfer() {
        System.out.println(Color.GREEN + "1- yes");
        System.out.println(Color.GREEN + "1- no");
        System.out.print(Color.CYAN + "Do you confirm this transfer? ");
        String input = ScannerWrapper.getInstance().nextLine();
        if (!"1".equals(input) && !"2".equals(input)) {
            System.out.println(Color.RED + "Invalid input!");
            return false;
        } else {
            return "1".equals(input);
        }
    }

    public void setTransactionForCardByCardAndInterestBridgeBAnk(int money, UserAccount myAccount, MockAccount mockAccount) {
        String dateNow = Calendar.getStringNow();
        Transfer newTransfer1 = new Transfer(-money, myAccount.getAccountNumber(), mockAccount.getAccountNumber(), mockAccount.getName(), dateNow);
        myAccount.addTransfer(newTransfer1);

        RecentlyAccountForTransfer account = new RecentlyAccountForTransfer(mockAccount.getName(), mockAccount.getAccountNumber());

        boolean isResentAcc = false;
        for (int i = 0; i < myAccount.getRecentlyAccount().size(); i++) {
            if (myAccount.getRecentlyAccount().get(i).getAccountNumber() == mockAccount.getAccountNumber()) {
                isResentAcc = true;
            }
        }
        if (!isResentAcc) {
            myAccount.addRecentlyAccount(account);
        }

    }

    public void printRecite(int money, UserAccount myAccount, int destination, String nameOfDestination) {
        int min = 100000;
        int max = 999999;
        int issueTracking = ThreadLocalRandom.current().nextInt(min, max + 1);

        int originAccount = myAccount.getAccountNumber();
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

    public void setTransactionForFariByFari(int money, UserAccount myAccount, UserAccount destinAccount, String nameOfDestination) {
        int accountNumber1 = myAccount.getAccountNumber();
        int accountNumber2 = destinAccount.getAccountNumber();
        String destination = destinAccount.getFirstName() + " " + destinAccount.getLastName();
        String dateNow = Calendar.getStringNow();

        Transfer newTransfer1 = new Transfer(-money, accountNumber1, accountNumber2, nameOfDestination, dateNow);
        myAccount.addTransfer(newTransfer1);

        Transfer newTransfer2 = new Transfer(money, accountNumber1, accountNumber2, destination, dateNow);
        destinAccount.addTransfer(newTransfer2);

        RecentlyAccountForTransfer account = new RecentlyAccountForTransfer(nameOfDestination, accountNumber2);

        boolean isResentAcc = false;
        for (int i = 0; i < myAccount.getRecentlyAccount().size(); i++) {
            if (myAccount.getRecentlyAccount().get(i).getAccountNumber() == destinAccount.getAccountNumber()) {
                isResentAcc = true;
            }
        }
        if (!isResentAcc) {
            myAccount.addRecentlyAccount(account);
        }
    }

    public int remainingFundHandler(int money, UserAccount myAccount) {
        int moneyRemain = computingRemainingMoney(money);
        if (moneyRemain + money > myAccount.getBalanceAccount()) {
            return money;
        } else {
            myAccount.getRemainingFund().setFundBalance(myAccount.getRemainingFund().getFundBalance() + moneyRemain);
            return money + moneyRemain;
        }
    }

    public int computingRemainingMoney(int money) {
        int copyMoney = money;
        int countRagam = 0;
        while (copyMoney > 0) {
            countRagam++;
            copyMoney /= 10;
        }

        int sum = (int) (money / Math.pow(10, (countRagam * 3 / 4)) + 1) * (int) (Math.pow(10, (countRagam * 3 / 4)));
        return sum - money;
    }

}

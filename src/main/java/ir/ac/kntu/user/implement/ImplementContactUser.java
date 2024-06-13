package ir.ac.kntu.user.implement;

import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.user.info.Contact;
import ir.ac.kntu.user.info.UserAccount;
import ir.ac.kntu.user.menus.ContactInformationContactMenu;

public class ImplementContactUser {
    public void addContact(UserAccount myAccount) {
        System.out.print(Color.YELLOW + "Enter the first name of contact: ");
        String fName = ScannerWrapper.getInstance().nextLine();

        System.out.print(Color.YELLOW + "Enter the last name of contact: ");
        String lName = ScannerWrapper.getInstance().nextLine();

        System.out.print(Color.YELLOW + "Enter the phone number name of contact: ");
        String phoneNumber = ScannerWrapper.getInstance().nextLine();

        boolean isPhoneNumber = checkPhoneNumber(myAccount, phoneNumber);
        if (!isPhoneNumber) {
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
        if (numberOfContact > myAccount.getMyContacts().size()) {
            System.out.println(Color.RED + "Enter index in the rage!");
            return;
        }

        numberOfContact--;
        ContactInformationContactMenu contactInf = new ContactInformationContactMenu();
        contactInf.implementInformationContact(myAccount, numberOfContact);
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

    public boolean checkPhoneNumber(UserAccount myAccount, String phoneNumber) {
        for (Contact entry : myAccount.getMyContacts()) {
            if (entry.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
}

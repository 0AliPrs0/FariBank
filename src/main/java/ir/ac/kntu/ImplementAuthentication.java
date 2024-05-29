package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class ImplementAuthentication {
    public void manageAuthentication(Bank myBank){
        List<Authentication> authentications = new ArrayList<>();
        for (int i = 0; i < myBank.getAuthentications().size(); i++) {
            if (!myBank.getAuthentications().get(i).isCheckSupport()) {
                authentications.add(myBank.getAuthentications().get(i));
            }
        }

        for (int i = 0; i < authentications.size(); i++) {
            String name = authentications.get(i).getFirstName() + " " + authentications.get(i).getLastName();
            System.out.println(Color.GREEN + (i + 1) + "- " + Color.BLUE + name);
        }

        System.out.println(Color.YELLOW + "Enter the number of authentication");
        String input = ScannerWrapper.getInstance().nextLine();
        int numAuthentication;
        try {
            numAuthentication = Integer.parseInt(input);
        } catch (Exception e) {
            return;
        }

        if (numAuthentication > authentications.size()) {
            System.out.println(Color.RED + "Enter index in the rage!");
            return;
        }

        Authentication authentication = authentications.get(numAuthentication - 1);
        showUserForAuthentication(authentication, myBank);
    }

    public void showUserForAuthentication(Authentication authentication, Bank myBank) {
        int index = 0;
        for (int i = 0; i < myBank.getAuthentications().size(); i++) {
            if (myBank.getAuthentications().get(i).getPhoneNumber().equals(authentication.getPhoneNumber())) {
                index = i;
            }
        }
        myBank.getAuthentications().get(index).setCheckSupport(true);
        System.out.println(authentication.toString());
        System.out.println(Color.YELLOW + "Do you confirm this user ?");
        System.out.println(Color.BLUE + "1- Yes");
        System.out.println(Color.BLUE + "2- No");
        String input = ScannerWrapper.getInstance().nextLine();
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (Exception e) {
            return;
        }

        if (number == 1) {
            myBank.getAuthentications().get(index).setAcceptRequest(true);
        } else {
            System.out.println(Color.YELLOW + "Enter the reason for rejecting this user's authentication");
            String supportOpinion = ScannerWrapper.getInstance().nextLine();
            myBank.getAuthentications().get(index).setSupportOpinion(supportOpinion);
        }

    }
}

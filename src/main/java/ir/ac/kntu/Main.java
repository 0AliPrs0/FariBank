package ir.ac.kntu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {


    public static void main(String[] args) {
//        Date date = new Date();
//        date.getTime();
//        Calendar calendar = Calendar.getInstance();
//        Date currentDate = calendar.getTime();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
//        String formattedDate = sdf.format(currentDate);
//
//        System.out.println(formattedDate);
        Bank myBank = new Bank();

        MainMenu.getInstance().implementMenu(myBank);


    }

}

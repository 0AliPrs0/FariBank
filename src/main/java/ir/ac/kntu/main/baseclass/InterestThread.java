package ir.ac.kntu.main.baseclass;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.manager.implement.ImplementAutoTransaction;

public class InterestThread extends Thread {
    private Bank myBank;
    public InterestThread(Bank myBank){
        this.myBank = myBank;
    }
    @Override
    public void run() {
        ImplementAutoTransaction autoTransaction = new ImplementAutoTransaction();
        for (int i = 0; i < 100; i++) {
            autoTransaction.handleInterest(myBank, true);
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package ir.ac.kntu;

import java.util.Date;

public class BonusFund extends CapitalFund{
    private Date dataOfDeposit;
    private int numberOfDeposit;

    public BonusFund() {
    }

    public BonusFund(String fundName, Date dataOfDeposit, int numberOfDeposit) {
        super(fundName, FundType.BONUS_FUND, 0);
        this.dataOfDeposit = dataOfDeposit;
        this.numberOfDeposit = numberOfDeposit;
    }

    public Date getDataOfDeposit() {
        return dataOfDeposit;
    }

    public void setDataOfDeposit(Date dataOfDeposit) {
        this.dataOfDeposit = dataOfDeposit;
    }

    public int getNumberOfDeposit() {
        return numberOfDeposit;
    }

    public void setNumberOfDeposit(int numberOfDeposit) {
        this.numberOfDeposit = numberOfDeposit;
    }


}

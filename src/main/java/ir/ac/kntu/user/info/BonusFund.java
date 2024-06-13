package ir.ac.kntu.user.info;

import ir.ac.kntu.main.enums.FundType;

import java.util.Date;

public class BonusFund extends CapitalFund {
    private Date dataOfDeposit;
    private int numberOfDeposit;
    private int sumOfDeposit;
    private int fixedFundBalance;

    public BonusFund() {
    }

    public BonusFund(int fundBalance, String fundName, Date dataOfDeposit, int numberOfDeposit) {
        super(fundName, FundType.BONUS_FUND, fundBalance);
        this.dataOfDeposit = dataOfDeposit;
        this.numberOfDeposit = numberOfDeposit;
        this.sumOfDeposit = numberOfDeposit;
        this.fixedFundBalance = fundBalance;
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

    public int getSumOfDeposit() {
        return sumOfDeposit;
    }

    public void setSumOfDeposit(int sumOfDeposit) {
        this.sumOfDeposit = sumOfDeposit;
    }

    public int getFixedFundBalance() {
        return fixedFundBalance;
    }

    public void setFixedFundBalance(int fixedFundBalance) {
        this.fixedFundBalance = fixedFundBalance;
    }
}

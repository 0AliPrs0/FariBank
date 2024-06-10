package ir.ac.kntu;

public class BonusFund extends CapitalFund{
    private String dataOfDeposit;
    private int numberOfDeposit;

    public BonusFund() {
    }

    public BonusFund(String fundName, String dataOfDeposit, int numberOfDeposit) {
        super(fundName, FundType.BONUS_FUND, 0);
        this.dataOfDeposit = dataOfDeposit;
        this.numberOfDeposit = numberOfDeposit;
    }

    public String getDataOfDeposit() {
        return dataOfDeposit;
    }

    public void setDataOfDeposit(String dataOfDeposit) {
        this.dataOfDeposit = dataOfDeposit;
    }

    public int getNumberOfDeposit() {
        return numberOfDeposit;
    }

    public void setNumberOfDeposit(int numberOfDeposit) {
        this.numberOfDeposit = numberOfDeposit;
    }


}

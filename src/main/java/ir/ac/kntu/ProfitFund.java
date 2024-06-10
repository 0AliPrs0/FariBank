package ir.ac.kntu;

public class ProfitFund extends CapitalFund{
    public ProfitFund() {
    }

    public ProfitFund(String fundName) {
        super(fundName, FundType.PROFIT_FUND, 0);
    }

}

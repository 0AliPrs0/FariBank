package ir.ac.kntu;

public class ProfitFund extends CapitalFund{
    public ProfitFund() {
    }

    public ProfitFund(int fundBalance) {
        super("profit fund", FundType.PROFIT_FUND, fundBalance);
    }

}

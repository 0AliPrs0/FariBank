package ir.ac.kntu;

public class RemainingFund extends CapitalFund{
    public RemainingFund() {
    }

    public RemainingFund(String fundName, int fundBalance) {
        super(fundName, FundType.REMAINING_FUND, fundBalance);
    }
}

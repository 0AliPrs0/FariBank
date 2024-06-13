package ir.ac.kntu.user.info;

import ir.ac.kntu.main.enums.FundType;

public class ProfitFund extends CapitalFund {
    public ProfitFund() {
    }

    public ProfitFund(String fundName) {
        super(fundName, FundType.PROFIT_FUND, 0);
    }

}

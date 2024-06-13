package ir.ac.kntu.user.info;

import ir.ac.kntu.main.enums.FundType;

public class RemainingFund extends CapitalFund {

    public RemainingFund() {
        super("remaining fund", FundType.REMAINING_FUND, 0);
    }
}

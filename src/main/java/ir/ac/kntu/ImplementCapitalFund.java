package ir.ac.kntu;

public class ImplementCapitalFund {
    public void addFund(UserAccount myAccount, Bank myBank){

    }

    public void removeFund(UserAccount myAccount, Bank myBank){
        System.out.println(Color.CYAN + "Enter the name of fund for removing");
        String fundName = ScannerWrapper.getInstance().nextLine();

        BonusFund bonusFund = searchBonusFund(myAccount, fundName);
        ProfitFund profitFund = searchProfitFund(myAccount, fundName);

        if (bonusFund != null) {
            myAccount.removeBonusFund(bonusFund);
        } else if (profitFund != null) {
            myAccount.removeProfitFund(profitFund);
        } else {
            System.out.println(Color.RED + "There is not fund with this name!");
        }
    }

    public BonusFund searchBonusFund(UserAccount myAccount, String fundName){
        for (BonusFund bonusFund : myAccount.getBonusFunds()){
            if (bonusFund.getFundName().equals(fundName)){
                return bonusFund;
            }
        }
        return null;
    }

    public ProfitFund searchProfitFund(UserAccount myAccount, String fundName){
        for (ProfitFund profitFund : myAccount.getProfitFunds()){
            if (profitFund.getFundName().equals(fundName)){
                return profitFund;
            }
        }
        return null;
    }

    public void fundManagement(UserAccount myAccount, Bank myBank){

    }
}

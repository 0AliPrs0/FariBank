package ir.ac.kntu;

public class InterestRatesAndFees {
    private String bonusFundInterestRate;
    private String wadgeCardByCard;
    private String wadgeInterBankBridge;
    private String wadgeInterBankPaya;
    private String wadgeFariByFari;

    public InterestRatesAndFees(String bonusFundInterestRate, String wadgeCardByCard, String wadgeInterBankBridge, String wadgeInterBankPaya, String wadgeFariByFari) {
        this.bonusFundInterestRate = bonusFundInterestRate;
        this.wadgeCardByCard = wadgeCardByCard;
        this.wadgeInterBankBridge = wadgeInterBankBridge;
        this.wadgeInterBankPaya = wadgeInterBankPaya;
        this.wadgeFariByFari = wadgeFariByFari;
    }

    public String getBonusFundInterestRate() {
        return bonusFundInterestRate;
    }

    public void setBonusFundInterestRate(String bonusFundInterestRate) {
        this.bonusFundInterestRate = bonusFundInterestRate;
    }

    public String getWadgeCardByCard() {
        return wadgeCardByCard;
    }

    public void setWadgeCardByCard(String wadgeCardByCard) {
        this.wadgeCardByCard = wadgeCardByCard;
    }

    public String getWadgeInterBankBridge() {
        return wadgeInterBankBridge;
    }

    public void setWadgeInterBankBridge(String wadgeInterBankBridge) {
        this.wadgeInterBankBridge = wadgeInterBankBridge;
    }

    public String getWadgeInterBankPaya() {
        return wadgeInterBankPaya;
    }

    public void setWadgeInterBankPaya(String wadgeInterBankPaya) {
        this.wadgeInterBankPaya = wadgeInterBankPaya;
    }

    public String getWadgeFariByFari() {
        return wadgeFariByFari;
    }

    public void setWadgeFariByFari(String wadgeFariByFari) {
        this.wadgeFariByFari = wadgeFariByFari;
    }
}

package ir.ac.kntu;

public class InterestRatesAndFees {
    private String fundInterestRate;
    private String cardByCard;
    private String interBankBridge;
    private String interBankPaya;
    private String fariByFari;

    public InterestRatesAndFees() {
    }

    public InterestRatesAndFees(String fundInterestRate, String cardByCard, String interBankBridge, String interBankPaya, String fariByFari) {
        this.fundInterestRate = fundInterestRate;
        this.cardByCard = cardByCard;
        this.interBankBridge = interBankBridge;
        this.interBankPaya = interBankPaya;
        this.fariByFari = fariByFari;
    }

    public String getFundInterestRate() {
        return fundInterestRate;
    }

    public void setFundInterestRate(String fundInterestRate) {
        this.fundInterestRate = fundInterestRate;
    }

    public String getCardByCard() {
        return cardByCard;
    }

    public void setCardByCard(String cardByCard) {
        this.cardByCard = cardByCard;
    }

    public String getInterBankBridge() {
        return interBankBridge;
    }

    public void setInterBankBridge(String interBankBridge) {
        this.interBankBridge = interBankBridge;
    }

    public String getInterBankPaya() {
        return interBankPaya;
    }

    public void setInterBankPaya(String interBankPaya) {
        this.interBankPaya = interBankPaya;
    }

    public String getFariByFari() {
        return fariByFari;
    }

    public void setFariByFari(String fariByFari) {
        this.fariByFari = fariByFari;
    }
}

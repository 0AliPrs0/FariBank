package ir.ac.kntu;

public class ChargeAccount {
    private int chargeAmount;
    private String dateOfChargeAccount;

    public ChargeAccount() {
    }

    public ChargeAccount(int chargeAmount, String dateOfChargeAccount) {
        this.chargeAmount = chargeAmount;
        this.dateOfChargeAccount = dateOfChargeAccount;
    }

    public int getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(int chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getDateOfChargeAccount() {
        return dateOfChargeAccount;
    }

    public void setDateOfChargeAccount(String dateOfChargeAccount) {
        this.dateOfChargeAccount = dateOfChargeAccount;
    }

    @Override
    public String toString() {
        return  "chargeAmount=" + chargeAmount +
                ", dateOfChargeAccount='" + dateOfChargeAccount;
    }
}

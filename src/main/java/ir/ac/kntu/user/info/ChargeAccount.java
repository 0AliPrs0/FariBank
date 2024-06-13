package ir.ac.kntu.user.info;

public class ChargeAccount {
    private int chargeAmount;
    private String dateOfCharge;

    public ChargeAccount() {
    }

    public ChargeAccount(int chargeAmount, String dateOfCharge) {
        this.chargeAmount = chargeAmount;
        this.dateOfCharge = dateOfCharge;
    }

    public int getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(int chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getDateOfChargeAccount() {
        return dateOfCharge;
    }

    public void setDateOfChargeAccount(String dateOfCharge) {
        this.dateOfCharge = dateOfCharge;
    }

    @Override
    public String toString() {
        return "chargeAmount=" + chargeAmount +
                ", dateOfChargeAccount='" + dateOfCharge + '\'';
    }
}

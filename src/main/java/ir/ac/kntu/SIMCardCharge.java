package ir.ac.kntu;

public class SIMCardCharge {
    private String phoneNumber;
    private int chargeAmount;
    private String dateOfCharge;

    public SIMCardCharge() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(int chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getDateOfCharge() {
        return dateOfCharge;
    }

    public void setDateOfCharge(String dateOfCharge) {
        this.dateOfCharge = dateOfCharge;
    }

    @Override
    public String toString() {
        return  "phoneNumber='" + phoneNumber + '\'' +
                ", chargeAmount=" + chargeAmount +
                ", dateOfCharge='" + dateOfCharge + '\'';
    }
}

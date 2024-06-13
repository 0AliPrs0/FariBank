package ir.ac.kntu.user.info;

public class Transfer {
    private int amountTransfer;
    private int beginningAccount;
    private int destinAccount;
    private String nameOfDestination;
    private String dateOfTransfer;

    public Transfer() {
    }

    public Transfer(int amountTransfer, int beginningAccount, int destinAccount, String nameOfDestination, String dateOfTransfer) {
        this.amountTransfer = amountTransfer;
        this.beginningAccount = beginningAccount;
        this.destinAccount = destinAccount;
        this.nameOfDestination = nameOfDestination;
        this.dateOfTransfer = dateOfTransfer;
    }

    public int getAmountTransfer() {
        return amountTransfer;
    }

    public void setAmountTransfer(int amountTransfer) {
        this.amountTransfer = amountTransfer;
    }

    public int getBeginningAccountNumber() {
        return beginningAccount;
    }

    public void setBeginningAccountNumber(int beginningAccount) {
        this.beginningAccount = beginningAccount;
    }

    public int getDestinationAccountNumber() {
        return destinAccount;
    }

    public void setDestinationAccountNumber(int destinAccount) {
        this.destinAccount = destinAccount;
    }

    public String getNameOfDestinationAccountOwner() {
        return nameOfDestination;
    }

    public void setNameOfDestinationAccountOwner(String nameOfDestination) {
        this.nameOfDestination = nameOfDestination;
    }

    public String getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(String dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }

    @Override
    public String toString() {
        return  "amount transfer=" + amountTransfer +
                ", beginning accountNumber=" + beginningAccount +
                ", destination accountNumber=" + destinAccount +
                ", nameOfDestination accountOwner='" + nameOfDestination + '\'' +
                ", date of transfer='" + dateOfTransfer + '\'';
    }
}

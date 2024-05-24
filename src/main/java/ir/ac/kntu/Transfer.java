package ir.ac.kntu;

public class Transfer {
    private int amountTransfer;
    private int beginningAccountNumber;
    private int destinationAccountNumber;
    private String nameOfDestinationAccountOwner;
    private String dateOfTransfer;

    public Transfer() {
    }

    public Transfer(int amountTransfer, int beginningAccountNumber, int destinationAccountNumber, String nameOfDestinationAccountOwner, String dateOfTransfer) {
        this.amountTransfer = amountTransfer;
        this.beginningAccountNumber = beginningAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.nameOfDestinationAccountOwner = nameOfDestinationAccountOwner;
        this.dateOfTransfer = dateOfTransfer;
    }

    public int getAmountTransfer() {
        return amountTransfer;
    }

    public void setAmountTransfer(int amountTransfer) {
        this.amountTransfer = amountTransfer;
    }

    public int getBeginningAccountNumber() {
        return beginningAccountNumber;
    }

    public void setBeginningAccountNumber(int beginningAccountNumber) {
        this.beginningAccountNumber = beginningAccountNumber;
    }

    public int getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(int destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public String getNameOfDestinationAccountOwner() {
        return nameOfDestinationAccountOwner;
    }

    public void setNameOfDestinationAccountOwner(String nameOfDestinationAccountOwner) {
        this.nameOfDestinationAccountOwner = nameOfDestinationAccountOwner;
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
                ", beginning accountNumber=" + beginningAccountNumber +
                ", destination accountNumber=" + destinationAccountNumber +
                ", nameOfDestination accountOwner='" + nameOfDestinationAccountOwner + '\'' +
                ", date of transfer='" + dateOfTransfer + '\'';
    }
}

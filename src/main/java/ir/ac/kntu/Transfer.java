package ir.ac.kntu;

public class Transfer {
    private int beginningAccountNumber;
    private int destinationAccountNumber;
    private String nameOfDestinationAccountOwner;
    private String dateOfTransfer;

    public Transfer() {
    }

    public Transfer(int beginningAccountNumber, int destinationAccountNumber, String nameOfDestinationAccountOwner, String dateOfTransfer) {
        this.beginningAccountNumber = beginningAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.nameOfDestinationAccountOwner = nameOfDestinationAccountOwner;
        this.dateOfTransfer = dateOfTransfer;
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
        return "Transfer{" +
                "beginningAccountNumber=" + beginningAccountNumber +
                ", destinationAccountNumber=" + destinationAccountNumber +
                ", nameOfDestinationAccountOwner='" + nameOfDestinationAccountOwner + '\'' +
                ", dateOfTransfer='" + dateOfTransfer + '\'' +
                '}';
    }
}

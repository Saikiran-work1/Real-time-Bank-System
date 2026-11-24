class Transaction
{
    private int transactionId;
    private int payerAcNo;
    private int receiverAcNo;
    private float amount;
    private String transactionType;

    public Transaction(int transactionId, int payerAcno, int receiverAcNo,float amount, String transactionType)
    {
        this.transactionId = transactionId;
        this.payerAcNo = payerAcNo;
        this.receiverAcNo = receiverAcNo;
        this.transactionType = transactionType; 
        this.amount = amount;
    }

    public String getTransactionDetails()
    {
        return "Transaction ID: " + transactionId + "\nPayer Account Number: " + payerAcNo + "\nReceiver Account Number: " + receiverAcNo + "\nAmount: " + amount + "\nTransaction Type: " + transactionType;
    }

}

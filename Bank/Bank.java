
import java.util.ArrayList;

class Bank
{
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;
    private AccountDAO accountDAO;

    private int nextTransactionId;

    
public Bank()
{
    accountDAO = new AccountDAO();
    accounts = new ArrayList<>();
    transactions = new ArrayList<>();
    nextTransactionId = 1;

    accounts.addAll(accountDAO.getAllAccounts());
}


    public Account createAccount(String customerName, String password)
    {
        Account account = new Account(0, customerName, password);

        boolean saved = accountDAO.createAccount(account);

        if (!saved)
        {
            System.out.println("Account creation failed.");
            return null;
        }

        accounts.add(account);
        return account;
    }

    public Account findAccount(int accountNumber)
    {
        for (Account account : accounts)
        {
            if (account.getAccountNumber() == accountNumber)
            {
                return account;
            }
        }

        return null;
    }

    public void closeAccount(int accountNumber)
    {
        Account account = findAccount(accountNumber);

        if (account != null)
        {
            account.closeAccount();
        }
    }

    public boolean transfer(int payerAcNo, int receiverAcNo, float amount)
    {
        Account payer = findAccount(payerAcNo);
        Account receiver = findAccount(receiverAcNo);

        if (payer == null || receiver == null)
        {
            return false;
        }

        if (payerAcNo == receiverAcNo)
        {
            return false;
        }

        if (payer.withdraw(amount))
        {
            receiver.deposit(amount);

            Transaction transaction = new Transaction(
                nextTransactionId,
                payerAcNo,
                receiverAcNo,
                amount,
                "Transfer"
            );

            transactions.add(transaction);
            nextTransactionId++;

            return true;
        }

        return false;
    }
}

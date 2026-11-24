class Account
{
    private int accountNumber;
    private String customerName;
    private String password;
    private float balance;
    private boolean isActive;

   public Account(int accountNumber, String customerName, String password) {
    this.accountNumber = accountNumber;
    this.customerName = customerName;
    this.password = PasswordUtil.hashPassword(password);
    this.balance = 0;
    this.isActive = true;
}
    public void deposit(float amount)
    {
        balance = balance + amount;
    }



    public boolean withdraw(float amount)
   {
      if (amount > 0 && amount <= balance)
       {
          balance = balance - amount;
          return true;
       }

      return false;
   }
    public float checkBalance()
    {
        return balance;
    }


    
    public boolean verifyPassword(String password) 
    {
        return PasswordUtil.verifyPassword(password, this.password);
    }

    public void closeAccount()
    {
        isActive = false;
    }

    public void changePassword(String newPassword) 
    {
        this.password = PasswordUtil.hashPassword(newPassword);
    }

    public String getName()
    {
        return customerName;
    }
    
    public int getAccountNumber()
    {
        return accountNumber;
    }


    public boolean isActive()
    {
        return isActive;
    }

    String getPasswordHash()
    {
        return password;
    }

    public void setAccountNumber(int accountNumber)
    {
        this.accountNumber = accountNumber;
    }


    
public void setPasswordHash(String passwordHash)
{
    this.password = passwordHash;
}

public void setBalance(float balance)
{
    this.balance = balance;
}

public void setActive(boolean active)
{
    this.isActive = active;
}





}
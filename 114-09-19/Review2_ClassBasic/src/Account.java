public class Account {
    // 私人，帳號
    private String accountNumber;
    // 私人，餘額
    private double balance;

    // 公開建構子
    // @param accountNumber 帳號
    // @param initialBalance 初始餘額
    public Account(String accountNumber, double initialBalance)
    {
        this.setAccountNumber(accountNumber);
        try
        {
            this.setBalance(initialBalance);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("建立帳戶失敗：" + e.getMessage());
            this.balance = 0;
        }
    }

    // 取得帳號
    public String getAccountNumber()
    {
        return accountNumber;
    }

    // 取得餘額
    public double getBalance()
    {
        return balance;
    }

    // 設定餘額
    public void setBalance(double balance)
    {
        if (balance > 0)
        {
            this.balance = balance;
        }
        else {
            throw new IllegalArgumentException("帳戶金額必須為正數");
        }
    }

    // 設定帳號
    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    // 存款
    public void deposit(double amount) {
        if (amount > 0)
        {
            balance += amount;
        }
        else {
            throw new IllegalArgumentException("存款金額必須為正數");
        }
    }

    // 提款
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance)
        {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("提款金額不正確");
        }
    }

}
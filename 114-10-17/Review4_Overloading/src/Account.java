//Overloading example in Account class with deposit methods

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

    public Account (){
        this("000000",0);
    }

    public Account(String accountNumber)
    {
        this(accountNumber, 0);
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

    // 共用輸入金額並驗證的方法（改為立即驗證，不再互動輸入）
    private double getValidAmount(double firstValue, String prompt, java.util.function.Predicate<Double> validator, String errorMsg) {
        if (validator.test(firstValue)) {
            return firstValue;
        }
        throw new IllegalArgumentException(errorMsg);
    }

    // 設定餘額
    public void setBalance(double balance)
    {
        double validBalance = getValidAmount(
                balance,
                "請輸入帳戶金額（必須為正數）：",
                b -> b > 0,
                "帳戶金額必須為正數，已超過三次機會。"
        );
        this.balance = validBalance;
    }

    // 設定帳號
    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    // 存款
    public void deposit(double amount) {
        double validAmount = getValidAmount(
                amount,
                "請輸入存款金額（必須為正數）：",
                a -> a > 0,
                "存款金額必須為正數，已超過三次機會。"
        );
        balance += validAmount;
    }
    /** 以不同貨幣存款
     * @param amount 存款金額
     * @param currency 貨幣類型 (如 "USD", "EUR", "JPY")
     */

    public void  deposit(double amount, String currency)
    {
        double exchangeRate = 1.0;
        switch (currency.toUpperCase()) {
            case "USD":
                exchangeRate = 30.0; // 假設1 USD = 30 TWD
                break;
            case "EUR":
                exchangeRate = 35.0; // 假設1 EUR = 35 TWD
                break;
            case "JPY":
                exchangeRate = 0.25; // 假設1 JPY = 0.25 TWD
                break;
            default:
                throw new IllegalArgumentException("不支援的貨幣類型: " + currency);
        }
        double amountInTWD = amount * exchangeRate;
        this.deposit(amountInTWD);
    }

    /** 多重存款
     * @param amounts 多個存款金額
     */
    public void deposit(double...amounts)
    {
        double total = 0;
        for (double amount : amounts)
        {
            if (amount >= 0)
            {
                total += amount;
            }
            else
            {
                throw new IllegalArgumentException("存款金額須為正數");
            }
        }
        this.deposit(total);
    }

    // 提款
    public void withdraw(double amount) {
        double validAmount = getValidAmount(
                amount,
                "請輸入提款金額（必須為正且小於等於餘額）：",
                a -> a > 0 && a <= balance,
                "提款金額不正確，已超過三次機會。"
        );
        balance -= validAmount;
    }

}
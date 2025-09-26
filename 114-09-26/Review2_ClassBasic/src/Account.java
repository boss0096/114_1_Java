import java.util.Scanner;

public class Account {
    // 私人，帳號
    private String accountNumber;
    // 私人，餘額
    private double balance;
    private static final Scanner scanner = new Scanner(System.in);

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

    // 共用輸入金額並驗證的方法
    private double getValidAmount(double firstValue, String prompt, java.util.function.Predicate<Double> validator, String errorMsg) {
        if (validator.test(firstValue)) {
            return firstValue;
        }
        int attempts = 0;
        double inputAmount = firstValue;
        while (attempts < 3) {
            System.out.print(prompt);
            inputAmount = scanner.nextDouble();
            if (validator.test(inputAmount)) {
                return inputAmount;
            } else {
                attempts++;
            }
        }
        System.out.println(errorMsg);
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
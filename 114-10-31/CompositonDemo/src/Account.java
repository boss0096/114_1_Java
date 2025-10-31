import java.time.LocalDateTime;

public class Account {
    private static int accountCount = 0; // 帳戶數量統計

    // 帳戶號碼，唯一識別每個帳戶
    private String accountNumber;
    // 帳戶擁有者 (使用 Person 組合)
    private Person owner;
    // 帳戶餘額
    private double balance;


    private Date openigDate;
    private Time2 openingTime;

    /**
     * 主建構子：接受帳號、持有人姓名、持有人身分證與初始餘額
     * @param accountNumber 帳戶號碼
     * @param ownerName 擁有者姓名
     * @param ownerId 擁有者身分證字號
     * @param initialBalance 初始餘額
     */
    public Account(String accountNumber, String ownerName, String ownerId, double initialBalance) {
        LocalDateTime now = LocalDateTime.now();
        this.setAccountNumber(accountNumber);
        this.owner = new Person(ownerName, ownerId);
        try {
            this.setBalance(initialBalance);
        } catch (IllegalArgumentException e) {
            System.out.println("初始餘額錯誤: " + e.getMessage() + "，將餘額設為0");
            this.balance = 0; // 明確設為 0
        }
        accountCount++; // 帳戶數量加1
        this.openigDate = new Date(now.getMonthValue(), now.getDayOfMonth(), now.getYear());
        this.openingTime = new Time2(now.getHour(), now.getMinute(), now.getSecond());
    }

    /**
     * 常用的便利建構子：只有帳號與初始餘額（持有人名稱預設為 未知，身分證為 N/A）
     * @param accountNumber 帳戶號碼
     * @param initialBalance 初始餘額
     */
    public Account(String accountNumber, double initialBalance) {
        this(accountNumber, "未知", "N/A", initialBalance);
    }

    public Account() {
        this("未知", "未知", "N/A", 0);
    }

    public Account(String accountNumber) {
        this(accountNumber, "未知", "N/A", 0);
    }

    /**
     * 取得帳戶號碼
     * @return 帳戶號碼
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * 取得帳戶餘額
     * @return 帳戶餘額
     */
    public double getBalance() {
        return balance;
    }

    /**
     * 取得帳戶擁有者名稱
     * @return 帳戶擁有者名稱
     */
    public String getOwnerName() {
        return owner != null ? owner.getName() : "未知";
    }

    // 取得 Person 物件
    public Person getOwner() {
        return owner;
    }

    /**
     * 設定帳戶餘額
     * @param balance 欲設定的帳戶餘額，必須為正數
     * @throws IllegalArgumentException 若餘額非正數則拋出例外
     */
    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance; // 設定新的帳戶餘額（允許 0）
        } else {
            throw new IllegalArgumentException("帳戶餘額必須為非負數");
        }
    }

    /**
     * 設定帳戶號碼
     * @param accountNumber 欲設定的帳戶號碼
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * 存款方法，將指定金額存入帳戶
     * @param amount 存入金額，必須為正數
     * @throws IllegalArgumentException 若金額非正數則拋出例外
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount; // 增加餘額
        } else {
            throw new IllegalArgumentException("存款金額必須為正數");
        }
    }

    /**
     * 提款方法，從帳戶扣除指定金額
     * @param amount 提款金額，必須為正數且不得超過餘額
     * @throws IllegalArgumentException 若金額不合法則拋出例外
     */
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount; // 減少餘額
        } else {
            throw new IllegalArgumentException("提款金額不合法");
        }
    }
    public String toString() {
        String ownerStr = (owner != null) ? owner.toString() : "未知";
        return String.format("帳戶號碼: %s, 持有人: %s, 餘額: %.2f, 開戶日期: %s, 開戶時間: %s",
                accountNumber, ownerStr, balance, openigDate.toString(), openingTime.toString());
    }
}

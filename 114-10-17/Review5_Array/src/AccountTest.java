public class AccountTest
{
    private static int customerCount;
    public static void main(String[] args) {
        Account[] customers = new Account[10];
        Account acc1 = new Account("A001","John", 1000);
        addCustomer(customers, acc1);
        Account acc2 = new Account("A002","Mary", 2000);
        addCustomer(customers, acc2);
        Account acc3 = new Account("A003","Bob", 1500);
        addCustomer(customers, acc3);

        //顯示所有客戶資訊

        System.out.println("\n所有客戶資訊：");
        printCustomerAccounts(customers);
    }



    public static void addCustomer(Account[] customers, Account newAccount) {
        if (customerCount < customers.length) {
            customers[customerCount] = newAccount;
            customerCount++;
            System.out.println("新增客戶成功: " + newAccount.getAccountNumber());
            return;
        }
        System.out.println("無法新增客戶，客戶數量已達上限");
    }

    public static void printCustomerAccounts(Account[] customers) {
        for (int i = 0; i < customerCount; i++) {
            printCustomerInfo(customers[i]);
        }
    }

    public static void printCustomerInfo(Account account) {
        System.out.println("帳號: " + account.getAccountNumber() +
                           ", 客戶名稱: " + account.getOwnerName() +
                           ", 餘額: " + account.getBalance());
    }

}

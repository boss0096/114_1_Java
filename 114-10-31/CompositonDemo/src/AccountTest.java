import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountTest {
    public static void main(String[] args) {
        List<Account> customers = new ArrayList<>(); // 儲存客戶帳戶的列表
        Account acc1 = new Account("A001", "Alice", "N/A", 5000);
        addCustomer(customers, acc1);
        Account acc2 = new Account("A002", "Bob", "N/A", 3000);
        addCustomer(customers, acc2);
        Account acc3 = new Account("A003", "Charlie", "N/A", -100);
        addCustomer(customers, acc3);

        operation(customers);
        // 顯示所有客戶帳戶資訊
//        System.out.println("\n所有位客戶帳戶資訊:");
//        printCustomerAccounts(customers);
    }

    public static void operation(List<Account> customers) {
        Scanner scanner = new Scanner(System.in);
        Account selectedAccount = null;
        while (true) {
            menu();
            System.out.print("請選擇功能(1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除換行符號
            switch (choice) {
                case 1:
                    System.out.print("輸入帳戶號碼: ");
                    String accNum = scanner.nextLine();
                    System.out.print("輸入持有人名稱: ");
                    String ownerName = scanner.nextLine();
                    System.out.print("輸入初始餘額: ");
                    double initialBalance = scanner.nextDouble();
                    scanner.nextLine();
                    Account newAccount = new Account(accNum, ownerName, "N/A", initialBalance);
                    addCustomer(customers, newAccount);
                    break;
                case 2:
                    System.out.print("輸入要查詢的帳戶號碼: ");
                    String searchAccNum = scanner.nextLine();
                    selectedAccount = customerInAction(customers, searchAccNum);
                    if (selectedAccount == null) {
                        // 找不到帳戶，回到主選單
                        break;
                    }
                    // 進入針對該帳戶的子選單（存款/提款/列印/回主選單）
                    accountOperation(scanner, selectedAccount);
                    break;

                case 3:
                    System.out.println("\n所有客戶帳戶資訊:");
                    printCustomerAccounts(customers);
                    break;
                case 4:
                    System.out.print("輸入要刪除的帳戶號碼: ");
                    String deleteAccNum = scanner.nextLine();
                    deleteCustomer(customers, deleteAccNum);
                    break;
                case 5:
                    System.out.println("離開系統，謝謝使用!");
                    return;
                default:
                    System.out.println("無效的選擇，請重新輸入");
            }
        }
    }

    // 新增：對單一帳戶的操作（存款、提款、列印或返回）
    public static void accountOperation(Scanner scanner, Account account) {
        while (true) {
            System.out.println("\n帳戶操作選單:");
            System.out.println("1. 存款");
            System.out.println("2. 提款");
            System.out.println("3. 顯示帳戶資訊");
            System.out.println("4. 返回主選單");
            System.out.print("請選擇功能(1-4): ");

            int actionChoice;
            try {
                actionChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("輸入格式錯誤，請輸入數字");
                continue;
            }

            switch (actionChoice) {
                case 1: // 存款
                    System.out.print("輸入存款金額: ");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        account.deposit(amount);
                        System.out.printf("存款成功，新的餘額: %.2f%n", account.getBalance());
                    } catch (NumberFormatException e) {
                        System.out.println("輸入格式錯誤，請輸入數字");
                    } catch (IllegalArgumentException e) {
                        System.out.println("錯誤: " + e.getMessage());
                    }
                    break;
                case 2: // 提款
                    System.out.print("輸入提款金額: ");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        account.withdraw(amount);
                        System.out.printf("提款成功，新的餘額: %.2f%n", account.getBalance());
                    } catch (NumberFormatException e) {
                        System.out.println("輸入格式錯誤，請輸入數字");
                    } catch (IllegalArgumentException e) {
                        System.out.println("錯誤: " + e.getMessage());
                    }
                    break;
                case 3:
                    printCustomerInfo(account);
                    break;
                case 4:
                    return; // 返回主選單
                default:
                    System.out.println("無效的選擇，請重新輸入");
            }
        }
    }

    public static Account customerInAction(List<Account> customers, String accountNumber) {
        for (Account account : customers) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        System.out.println("找不到指定的帳戶號碼: " + accountNumber);
        return null;
    }

    public static void addCustomer(List<Account> customers, Account newAccount) {
        customers.add(newAccount);
        System.out.println("新增客戶成功: " + newAccount.getAccountNumber());
    }

    public static void deleteCustomer(List<Account> customers, String accountNumber) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getAccountNumber().equals(accountNumber)) {
                Account removedAccount = customers.remove(i);
                System.out.println("刪除客戶成功: " + removedAccount.getAccountNumber() + " (" + removedAccount.getOwnerName() + ")");
                return;
            }
        }
        System.out.println("找不到指定的帳戶號碼: " + accountNumber);
    }

    // 列印所有客戶帳戶資訊，使用 for-each 寫法

    public static void printCustomerAccounts(List<Account> customers) {
        for (Account customer : customers) {
            printCustomerInfo(customer);
        }
    }

    public static void printCustomerInfo(Account account) {
        if (account == null) {
            System.out.println("無法列印帳戶資訊，帳戶不存在");
            return;
        }
//
        System.out.println(account.toString());
    }

    // 功能選單 (1) 新增客戶 (2) 列印指定客戶帳戶資訊 (3) 顯示所有客戶帳戶資訊 (4) 刪除客戶帳戶 (5) 離開
    public static void menu() {
        System.out.println("功能選單:");
        System.out.println("1. 新增客戶");
        System.out.println("2. 列印指定客戶帳戶資訊");
        System.out.println("3. 顯示所有客戶帳戶資訊");
        System.out.println("4. 刪除客戶帳戶");
        System.out.println("5. 離開");
    }
}

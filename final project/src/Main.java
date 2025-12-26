// // Source code recreated from a .class file by IntelliJ IDEA
// // (powered by FernFlower decompiler)

// 匯入輸出用的 PrintStream（用於列印資料）
import java.io.PrintStream; // 此行用於明確引入 PrintStream 類別
// 匯入時間處理的類別
import java.time.Duration; // Duration 用來表示一段時間長度
import java.time.LocalDateTime; // LocalDateTime 用於表示時間點
// 匯入集合和輸入掃描器
import java.util.HashMap; // HashMap 用於儲存鍵值對
import java.util.Map; // Map 介面
import java.util.Scanner; // Scanner 用於從標準輸入讀取使用者輸入
// 匯入停車位相關類別
import spots.ElectricSpot; // 電動車位類別
import spots.ParkingFloor; // 停車樓層類別
import spots.ParkingSpot; // 停車位介面/基底類別
import spots.RegularSpot; // 一般車位類別
import spots.SmallSpot; // 機車/小型車位類別
// 匯入收費策略類別
import strategies.MemberPricingStrategy; // 會員收費策略
import strategies.StandardPricingStrategy; // 非會員或標準收費策略
// 匯入系統核心類別
import system.ParkingLot; // 停車場系統主要類別
import system.ParkingTicket; // 停車票券類別
// 匯入車輛類別
import vehicles.ElectricVehicle; // 電動車
import vehicles.Motorcycle; // 機車
import vehicles.Sedan; // 轎車
import vehicles.Truck; // 貨車
import vehicles.Vehicle; // 車輛基底類別

// 主程式類別宣告
public class Main {
    // 儲存目前場內的票券：Key 為車牌號碼，Value 為 ParkingTicket 物件
    private static Map<String, ParkingTicket> activeTickets = new HashMap();
    // 使用的 Scanner 物件（從 System.in 讀取輸入），在宣告時初始化以避免靜態初始化順序問題
    private static Scanner scanner = new Scanner(System.in);

    // 顯示費率資訊的方法（在主程式顯示選單前呼叫）
    private static void showPricingInfo() {
        // 建立標準收費策略物件，用來計算標準/非會員費用
        StandardPricingStrategy var0 = new StandardPricingStrategy();
        // 建立會員收費策略物件，用來計算會員費用
        MemberPricingStrategy var1 = new MemberPricingStrategy();
        // 設定時間單位為 1 小時（Duration）
        Duration var2 = Duration.ofHours(1L);
        // 使用標準策略計算 1 小時的費用
        double var3 = var0.calculate(var2);
        // 使用會員策略計算 1 小時的費用
        double var5 = var1.calculate(var2);
        // 設定充電費用每分鐘為 2（此範例值，單位：元/分鐘）
        double var7 = (double)2.0F;
        // 將每分鐘費用轉為每小時費用，60 分鐘 * 每分鐘費用
        double var9 = var7 * (double)60.0F;
        // 顯示分隔線
        System.out.println("--------------------------------");
        // 顯示費率標題
        System.out.println("=== 費率說明 ===");
        // 顯示停車費用說明標頭
        System.out.println("* 停車費用 (以小時計算)：");
        // 顯示非會員 (標準) 每小時費用
        System.out.println("  - 非會員：每小時 " + var3 + " 元");
        // 顯示會員每小時費用，並比較與非會員差異
        System.out.println("  - 會員：每小時 " + var5 + " 元 (會員享有折扣，較非會員每小時省 " + (var3 - var5) + " 元)");
        // 顯示電動車充電費用（每分鐘）及換算後每小時金額
        System.out.println("* 電動車充電費用：每分鐘 " + var7 + " 元 (約每小時 " + var9 + " 元)，僅對電動車且實際充電時計算");
        // 顯示依車種換算後的每小時停車費（以所需車位數乘以每個車位的每小時費用）
        System.out.println("* 車種每小時費用 (示意)：");
        // 建立範例車種以取得所需車位數
        Motorcycle tmpMoto = new Motorcycle("MOTO");
        Sedan tmpSedan = new Sedan("SEDAN");
        Truck tmpTruck = new Truck("TRUCK");
        ElectricVehicle tmpEV = new ElectricVehicle("EV", true);
        // 計算各車種在非會員與會員身分下的每小時費用
        double motoStd = var3 * tmpMoto.getRequiredSpotCount();
        double motoMem = var5 * tmpMoto.getRequiredSpotCount();
        double sedanStd = var3 * tmpSedan.getRequiredSpotCount();
        double sedanMem = var5 * tmpSedan.getRequiredSpotCount();
        double truckStd = var3 * tmpTruck.getRequiredSpotCount();
        double truckMem = var5 * tmpTruck.getRequiredSpotCount();
        double evStd = var3 * tmpEV.getRequiredSpotCount();
        double evMem = var5 * tmpEV.getRequiredSpotCount();
        // 印出結果
        System.out.println("  - 機車 (Motorcycle)：非會員 每小時 " + motoStd + " 元，會員 每小時 " + motoMem + " 元");
        System.out.println("  - 轎車 (Sedan)：非會員 每小時 " + sedanStd + " 元，會員 每小時 " + sedanMem + " 元");
        System.out.println("  - 貨車 (Truck)：非會員 每小時 " + truckStd + " 元，會員 每小時 " + truckMem + " 元");
        System.out.println("  - 電動車 (EV, 不含充電)：非會員 每小時 " + evStd + " 元，會員 每小時 " + evMem + " 元");
         // 顯示備註，說明最終結算以離場時計算
         System.out.println("* 備註：停車費由系統內的定價策略決定；實際結算以離場時計算之停車時長與是否為會員為準。\n");
         // 顯示系統標題（費率說明結束後會由選單方法印出功能選項）
         System.out.println("=== 智慧停車場管理系統 (正式版) ===");
         // 確保剛印出的內容馬上 flush 到終端，避免被緩衝區擋住
         System.out.flush();
    }

    // 顯示功能選單與提示輸入的輔助方法（每次等待輸入前呼叫）
    private static void showMenu() {
        System.out.println("請選擇功能:");
        System.out.println("1. 車輛進場 (Entry)");
        System.out.println("2. 車輛離場 (Exit)");
        System.out.println("3. 查詢場內車輛 (Status)");
        System.out.println("0. 結束系統 (Quit)");
        // 提示使用者輸入選項（不換行，等待輸入）
        System.out.print("輸入選項: ");
        // 強制 flush，確保 prompt 會即時顯示
        System.out.flush();
    }

    // 主程式入口點
    public static void main(String[] var0) {
        // 初始化停車場（建立樓層與車位）
        ParkingLot var1 = initializeParkingLot();
        // 顯示系統已初始化的文字
        System.out.println("系統初始化完成，等待指令...");
        // 顯示費率（只印一次）
        showPricingInfo();

        // 進入主迴圈，不斷等待與處理使用者輸入
        while(true) {
            try {
                // 每回合先顯示選單再讀取使用者輸入
                showMenu();
                // 使用 scanner 讀取使用者輸入的一行文字，並依據選項執行相對應的處理
                String userInput = scanner.nextLine();
                switch (userInput) {
                    case "1":
                        // 處理進場流程
                        handleEntryProcess(var1);
                        break;
                    case "2":
                        // 處理離場流程
                        handleExitProcess(var1);
                        break;
                    case "3":
                        // 顯示場內車輛狀態
                        showStatus();
                        break;
                    case "0":
                        // 結束系統
                        System.out.println("系統已關閉。");
                        return;
                    default:
                        // 無效輸入提示
                        System.out.println("無效的選項，請重新輸入。");
                }
            } catch (Exception var5) {
                // 若執行期間發生例外，印出錯誤訊息到標準錯誤
                System.err.println("發生錯誤: " + var5.getMessage());
            }
        }
    }

    // 初始化停車場的輔助方法（建立樓層與車位）
    private static ParkingLot initializeParkingLot() {
        // 建立 ParkingLot 實例
        ParkingLot var0 = new ParkingLot();
        // 建立 1 樓物件
        ParkingFloor var1 = new ParkingFloor(1);

        // 在 1 樓新增 1-5 的 SmallSpot（通常是機車/小型車位）
        for(int var2 = 1; var2 <= 5; ++var2) {
            var1.addSpot(new SmallSpot(var2, 1));
        }

        // 在 1 樓新增 6-15 的 RegularSpot（一般車位）
        for(int var4 = 6; var4 <= 15; ++var4) {
            var1.addSpot(new RegularSpot(var4, 1));
        }

        // 在 1 樓新增 16-18 的 ElectricSpot（電動車充電位）
        for(int var5 = 16; var5 <= 18; ++var5) {
            var1.addSpot(new ElectricSpot(var5, 1));
        }

        // 建立 2 樓物件
        ParkingFloor var6 = new ParkingFloor(2);

        // 在 2 樓新增 201-220 的 RegularSpot（較多一般車位）
        for(int var3 = 1; var3 <= 20; ++var3) {
            var6.addSpot(new RegularSpot(200 + var3, 2));
        }

        // 將樓層加入到停車場中
        var0.addFloor(var1);
        var0.addFloor(var6);
        // 回傳建置完成的停車場
        return var0;
    }

    // 處理車輛進場的流程
    private static void handleEntryProcess(ParkingLot var0) {
        // 顯示進場標題
        System.out.println("\n[車輛進場]");
        // 提示輸入車牌
        System.out.print("請輸入車牌號碼: ");
        // 讀取並去除首尾空白
        String var1 = scanner.nextLine().trim();
        // 若該車牌已存在於 activeTickets，顯示錯誤
        if (activeTickets.containsKey(var1)) {
            System.out.println("錯誤：該車輛已經在停車場內！");
        } else {
            // 顯示車種選擇選單
            System.out.println("選擇車種: 1.轎車 (vehicles.Sedan)  2.貨車 (vehicles.Truck)  3.機車 (vehicles.Motorcycle)  4.電動車 (EV)");
            System.out.print("輸入選項 (1-4): ");
            // 讀取車種選擇
            String var2 = scanner.nextLine();
            Object var3 = null;

            try {
                // 根據使用者選擇建立對應的 Vehicle 物件
                switch (var2) {
                    case "1":
                        var3 = new Sedan(var1);
                        break;
                    case "2":
                        var3 = new Truck(var1);
                        break;
                    case "3":
                        var3 = new Motorcycle(var1);
                        break;
                    case "4":
                        // 若為電動車，詢問是否需要充電
                        System.out.print("是否需要充電? (y/n): ");
                        boolean var6 = scanner.nextLine().trim().equalsIgnoreCase("y");
                        var3 = new ElectricVehicle(var1, var6);
                        break;
                    default:
                        // 若選項不在範圍內，提示無效車種並返回
                        System.out.println("無效的車種。");
                        return;
                }

                // 詢問是否為會員，若是則設定 vehicle 的 member 屬性
                System.out.print("是否為會員? (y/n): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    ((Vehicle)var3).setMember(true);
                }

                // 呼叫停車場系統的進場處理，取得 ParkingTicket
                ParkingTicket var4 = var0.handleEntry((Vehicle)var3);
                // 將票券放入 activeTickets，Key 為車牌，Value 為票券
                activeTickets.put(var1, var4);
                // 顯示進場成功訊息
                System.out.println(">>> 進場成功!");
                System.out.println("票券 ID: " + var4.getTicketId());
                System.out.print("分配車位: ");

                // 列出分配到的每個車位（樓層與編號）
                for(ParkingSpot var11 : var4.getAllocatedSpots()) {
                    PrintStream var10000 = System.out;
                    int var10001 = var11.getFloor();
                    var10000.print(var10001 + "樓-" + var11.getId() + "號 ");
                }

                // 換行結束列印
                System.out.println();
            } catch (IllegalArgumentException var7) {
                // 捕捉到不合法的參數例外，顯示失敗原因
                System.out.println("進場失敗: " + var7.getMessage());
            } catch (RuntimeException var8) {
                // 捕捉到執行期例外（例如無車位），顯示失敗原因
                System.out.println("進場失敗 (無車位或其他原因): " + var8.getMessage());
            }

        }
    }

    // 處理車輛離場的流程
    private static void handleExitProcess(ParkingLot var0) {
        // 顯示離場標題
        System.out.println("\n[車輛離場]");
        // 提示使用者輸入車牌
        System.out.print("請輸入車牌號碼: ");
        String var1 = scanner.nextLine().trim();
        // 若找不到該車牌的進場紀錄，顯示錯誤
        if (!activeTickets.containsKey(var1)) {
            System.out.println("錯誤：找不到此車牌的進場紀錄。");
        } else {
            // 取得對應的票券
            ParkingTicket var2 = (ParkingTicket)activeTickets.get(var1);
            // 模擬停車時數（以小時計）輸入
            System.out.print("模擬停車時數 (小時): ");
            String var3 = scanner.nextLine();
            double var4 = (double)0.0F;

            try {
                // 解析輸入的停車小時數
                var4 = Double.parseDouble(var3);
            } catch (NumberFormatException var16) {
                // 若解析失敗則提示錯誤並中止離場流程
                System.out.println("無效的數字格式。");
                return;
            }

            // 將小時轉為分鐘（long），作為模擬的停車分鐘數
            long var6 = (long)(var4 * (double)60.0F);
            // 設定票券的離場時間為現在加上模擬分鐘（代表已停到該時間）
            LocalDateTime var8 = LocalDateTime.now().plusMinutes(var6);
            var2.setExitTime(var8);
            // 充電相關的預設變數
            boolean var9 = false;
            Duration var10 = Duration.ZERO;
            // 若該票券的車輛需要充電，詢問是否有充電並讀取充電分鐘數
            if (var2.getVehicle().needsCharging()) {
                System.out.print("是否有進行充電? (y/n): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    var9 = true;
                    System.out.print("模擬充電時長 (分鐘): ");
                    String var11 = scanner.nextLine();

                    try {
                        long var12 = Long.parseLong(var11);
                        var10 = Duration.ofMinutes(var12);
                    } catch (NumberFormatException var15) {
                        // 若解析失敗，將充電時長設為 0 並提示
                        System.out.println("無效的數字格式，充電時長設為 0 分鐘。\n");
                        var10 = Duration.ZERO;
                    }
                }
            }

            try {
                // 執行離場結算，傳入票券、是否充電、以及充電時長
                double var18 = var0.handleExit(var2, var9, var10);
                 // 結算完成後從 activeTickets 移除該筆紀錄
                 activeTickets.remove(var1);
                 // 顯示離場成功與結算資訊
                 System.out.println(">>> 離場成功!");
                 System.out.println("車牌號碼: " + var2.getVehicle().getLicensePlate());
                 System.out.println("停車時長: " + var6 + " 分鐘");
                 System.out.println("應付費用: " + var18 + " 元");
             } catch (Exception var14) {
                 // 若離場或結算過程發生錯誤，顯示錯誤訊息
                 System.out.println("離場失敗: " + var14.getMessage());
             }

         }
     }

    // 顯示目前場內車輛清單
    private static void showStatus() {
        // 標題
        System.out.println("\n[查詢場內車輛]");
        // 若 activeTickets 為空，顯示無車輛訊息
        if (activeTickets.isEmpty()) {
            System.out.println("目前沒有車輛在停車場內。");
        } else {
            // 否則列出每一張票券的車牌與進場時間
            System.out.println("目前場內車輛:");

            for(ParkingTicket var1 : activeTickets.values()) {
                PrintStream var10000 = System.out;
                String var10001 = var1.getVehicle().getLicensePlate();
                var10000.println("車牌號碼: " + var10001 + "，進場時間: " + String.valueOf(var1.getEntryTime()));
            }
        }

    }
}


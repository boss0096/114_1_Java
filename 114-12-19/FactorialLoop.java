/**
 * 📌 階乘計算 - 迴圈版本
 * 用來比較遞迴和迴圈的差異
 */
public class FactorialLoop {
    
    /**
     * 使用 for 迴圈計算階乘
     * @param n 要計算階乘的數字
     * @return n 的階乘值
     */
    public static long factorialLoop(int n) {
        // 用一個變數來累積結果
        long result = 1;
        
        // 從 1 乘到 n
        for (int i = 1; i <= n; i++) {
            result = result * i;  // 或寫成 result *= i;
        }
        
        return result;
    }
    
    /**
     * 使用遞迴計算階乘（與迴圈版本比較）
     */
    public static long factorialRecursive(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }
    
    public static void main(String[] args) {
        System.out.println("===== 迴圈 vs 遞迴 比較 =====");
        
        int testNumber = 10;
        
        System.out.println("迴圈版本：" + testNumber + "! = " + factorialLoop(testNumber));
        System.out.println("遞迴版本：" + testNumber + "! = " + factorialRecursive(testNumber));
        
        // 兩個結果相同：3628800
    }
}

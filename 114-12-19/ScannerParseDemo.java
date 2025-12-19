import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScannerParseDemo {
    public static void main(String[] args) {
        createTestFile();
        
        try (Scanner scanner = new Scanner(new File("scores.txt"))) {
            System.out.println("=== 成績資料 ===");
            
            while (scanner.hasNext()) {
                String name = scanner.next();      // 讀取字串
                int score = scanner.nextInt();     // 讀取整數
                double gpa = scanner.nextDouble(); // 讀取浮點數
                
                System.out.printf("%s: %d 分, GPA: %.2f%n", name, score, gpa);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private static void createTestFile() {
        try (PrintWriter pw = new PrintWriter("scores.txt")) {
            pw.println("王小明 85 3.42");
            pw.println("李雅婷 92 3.88");
            pw.println("陳建宏 76 2.95");
            pw.println("林欣怡 88 3.56");
            pw.println("張志豪 64 2.31");
            pw.println("黃品妍 90 3.75");
            pw.println("吳承翰 71 2.68");
            pw.println("蔡依珊 95 3.92");
            pw.println("劉冠宇 83 3.25");
            pw.println("鄭詩涵 78 3.04");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

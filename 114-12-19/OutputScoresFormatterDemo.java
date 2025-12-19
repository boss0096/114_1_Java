import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class OutputScoresFormatterDemo {
    public static void main(String[] args) {
        File inputFile = new File("scores.txt");

        List<Student> students = new ArrayList<>();

        // 讀取資料放入 List<Student>
        try (Scanner sc = new Scanner(inputFile)) {
            while (sc.hasNext()) {
                String name = sc.next();
                if (!sc.hasNextInt()) break;
                int score = sc.nextInt();
                if (!sc.hasNextDouble()) break;
                double gpa = sc.nextDouble();

                students.add(new Student(name, score, gpa));
            }
        } catch (FileNotFoundException e) {
            System.out.println("讀取檔案失敗: " + e.getMessage());
            return;
        }

        // 使用 Formatter 寫入 outputScores.txt（含標題）
        try (Formatter fmt = new Formatter("outputScores.txt")) {
            fmt.format("=== 學生成績單 (從 scores.txt 讀取) ===%n%n");
            fmt.format("%-10s %5s %8s%n", "姓名", "分數", "GPA");

            for (Student s : students) {
                fmt.format("%-10s %5d %8.2f%n", s.getName(), s.getScore(), s.getGpa());
            }

            System.out.println("outputScores.txt 已寫入 (共 " + students.size() + " 筆)");
        } catch (FileNotFoundException e) {
            System.out.println("無法建立輸出檔案: " + e.getMessage());
        }
    }
}

// 簡單的 Student 類別
class Student {
    private final String name;
    private final int score;
    private final double gpa;

    Student(String name, int score, double gpa) {
        this.name = name;
        this.score = score;
        this.gpa = gpa;
    }

    String getName() { return name; }
    int getScore() { return score; }
    double getGpa() { return gpa; }
}

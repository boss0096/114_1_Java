public class Date {
    private int month; // 1-12 月
    private int day; // 1-31，依月份而定
    private int year; // 年份

    private static final int[] daysPerMonth =
            {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // 建構子：確認 month 與 day 在指定年分下的合法性
    public Date(int month, int day, int year) {
        // 檢查月份範圍
        if (month <= 0 || month > 12) {
            throw new IllegalArgumentException(
                    "月份 (" + month + ") 必須是 1-12");
        }

        // 檢查該月的天數範圍
        if (day <= 0 ||
                (day > daysPerMonth[month] && !(month == 2 && day == 29))) {
            throw new IllegalArgumentException("日 (" + day +
                    ") 超出指定月份與年份的範圍");
        }

        // 若為二月 29 日，檢查是否為閏年
        if (month == 2 && day == 29 && !(year % 400 == 0 ||
                (year % 4 == 0 && year % 100 != 0))) {
            throw new IllegalArgumentException("日 (" + day +
                    ") 超出指定月份與年份的範圍");
        }

        this.month = month;
        this.day = day;
        this.year = year;

        //System.out.printf("Date 物件建構，日期為 %s%n", this);
    }

    // 回傳格式為 月/日/年 的字串
    public String toString() {
        return String.format("%d/%d/%d", month, day, year);
    }
}
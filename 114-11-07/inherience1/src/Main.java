// src/Main.java
// 範例主程式：示範 SwordMan 與 Magician 的互動，移除未使用的警告
public class Main {
    public static void main(String[] args) {
        // 建立角色
        SwordMan swordsman1 = new SwordMan("艾倫(好賤)", 100, 15);
        SwordMan swordsman2 = new SwordMan("布萊恩(超賤)", 80, 100);
        Magician magician1 = new Magician("梅林(好法)", 70, 10, 20);
        Magician magician2 = new Magician("莫甘娜(超法)", 60, 12, 18);

        System.out.println("戰鬥開始：");

        // 劍士互相攻擊
        swordsman1.attack(swordsman2);
        swordsman2.attack(swordsman1);

        // 魔法師互相攻擊
        magician1.attack(magician2);
        magician2.attack(magician1);

        // 魔法師治療劍士
        magician1.heal(swordsman2);

        // 顯示角色狀態（並顯示魔法師的治療量，使用 getHealpower()）
        System.out.println(swordsman1.getName() + " 生命: " + swordsman1.getHealth() + "，存活: " + swordsman1.isAlive());
        System.out.println(swordsman2.getName() + " 生命: " + swordsman2.getHealth() + "，存活: " + swordsman2.isAlive());
        System.out.println(magician1.getName() + " 生命: " + magician1.getHealth() + "，存活: " + magician1.isAlive() + "，治療量: " + magician1.getHealpower());
        System.out.println(magician2.getName() + " 生命: " + magician2.getHealth() + "，存活: " + magician2.isAlive() + "，治療量: " + magician2.getHealpower());
    }
}

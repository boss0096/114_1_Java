public class Magician extends Role {
    // 治療量（建立後不變）
    private final int healpower;

    // 建構子：使用父類別 Role 的建構子初始化名稱、生命、攻擊力，並設定治療量
    public Magician(String name, int health, int attackPower, int healpower) {
        super(name, health, attackPower);
        this.healpower = healpower;
    }

    // 取得治療量
    public int getHealpower() {
        return healpower;
    }

    // 使用魔法攻擊對手：接受 Role 以支援不同類型的目標
    public void attack(Role opponent) {
        opponent.setHealth(opponent.getHealth() - this.getAttackPower());
        System.out.println(this.getName() + " 使用魔法攻擊 " + opponent.getName() + "，造成 "
                + this.getAttackPower() + " 點傷害。 (" + opponent.getName() + " 生命剩餘: " + opponent.getHealth() + ")");
    }

    // 治療友軍：接受 Role 以支援不同類型的目標
    public void heal(Role ally) {
        ally.setHealth(ally.getHealth() + this.healpower);
        System.out.println(this.getName() + " 治療 " + ally.getName() + "，恢復 " + this.healpower + " 點生命值。 (" + ally.getName() + " 生命: " + ally.getHealth() + ")");
    }

    // 以字串描述 Magician（包含父類資訊與治療量）
    @Override
    public String toString() {
        return super.toString() + ", 治療量: " + healpower;
    }
}

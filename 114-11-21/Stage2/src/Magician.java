public class Magician extends Role{
    // 治癒力
    private int healPower;

    // 建構子：初始化魔法師的名稱、生命值和攻擊力
    public Magician(String name, int health, int attackPower, int healPower) {
        super(name, health, attackPower);
        this.healPower = healPower;
    }

    // 取得治癒力
    public int getHealPower() {
        return healPower;
    }

    // 攻擊對手(劍客/魔法師)，使用 Role.takeDamage() 統一受傷流程
    @Override
    public void attack(Role opponent) {
        System.out.println("✨ " + this.getName() + " 施放魔法攻擊 " + opponent.getName() + "！");
        opponent.takeDamage(this.getAttackPower());
    }

    // 治療隊友(劍客/魔法師)
    public void heal(Role ally) {
        ally.setHealth(ally.getHealth() + this.healPower);
        System.out.println(this.getName() + " 治療 " + ally.getName() + " 回復 " + healPower + " 點生命值。" + ally);
    }

    @Override
    public String toString() {
        return super.toString() + ", 治癒力: " + healPower;
    }

    @Override
    public void showSpecialSkill() {
        // 顯示魔法師特殊技能方框（使用角色名稱作為標題）
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ " + this.getName() + " 的特殊技能        ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：元素爆發          ║");
        System.out.println("║ 技能描述：召喚強大魔法攻擊  ║");
        System.out.println("║ 技能效果：範圍魔法傷害      ║");
        System.out.println("║ 額外效果：恢復自身魔力      ║");
        System.out.println("╚═════════════════════════════╝");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 的生命之火熄滅了...");
        System.out.println("✨ " + this.getName() + " 的身體化為無數魔法粒子，消散在空氣中。");
        System.out.println("🌟 魔法書掉落在地上，微微發光。");
        System.out.println("---");
    }

    @Override
    public void prepareBattle() {
        System.out.println(this.getName() + " 翻開魔法書，感應元素之力，開始凝聚魔力。\n");
    }

    @Override
    public void afterBattle() {
        System.out.println(this.getName() + " 閉目冥想，回溯咒文並恢復魔力。\n");
    }
}

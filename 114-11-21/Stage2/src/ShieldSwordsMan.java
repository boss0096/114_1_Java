public class ShieldSwordsMan extends SwordsMan{
    private int defenseCapacity;
    // 建構子：初始化持盾劍士的名稱、生命值和攻擊力
    public ShieldSwordsMan(String name, int health, int attackPower, int defenseCapacity) {
        super(name, health, attackPower);
        this.defenseCapacity = defenseCapacity;
    }

    // 攻擊對手，使用 Role.takeDamage() 統一受傷流程
    @Override
    public void attack(Role opponent) {
        int reducedDamage = this.getAttackPower() - 5; // 持盾劍士攻擊力減少5點
        System.out.println("🛡️ " + this.getName() + " 使用盾牌助力揮擊 " + opponent.getName() + "！");
        opponent.takeDamage(reducedDamage);
    }

    public int getDefenseCapacity() {
        return defenseCapacity;
    }

    public void defence() {
        this.setHealth(this.getHealth() + defenseCapacity);
        System.out.println(this.getName() + " 使用盾牌防禦，恢復 " + defenseCapacity + " 點生命值。" + this);
    }

    @Override
    public void showSpecialSkill() {
        // 顯示持盾劍士特殊技能方框（使用角色名稱作為標題）
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ " + this.getName() + " 的特殊技能      ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：盾牌猛擊          ║");
        System.out.println("║ 技能描述：使用盾牌撞擊敵人  ║");
        System.out.println("║ 技能效果：造成傷害並暈眩    ║");
        System.out.println("║ 防禦加成：+8 防禦力         ║");
        System.out.println("╚═════════════════════════════╝");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 力竭倒下...");
        System.out.println("🛡️  厚重的盾牌砸在地上，揚起一陣塵土。");
        System.out.println("⚔️  " + this.getName() + " 的劍也隨之掉落。");
        System.out.println("---");
    }

    @Override
    public void prepareBattle() {
        System.out.println(this.getName() + " 固守陣型，舉起盾牌保護隊友，準備應戰。\n");
    }

    @Override
    public void afterBattle() {
        System.out.println(this.getName() + " 修補盾牌，檢查裝備並堅定信念。\n");
    }
}

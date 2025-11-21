public  class SwordsMan extends Role{
    // 建構子：初始化劍士的名稱、生命值和攻擊力
    public SwordsMan(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    // 攻擊對手(劍客/魔法師)，使用 Role.takeDamage() 統一受傷流程
    @Override
    public void attack(Role opponent) {
        System.out.println("🗡️  " + this.getName() + " 揮劍攻擊 " + opponent.getName() + "！");
        opponent.takeDamage(this.getAttackPower());
    }

    @Override
    public void showSpecialSkill() {
        // 顯示劍士特殊技能方框（使用角色名稱作為標題）
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│ " + this.getName() + " 的特殊技能        │");
        System.out.println("├─────────────────────────────┤");
        System.out.println("│ 技能名稱：連續斬擊             │");
        System.out.println("│ 技能描述：快速揮劍三次          │");
        System.out.println("│ 技能效果：造成 150% 傷害       │");
        System.out.println("└─────────────────────────────┘");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 倒下了...");
        System.out.println("⚔️  " + this.getName() + " 的劍掉落在地上，發出清脆的聲響。");
        System.out.println("---");
    }

    @Override
    public void prepareBattle() {
        System.out.println(this.getName() + " 擦拭劍刃，調整站位，準備迎戰。\n");
    }

    @Override
    public void afterBattle() {
        System.out.println(this.getName() + " 放下武器，整理傷口並檢視戰利品。\n");
    }
}

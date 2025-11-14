public class ShieldSowrdsMan extends SwordsMan{

    private int defensecapacity;

        // 建構子：初始化劍士的名稱、生命值和攻擊力

        public ShieldSowrdsMan(String name, int health, int attackPower,int defensecapacity) {
            super(name, health, attackPower);
            this.defensecapacity = defensecapacity;
        }
        @Override
        public void attack(Role opponent) {
            int reducedDamage = this.getAttackPower() - 5; // 減少5點傷害
            opponent.setHealth(opponent.getHealth() - reducedDamage);
            System.out.println(this.getName() + " 使用盾牌攻擊 " + opponent.getName() + " 造成 " +
                    reducedDamage + " 點傷害。" + opponent);
        }

        public int getDefensecapacity()
        {
            return defensecapacity;
    }

public void defence()
{
    this.setHealth(this.getHealth() + defensecapacity);
    System.out.println(this.getName() + " 使用盾牌防禦，回復 " + defensecapacity + " 點生命值。" + this);

}
}

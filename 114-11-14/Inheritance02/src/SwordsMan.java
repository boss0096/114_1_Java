public class SwordsMan extends Role{
    // 建構子：初始化劍士的名稱、生命值和攻擊力

    //super繼承Role的建構子
    public SwordsMan(String name, int health, int attackPower)
    {
        super(name, health, attackPower);
    }

    // 攻擊對手，參考父類別
    public void attack(Role opponent) {
        opponent.setHealth(opponent.getHealth() - this.getAttackPower());
        System.out.println(this.getName() + " 攻擊 " + opponent.getName() + " 造成 " +
                this.getAttackPower() + " 點傷害。" + opponent);
    }
}

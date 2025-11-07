public class SwordMan extends Role
{

    // 建構子：初始化名稱、生命與攻擊力
    public SwordMan(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }


    // 攻擊對手：接受 Role 以支援不同類型的目標，對目標造成傷害並輸出中文訊息
    public void attack(Role opponent) {
        opponent.setHealth(opponent.getHealth() - this.getAttackPower());
        System.out.println(this.getName() + " 攻擊 " + opponent.getName() + "，造成 "
                + this.getAttackPower() + " 點傷害。 (" + opponent.getName() + " 生命剩餘: " + opponent.getHealth() + ")");
    }

}

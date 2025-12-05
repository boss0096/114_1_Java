// filepath: c:\Users\m306\Desktop\114_1_Java\114-12-05\Stage5\src\Priest.java
public class Priest extends RangedRole implements Healable {
    private int healPower;

    public Priest(String name, int health, int attackPower,
                 int healPower, int range, int maxEnergy) {
        super(name, health, attackPower, range, maxEnergy);
        this.healPower = healPower;
    }

    @Override
    public int getHealPower() {
        return healPower;
    }


    public boolean canHeal() {
        return getEnergy() >= 15;
    }

    @Override
    public void heal(Role target) {
        int cost = 15; // 治療消耗能量
        if (!consumeEnergy(cost)) {
            System.out.println("⚠️ " + this.getName() + " 能量不足，無法施放治癒！");
            return;
        }
        int oldHealth = target.getHealth();
        int healed = Math.min(this.healPower, target.getMaxHealth() - oldHealth);
        if (healed <= 0) {
            System.out.println("ℹ️ " + this.getName() + " 嘗試治療 " + target.getName() + "，但對方已經是滿血。");
            return;
        }
        target.setHealth(oldHealth + healed);
        System.out.println("💚 " + this.getName() + " 治療 " + target.getName() + " 回復 " + healed + " 點生命值。(消耗 " + cost + " 能量) (" + oldHealth + " → " + target.getHealth() + ")");
    }

    @Override
    public void attack(Role opponent) {
        int cost = 10;
        if (!consumeEnergy(cost)) {
            System.out.println("⚠️ " + this.getName() + " 能量不足，無法施放攻擊！");
            return;
        }
        System.out.println("✨ " + this.getName() + " 施放神聖射擊攻擊 " + opponent.getName() + "！(消耗 " + cost + " 能量)");
        opponent.takeDamage(this.getAttackPower());
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ " + this.getName() + " 的特殊技能        ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：聖光療癒          ║");
        System.out.println("║ 技能描述：以聖光療癒友軍      ║");
        System.out.println("║ 技能效果：回復單體生命並恢復自身能量║");
        System.out.println("╚═════════════════════════════╝");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 被黑暗吞噬，最後的禱言化為光點...");
    }

    @Override
    public void prepareBattle() {
        System.out.println("🙏 " + this.getName() + " 默念經文，準備施放祝福...");
    }

    @Override
    public void afterBattle() {
        // 戰後回復少量能量
        recoverEnergy(15);
        System.out.println("🛐 " + this.getName() + " 平靜心神，恢復部分能量。(+15 能量)");
    }

    @Override
    public String getRangedAttackType() {
        return "神聖射擊";
    }

    @Override
    public String toString() {
        return super.toString() + ", 治癒力: " + healPower + ", 射程: " + getRange() + ", 能量: " + getEnergy() + "/" + getMaxEnergy();
    }
}


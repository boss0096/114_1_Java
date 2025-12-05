/**
 * Paladin（聖騎士）
 * 近戰角色，同時具備防禦和治療能力
 * 實現 Defendable 和 Healable 介面，展示多重介面實作
 */

package com.RPG.roles.melee;
import com.RPG.core.Role;
import com.RPG.interfaces.Defendable;
import com.RPG.interfaces.Healable;

public class Paladin extends MeleeRole implements Defendable, Healable {
    private int holyPower;      // 聖能（防禦和治療消耗）
    private int maxHolyPower;
    private int healPower;      // 治療力
    private int defenseCapacity;  // 防禦力

    // 建構子：name, health, attackPower, armor, healPower, defenseCapacity, maxHolyPower
    public Paladin(String name, int health, int attackPower, int armor,
                   int healPower, int defenseCapacity, int maxHolyPower) {
        super(name, health, attackPower, armor);
        this.healPower = healPower;
        this.defenseCapacity = defenseCapacity;
        this.maxHolyPower = maxHolyPower;
        this.holyPower = maxHolyPower;
    }

    // 取得聖能
    public int getHolyPower() {
        return holyPower;
    }

    // 取得最大聖能
    public int getMaxHolyPower() {
        return maxHolyPower;
    }

    // 消耗聖能
    private boolean consumeHolyPower(int amount) {
        if (holyPower >= amount) {
            holyPower -= amount;
            return true;
        }
        return false;
    }

    // 恢復聖能
    private void recoverHolyPower(int amount) {
        holyPower = Math.min(maxHolyPower, holyPower + amount);
    }

    // ⭐ 實現 Defendable 介面
    @Override
    public int getDefenseCapacity() {
        return defenseCapacity;
    }

    @Override
    public boolean canDefend() {
        return holyPower >= 15;
    }

    @Override
    public void defend() {
        if (!canDefend()) {
            System.out.println("⚠️ " + this.getName() + " 聖能不足，無法使用聖盾！");
            return;
        }

        int cost = 15;
        if (!consumeHolyPower(cost)) {
            System.out.println("⚠️ " + this.getName() + " 聖能不足，無法使用聖盾！");
            return;
        }

        int oldHealth = this.getHealth();
        this.setHealth(this.getHealth() + defenseCapacity);
        System.out.println("⚡️ " + this.getName() + " 激活聖盾！恢復 " + defenseCapacity +
                         " 點生命值。(消耗 " + cost + " 聖能)(" + oldHealth + " → " + this.getHealth() + ")");
    }

    // ⭐ 實現 Healable 介面
    @Override
    public int getHealPower() {
        return healPower;
    }

    @Override
    public boolean canHeal() {
        return holyPower >= 20;
    }

    @Override
    public void heal(Role ally) {
        if (!canHeal()) {
            System.out.println("⚠️ " + this.getName() + " 聖能不足，無法施放治癒！");
            return;
        }

        int cost = 20;
        if (!consumeHolyPower(cost)) {
            System.out.println("⚠️ " + this.getName() + " 聖能不足，無法施放治癒！");
            return;
        }

        int oldHealth = ally.getHealth();
        ally.setHealth(ally.getHealth() + this.healPower);
        System.out.println("💛 " + this.getName() + " 施放聖光治療 " + ally.getName() +
                         " 回復 " + healPower + " 點生命值。(消耗 " + cost + " 聖能)" +
                         "(" + oldHealth + " → " + ally.getHealth() + ")");
    }

    // 攻擊對手
    @Override
    public void attack(Role opponent) {
        System.out.println("⚔️ " + this.getName() + " 揮動聖劍攻擊 " + opponent.getName() + "！");
        opponent.takeDamage(this.getAttackPower());
    }

    // 展示特殊技能
    @Override
    public void showSpecialSkill() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ " + this.getName() + " 的特殊技能        ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：聖光制裁          ║");
        System.out.println("║ 技能描述：融合攻擊、防禦、治療 ║");
        System.out.println("║ 技能效果：全能型戰士        ║");
        System.out.println("║ 聖能消耗：防禦 15 | 治療 20 ║");
        System.out.println("╚═════════════════════════════╝");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 聖光消散...");
        System.out.println("⚔️ " + this.getName() + " 的聖劍插在地上，光芒漸漸熄滅。");
        System.out.println("✨ 聖騎士的聖能完全消耗殆盡。");
        System.out.println("---");
    }

    @Override
    public void prepareBattle() {
        System.out.println("⚡️ " + this.getName() + " 誦唱聖言，聖光環繞全身...");
        System.out.println("✨ 聖能充滿了聖騎士的軀體，準備迎戰。");
    }

    @Override
    public void afterBattle() {
        // 戰後恢復聖能
        recoverHolyPower(30);
        System.out.println("🧘 " + this.getName() + " 冥想祈禱，恢復聖能。(+30 聖能)");
    }

    @Override
    protected void onMeleePrepare() {
        System.out.println("⚔️ " + this.getName() + " 檢查聖劍的鋒利度...");
    }

    @Override
    public String getWeaponType() {
        return "聖劍";
    }

    @Override
    public String toString() {
        return super.toString() + ", 護甲: " + getArmor() +
               ", 治療力: " + healPower + ", 防禦力: " + defenseCapacity +
               ", 聖能: " + holyPower + "/" + maxHolyPower;
    }
}


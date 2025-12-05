package com.RPG.roles.ranged;

import com.RPG.core.Role;

public class Archer extends RangedRole {
    private int arrowCount;

    // 建構子：name, health, attackPower, arrowCount, range, maxEnergy
    public Archer(String name, int health, int attackPower, int arrowCount, int range, int maxEnergy) {
        super(name, health, attackPower, range, maxEnergy);
        this.arrowCount = arrowCount;
    }

    // 攻擊
    @Override
    public void attack(Role opponent) {
        int energyCost = 10;
        if (arrowCount <= 0) {
            System.out.println("⚠️ " + this.getName() + " 沒有箭矢了，無法攻擊！");
            return;
        }
        if (!consumeEnergy(energyCost)) {
            System.out.println("⚠️ " + this.getName() + " 能量不足，無法射箭！");
            return;
        }
        arrowCount--;
        System.out.println("🏹 " + this.getName() + " 射出一支箭攻擊 " + opponent.getName() + "！(消耗 " + energyCost + " 能量，剩餘箭矢 " + arrowCount + ")");
        opponent.takeDamage(this.getAttackPower());
    }

    public int getArrowCount() {
        return arrowCount;
    }

    // 展示特殊技能
    @Override
    public void showSpecialSkill() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ " + this.getName() + " 的特殊技能        ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：連珠箭            ║");
        System.out.println("║ 技能描述：短時間內發射多支箭  ║");
        System.out.println("║ 技能效果：快速射擊，消耗箭矢  ║");
        System.out.println("╚═════════════════════════════╝");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 被擊倒，箭袋散落一地。");
    }

    @Override
    public void prepareBattle() {
        System.out.println("🏹 " + this.getName() + " 檢查弓與箭矢，調整弦線。");
    }

    @Override
    public void afterBattle() {
        // 戰後回復少量能量
        recoverEnergy(10);
        System.out.println("🏕️ " + this.getName() + " 進行簡單整理並補充箭矢。(+10 能量)");
    }

    @Override
    public String getRangedAttackType() {
        return "弓箭攻擊";
    }

    @Override
    public String toString() {
        return super.toString() + ", 箭矢: " + arrowCount + ", 射程: " + getRange() + ", 能量: " + getEnergy() + "/" + getMaxEnergy();
    }
}


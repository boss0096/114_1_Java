package com.RPG.roles.melee;
import com.RPG.core.Role;


public abstract class MeleeRole extends Role {
    private int armor;  // 護甲值：近戰角色特有

    // 新增建構子
    public MeleeRole(String name, int health, int attackPower, int armor) {
        super(name, health, attackPower);
        this.armor = armor;
    }

    // 具體方法：計算防禦
    public int calculateDefense(int incomingDamage) {
        return Math.max(0, incomingDamage - armor);
    }

    // 讓近戰角色在受到傷害時，先計算護甲減免
    @Override
    public void takeDamage(int damage) {
        int actual = calculateDefense(damage);
        super.takeDamage(actual);
    }

    // 取得護甲值
    public int getArmor() {
        return armor;
    }

    // 抽象方法：取得武器類型
    public abstract String getWeaponType();

    // 抽象方法：近戰特殊準備
    protected abstract void onMeleePrepare();
}

public abstract class RangedRole extends Role {
    private int range;      // 攻擊範圍
    private int energy;     // 能量值
    private int maxEnergy;

    // 新增建構子
    public RangedRole(String name, int health, int attackPower, int range, int maxEnergy) {
        super(name, health, attackPower);
        this.range = range;
        this.maxEnergy = maxEnergy;
        this.energy = maxEnergy; // 初始能量為最大值
    }

    // 具體方法：檢查射程
    public boolean isInRange(int distance) {
        return distance <= range;
    }

    // 具體方法：能量管理
    public boolean consumeEnergy(int amount) {
        if (energy >= amount) {
            energy -= amount;
            return true;
        }
        return false;
    }

    // 恢復能量（戰後或其他場合）
    public void recoverEnergy(int amount) {
        energy = Math.min(maxEnergy, energy + amount);
    }

    // 取得目前能量
    public int getEnergy() {
        return energy;
    }

    // 取得最大能量
    public int getMaxEnergy() {
        return maxEnergy;
    }

    // 取得射程
    public int getRange() {
        return range;
    }

    // 抽象方法：取得攻擊類型
    public abstract String getRangedAttackType();
}
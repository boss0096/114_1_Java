public class Role {
    private String name;

    private int health;

    private int attackPower;

    public Role(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public String toString() {
        return "角色名稱: " + name + ", 生命值: " + health + ", 攻擊力: " + attackPower;
    }
}


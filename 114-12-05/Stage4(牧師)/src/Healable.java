public interface Healable {
    void heal(Role target);
    int getHealPower();
    boolean canHeal();

    // 新增：群體治療（預設方法）
    default void healGroup(Role[] targets) {
        for (Role target : targets) {
            if (target != null && target.isAlive()) {
                heal(target);
            }
        }
    }
}

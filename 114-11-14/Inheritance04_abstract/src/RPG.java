public class RPG {
    public static void main(String[] args) {
        // 建立劍士和魔法師角色
        SwordsMan swordsMan_light = new SwordsMan("光明劍士", 100, 20);
        SwordsMan swordsMan_dark = new SwordsMan("黑暗劍士", 100, 25);

        Magician magician_light = new Magician("光明法師", 80, 15, 10);
        Magician magician_dark = new Magician("黑暗法師", 80, 20, 5);

        ShieldSowrdsMan shieldSowrdsMan = new ShieldSowrdsMan("盾牌劍士", 120, 18, 8);

        Role[] gameRoles = {swordsMan_light, swordsMan_dark, magician_light, magician_dark, shieldSowrdsMan};

        // 戰鬥過程
        System.out.println("戰鬥開始！");
        for (Role currentRole : gameRoles) {
            if (!currentRole.isAlive()) {
                continue;
            }
            if (currentRole instanceof SwordsMan) {
                if (currentRole instanceof ShieldSowrdsMan) {
                    Role target = gameRoles[(int) (Math.random() * gameRoles.length)];
                    if (target instanceof ShieldSowrdsMan)
                        ((ShieldSowrdsMan) target).defence();
                    currentRole.attack(target);
                } else if (currentRole instanceof Magician) {
                    Magician magician = (Magician) currentRole;

                    if (Math.random() < 0.5) {
                        // 攻擊隨機對手
                        magician.attack(gameRoles[(int) (Math.random() * gameRoles.length)]);
                        if (currentRole instanceof ShieldSowrdsMan) {
                            Role target = gameRoles[(int) (Math.random() * gameRoles.length)];
                            if (target instanceof ShieldSowrdsMan)
                                ((ShieldSowrdsMan) target).defence();
                            currentRole.attack(target);

                        } else {
                            // 治療自己
                            magician.heal(gameRoles[(int) (Math.random() * gameRoles.length)]);
                        }
                    }
                }
            }
        }
    }
}
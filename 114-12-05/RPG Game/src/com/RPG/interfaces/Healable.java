


/**
 * Healable 介面：治療能力
 * 實作此介面的角色可以進行治療動作
 */


package com.RPG.interfaces;
import com.RPG.core.Role;

public interface Healable {
    /**
     * 取得治療力
     */
    int getHealPower();

    /**
     * 檢查是否可以治療
     */
    boolean canHeal();

    /**
     * 執行治療動作
     */
    void heal(Role target);

    /**
     * 預設方法：顯示治療資訊
     */
    default void showHealInfo() {
        System.out.println("    治療類型：魔法治癒");
    }
}


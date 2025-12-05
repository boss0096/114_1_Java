/**
 * Defendable 介面：防禦能力
 * 實作此介面的角色可以進行防禦動作
 */
public interface Defendable {
    /**
     * 取得防禦力
     */
    int getDefenseCapacity();

    /**
     * 檢查是否可以防禦
     */
    boolean canDefend();

    /**
     * 執行防禦動作
     */
    void defend();
}


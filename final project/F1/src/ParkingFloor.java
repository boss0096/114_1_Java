
import java.util.ArrayList;
import java.util.List;

/**
 * 類別：樓層 (ParkingFloor)
 * 負責單一樓層的車位管理與搜尋
 */
public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, int capacity) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();
        // 模擬初始化配置：
        // Index 0: 小型車位
        // Index 1: 充電車位
        // Index 2~End: 標準車位
        for (int i = 0; i < capacity; i++) {
            if (i == 0) spots.add(new SmallSpot(i, floorNumber));
            else if (i == 1) spots.add(new ElectricSpot(i, floorNumber));
            else spots.add(new RegularSpot(i, floorNumber));
        }
    }

    /**
     * 尋找單一可用車位
     */
    public ParkingSpot findSingleSpot(Vehicle v) {
        for (ParkingSpot s : spots) {
            // 檢查是否空閒且相容
            if (!s.isOccupied() && s.canAccommodate(v)) {
                return s;
            }
        }
        return null;
    }

    /**
     * 尋找連續 N 個可用車位 (給貨車使用)
     */
    public List<ParkingSpot> findConsecutiveSpots(Vehicle v, int count) {
        List<ParkingSpot> result = new ArrayList<>();

        // sliding window 搜尋
        for (int i = 0; i <= spots.size() - count; i++) {
            List<ParkingSpot> temp = new ArrayList<>();
            boolean match = true;

            for (int j = 0; j < count; j++) {
                ParkingSpot s = spots.get(i + j);
                // 任一車位被佔用或不相容，則此區段無效
                if (s.isOccupied() || !s.canAccommodate(v)) {
                    match = false;
                    break;
                }
                temp.add(s);
            }

            if (match) {
                return temp;
            }
        }
        return result;
    }
}


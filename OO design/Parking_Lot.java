/*

Parking Lot

Design a parking lot.

see CC150 OO Design for details.
1) n levels, each level has m rows of spots and each row has k spots.So each level has m x k spots.
2) The parking lot can park motorcycles, cars and buses
3) The parking lot has motorcycle spots, compact spots, and large spots
4) Each row, motorcycle spots id is in range [0,k/4)(0 is included, k/4 is not included), compact spots id is in range [k/4,k/4*3) and large spots id is in range [k/4*3,k).
5) A motorcycle can park in any spot
6) A car park in single compact spot or large spot
7) A bus can park in five large spots that are consecutive and within same row. it can not park in small spots


解：
这道题考察OO design。
逻辑不是很复杂，重点在实现方法上。

基本思路是设计好每一层所有的停车位，来了车就按照顺序往里面停。
不需要考虑最优化停车的问题，所以思路简单了很多，按照顺序一个一个找空余车位即可。
用一个HashMap来保存车和车位的对应关系，以方便删除。

车：
属性：
1.大小。
行为：
1.检查是否能停进车位。
2.停进车位。
3.离开车位。

车位：
属性：
1.大小。
2.停在里面的车。可能为null。
行为：
1.没啥行为。

每一层车库：
属性：
1.所有车位组成的数组。
2.登记簿（HashMap）。
行为：
1.停一个车进去。
2.注册一辆车。
3.取一辆车出来。
4.移除一辆车的记录。

车库：
属性：
1.所有层。
行为：
1.停一辆车进去。
2.取一辆车出来。


*/

// enum type for Vehicle
enum VehicleSize {
    Motorcycle,
    Compact,
    Large,
    Bus
}

abstract class Vehicle {
    public abstract VehicleSize getSize();
    protected abstract boolean fitSpot(Spot[] row, int idx);
    protected abstract void parkToSpot(Spot[] row, int idx);
    
    public boolean park(Spot[] row, int idx) {
        if (!fitSpot(row, idx)) {
            return false;
        }
        
        parkToSpot(row, idx);
        return true;
    }
    
    public void unpark(List<Spot> spots) {
        for (Spot spot : spots) {
            spot.setVehicle(null);
        }
    }
}

class Motorcycle extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Motorcycle;
    }
    
    @Override
    protected boolean fitSpot(Spot[] row, int idx) {
        return true;
    }
    
    @Override
    protected void parkToSpot(Spot[] row, int idx) {
        row[idx].setVehicle(this);
    }
}

class Car extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Compact;
    }
    
    @Override
    protected boolean fitSpot(Spot[] row, int idx) {
        if (row[idx].getSize() == VehicleSize.Motorcycle) {
            return false;
        }
        
        return true;
    }
    
    @Override
    protected void parkToSpot(Spot[] row, int idx) {
        row[idx].setVehicle(this);
    }
}

class Bus extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Bus;
    }
    
    @Override
    protected boolean fitSpot(Spot[] row, int idx) {
        int k;
        
        if (row[idx].getSize() != VehicleSize.Large) {
            return false;
        }
        
        for (k = idx; k < row.length && row[k].vehicle == null; k++);
        
        if (k - idx >= 5) {
            return true;
        }
        
        return false;
    }
    
    @Override
    protected void parkToSpot(Spot[] row, int idx) {
        for (int i = idx; i < idx + 5; i++) {
            row[i].setVehicle(this);
        }
    }
}

class Spot {
    VehicleSize size;
    Vehicle vehicle;
    
    public Spot(VehicleSize size) {
        this.size = size;
        vehicle = null;
    }
    
    public boolean isAvailable() {
        return vehicle == null;
    }
    
    public VehicleSize getSize() {
        return size;
    }
    
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

/* Represents a level in a parking garage */
class Level {
    Spot[][] spots;
    HashMap<Vehicle, List<Spot>> map;
    
    public Level(int m, int n) {
        map = new HashMap<Vehicle, List<Spot>>();
        spots = new Spot[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j < n / 4) {
                    spots[i][j] = new Spot(VehicleSize.Motorcycle);
                } else if (j >= n / 4 * 3) {
                    spots[i][j] = new Spot(VehicleSize.Large);
                } else {
                    spots[i][j] = new Spot(VehicleSize.Compact);
                }
            }
        }
    }
    
    public boolean parkVehicle(Vehicle vehicle) {
        for (int i = 0; i < spots.length; i++) {
            for (int j = 0; j < spots[i].length; j++) {
                Spot spot = spots[i][j];
                
                if (!spot.isAvailable()) {
                    continue;
                }
                
                if (vehicle.park(spots[i], j)) {
                    register(spots[i], j, vehicle);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private void register(Spot[] row, int idx, Vehicle vehicle) {
        if (!map.containsKey(vehicle)) {
            map.put(vehicle, new ArrayList<Spot>());
        }
        
        if (vehicle.getSize() == VehicleSize.Bus) {
            for (int i = idx; i < idx + 5; i++) {
                map.get(vehicle).add(row[i]);
            }
        } else {
            map.get(vehicle).add(row[idx]);
        }
    }
    
    public boolean unparkVehicle(Vehicle vehicle) {
        if (isParked(vehicle)) {
            vehicle.unpark(getVehicleSpotList(vehicle));
            unregister(vehicle);
            return true;
        }
        
        return false;
    }
    
    private void unregister(Vehicle vehicle) {
        map.remove(vehicle);
    }
    
    private boolean isParked(Vehicle vehicle) {
        return map.containsKey(vehicle);
    }
    
    private List<Spot> getVehicleSpotList(Vehicle vehicle) {
        return map.get(vehicle);
    }
}

public class ParkingLot {
    
    Level[] levels;
    
    // @param n number of leves
    // @param num_rows  each level has num_rows rows of spots
    // @param spots_per_row each row has spots_per_row spots
    public ParkingLot(int n, int num_rows, int spots_per_row) {
        levels = new Level[n];
        
        for (int i = 0; i < levels.length; i++) {
            levels[i] = new Level(num_rows, spots_per_row);
        }
    }

    // Park the vehicle in a spot (or multiple spots)
    // Return false if failed
    public boolean parkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.parkVehicle(vehicle)) {
                return true;
            }
        }
        
        return false;
    }

    // unPark the vehicle
    public void unParkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.unparkVehicle(vehicle)) {
                return;
            }
        }
    }
}

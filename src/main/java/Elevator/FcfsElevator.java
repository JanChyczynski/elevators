package Elevator;

import static java.lang.Math.abs;

public class FcfsElevator extends Elevator{
    public FcfsElevator(int startFloor) {
        super(startFloor);
    }

    @Override
    public int callCost(int floor, Direction direction) {
        int currentFloor = this.floor;
        if(destinations.isEmpty()){
            return abs(floor - currentFloor);
        }
        else {
            int cost = 0;
            int prevFloor = currentFloor;
            for(int nextFloor : destinations){
                cost += abs(prevFloor - nextFloor) + PICKUP_COST;
                prevFloor = nextFloor;
            }
            cost += abs(destinations.get(destinations.size()-1) - floor);
            return cost;
        }
    }

    @Override
    public void call(int floor, Direction direction) {
        destinations.add(floor);
    }

    @Override
    public void chooseDestination(int floor) {
        destinations.add(floor);
    }
}

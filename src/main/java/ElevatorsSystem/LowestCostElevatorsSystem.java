package ElevatorsSystem;

import Elevator.Direction;
import Elevator.IElevator;

import java.util.ArrayList;

public class LowestCostElevatorsSystem extends ElevatorsSystem {
    public LowestCostElevatorsSystem(ArrayList<IElevator> elevators) {
        super(elevators);
    }

    public int call(int floor, Direction direction) {
        int minCost = Integer.MAX_VALUE;
        int bestElevatorID = -1;
        for (int i = 0; i < elevators.size(); i++) {
            IElevator elevator = elevators.get(i);
            int callCost = elevator.callCost(floor, direction);
            if (minCost > callCost || minCost == Integer.MAX_VALUE) {
                minCost = callCost;
                bestElevatorID = i;
            }
        }
        elevators.get(bestElevatorID).call(floor, direction);
        return bestElevatorID;
    }
}

import Elevator.Direction;
import Elevator.IElevator;

import java.util.ArrayList;

public interface IElevatorSystem {
    int call(int floor, Direction direction);

    void chooseDestination(int elevatorID, int destinationFloor);

    void simulate(int time);

    ArrayList<IElevator> getElevators();
}

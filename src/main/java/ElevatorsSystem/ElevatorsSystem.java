package ElevatorsSystem;

import Elevator.IElevator;

import java.util.ArrayList;

public abstract class ElevatorsSystem implements IElevatorsSystem {
    protected final ArrayList<IElevator> elevators;

    public ElevatorsSystem(ArrayList<IElevator> elevators) {
        this.elevators = elevators;
        if (elevators.isEmpty()) {
            throw new IllegalArgumentException("Elevator.Elevator system requires at least 1 elevator to be constructed");
        }
    }

    public void chooseDestination(int elevatorID, int destinationFloor) {
        elevators.get(elevatorID).chooseDestination(destinationFloor);
    }

    public void simulate(int time) {
        if (time < 0) {
            throw new IllegalArgumentException("Time to simulate should be non-negative");
        }
        for (IElevator elevator : elevators) {
            elevator.simulate(time);
        }
    }

    public ArrayList<IElevator> getElevators() {
        return elevators;
    }
}

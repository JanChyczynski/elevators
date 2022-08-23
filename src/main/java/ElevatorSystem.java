import Elevator.IElevator;

import java.util.ArrayList;

public abstract class ElevatorSystem implements IElevatorSystem {
    protected final ArrayList<IElevator> elevators;

    public ElevatorSystem(ArrayList<IElevator> elevators) {
        this.elevators = elevators;
        if(elevators.isEmpty())
        {
            throw new IllegalArgumentException("Elevator.Elevator system requires at least 1 elevator to be constructed");
        }
    }

    public void chooseDestination(int elevatorID, int destinationFloor){
        elevators.get(elevatorID).chooseDestination(destinationFloor);
    }

    public void simulate(int time){
        for(IElevator elevator : elevators){
            elevator.simulate(time);
        }
    }

    public ArrayList<IElevator> getElevators(){
        return elevators;
    }
}

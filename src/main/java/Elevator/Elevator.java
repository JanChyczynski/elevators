package Elevator;

import java.util.LinkedList;
import java.util.List;

public abstract class Elevator implements IElevator{
    protected int floor;
    protected List<Integer> destinations;
    protected ElevatorStatus status;

    public static final int PICKUP_COST = 2;

    public Elevator(int startFloor) {
        this.floor = startFloor;
        this.destinations = new LinkedList<>();
        this.status = ElevatorStatus.WAITING;
    }

    @Override
    public void simulate(int time) {
        for (int i = 0; i < time; i++) {
            switch (status) {
                case MOVING:
                    if(floor == getNextStop()) {
                        status = ElevatorStatus.PICKUP;
                        destinations.remove(0);
                    }
                    else {
                        floor += floor < getNextStop()? 1 : -1;
                    }
                    break;
                case WAITING:
                    if(!destinations.isEmpty()){
                        status = ElevatorStatus.MOVING;
                    }
                    break;
                case PICKUP:
                    status = destinations.isEmpty()? ElevatorStatus.WAITING : ElevatorStatus.MOVING;
                    break;
            }
        }
    }

    @Override
    public int getFloor() {
        return floor;
    }

    @Override
    public int getNextStop() {
        return destinations.isEmpty()? floor : destinations.get(0);
    }

    public List<Integer> getDestinations() {
        return destinations;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "floor=" + floor +
                (destinations.isEmpty()? "" : (", next stop=" + getNextStop()) )+
                ", status=" + status +
                '}';
    }
}

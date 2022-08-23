package Elevator;

public abstract class Elevator implements IElevator{
    @Override
    public void call(int floor, Direction direction) {
    }

    @Override
    public void chooseDestination(int floor) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void simulate(int time) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int getFloor() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int getNextStop() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

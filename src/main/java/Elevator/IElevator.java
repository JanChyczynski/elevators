package Elevator;

public interface IElevator {
    int callCost(int floor, Direction direction);

    void call(int floor, Direction direction);

    void chooseDestination(int floor);

    void simulate(int time);

    int getFloor();

    int getNextStop();
}

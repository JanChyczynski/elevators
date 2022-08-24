package Elevator;

public class TestElevator extends Elevator{
    private final int cost;
    public TestElevator(int cost) {
        super(0);
        this.cost = cost;
    }

    @Override
    public int callCost(int floor, Direction direction) {
        return cost;
    }

    @Override
    public void chooseDestination(int floor) {}

    @Override
    public void call(int floor, Direction direction) {}
}

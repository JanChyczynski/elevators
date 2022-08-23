package Elevator;

public class TestElevator extends Elevator{
    private final int cost;
    public TestElevator(int cost) {
        this.cost = cost;
    }

    @Override
    public int callCost(int floor, Direction direction) {
        return cost;
    }
}

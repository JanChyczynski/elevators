package Elevator;

import static java.lang.Math.abs;


public class MinOverheadElevator extends Elevator {
    public MinOverheadElevator(int startFloor) {
        super(startFloor);
    }

    @Override
    public int callCost(int floor, Direction direction) {
        return findMinCost(floor, direction).cost;
    }

    @Override
    public void call(int floor, Direction direction) {
        int index = findMinCost(floor, direction).index;
        if (destinations.size() <= index || destinations.get(index) != floor) {
            destinations.add(index, floor);
        }
    }

    @Override
    public void chooseDestination(int floor) {
        int index = findMinCost(floor, null).index;
        if (destinations.size() <= index || destinations.get(index) != floor) {
            destinations.add(index, floor);
        }
    }

    protected record MinCostResult(int cost, int index) {
    }

    protected int overheadCost(int time) {
        return time * time;
    }

    protected MinCostResult findMinCost(int floor, Direction direction) {
        int currentFloor = this.floor;
        if (destinations.isEmpty()) {
            return new MinCostResult(abs(floor - currentFloor), 0);
        } else {
            int currentTripTime = 0;
            int prevFloor = currentFloor;
            MinCostResult bestResult = new MinCostResult(Integer.MAX_VALUE, 0);
            for (int i = 0; i < destinations.size(); i++) {
                int nextFloor = destinations.get(i);
                currentTripTime += abs(prevFloor - nextFloor) + PICKUP_COST;

                if (direction == null ||
                        (direction == Direction.UP && nextFloor >= floor) ||
                        (direction == Direction.DOWN && nextFloor <= floor)) {
                    bestResult = updateBestResult(floor, prevFloor, bestResult, i, nextFloor, currentTripTime);
                }

                prevFloor = nextFloor;
            }
            currentTripTime += abs(destinations.get(destinations.size() - 1) - floor);
            int currentCost = overheadCost(currentTripTime);
            if (currentCost < bestResult.cost) {
                bestResult = new MinCostResult(currentCost, destinations.size());
            }
            return bestResult;
        }
    }

    private MinCostResult updateBestResult(int floor, int prevFloor, MinCostResult bestResult, int i, int nextFloor, int currentTripTime) {
        int callerTime = currentTripTime + abs(prevFloor - floor);
        int currentCost = overheadCost(callerTime);
        int overhead = (floor != nextFloor ? PICKUP_COST : 0) +
                +abs(prevFloor - floor) + abs(floor - nextFloor)
                - abs(prevFloor - nextFloor);

        currentCost += (destinations.size() - i) * overheadCost(overhead);

        if (currentCost < bestResult.cost) {
            bestResult = new MinCostResult(currentCost, i);
        }
        return bestResult;
    }
}

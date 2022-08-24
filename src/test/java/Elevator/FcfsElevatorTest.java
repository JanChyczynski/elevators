package Elevator;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class FcfsElevatorTest {
    @Test
    public void shouldQueueDestinations() {
        FcfsElevator elevator = new FcfsElevator(0);
        assert (elevator.getDestinations().isEmpty());
        elevator.call(3, Direction.UP);
        assertEquals(elevator.getDestinations(), new LinkedList<>(Arrays.asList(3)));
        elevator.call(2, Direction.UP);
        assertEquals(elevator.getDestinations(), new LinkedList<>(Arrays.asList(3, 2)));
        elevator.chooseDestination(4);
        assertEquals(elevator.getDestinations(), new LinkedList<>(Arrays.asList(3, 2, 4)));
        elevator.call(1, Direction.UP);
        assertEquals(elevator.getDestinations(), new LinkedList<>(Arrays.asList(3, 2, 4, 1)));
        elevator.chooseDestination(6);
        assertEquals(elevator.getDestinations(), new LinkedList<>(Arrays.asList(3, 2, 4, 1, 6)));

    }

    @Test
    public void shouldCalculateCost() {
        FcfsElevator elevator = new FcfsElevator(0);
        assert (elevator.getDestinations().isEmpty());
        elevator.call(3, Direction.UP);
        assertEquals(12, elevator.callCost(10, Direction.UP), 12);
        elevator.call(2, Direction.UP);
        assertEquals(8, elevator.callCost(2, Direction.UP));
        assertEquals(11, elevator.callCost(5, Direction.UP));
        elevator.chooseDestination(4);
        assertEquals(13, elevator.callCost(5, Direction.UP));
        elevator.call(1, Direction.UP);
        assertEquals(18, elevator.callCost(0, Direction.UP));
        elevator.chooseDestination(6);
        assertEquals(30, elevator.callCost(0, Direction.UP));
    }

    @Test
    public void testGetNextStop() {
        FcfsElevator elevator = new FcfsElevator(0);
        assertEquals(0, elevator.getNextStop());
        elevator.call(3, Direction.UP);
        assertEquals(3, elevator.getNextStop());
        elevator.call(2, Direction.UP);
        assertEquals(3, elevator.getNextStop());
    }

}
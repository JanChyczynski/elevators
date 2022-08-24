package ElevatorsSystem;

import Elevator.Direction;
import Elevator.IElevator;
import Elevator.TestElevator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LowestCostElevatorsSystemTest {
    @Test
    void shouldCallElevatorWithMinCost(){
        ArrayList<IElevator> elevators1 = new ArrayList<>(List.of(new TestElevator(4)));
        LowestCostElevatorsSystem elevatorsSystem1 = new LowestCostElevatorsSystem(elevators1);
        assertEquals(0, elevatorsSystem1.call(1, Direction.UP));

        ArrayList<IElevator> elevators2 = new ArrayList<>(Arrays.asList(new TestElevator(4),
                new TestElevator(1), new TestElevator(3)));
        LowestCostElevatorsSystem elevatorsSystem2 = new LowestCostElevatorsSystem(elevators2);
        assertEquals(1, elevatorsSystem2.call(1, Direction.UP));
    }
}
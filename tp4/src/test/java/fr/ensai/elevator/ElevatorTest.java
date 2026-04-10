package fr.ensai.elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ElevatorTest {
    
    @Test 
    void isFull_EmptyElevator() {
        //GIVEN
        Elevator elevator = new Elevator(1, 0, 2);

        //WHEN
        boolean result = elevator.isFull();

        //THEN 
        assertFalse(result);
    }

    @Test
    void isFull_FullElevator() {
        //GIVEN
        Elevator elevator = new Elevator(1, 0, 2);
        elevator.addPassenger(new Person(1));
        elevator.addPassenger(new Person(2));

        //WHEN
        boolean result = elevator.isFull();

        //THEN 
        assertTrue(result);
    }

    @Test
    void addDestination_shoudlAddFloor_whenFloorGiven() {
        //GIVEN
        Elevator elevator = new Elevator(1, 0, 2);

        //WHEN
        elevator.addDestination(1);

        //THEN
        assertTrue(elevator.containDestination(1));
        assertEquals(1, elevator.getDestinationQueueSize());
    }

    @Test 
    void addDestination_shouldNotDuplicated_WhenFloorAddedTwice() {
        //GIVEN
        Elevator elevator = new Elevator(1, 0, 2);

        //WHEN
        elevator.addDestination(1);
        elevator.addDestination(1);

        //THEN
        assertTrue(elevator.containDestination(1));
        assertEquals(1, elevator.getDestinationQueueSize());
    }

    @Test
    void addDestination_AddMultipleFloor() {
        //GIVEN
        Elevator elevator = new Elevator(1, 0, 3);

        //WHEN
        elevator.addDestination(1);
        elevator.addDestination(2);

        //THEN
        assertTrue(elevator.containDestination(1));
        assertTrue(elevator.containDestination(2));
        assertEquals(2, elevator.getDestinationQueueSize());
    }

}

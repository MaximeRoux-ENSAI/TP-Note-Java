package fr.ensai.elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
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

}

package fr.ensai.elevator;

import java.util.List;
import java.util.Random;

public class CrazyElevator extends Elevator {

    private static final Random random = new Random();

    /**
     * Creates a crazy elevator.
     *
     * @param id the elevator id
     * @param currentFloor the initial floor
     * @param capacity the maximum number of passengers
     */
    public CrazyElevator(int id, int currentFloor, int capacity) {
        super(id, currentFloor, capacity);
    }

    /**
     * Moove randomly :
     *  - remain stationary
     *  - skip its next destination and go directly to the next one
     *  - go to the next floor as a normal elevator
     */
    @Override
    public void move() {
        List<Integer> destinations = this.getDestinationQueue();

        if (destinations.isEmpty()) {
            return;
        }

        int choice = random.nextInt(3);

        if (choice == 0) {
            return;
        } else if (choice == 1) {
            if (destinations.size() >= 2) {
                destinations.remove(0);
                this.setCurrentFloor(destinations.remove(0));
            }
        } else {
            this.setCurrentFloor(destinations.remove(0));
        }
    }

    /**
     * Half the time, a crazyElevator may decide not to unload its passengers.
     * 
     * @param floor the Floor where passengers will exit
     * @return the number of passengers unloaded
     */
    @Override
    public int unloadPassengers(Floor floor){
        int choice = random.nextInt(2);

        if (choice == 0) {
            return (0);
        }

        return super.unloadPassengers(floor);
    }

    /**
     * If it is full, it will send all its passengers into another dimension.
     * (On les supprime)
     * 
     * @param floor the Floor where passengers board the elevator
     */
    @Override
    public void loadPassengers(Floor floor) {
        if (this.isFull()) {
            this.clearPassenger();
            return;
        }

        super.loadPassengers(floor);
    }
}

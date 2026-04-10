package fr.ensai.elevator;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {

        // --------------------------------------------------------------------
        // Create Floors, Elevators and Hotel
        // --------------------------------------------------------------------

        final int nbElevator = Config.getInt("hotel.elevators.count");
        final int nbCrazyElevator = Config.getInt("hotel.crazyelevators.count");
        final int nbFloor = Config.getInt("hotel.floors.count");
        final int elevatorCapacity = Config.getInt("hotel.elevator.capacity");
        final int nbSteps = Config.getInt("steps.count");
        final int msBetweenSteps = Config.getInt("steps.delay");
        

        List<Floor> floors = new ArrayList<>();
        for( int i = 0; i < nbFloor; i++){
            floors.add(new Floor(i));
        } 

        List<Elevator> elevators = new ArrayList<>();
        for(int i = 0; i < nbElevator; i++){
            elevators.add(new Elevator(i + 1, 0, elevatorCapacity));
        }
        for(int i = 0; i < nbCrazyElevator; i++){
            elevators.add(new CrazyElevator(nbElevator + i + 1, 0, elevatorCapacity));
        }

        Hotel hotel = new Hotel(floors, elevators);

        // --------------------------------------------------------------------
        // Run simulation
        // --------------------------------------------------------------------

        for (int step = 1; step <= nbSteps; step++) {
            logger.info("\nStep: {}\n--------", step);

            hotel.update();
            hotel.spawnPerson();
            hotel.display(step);
            Thread.sleep(msBetweenSteps);
        }
    }

}
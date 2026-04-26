package src.easy.parkinglot.models;

import src.easy.parkinglot.strategies.SpotAssignmentStrategy;

import java.util.List;

public class ParkingLot {
    private static ParkingLot instance;
    private String name;
    private List<ParkingFloor> parkingFloors;
    private List<Gate> entryGates;
    private List<Gate> exitGates;
    private SpotAssignmentStrategy spotAssignmentStrategy;

    public ParkingLot(String name, List<ParkingFloor> parkingFloors, List<Gate> entryGates, List<Gate> exitGates, SpotAssignmentStrategy spotAssignmentStrategy) {
        this.name = name;
        this.parkingFloors = parkingFloors;
        this.entryGates = entryGates;
        this.exitGates = exitGates;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
    }

    // Singleton
    public static ParkingLot getInstance(String name,
                                         List<ParkingFloor> floors,
                                         List<Gate> entryGates,
                                         List<Gate> exitGates,
                                         SpotAssignmentStrategy strategy) {
        if(instance == null) {
            instance = new ParkingLot(name, floors, entryGates, exitGates, strategy);
        }
        return instance;
    }

    public static ParkingLot getInstance() {
        return instance;
    }

    public String getName() {
        return name;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public List<Gate> getEntryGates() {
        return entryGates;
    }

    public List<Gate> getExitGates() {
        return exitGates;
    }

    public SpotAssignmentStrategy getSpotAssignmentStrategy() {
        return spotAssignmentStrategy;
    }
}

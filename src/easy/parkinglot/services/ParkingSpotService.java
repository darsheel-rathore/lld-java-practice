package src.easy.parkinglot.services;

import src.easy.parkinglot.exceptions.NoSpotAvailableException;
import src.easy.parkinglot.models.ParkingFloor;
import src.easy.parkinglot.models.ParkingSpot;
import src.easy.parkinglot.models.Vehicle;
import src.easy.parkinglot.strategies.SpotAssignmentStrategy;

import java.util.List;
import java.util.Optional;

public class ParkingSpotService {

    private final SpotAssignmentStrategy spotAssignmentStrategy;

    public ParkingSpotService(SpotAssignmentStrategy spotAssignmentStrategy) {
        this.spotAssignmentStrategy = spotAssignmentStrategy;
    }

    public ParkingSpot findAndAssistSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        Optional<ParkingSpot> spot = spotAssignmentStrategy.assignSpot(floors, vehicle);
        if (spot.isEmpty()) {
            throw new NoSpotAvailableException("No spot available for vehicle type: " + vehicle.getVehicleType());
        }
        spot.get().assignVehicle(vehicle);
        return spot.get();
    }

    public void freeSpot(ParkingSpot spot) {
        spot.removeVehicle();
    }
}

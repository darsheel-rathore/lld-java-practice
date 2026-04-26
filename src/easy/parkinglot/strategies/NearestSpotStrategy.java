package src.easy.parkinglot.strategies;

import src.easy.parkinglot.enums.SpotType;
import src.easy.parkinglot.enums.VehicleType;
import src.easy.parkinglot.models.ParkingFloor;
import src.easy.parkinglot.models.ParkingSpot;
import src.easy.parkinglot.models.Vehicle;

import java.util.List;
import java.util.Optional;

public class NearestSpotStrategy implements SpotAssignmentStrategy{
    @Override
    public Optional<ParkingSpot> assignSpot(List<ParkingFloor> floors, Vehicle vehicle) {

        SpotType required = mapToStopType(vehicle.getVehicleType());
        // Floors are ordered - first available nearest spot wins
        for(ParkingFloor floor : floors) {
            for(ParkingSpot spots : floor.getParkingSpots()) {
                if(spots.getSpotType() == required && spots.isAvailable()) {
                    return Optional.of(spots);
                }
            }
        }
        return Optional.empty();
    }

    private SpotType mapToStopType(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> SpotType.CAR;
            case TRUCK -> SpotType.TRUCK;
            case BIKE -> SpotType.BIKE;
        };
    }
}

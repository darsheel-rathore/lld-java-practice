package src.easy.parkinglot.strategies;

import src.easy.parkinglot.models.ParkingFloor;
import src.easy.parkinglot.models.ParkingSpot;
import src.easy.parkinglot.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface SpotAssignmentStrategy {
    Optional<ParkingSpot> assignSpot(List<ParkingFloor> floors, Vehicle vehicle);
}

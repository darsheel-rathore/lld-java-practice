package src.easy.parkinglot.models;

import src.easy.parkinglot.enums.SpotStatus;
import src.easy.parkinglot.enums.SpotType;

public class ParkingSpot {
    private String spotId;
    private SpotType spotType;
    private SpotStatus spotStatus;
    private Vehicle vehicle;
    private int floorNumber;
    private int spotNumber;

    public ParkingSpot(String spotId, SpotType spotType, int floorNumber, int spotNumber) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.floorNumber = floorNumber;
        this.spotNumber = spotNumber;
        this.spotStatus = SpotStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return spotStatus == SpotStatus.AVAILABLE;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.spotStatus = SpotStatus.OCCUPIED;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.spotStatus = SpotStatus.AVAILABLE;
    }

    public String getSpotId() {
        return spotId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public SpotStatus getSpotStatus() {
        return spotStatus;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getSpotNumber() {
        return spotNumber;
    }
}

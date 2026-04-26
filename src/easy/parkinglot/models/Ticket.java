package src.easy.parkinglot.models;

import src.easy.parkinglot.enums.TicketStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private Gate entryGate;
    private ParkingSpot parkingSpot;
    private LocalDateTime entryTime;
    private TicketStatus ticketStatus;

    public Ticket(Vehicle vehicle, Gate entryGate, ParkingSpot parkingSpot) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.entryGate = entryGate;
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();
        this.ticketStatus = TicketStatus.ACTIVE;
    }

    public void closeTicket() {
        this.ticketStatus = TicketStatus.CLOSED;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Gate getEntryGate() {
        return entryGate;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }
}

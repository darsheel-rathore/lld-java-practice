package src.easy.parkinglot.controllers;

import src.easy.parkinglot.models.*;
import src.easy.parkinglot.services.ParkingSpotService;

public class EntryController {
    private ParkingSpotService parkingSpotService;

    public EntryController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    public Ticket entry(Vehicle vehicle, Gate gate) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        ParkingSpot parkingSpot = parkingSpotService.findAndAssistSpot(parkingLot.getParkingFloors(), vehicle);
        Ticket ticket = new Ticket(vehicle, gate, parkingSpot);

        System.out.println("✅ Entry successful!");
        System.out.println("   Vehicle  : " + vehicle.getVehicleNumber());
        System.out.println("   Spot     : " + parkingSpot.getSpotId() + " | Floor: " + parkingSpot.getFloorNumber());
        System.out.println("   Ticket   : " + ticket.getTicketId());
        System.out.println("   Entry at : " + ticket.getEntryTime());

        return ticket;
    }
}

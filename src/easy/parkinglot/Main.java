package src.easy.parkinglot;

import src.easy.parkinglot.adapter.PaymentGateway;
import src.easy.parkinglot.adapter.RazorpayAdapter;
import src.easy.parkinglot.controllers.EntryController;
import src.easy.parkinglot.controllers.ExitController;
import src.easy.parkinglot.enums.GateType;
import src.easy.parkinglot.enums.SpotType;
import src.easy.parkinglot.enums.VehicleType;
import src.easy.parkinglot.externalSDK.RazorpayClient;
import src.easy.parkinglot.models.*;
import src.easy.parkinglot.services.ParkingSpotService;
import src.easy.parkinglot.strategies.HourlyTieredPricingStrategy;
import src.easy.parkinglot.strategies.NearestSpotStrategy;
import src.easy.parkinglot.strategies.PricingStrategy;
import src.easy.parkinglot.strategies.SpotAssignmentStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Setup Parking lot
        ParkingFloor floor1 = new ParkingFloor(1, Arrays.asList(
                new ParkingSpot("F1-B1", SpotType.BIKE, 1, 1),
                new ParkingSpot("F1-B2", SpotType.BIKE, 1, 2),
                new ParkingSpot("F1-C1", SpotType.CAR, 1, 3),
                new ParkingSpot("F1-C2", SpotType.CAR, 1, 4),
                new ParkingSpot("F1-T1", SpotType.TRUCK, 1, 5)
        ));

        ParkingFloor floor2 = new ParkingFloor(2, Arrays.asList(
                new ParkingSpot("F2-C1", SpotType.CAR, 2, 1),
                new ParkingSpot("F2-C2", SpotType.CAR, 2, 2),
                new ParkingSpot("F2-T1", SpotType.TRUCK, 2, 3)
        ));

        Operator operator1 = new Operator("OP1", "Ramesh");
        Operator operator2 = new Operator("OP2", "Suresh");

        Gate entryGate = new Gate(1, GateType.ENTRY, operator1);
        Gate exitGate = new Gate(2, GateType.EXIT, operator2);

        SpotAssignmentStrategy spotAssignmentStrategy = new NearestSpotStrategy();

        ParkingLot.getInstance("Phoenix Mall Parkign",
                Arrays.asList(floor1, floor2),
                List.of(entryGate),
                List.of(exitGate),
                spotAssignmentStrategy);

        // Wire Dependencies
        ParkingSpotService parkingSpotService = new ParkingSpotService(spotAssignmentStrategy);
        PricingStrategy pricingStrategy = new HourlyTieredPricingStrategy();
        PaymentGateway paymentGateway = new RazorpayAdapter(new RazorpayClient());
        EntryController entryController = new EntryController(parkingSpotService);
        ExitController exitController = new ExitController(parkingSpotService, pricingStrategy, paymentGateway);

        // Scenario 1
        System.out.println("════════════════════════════════════");
        System.out.println("  SCENARIO 1: Car Entry & Exit");
        System.out.println("════════════════════════════════════");
        Vehicle car = new Car("MH01", VehicleType.CAR);
        Ticket carTicket = entryController.entry(car, entryGate);
        exitController.exit(carTicket);

        // ── Scenario 2: Bike enters and exits ─────────────
        System.out.println("\n════════════════════════════════════");
        System.out.println("  SCENARIO 2: Bike Entry & Exit");
        System.out.println("════════════════════════════════════");
        Vehicle bike = new Bike("KA-02-CD-5678", VehicleType.BIKE);
        Ticket bikeTicket = entryController.entry(bike, entryGate);
        exitController.exit(bikeTicket);

        // ── Scenario 3: Invalid ticket reuse ──────────────
        System.out.println("\n════════════════════════════════════");
        System.out.println("  SCENARIO 3: Reuse Closed Ticket");
        System.out.println("════════════════════════════════════");
        try {
            exitController.exit(carTicket); // already closed
        } catch (Exception e) {
            System.out.println("❌ Exception caught: " + e.getMessage());
        }
    }
}

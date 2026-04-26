package src.easy.parkinglot.controllers;

import src.easy.parkinglot.adapter.PaymentGateway;
import src.easy.parkinglot.enums.PaymentMode;
import src.easy.parkinglot.enums.TicketStatus;
import src.easy.parkinglot.exceptions.InvalidTicketException;
import src.easy.parkinglot.exceptions.PaymentFailedException;
import src.easy.parkinglot.models.Bill;
import src.easy.parkinglot.models.Payment;
import src.easy.parkinglot.models.Ticket;
import src.easy.parkinglot.services.ParkingSpotService;
import src.easy.parkinglot.strategies.PricingStrategy;

import java.time.LocalDateTime;

public class ExitController {
    private ParkingSpotService parkingSpotService;
    private PricingStrategy pricingStrategy;
    private PaymentGateway paymentGateway;

    public ExitController(ParkingSpotService parkingSpotService, PricingStrategy pricingStrategy, PaymentGateway paymentGateway) {
        this.parkingSpotService = parkingSpotService;
        this.pricingStrategy = pricingStrategy;
        this.paymentGateway = paymentGateway;
    }

    public Bill exit(Ticket ticket) {
        if (ticket.getTicketStatus() == TicketStatus.CLOSED) {
            throw new InvalidTicketException("Ticket " + ticket.getTicketId() + " is already closed!");
        }

        LocalDateTime exitTime = LocalDateTime.now();
        double amount = pricingStrategy.calculatePrice(ticket.getVehicle(), ticket.getEntryTime(), exitTime);
        Bill bill = new Bill(ticket, exitTime, amount);

        // Process payment via adapter
        boolean success = paymentGateway.processPayment(ticket.getVehicle().getVehicleNumber(), amount);

        if (!success) {
            throw new PaymentFailedException("Payment failed for vehicle " + ticket.getVehicle().getVehicleNumber());
        }

        Payment payment = new Payment(amount, PaymentMode.RAZORPAY);
        payment.markSuccess();
        bill.setPayment(payment);

        // Free the spot
        parkingSpotService.freeSpot(ticket.getParkingSpot());
        ticket.closeTicket();

        System.out.println("\n💳 Exit Successful!");
        System.out.println("   Vehicle  : " + ticket.getVehicle().getVehicleNumber());
        System.out.println("   Duration : " + ticket.getEntryTime()
                + " → " + exitTime);
        System.out.println("   Amount   : Rs." + amount);
        System.out.println("   Payment  : " + payment.getPaymentStatus());
        System.out.println("   Spot     : " + ticket.getParkingSpot().getSpotId()
                + " is now FREE");

        return bill;
    }
}

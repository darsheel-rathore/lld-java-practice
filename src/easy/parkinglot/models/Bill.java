package src.easy.parkinglot.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Bill {
    private String billId;
    private Ticket ticket;
    private LocalDateTime exitTime;
    private double amount;
    private Payment payment;

    public Bill(Ticket ticket, LocalDateTime exitTime, double amount) {
        this.billId = UUID.randomUUID().toString();
        this.ticket = ticket;
        this.exitTime = exitTime;
        this.amount = amount;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getBillId() {
        return billId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getAmount() {
        return amount;
    }

    public Payment getPayment() {
        return payment;
    }
}

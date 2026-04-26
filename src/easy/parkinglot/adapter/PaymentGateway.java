package src.easy.parkinglot.adapter;

public interface PaymentGateway {
    boolean processPayment(String vehicleNumber, double amount);
}

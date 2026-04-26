package src.easy.parkinglot.adapter;

import src.easy.parkinglot.externalSDK.RazorpayClient;

public class RazorpayAdapter implements PaymentGateway{

    private RazorpayClient razorpayClient;

    public RazorpayAdapter(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public boolean processPayment(String vehicleNumber, double amount) {
        return this.razorpayClient.razorpayPay(vehicleNumber, amount);
    }
}

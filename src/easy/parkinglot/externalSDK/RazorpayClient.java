package src.easy.parkinglot.externalSDK;

public class RazorpayClient {

    public boolean razorpayPay(String orderId, double rupees) {
        System.out.println("Razorpay processing payment of Rs. " + rupees + " for order: "  + orderId);
        return true;
    }
}

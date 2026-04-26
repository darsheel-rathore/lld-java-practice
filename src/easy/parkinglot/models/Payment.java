package src.easy.parkinglot.models;

import src.easy.parkinglot.enums.PaymentMode;
import src.easy.parkinglot.enums.PaymentStatus;

import java.util.UUID;

public class Payment {
    private String paymentId;
    private double amount;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;

    public Payment(double amount, PaymentMode paymentMode) {
        this.paymentId = UUID.randomUUID().toString();
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public void markSuccess() {
        this.paymentStatus = PaymentStatus.SUCCESS;
    }

    public void markFailed() {
        this.paymentStatus = PaymentStatus.FAILED;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}

package src.easy.parkinglot.strategies;

import src.easy.parkinglot.models.Vehicle;

import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculatePrice(Vehicle vehicle, LocalDateTime entryTime, LocalDateTime exitTime);
}

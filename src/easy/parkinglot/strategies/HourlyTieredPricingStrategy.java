package src.easy.parkinglot.strategies;

import src.easy.parkinglot.models.Vehicle;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class HourlyTieredPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle, LocalDateTime entryTime, LocalDateTime exitTime) {

        // Ceil to nearest hour
        long minutes = ChronoUnit.MINUTES.between(entryTime, exitTime);

        // You can uncomment this to see the hourly logic is working
        // As all the processing will get completed within 1 minute then overall amount will be zero.
        // minutes = minutes == 0 ? new Random().nextInt(1, 600) : minutes;

        int hours = (int) Math.ceil(minutes / 60.0);

        return switch (vehicle.getVehicleType()) {
            case BIKE -> calculateBikePrice(hours);
            case CAR -> calculateCarPrice(hours);
            case TRUCK -> calculateTruckPrice(hours);
        };
    }

    // BIKE: 0-2: 20/hr, 2-4: 25/hr, 4-6: 30/hr, 6+: 40/hr
    private double calculateBikePrice(int hours) {
        return tieredPrice(hours, 20, 25, 30, 40);
    }

    // CAR: 0-2: 25/hr, 2-4: 30/hr, 4-6: 35/hr, 6+: 45/hr
    private double calculateCarPrice(int hours) {
        return tieredPrice(hours, 25, 30, 35, 45);
    }

    // TRUCK: 0-2: 50/hr, 2-4: 60/hr, 4-6: 65/hr, 6+: 80/hr
    private double calculateTruckPrice(int hours) {
        return tieredPrice(hours, 50, 60, 65, 80);
    }

    private double tieredPrice(int hour, double r1, double r2, double r3, double r4) {
        double total = 0;
        if(hour <= 2) return hour * r1;

        total += 2 * r1;
        if(hour <= 4) return total + (hour - 2) * r2;

        total += 2 * r2;
        if(hour <= 6) return total + (hour - 4) * r3;

        total += 2 * r3;
        return total + (hour - 6) * r4;
    }
}

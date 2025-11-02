package com.Skoglund.Price;

public class PremiumPrice implements PricePolicy {
    @Override
    public double calculatePrice(double basePrice, long days) {
        return basePrice * days * 1.2;
    }
}

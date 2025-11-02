package com.Skoglund.Price;

public class StudentPrice implements PricePolicy {
    @Override
    public double calculatePrice(double basePrice, long days) {
        return basePrice * days * 0.8;
    }
}

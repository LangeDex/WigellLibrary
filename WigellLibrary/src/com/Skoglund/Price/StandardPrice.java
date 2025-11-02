package com.Skoglund.Price;

public class StandardPrice implements PricePolicy {
    @Override
    public double calculatePrice(double basePrice, long days) {
        return basePrice * days;
    }

}

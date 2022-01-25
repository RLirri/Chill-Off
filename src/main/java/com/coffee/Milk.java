package com.coffee;

public class Milk extends CondimentDecorator {
    Beverage beverage;
    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }
    public String getDescription ( ) {
        return beverage.getDescription( ) + ", Milk";
    }
}

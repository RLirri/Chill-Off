package com.coffee;

public class Chocolate extends CondimentDecorator {
    Beverage beverage;
    public Chocolate(Beverage beverage) {
        this.beverage = beverage;
    }
    public String getDescription ( ) {
        return beverage.getDescription( ) + ", Chocolate";
    }

}

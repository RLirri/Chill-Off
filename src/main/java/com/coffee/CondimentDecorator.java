package com.coffee;

public abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription ( );
}


//class Mocha extends CondimentDecorator {
//    Beverage beverage;
//    public Mocha(Beverage beverage) {
//        this.beverage = beverage;
//    }
//    public String getDescription ( ) {
//        return beverage.getDescription( ) + ", Mocha";
//    }
//
//}

//class Milk extends CondimentDecorator {
//    Beverage beverage;
//    public Milk(Beverage beverage) {
//        this.beverage = beverage;
//    }
//    public String getDescription ( ) {
//        return beverage.getDescription( ) + ", Milk";
//    }
//
//}

//class Chocolate extends CondimentDecorator {
//    Beverage beverage;
//    public Chocolate(Beverage beverage) {
//        this.beverage = beverage;
//    }
//    public String getDescription ( ) {
//        return beverage.getDescription( ) + ", Chocolate";
//    }
//
//}




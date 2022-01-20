package com.coffee;

public abstract class Beverage {
    String description = "Unknown Beverage";
    public String getDescription ( ) {
        return description;
    }

}


 class Espresso extends Beverage {
    public Espresso ( ) {
        description = "Expresso";
    }


}

 class Americano extends Beverage {
    public Americano ( ) {
        description = "Americano";
    }

}

class Cappuccino extends Beverage {
    public Cappuccino ( ) {
        description = "Cappuccino";
    }

}


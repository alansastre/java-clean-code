package conditionals.polymorphism.bad;

public class Product {

    private static final Double COMMISSION = 0.10;
    private static final Double SHIPPING = 5.99;

    private String name;
    private String type;
    private Double price;

    double calculatePrice(){

        switch(this.type){
            case "SIMPLE":
                return this.price + SHIPPING;
            case "MEDIUM":
                return calculateMedium();
            case "PREMIUM":
                return this.price + price * COMMISSION;
            default:
                return this.price;
        }
    }

    private double calculateMedium() {
        return this.price + price * COMMISSION + SHIPPING;
    }


}

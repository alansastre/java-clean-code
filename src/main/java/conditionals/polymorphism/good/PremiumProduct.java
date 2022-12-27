package conditionals.polymorphism.good;

public class PremiumProduct extends Product {

    @Override
    double calculatePrice() {
        return this.getPrice() + SHIPPING + COMMISSION;
    }
}

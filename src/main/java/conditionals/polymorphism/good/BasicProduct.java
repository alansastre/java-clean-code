package conditionals.polymorphism.good;

public class BasicProduct extends Product {

    @Override
    double calculatePrice() {
        return this.getPrice() + SHIPPING;
    }
}

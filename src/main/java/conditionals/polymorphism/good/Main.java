package conditionals.polymorphism.good;

public class Main {

    public static void main(String[] args) {

        Product product = new BasicProduct();
        product.calculatePrice();

        Product product2 = new PremiumProduct();
        product2.calculatePrice();
    }

    static double calculatePrice(Product product){

    }
}

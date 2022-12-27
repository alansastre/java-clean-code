package methods.removeassign.bad;

public class Main {


    double calculateDiscount(Order order, double totalPrice){
        if (order.getPrice() > 100)
            totalPrice += order.getPrice() * order.getOffer(); // sobreescribe totalPrice
        return totalPrice;
    }
}

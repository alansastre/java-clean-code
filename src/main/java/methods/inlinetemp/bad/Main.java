package methods.inlinetemp.bad;

public class Main {
	
	/*
	 * Calcular si debería tener descuento sí o no
	 */
    boolean calculateDiscount(Order order){
        double discount = order.getPrice();
        return discount > 200;
    }

}

package methods.inlinemethod.bad;

public class Main {
	
    private double interest = 5d;

    double getRateInterest(){
        return getAdjustedInterest() ? 2 : 1;
    }

	private boolean getAdjustedInterest() {
		return interest > 5;
	}
}

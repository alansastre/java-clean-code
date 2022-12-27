package data.replacearray.bad;

public class Main {

	public static void main(String[] args) {
		String[] row = new String[4];
		row[0] = "Chair";
		row[1] = "49.99";
		row[2] = "Black";
		row[3] = "5";

		double finalPrice = Double.parseDouble(row[1]) * Double.parseDouble(row[3]);
	}
}

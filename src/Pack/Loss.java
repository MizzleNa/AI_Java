package Pack;

public class Loss {
	public static boolean cal(Layer t , Layer y) {
		cal(
			y.options.cost_loss,
			t.matrixs.get(0),
			y.matrixs.get(1),
			y.matrixs.get(2)
		);
		
		return true;
	}
	
	public static boolean cal(String name , Matrix t , Matrix y, Matrix out) {
		if(name == "meansquare") {
			meansquare(t,y,out);
			return true;
		}
		if(name == "crossentropy") {
			crossentropy(t,y,out);
			return true;
		}
		return true;
	}
	//meansquare
	private static double meansquare(double t, double y) {
		double loss = (t-y);
		return loss;
	}
	
	private static boolean meansquare(Matrix t, Matrix y, Matrix result) {
		int size = result.col * result.row;
		for(int i = 0; i < size ; i++) {
			result.value.set(i, meansquare(t.value.get(i) ,y.value.get(i)) );
		}
		return true;
	}
	//crossentropy
	private static double crossentropy(double t, double y) {
		double hyper = 1.0E20;
		double loss = -( ( t-(y+hyper) )/( (y+hyper) * (1.0-(y+hyper) ) ) );
		return loss;
	}
	
	private static boolean crossentropy(Matrix t, Matrix y, Matrix result) {
		int size = result.col * result.row;
		for(int i = 0; i < size ; i++) {
			result.value.set(i, crossentropy(t.value.get(i) ,y.value.get(i)) );
		}
		return true;
	}
}

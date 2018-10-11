package Pack;


public class Cost {
	public static boolean cal(Layer y , Layer t) {
		cal(
			y.options.cost_loss,
			t.matrixs.get(0),
			y.matrixs.get(1),
			y.matrixs.get(5)
		);
		
		return true;
	}
	
	public static boolean cal(String name  , Matrix t , Matrix y, Matrix out) {
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
	//cross_entropy
	private static double crossentropy(double t, double y) {
		return -(t*Math.log(y) + (1.0-t)*Math.log(1.0-y));
	}
	
	private static boolean crossentropy(Matrix t, Matrix y, Matrix result) {
		int size = result.col * result.row;
		for(int i = 0; i < size ; i++) {
			result.value.set(i, crossentropy(t.value.get(i) ,y.value.get(i)) );
		}
		return true;
	}
	//mean_square
	private static double meansquare(double t, double y) {
		return 1.0/2.0*(t-y)*(t-y);
	}
	
	private static boolean meansquare(Matrix t, Matrix y, Matrix result) {
		int size = result.col * result.row;
		for(int i = 0; i < size ; i++) {
			result.value.set(i, meansquare(t.value.get(i) ,y.value.get(i)) );
		}
		return false;
	}
}

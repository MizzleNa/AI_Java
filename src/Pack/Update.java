package Pack;

public class Update {
	
	public static boolean cal(Layer weight) {
		weight(
			weight.options.update,
			weight.matrixs.get(0),
			weight.matrixs.get(1),
			weight.matrixs.get(0)
		);
		if(weight.options.bias) {
			bias(
				weight.options.update,
				weight.matrixs.get(2),
				weight.matrixs.get(3),
				weight.matrixs.get(2)
			);
		}
		return true;
	}
	
	public static boolean weight(String name , Matrix weight , Matrix d_weight , Matrix out) {
		if(name == "sgd") {
			Cal.plus(
				weight, 
				d_weight, 
				out 
			);
		}
		return true;
	}
	
	public static boolean bias(String name , Matrix bias , Matrix d_bias , Matrix out) {
		if(name == "sgd") {
			Cal.plus(
				bias, 
				d_bias, 
				out 
			);
		}
		return true;
	}
	
}

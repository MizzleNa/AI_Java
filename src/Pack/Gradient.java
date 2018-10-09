package Pack;

public class Gradient {
	public static boolean cal(String name , Matrix affine , Matrix out) {
		
		if(name == "softmax") {
			softmax(affine, out);
			return true;
		}
		
		if(name == "sigmoid") {
			sigmoid(affine, out);
			return true;
		}
		
		if(name == "relu") {
			relu(affine, out);
			return true;
		}
		
		if(name == "tanh") {
			tanh(affine, out);
			return true;
		}
		
		if(name == "line") {
			line(affine, out);
			return true;
		}
		return true;
	}
	
	//line
	private static double line(double v) {
		return 1;
	}
	
	private static boolean line(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, line(affine.value.get(i)));
		}
		return true;
	}
	
	//sigmoid
	private static double sigmoid(double v) {
		return ( 1.0 / (1.0+Math.exp(-v))) * (1.0 - ( 1.0 / (1.0+Math.exp(-v))));
	}

	private static boolean sigmoid(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, sigmoid(affine.value.get(i)));
		}
		return true;
	}
	
	//relu
	private static double relu(double v) {
		return v > 0.0 ? 1.0 : 0.0;
	}

	private static boolean relu(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, relu(affine.value.get(i)));
		}
		return true;
	}
	
	//tanh
	private static double tanh(double v) {
		return (1.0-Math.tanh(v))*(1.0+Math.tanh(v));
	}

	private static boolean tanh(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, tanh(affine.value.get(i)));
		}
		return true;
	}
	//softmax
	private static double softmax(double v) {
		return 1.0;
	}

	private static boolean softmax(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, softmax(affine.value.get(i)));
		}
		return true;
	}
}

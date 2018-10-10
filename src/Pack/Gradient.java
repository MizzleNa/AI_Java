package Pack;

public class Gradient {
	public static boolean cal(Layer y) {
		if(y.options.softmax) {
			//gradient
			cal(
				"softmax",
				y.matrixs.get(0),
				y.matrixs.get(3)
			);
			//loss*gradient
			Cal.multiply(
				y.matrixs.get(2), 
				y.matrixs.get(3), 
				y.matrixs.get(4)
			);
		}else {
			//gradient
			cal(
				y.options.activity_gradient,
				y.matrixs.get(0),
				y.matrixs.get(3)
			);
			//loss*gradient
			Cal.multiply(
				y.matrixs.get(2), 
				y.matrixs.get(3), 
				y.matrixs.get(4)
			);
		}
		return true;
	}
	
	public static boolean cal(String name , Matrix affine , Matrix out) {
		
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
		
		if(name == "softmax") {
			softmax(affine, out);
			return true;
		}
		
		if(name == "line") {
			line(affine, out);
			return true;
		}
		return true;
	}
	
	//softmax
	public static double softmax(double v) {
		return 1;
	}

	public static boolean softmax(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, softmax(affine.value.get(i)));
		}
		return true;
	}
		
	//line
	public static double line(double v) {
		return 1;
	}
	
	public static boolean line(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, line(affine.value.get(i)));
		}
		return true;
	}
	
	//sigmoid
	public static double sigmoid(double v) {
		return ( 1.0 / (1.0+Math.exp(-v))) * (1.0 - ( 1.0 / (1.0+Math.exp(-v))));
	}

	public static boolean sigmoid(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, sigmoid(affine.value.get(i)));
		}
		return true;
	}
	
	//relu
	public static double relu(double v) {
		return v > 0.0 ? 1.0 : 0.0;
	}

	public static boolean relu(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, relu(affine.value.get(i)));
		}
		return true;
	}
	
	//tanh
	public static double tanh(double v) {
		return (1.0-Math.tanh(v))*(1.0+Math.tanh(v));
	}

	public static boolean tanh(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, tanh(affine.value.get(i)));
		}
		return true;
	}
}

package Pack;

public class Activity {
	
	public static boolean cal(Layer affine) {
		String activity_gradient = affine.options.activity_gradient;
		
		if(affine.options.softmax && affine.options.name == "output") {
			activity_gradient = "softmax";
		}
		
		cal(
			activity_gradient,
			affine.matrixs.get(0),
			affine.matrixs.get(1)
		);
		
		return true;
	}
	
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
	public static double line(double v) {
		return v;
	}
	
	public static boolean line(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, line(affine.value.get(i)));
		}
		return true;
	}
	
	//sigmoid
	public static double sigmoid(double v) {
		return ( 1.0 / (1.0+Math.exp(-v)));
	}

	public static boolean sigmoid(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, sigmoid(affine.value.get(i)));
		}
		return true;
	}
	
	//relu
	public static double relu(double v) {
		return v > 0 ? v : 0;
	}

	public static boolean relu(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, relu(affine.value.get(i)));
		}
		return true;
	}
	
	//tanh
	public static double tanh(double v) {
		return Math.tanh(v);
	}

	public static boolean tanh(Matrix affine , Matrix result) {
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, tanh(affine.value.get(i)));
		}
		return true;
	}
	
	//softmax
	public static double softmax(double x , double max , double sum) {
		return Math.exp(x-max) / sum;
	}
	
	public static boolean softmax(Matrix affine , Matrix result) {
		double sum = 0.0;
		double max = affine.value.get(0);
		for(int i = 0; i < affine.value.size() ; i++) {
			max = Math.max(max, affine.value.get(i));
		}
		for(int i = 0; i < affine.value.size() ; i++) {
			sum += Math.exp(affine.value.get(i) - max);
		}	
		
		for(int i = 0; i < affine.value.size() ; i++) {
			result.value.set(i, softmax(affine.value.get(i) , max, sum));
		}
		return true;
	}

	
}

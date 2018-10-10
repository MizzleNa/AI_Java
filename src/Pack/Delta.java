package Pack;

public class Delta {
	
	public static boolean cal(Layer now, Layer before, Layer weight) {
		//now : 현제 layer
		//before : 이전 layer
		//ex> now : hidden / before : output
		int matchs = 1;
		if(now.options.name == "input") {
			matchs = 0;
		}
		
		weight(
			now.matrixs.get(matchs),
			before.matrixs.get(4),
			weight.matrixs.get(1)
		);
		
		Cal.multiply(
			weight.matrixs.get(1), 
			weight.options.weight_parameter, 
			weight.matrixs.get(1)
		);
		
		if(weight.options.bias) {
			bias(
				before.matrixs.get(4),
				weight.matrixs.get(3)
			);
			
			Cal.multiply(
				weight.matrixs.get(3), 
				weight.options.weight_parameter, 
				weight.matrixs.get(3)
			);
		}
		return true;
	}

	public static boolean weight(Matrix activity , Matrix gradient , Matrix weight) {
		Cal.transpose(activity, activity);
		Cal.dot(gradient, activity, weight);
		Cal.transpose(activity, activity);
		return true;
	}
	
	public static boolean bias(Matrix gradient , Matrix bias) {
		Cal.multiply(gradient, 1.0, bias);
		return true;
	}
	
	
	
}

package Pack;

public class Update {
	public static double beta = 0.5;
	public static double eps =  1.0E-4;
	public static double parameter = 1.0;
	
	public static boolean cal(Layer weight) {
		parameter = weight.options.weight_parameter;
		
		cal(
			weight.options.update,
			weight.matrixs.get(0),
			weight.matrixs.get(1),
			weight.matrixs.get(0),
			weight.matrixs.get(4),
			weight.matrixs.get(6)
		);
		if(weight.options.bias) {
			cal(
				weight.options.update,
				weight.matrixs.get(2),
				weight.matrixs.get(3),
				weight.matrixs.get(2),
				weight.matrixs.get(5),
				weight.matrixs.get(7)
			);
		}
		return true;
	}
	
	public static boolean cal(String name , Matrix weight , Matrix d_weight , Matrix out , Matrix... param ) {
		if(name == "sgd") {
			int size = weight.col*weight.row;
			double value = 0.0;
			for(int i = 0; i < size ; i++) {
				value = weight.value.get(i) + parameter*d_weight.value.get(i);
				weight.value.set(i, value);
			}
			return true;
		}
		
		if(name == "momentum") {
			int size = weight.col*weight.row;
			double v;
			double value = 0.0;
			for(int i = 0; i < size ; i++) {
				v = param[0].value.get(i) + d_weight.value.get(i);
				param[0].value.set(i, v);
				
				value = weight.value.get(i) + parameter*param[0].value.get(i);
				weight.value.set(i, value);
			}
			return true;
		}
		
		if(name == "adagrad") {
			//[2018-10-12]식 확인 및 수정
			int size = weight.col*weight.row;
			double v;
			double value = 0.0;
			
			for(int i = 0; i < size ; i++) {
				v = param[0].value.get(i) + d_weight.value.get(i)*d_weight.value.get(i);
				param[0].value.set(i, v);
				 
				value = weight.value.get(i) + parameter*( d_weight.value.get(i) / ( Math.sqrt(v) + eps ) );
				weight.value.set(i, value);
			}
		}
		
		if(name == "adam") {
			//param[0] = beta*param[0] + (1-beta)*d_weight;
			//param[1] = beta*param[1] + (1-beta)*(d_weight*d_weight);
			
			int size = weight.col*weight.row;
			double m,v;
			double value = 0.0;
			
			for(int i = 0; i < size ; i++) {
				m = beta*param[0].value.get(i) + (1-beta)*d_weight.value.get(i);
				param[0].value.set(i, m);
				
				v = beta*param[1].value.get(i) + (1-beta)*d_weight.value.get(i)*d_weight.value.get(i);
				param[1].value.set(i, v);
				
				value = weight.value.get(i) + parameter*( m / ( Math.sqrt(v) + eps ) );
				weight.value.set(i , value);
			}
		}
		return true;
	}
	
	
}

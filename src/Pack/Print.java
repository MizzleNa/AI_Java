package Pack;

public class Print {
	public static void layer(Layer layer) {
		System.out.println("==========" + layer.options.name + "==========");
		
		for(Matrix mtx : layer.matrixs) {
			matrix(mtx);
		}
	}
	
	public static void matrix(Matrix matrix) {
		System.out.println("Name : " + matrix.name);
		System.out.println("Col : " + matrix.col + "\tRow : "+ matrix.row);
		for(int c = 0; c < matrix.col ; c++) {
			for(int r = 0; r < matrix.row ; r++) {
				System.out.print(matrix.value.get(c*matrix.row+r)+"\t");
			}	
			System.out.println();
		}
		System.out.println();
	}
	
	public static void value(Matrix matrix) {
		for(int i = 0; i < matrix.col*matrix.row ; i++) {
			System.out.print(matrix.value.get(i)+"\t");
		}
		System.out.println();
	}
	
	public static void layeroption(LayerOption option) {
		System.out.println("name : " +option.name);
		System.out.println("col : " +option.col);
		System.out.println("row : " +option.row);
		
		System.out.println("activity_gradient : " +option.activity_gradient);
		System.out.println("cost_loss : " +option.cost_loss);
		System.out.println("update : " +option.update);
		
		System.out.println("weight_parameter : " +option.weight_parameter);
		System.out.println("bias : " +option.bias);
		System.out.println("softmax : " +option.softmax);
		
		System.out.println();
	}
}

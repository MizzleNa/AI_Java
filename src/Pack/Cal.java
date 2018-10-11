package Pack;

public class Cal {
	public static boolean line(Matrix matrix, int line, Matrix result) {
		int col = result.col;
		
		for(int c = 0 ; c < col ; c++) {
			result.value.set(c, matrix.value.get(matrix.row*line+c));
		}

		return true;
	}
	
	public static boolean transpose(Matrix matrix, Matrix result) {
		int col = matrix.row;
		int row = matrix.col;

		Matrix copy = Make.matrix(matrix.name, 0.0, matrix.col, matrix.row);
		for(int i = 0; i < matrix.col*matrix.row ; i++) {
			copy.value.set(i, matrix.value.get(i));
		}
		
		//if( !(col == 1 || row == 1) ) {
			for(int c = 0 ; c < col ; c++) {
				for(int r = 0 ; r < row ; r++) {
					result.value.set(c*row+r,copy.value.get(r*col+c) );
				}	
			}
		//}
		result.col = copy.row;
		result.row = copy.col;
		
		//System.gc();
		
		return true;
	}
	
	public static boolean minus(Matrix matrix1, Matrix matrix2, Matrix result) {
		for(int i = 0; i < matrix1.value.size() ; i++) {
			result.value.set(i, matrix1.value.get(i)-matrix2.value.get(i));
		}
		return true;
	}

	public static boolean plus(Matrix matrix1, Matrix matrix2, Matrix result) {
		for(int i = 0; i < matrix1.value.size() ; i++) {
			result.value.set(i, matrix1.value.get(i)+matrix2.value.get(i));
		}
		return true;
	}

	public static boolean multiply(Matrix matrix1, Matrix matrix2, Matrix result) {
		int size = matrix1.col*matrix1.row;
		
		for(int i = 0 ; i < size ; i++) {
			result.value.set(i, matrix1.value.get(i)*matrix2.value.get(i));
		}
		return true;
	}

	public static boolean multiply(Matrix matrix1, double value, Matrix result) {
		int size = matrix1.col*matrix1.row;
		
		for(int i = 0 ; i < size ; i++) {
			result.value.set(i, matrix1.value.get(i)*value);
		}
		return true;
	}
	
	public static boolean pow(Matrix matrix1, double value, Matrix result) {
		int size = matrix1.col*matrix1.row;
		
		for(int i = 0 ; i < size ; i++) {
			result.value.set(i, Math.pow(matrix1.value.get(i),value) );
		}
		return true;
	}
	
	public static boolean divide(Matrix matrix1, double value, Matrix result) {
		int size = matrix1.col*matrix1.row;
		
		for(int i = 0 ; i < size ; i++) {
			result.value.set(i, matrix1.value.get(i)/value);
		}
		return true;
	}
	
	public static boolean divide(Matrix matrix1, Matrix matrix2, Matrix result) {
		int size = matrix1.col*matrix1.row;
		
		for(int i = 0 ; i < size ; i++) {
			result.value.set(i, matrix1.value.get(i)/matrix2.value.get(i));
		}
		return true;
	}
	
	public static boolean dot(Matrix first , Matrix second , Matrix result) {
		int col = first.col;
		int row = second.row;
		int inner = first.row;

		double sum;
		for(int c = 0; c < col ; c++) {
			for(int r = 0; r < row ; r++) {
				sum = 0.0;
				for(int i = 0; i < inner ; i++) {
					//inner = first : row이고 , second : col이다.
					sum += first.value.get(c*first.row+i)*second.value.get(i*second.row+r);
				}
				result.value.set(c*row+r, sum);
			}	
		}
		return true;
	}

}

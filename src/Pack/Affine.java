package Pack;

public class Affine {
	public static boolean cal(Matrix w, Matrix x, Matrix b, Matrix y) {
		
		int col = w.col;
		int row = x.row;
		int inner = w.row;//x.col;

		double sum;
		for(int c = 0; c < col ; c++) {
			for(int r = 0; r < row ; r++) {
				sum = 0.0;
				for(int i = 0; i < inner ; i++) {
					sum += w.value.get(c*w.row+i)*x.value.get(i*x.row+r);
				}
				sum += b.value.get(c);
				y.value.set(c*row+r, sum);
			}	
		}

		return true;
	}
	
	public static boolean cal(Matrix w, Matrix x, Matrix y) {
		int col = w.col;
		int row = x.row;
		int inner = w.row;//x.col;

		double sum;
		for(int c = 0; c < col ; c++) {
			for(int r = 0; r < row ; r++) {
				sum = 0.0;
				for(int i = 0; i < inner ; i++) {
					sum += w.value.get(c*w.row+i)*x.value.get(i*x.row+r);
				}
				y.value.set(c*row+r, sum);
			}	
		}

		return true;
	}
}

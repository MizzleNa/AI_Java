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

	public static boolean cal(Layer w, Layer x, Layer y , boolean reverse) {
		if(reverse) {
			//backward
			Cal.transpose(w.matrixs.get(0), w.matrixs.get(0));
			cal(w.matrixs.get(0), x.matrixs.get(4), y.matrixs.get(2));
			Cal.transpose(w.matrixs.get(0), w.matrixs.get(0));
		}else {
			//forward
			int matchs = 1;
			if(x.options.name == "input") {
				matchs = 0;
			}
			
			if(w.options.bias) {
				//get(0) : w , get(1) : dw , get(2) : b , get(3) : db
				cal(w.matrixs.get(0), x.matrixs.get(matchs), w.matrixs.get(2), y.matrixs.get(0));
			}else {
				cal(w.matrixs.get(0), x.matrixs.get(matchs), y.matrixs.get(0));
			}
		}
		return true;
	}
}

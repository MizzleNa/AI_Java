package Chapter;

import Pack.*;

public class Chp09 {

	public Chp09() {
		test();
	}
	
	public void test() {
		//run test
		Matrix inputDataSet = IO.fileMatrix("D:/DataSet/and/train.txt");
		Matrix targetDataSet = IO.fileMatrix("D:/DataSet/and/target.txt");

		//network set
		Matrix input = Make.matrix("input", 0.0, inputDataSet.row, 1);
		//
		Matrix w = Make.matrix("weight", "rand", targetDataSet.row, inputDataSet.row);
		Matrix b = Make.matrix("bias", "rand", targetDataSet.row, 1);
		Matrix dw = Make.matrix("dweight", "rand", targetDataSet.row, inputDataSet.row);
		Matrix db = Make.matrix("dbias", "rand", targetDataSet.row, 1);
		
		//
		Matrix affine = Make.matrix("affine", 0.0, targetDataSet.row, 1);
		Matrix output = Make.matrix("output", 0.0, targetDataSet.row, 1);
		Matrix cost = Make.matrix("cost", 0.0, targetDataSet.row, 1);
		Matrix loss = Make.matrix("loss", 0.0, targetDataSet.row, 1);
		Matrix gradient = Make.matrix("gradient", 0.0, targetDataSet.row, 1);
		Matrix J = Make.matrix("J", 0.0, targetDataSet.row, 1);
		//
		Matrix target = Make.matrix("target", 0.0, targetDataSet.row, 1);

		int epoch = 100;
		int depth = inputDataSet.col;
		
		for(int epo = 0 ; epo < epoch ; epo++) {
			for(int d = 0 ; d < depth ; d++) {
				//Cal
				//Forward
				Cal.line(inputDataSet, d, input);
				Cal.line(targetDataSet, d, target);

				Affine.cal(w, input, b, affine);
				//Affine.cal(w, input, affine);

				Activity.cal("sigmoid", affine, output);
				//Activity.cal("relu", affine, output);
				//Activity.cal("tanh", affine, output);
				//Activity.cal("line", affine, output);

				//Cost.cal("meansquare", target, output, cost);
				Cost.cal("crossentropy", target, output, cost);

				//Backward
				//Loss.cal("meansquare", target, output, loss);
				Loss.cal("crossentropy", target, output, loss);

				Gradient.cal("sigmoid", affine, gradient);
				//Gradient.cal("relu", affine, gradient);
				//Gradient.cal("tanh", affine, gradient);
				//Gradient.cal("line", affine, gradient);
				Cal.multiply(loss, gradient, J);

				//dw
				Cal.transpose(input, input);
				Cal.dot(J, input, dw);
				Cal.transpose(input, input);

				//db
				Cal.multiply(J, 1.0, db);//db

				//Update
				//w = w - dw
				Cal.minus(w, dw, w);
				//b = b - db
				Cal.minus(b, db, b);
				
				//Print.matrix(target);
				//Print.matrix(output);
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Chp09();
	}

}

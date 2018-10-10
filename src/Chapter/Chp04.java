package Chapter;

import Pack.*;

public class Chp04 {

	public Chp04() {
		test();
	}
	
	public void test() {
		//activity test
		Matrix inputDataSet = IO.fileMatrix("D:/DataSet/and/train.txt");
		Matrix targetDataSet = IO.fileMatrix("D:/DataSet/and/target.txt");
		
		//network set
		Matrix input = Make.matrix("input", 0.0, inputDataSet.row, 1);
		
		Matrix w = Make.matrix("w", "rand", targetDataSet.row, inputDataSet.row);
		Matrix bias = Make.matrix("bias", "rand", targetDataSet.row, 1);
		
		Matrix affine = Make.matrix("affine", 0.0, targetDataSet.row, 1);
		Matrix output = Make.matrix("output", 0.0, targetDataSet.row, 1);
		
		Matrix target = Make.matrix("target", 0.0, targetDataSet.row, 1);
		
		//
		Cal.line(inputDataSet, 2, input);
		Cal.line(targetDataSet, 2, target);
		
		Affine.cal(w, input, bias, affine);
		//Activity.cal("sigmoid", affine, output);
		//Activity.cal("relu", affine, output);
		//Activity.cal("tanh", affine, output);
		//Activity.cal("line", affine, output);
		
		Print.matrix(input);
		Print.matrix(w);
		Print.matrix(bias);
		
		Print.matrix(affine);
		Print.matrix(output);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Chp04();
	}

}

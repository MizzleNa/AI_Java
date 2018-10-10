package Chapter;

import Pack.*;

public class Chp06 {

	public Chp06() {
		test();
	}
	
	public void test() {
		//loss test
		Matrix inputDataSet = IO.fileMatrix("D:/DataSet/and/train.txt");
		Matrix targetDataSet = IO.fileMatrix("D:/DataSet/and/target.txt");

		//network set
		Matrix input = Make.matrix("input", 0.0, inputDataSet.row, 1);

		Matrix w = Make.matrix("w", "rand", targetDataSet.row, inputDataSet.row);
		Matrix bias = Make.matrix("bias", "rand", targetDataSet.row, 1);

		Matrix affine = Make.matrix("affine", 0.0, targetDataSet.row, 1);
		Matrix output = Make.matrix("output", 0.0, targetDataSet.row, 1);

		Matrix cost = Make.matrix("cost", 0.0, targetDataSet.row, 1);
		Matrix loss = Make.matrix("loss", 0.0, targetDataSet.row, 1);
		
		Matrix target = Make.matrix("target", 0.0, targetDataSet.row, 1);

		//Cal
		Cal.line(inputDataSet, 2, input);
		Cal.line(targetDataSet, 2, target);

		Affine.cal(w, input, bias, affine);
		//Affine.cal(w, input, affine);
		
		Activity.cal("sigmoid", affine, output);
		//Activity.cal("relu", affine, output);
		//Activity.cal("tanh", affine, output);
		//Activity.cal("line", affine, output);

		//Cost.cal("meansquare", target, output, cost);
		Cost.cal("crossentropy", target, output, cost);
		output =  Make.matrix("output", 1.0, targetDataSet.row, 1);
		//Loss.cal("meansquare", target, output, loss);
		Loss.cal("crossentropy", target, output, loss);
		
		Print.matrix(input);
		Print.matrix(w);
		Print.matrix(bias);

		Print.matrix(affine);
		Print.matrix(output);
		
		Print.matrix(cost);
		Print.matrix(loss);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Chp06();
	}

}

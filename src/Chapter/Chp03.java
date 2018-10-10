package Chapter;

import Pack.*;

public class Chp03 {

	public Chp03() {
		test();
	}
	
	public void test() {
		//affine test
		Matrix inputDataSet = IO.fileMatrix("D:/DataSet/and/train.txt");
		Matrix targetDataSet = IO.fileMatrix("D:/DataSet/and/target.txt");
		
		//network set
		Matrix input = Make.matrix("input", 0.0, inputDataSet.row, 1);
		
		Matrix w = Make.matrix("w", "rand", targetDataSet.row, inputDataSet.row);
		Matrix bias = Make.matrix("bias", "rand", targetDataSet.row, 1);
		
		Matrix affine = Make.matrix("affine", 0.0, targetDataSet.row, 1);
		
		Matrix target = Make.matrix("target", 0.0, targetDataSet.row, 1);
		
		//
		Cal.line(inputDataSet, 2, input);
		Cal.line(targetDataSet, 2, target);
		
		//Affine.cal(w, input, affine);
		Affine.cal(w, input, bias , affine);
		
		Print.matrix(input);
		Print.matrix(w);
		Print.matrix(bias);
		Print.matrix(affine);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Chp03();
	}

}

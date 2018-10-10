package Chapter;

import Pack.*;

public class Chp01 {

	public Chp01() {
		test();
	}
	
	public void test() {
		//iotest
		Matrix inputDataSet = IO.fileMatrix("D:/DataSet/and/train.txt");
		Matrix targetDataSet = IO.fileMatrix("D:/DataSet/and/target.txt");
		
		Print.matrix(inputDataSet);
		Print.matrix(targetDataSet);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Chp01();
	}

}

package Chapter;

import Pack.*;

public class Chp02 {

	public Chp02() {
		test();
	}
	
	public void test() {
		//cal test
		Matrix a = Make.matrix("A", "step", 3, 2);
		Matrix b = Make.matrix("B", "step", 2, 3);
		Matrix c = Make.matrix("C", 0, 3, 3);
		
		Cal.dot(a, b, c);
		Print.matrix(a);Print.matrix(b);Print.matrix(c);
	
		Matrix x = Make.matrix("x", "step", 3, 2);
		Matrix y = Make.matrix("y", "zero", 2, 3);
		Matrix z = Make.matrix("z", "step", 3, 2);
		Matrix line = Make.matrix("line", 0.0, 2, 1);
		
		Cal.plus(x, y, z);
		//Cal.minus(x, y, z);
		//Cal.transpose(x, y);
		//Cal.line(x, 2, line);
		Print.matrix(x);Print.matrix(y);Print.matrix(z);Print.matrix(line);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Chp02();
	}

}

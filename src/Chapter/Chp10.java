package Chapter;

import Pack.*;

public class Chp10 {

	public Chp10() {
		test();
	}
	
	public void test() {
		//multi layer
		Matrix inputDataSet = IO.fileMatrix("D:/DataSet/xor/train.txt");
		Matrix targetDataSet = IO.fileMatrix("D:/DataSet/xor/target.txt");

		//network set
		int hiddensize = 3;
		Matrix input = Make.matrix("input", 0.0, inputDataSet.row, 1);
		//
		Matrix w1 = Make.matrix("weight1", "rand", hiddensize, inputDataSet.row);
		Matrix b1 = Make.matrix("bias1", "rand", hiddensize, 1);
		Matrix dw1 = Make.matrix("dweight1", "zero", hiddensize, inputDataSet.row);
		Matrix db1 = Make.matrix("dbias1", "zero", hiddensize, 1);
		//
		Matrix affine1 = Make.matrix("affine1", 0.0, hiddensize, 1);
		Matrix hidden = Make.matrix("hidden1", 0.0, hiddensize, 1);
		Matrix loss1 = Make.matrix("loss1", 0.0, hiddensize, 1);
		Matrix gradient1 = Make.matrix("gradient1", 0.0, hiddensize, 1);
		Matrix J1 = Make.matrix("J1", 0.0, hiddensize, 1);
		//
		Matrix w2 = Make.matrix("weight2", "rand", targetDataSet.row, hiddensize);
		Matrix b2 = Make.matrix("bias2", "rand", targetDataSet.row, 1);
		Matrix dw2 = Make.matrix("dweight2", "zero", targetDataSet.row, hiddensize);
		Matrix db2 = Make.matrix("dbias2", "zero", targetDataSet.row, 1);
		//
		Matrix affine2 = Make.matrix("affine2", 0.0, targetDataSet.row, 1);
		Matrix output = Make.matrix("output2", 0.0, targetDataSet.row, 1);
		Matrix cost2 = Make.matrix("cost2", 0.0, targetDataSet.row, 1);
		Matrix loss2 = Make.matrix("loss2", 0.0, targetDataSet.row, 1);
		Matrix gradient2 = Make.matrix("gradient2", 0.0, targetDataSet.row, 1);
		Matrix J2 = Make.matrix("J2", 0.0, targetDataSet.row, 1);
		//
		Matrix target = Make.matrix("target", 0.0, targetDataSet.row, 1);

		Matrix avg_cost = Make.matrix("avg_cost", 0.0, targetDataSet.row, 1);
		int epoch = 1000;
		int depth = inputDataSet.col;
		
		for(int epo = 0 ; epo < epoch ; epo++) {
			for(int d = 0 ; d < depth ; d++) {
				//Cal
				//Forward
				Cal.line(inputDataSet, d, input);
				Cal.line(targetDataSet, d, target);

				Affine.cal(w1, input, b1, affine1);
				Activity.cal("sigmoid", affine1, hidden);

				Affine.cal(w2, hidden, b2, affine2);
				Activity.cal("sigmoid", affine2, output);
				//Activity.cal("softmax", output, output);
				
				Cost.cal("meansquare", target, output, cost2);
				//Cost.cal("crossentropy", target, output, cost2);
				//Backward
				
				//output->hidden
				Loss.cal("meansquare", target, output, loss2);
				//Loss.cal("crossentropy", target, output, loss2);
				Gradient.cal("sigmoid", affine2, gradient2);
				Cal.multiply(loss2, gradient2, J2);
				//Cal.multiply(loss2, 1.0, J2);
				
				//dw2
				Cal.transpose(hidden, hidden);
				Cal.dot(J2, hidden, dw2);
				Cal.transpose(hidden, hidden);
				
				//db2
				Cal.multiply(J2, 1.0, db2);
			
				//hidden->input
				Cal.transpose(w2, w2);
				Affine.cal(w2, J2, loss1);
				Cal.transpose(w2, w2);
				
				Gradient.cal("sigmoid", affine1, gradient1);
				Cal.multiply(loss1, gradient1, J1);
				
				//dw1
				Cal.transpose(input, input);
				Cal.dot(J1, input, dw1);
				Cal.transpose(input, input);
				
				//db1
				Cal.multiply(J1, 1.0, db1);
				
				//Update
				//w2 = w1 + dw1
				Cal.plus(w2, dw2, w2);
				
				//b2 = b1 + db1
				Cal.plus(b2, db2, b2);
				
				//w1 = w1 + dw1
				Cal.plus(w1, dw1, w1);
				
				//b1 = b1 + db1
				Cal.plus(b1, db1, b1);
				
				Cal.plus(avg_cost, cost2, avg_cost);
			}
			Cal.divide(avg_cost, depth, avg_cost);
			//Print.matrix(avg_cost);
		}
		
		for(int d = 0 ; d < depth ; d++) {
			System.out.println(d);
			Cal.line(inputDataSet, d, input);
			Cal.line(targetDataSet, d, target);

			Affine.cal(w1, input, b1, affine1);
			//Affine.cal(w1, input, affine1);

			Activity.cal("sigmoid", affine1, hidden);
			//Activity.cal("relu", affine1, hidden);
			//Activity.cal("tanh", affine1, hidden);
			//Activity.cal("line", affine1, hidden);
			
			Affine.cal(w2, hidden, b2, affine2);
			//Affine.cal(w2, hidden, affine2);

			Activity.cal("sigmoid", affine2, output);
			//Activity.cal("softmax", output, output);
			//Activity.cal("relu", affine2, output);
			//Activity.cal("tanh", affine2, output);
			//Activity.cal("line", affine2, output);
			
			//Cost.cal("meansquare", target, output, cost2);
			//Cost.cal("crossentropy", target, output, cost2);
			//Print.matrix(target);
			Print.matrix(output);
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Chp10();
	}

}

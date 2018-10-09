package Pack;

public class Test {

	public Test() {
		Ch1();
		//Ch2();
		//Ch3();
		//Ch4();
		//Ch5();
		//Ch6();
		//Ch7();
		//Ch8();
		//Ch9();
		//Ch10();
	}
	
	public void Ch1() {
		//iotest
		Matrix inputDataSet = IO.fileMatrix("D:/DataSet/and/train.txt");
		Matrix targetDataSet = IO.fileMatrix("D:/DataSet/and/target.txt");
		
		Print.matrix(inputDataSet);
		Print.matrix(targetDataSet);
	}
	
	public void Ch2() {
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
	
	public void Ch3() {
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
	
	public void Ch4() {
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
	
	public void Ch5() {
		//cost test
		Matrix inputDataSet = IO.fileMatrix("D:/DataSet/and/train.txt");
		Matrix targetDataSet = IO.fileMatrix("D:/DataSet/and/target.txt");

		//network set
		Matrix input = Make.matrix("input", 0.0, inputDataSet.row, 1);

		Matrix w = Make.matrix("w", "rand", targetDataSet.row, inputDataSet.row);
		Matrix bias = Make.matrix("bias", "rand", targetDataSet.row, 1);

		Matrix affine = Make.matrix("affine", 0.0, targetDataSet.row, 1);
		Matrix output = Make.matrix("output", 0.0, targetDataSet.row, 1);

		Matrix cost = Make.matrix("cost", 0.0, targetDataSet.row, 1);
		
		Matrix target = Make.matrix("target", 0.0, targetDataSet.row, 1);

		//
		Cal.line(inputDataSet, 2, input);
		Cal.line(targetDataSet, 2, target);

		Affine.cal(w, input, bias, affine);
		Activity.cal("sigmoid", affine, output);
		//Activity.cal("relu", affine, output);
		//Activity.cal("tanh", affine, output);
		//Activity.cal("line", affine, output);

		//Cost.cal("meansquare", target, output, cost);
		Cost.cal("crossentropy", target, output, cost);
		
		Print.matrix(input);
		Print.matrix(w);
		Print.matrix(bias);

		Print.matrix(affine);
		Print.matrix(output);
		
		Print.matrix(cost);
	}
	
	public void Ch6() {
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
	
	public void Ch7() {
		//loss test
		Matrix inputDataSet = IO.fileMatrix("D:/DataSet/and/train.txt");
		Matrix targetDataSet = IO.fileMatrix("D:/DataSet/and/target.txt");

		//network set
		Matrix input = Make.matrix("input", 0.0, inputDataSet.row, 1);
		//
		Matrix w = Make.matrix("w", "rand", targetDataSet.row, inputDataSet.row);
		Matrix bias = Make.matrix("bias", "rand", targetDataSet.row, 1);

		//
		Matrix affine = Make.matrix("affine", 0.0, targetDataSet.row, 1);
		Matrix output = Make.matrix("output", 0.0, targetDataSet.row, 1);
		Matrix cost = Make.matrix("cost", 0.0, targetDataSet.row, 1);
		Matrix loss = Make.matrix("loss", 0.0, targetDataSet.row, 1);
		Matrix gradient = Make.matrix("gradient", 0.0, targetDataSet.row, 1);
		//
		Matrix target = Make.matrix("target", 0.0, targetDataSet.row, 1);

		//Cal
		//Forward
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

		//Backward
		//Loss.cal("meansquare", target, output, loss);
		Loss.cal("crossentropy", target, output, loss);
		
		Gradient.cal("sigmoid", affine, gradient);
		//Gradient.cal("relu", affine, gradient);
		//Gradient.cal("tanh", affine, gradient);
		//Gradient.cal("line", affine, gradient);

		//
		Print.matrix(input);
		Print.matrix(w);
		Print.matrix(bias);

		Print.matrix(affine);
		Print.matrix(output);
		
		Print.matrix(cost);
		Print.matrix(loss);
		Print.matrix(gradient);

	}
	
	public void Ch8() {
		//dw , db test
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

		//Cal
		//Forward
		Cal.line(inputDataSet, 2, input);
		Cal.line(targetDataSet, 2, target);

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
	}
	
	public void Ch9() {
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
	
	public void Ch10() {
		//multi layer
		Matrix inputDataSet = IO.fileMatrix("D:/DataSet/point/train.txt");
		Matrix targetDataSet = IO.fileMatrix("D:/DataSet/point/target.txt");

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
		Matrix cost1 = Make.matrix("cost1", 0.0, hiddensize, 1);
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
		int epoch = 100;
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
				Activity.cal("softmax", output, output);
				
				//Cost.cal("meansquare", target, output, cost2);
				Cost.cal("crossentropy", target, output, cost2);
				//Backward
				
				//output->hidden
				//Loss.cal("meansquare", target, output, loss2);
				Loss.cal("crossentropy", target, output, loss2);
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
	
	public void Ch11() {
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Test();
	}

}

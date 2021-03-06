package Chapter;

import NeuralNetwork.ANN;
import Pack.*;

public class Chp12 {
	public Chp12() {
		test();
	}
	
	public void test() {
		//IO
		Matrix inputDataSet = IO.fileMatrix("D:/DataSet/iris/train.txt");
		Matrix targetDataSet = IO.fileMatrix("D:/DataSet/iris/target.txt");
				
		//Model Set		
		String activity_gradient = "sigmoid";
		
		String cost_loss = "meansquare";
		boolean softmax = false;
		
		boolean bias = true;
		double weight_parameter = 0.05;
		
		String update = "sgd";
		int epoch = 1000;
		
		ModelOption options = Make.modeloption(
			epoch,
			activity_gradient, 
			cost_loss, 
			update, 
			bias, 
			softmax, 
			weight_parameter, 
			inputDataSet.row , 4, 3, targetDataSet.row
		);
		
		Model model = Make.model(options);
		ANN ann = new ANN();
		ann.train(model, inputDataSet, targetDataSet);
		ann.test(model, inputDataSet, targetDataSet);
	}
	
	public static void main(String args[]) {
		new Chp12();
	}
}

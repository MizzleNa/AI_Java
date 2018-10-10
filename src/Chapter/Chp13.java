package Chapter;

import NeuralNetwork.ANN;
import Pack.*;

public class Chp13 {
	public Chp13() {
		test();
	}
	
	public void test() {
		//IO
		Matrix inputDataSet = IO.fileMatrix("D:/DataSet/xor/train.txt");
		Matrix targetDataSet = IO.fileMatrix("D:/DataSet/xor/target.txt");
				
		//Model Set		
		String activity_gradient = "relu";
		
		String cost_loss = "crossentropy";
		boolean softmax = true;
		
		boolean bias = true;
		double weight_parameter = 0.01;
		
		String update = "sgd";
		int epoch = 100;
		
		ModelOption options = Make.modeloption(
			epoch,
			activity_gradient, 
			cost_loss, 
			update, 
			bias, 
			softmax, 
			weight_parameter, 
			inputDataSet.row , 3, targetDataSet.row
		);
		
		Model model = Make.model(options);
		ANN ann = new ANN();
		ann.train(model, inputDataSet, targetDataSet);
		ann.test(model, inputDataSet, targetDataSet);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Chp13();
	}

}

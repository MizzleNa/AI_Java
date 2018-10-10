package Chapter;

import Pack.*;

public class Chp11 {

	public Chp11() {
		test();
	}
	
	public void test() {
		//IO
		Matrix inputDataSet = IO.fileMatrix("D:/DataSet/iris/train.txt");
		Matrix targetDataSet = IO.fileMatrix("D:/DataSet/iris/target.txt");
		
		int inputsize = inputDataSet.row;
		int hiddensize = 3;
		int outputsize = targetDataSet.row;
		
		//Make Layer
		LayerOption inputoption = Make.layeroption("input", inputsize, 1);
		Layer input = Make.layer(inputoption);
		
		LayerOption weight1option = Make.layeroption("weight", hiddensize, inputsize, "sgd", true, 0.1);
		Layer weight1 = Make.layer(weight1option);
		
		LayerOption hiddenoption = Make.layeroption("hidden", hiddensize, 1 , "sigmoid");
		Layer hidden = Make.layer(hiddenoption);
		
		LayerOption weight2option = Make.layeroption("weight", outputsize, hiddensize, "sgd", true, 0.1);
		Layer weight2 = Make.layer(weight2option);
		
		LayerOption outputoption = Make.layeroption("output", outputsize, 1 , "sigmoid" , "meansquare" , false);
		Layer output = Make.layer(outputoption);
		
		LayerOption targetoption = Make.layeroption("target", outputsize, 1);
		Layer target = Make.layer(targetoption);
		
		int depth = inputDataSet.col;
		int epoch = 1000;
		
		for(int epo = 0; epo < epoch ; epo++) {
			for(int dep = 0; dep < depth ; dep++) {
				Cal.line(inputDataSet, dep, input.matrixs.get(0));
				Cal.line(targetDataSet, dep, target.matrixs.get(0));

				//Forward
				Affine.cal(weight1, input, hidden , false);
				Activity.cal(hidden);

				Affine.cal(weight2, hidden, output , false);
				Activity.cal(output);

				//Backward
				Loss.cal(target, output);
				Gradient.cal(output);
				
				Affine.cal(weight2, output, hidden, true);
				Gradient.cal(hidden);

				//Delta
				Delta.cal(hidden, output, weight2);
				Delta.cal(input, hidden, weight1);

				//Update
				Update.cal(weight2);
				Update.cal(weight1);	
			}	
		}
		
		for(int dep = 0; dep < depth ; dep++) {
			Cal.line(inputDataSet, dep, input.matrixs.get(0));
			Cal.line(targetDataSet, dep, target.matrixs.get(0));

			//Forward
			Affine.cal(weight1, input, hidden , false);
			Activity.cal(hidden);

			Affine.cal(weight2, hidden, output , false);
			Activity.cal(output);
			
			Print.matrix(output.matrixs.get(1));
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Chp11();
	}

}

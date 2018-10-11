package NeuralNetwork;

import Pack.*;

public class ANN {
	public ANN() {
		
	}
	
	public void train(Model model, Matrix inputDataSet, Matrix targetDataSet) {
		int depth = inputDataSet.col;

		for(int epo = 0 ; epo < model.options.epoch ; epo++) {
			for(int dep = 0 ; dep < depth ; dep++) {
				//IO
				Cal.line(inputDataSet, dep, model.layers.firstElement().matrixs.get(0));
				Cal.line(targetDataSet, dep, model.layers.lastElement().matrixs.get(0));
				
				//Forward
				for(int m = 0 ; m < model.layers.size() ; m++) {
					if(model.layers.get(m).options.name == "hidden") {
						Affine.cal(
							model.layers.get(m-1), 
							model.layers.get(m-2),
							model.layers.get(m),
							false
						);
						Activity.cal(
							model.layers.get(m)
						);
					}
					
					if(model.layers.get(m).options.name == "output") {
						Affine.cal(
							model.layers.get(m-1), 
							model.layers.get(m-2),
							model.layers.get(m),
							false
						);
						Activity.cal(
							model.layers.get(m)
						);
					}
				}
				
				//Backward
				for(int m = model.layers.size()-1 ; m >= 0 ; m--) {
					if(model.layers.get(m).options.name == "hidden") {
						Affine.cal(
							model.layers.get(m+1), 
							model.layers.get(m+2), 
							model.layers.get(m), 
							true
						);
						
						Gradient.cal(
							model.layers.get(m)
						);
					}
					
					if(model.layers.get(m).options.name == "output") {
						Loss.cal(
							model.layers.get(m+1),
							model.layers.get(m)
						);
						
						Gradient.cal(
							model.layers.get(m)
						);
					}
				}
				
				//Delta
				for(int m = 0 ; m < model.layers.size() ; m++) {
					if(model.layers.get(m).options.name == "weight") {
						Delta.cal(
							model.layers.get(m-1), 
							model.layers.get(m+1), 
							model.layers.get(m)
						);
					}
				}
				
				//Update
				for(int m = 0 ; m < model.layers.size() ; m++) {
					if(model.layers.get(m).options.name == "weight") {
						Update.cal(
							model.layers.get(m)
						);
					}
				}
			}	
		}
	}

	public void test(Model model, Matrix inputDataSet, Matrix targetDataSet) {
		int depth = inputDataSet.col;
		
		for(int dep = 0 ; dep < depth ; dep++) {
			//IO
			Cal.line(inputDataSet, dep, model.layers.firstElement().matrixs.get(0));
			Cal.line(targetDataSet, dep, model.layers.lastElement().matrixs.get(0));
			
			//Forward
			for(int m = 0 ; m < model.layers.size() ; m++) {
				if(model.layers.get(m).options.name == "hidden") {
					Affine.cal(
						model.layers.get(m-1), 
						model.layers.get(m-2),
						model.layers.get(m),
						false
					);
					Activity.cal(
						model.layers.get(m)
					);
				}
				
				if(model.layers.get(m).options.name == "output") {
					Affine.cal(
						model.layers.get(m-1), 
						model.layers.get(m-2),
						model.layers.get(m),
						false
					);
					Activity.cal(
						model.layers.get(m)
					);
				}
			}
			
			Print.matrix(
				model.layers.get(model.layers.size()-2).matrixs.get(1)
			);
		}
	}
}

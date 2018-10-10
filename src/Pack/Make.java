package Pack;

import java.util.Vector;


public class Make {
	public static Matrix matrix(String name, String type , int col ,int row) {
		Matrix matrix = new Matrix();
		matrix.value = new Vector<Double>();
		matrix.col = col;
		matrix.row = row;
		matrix.name = name;

		if(type.equals("one")) {
			for(int size = 0; size < col *row ; size++) {
				matrix.value.add(1.0);
			}
		}else if(type.equals("rand")){
			for(int size = 0; size < col *row ; size++) {
				matrix.value.add(2.0*Math.random()-1.0);
			}
		}else if(type.equals("step")){
			double value = 0.0;
			for(int size = 0; size < col *row ; size++) {
				matrix.value.add(value++);
			}
		}else {
			//기본은 모든 값 0으로 설정
			for(int size = 0; size < col *row ; size++) {
				matrix.value.add(0.0);
			}
		}
		return matrix;
	}
	
	public static Matrix matrix(String name, double value , int col ,int row) {
		Matrix matrix = new Matrix();
		matrix.value = new Vector<Double>();
		matrix.col = col;
		matrix.row = row;
		matrix.name = name;

		for(int size = 0; size < col *row ; size++) {
			matrix.value.add(value);
		}
		return matrix;
	}
	
	public static Layer layer(LayerOption option) {
		Layer layer = new Layer();
		layer.matrixs = new Vector<Matrix>();
		layer.options = option;
		if(option.name.equals("input")) {
			layer.matrixs.add(Make.matrix(option.name, "zero", option.col, option.row));
		}
		if(option.name.equals("weight")) {
			layer.matrixs.add(Make.matrix("w", "rand", option.col, option.row));
			layer.matrixs.add(Make.matrix("dw", "rand", option.col, option.row));
			if(option.bias) {
				layer.matrixs.add(Make.matrix("b", "rand", option.col, 1));
				layer.matrixs.add(Make.matrix("db", "rand", option.col, 1));
			}else {
				layer.matrixs.add(Make.matrix("b", "zero", option.col, 1));
				layer.matrixs.add(Make.matrix("db", "zero", option.col, 1));
			}
		}
		if(option.name.equals("hidden")) {
			layer.matrixs.add(Make.matrix("affine", "zero", option.col, option.row));
			layer.matrixs.add(Make.matrix(option.name, "zero", option.col, option.row));
			layer.matrixs.add(Make.matrix("dft_affine", "zero", option.col, option.row));
			layer.matrixs.add(Make.matrix("gradient", "zero", option.col, option.row));
			layer.matrixs.add(Make.matrix("loss*gradient", "zero", option.col, option.row));
		}
		if(option.name.equals("output")) {
			layer.matrixs.add(Make.matrix("affine", "zero", option.col, option.row));
			layer.matrixs.add(Make.matrix(option.name, "zero", option.col, option.row));
			layer.matrixs.add(Make.matrix("loss", "zero", option.col, option.row));
			layer.matrixs.add(Make.matrix("gradient", "zero", option.col, option.row));
			layer.matrixs.add(Make.matrix("loss*gradient", "zero", option.col, option.row));
			layer.matrixs.add(Make.matrix("cost", "zero", option.col, option.row));
		}
		if(option.name.equals("target")) {
			layer.matrixs.add(Make.matrix(option.name, "zero", option.col, option.row));
		}
		return layer;
	}	

	public static LayerOption layeroption(String name, int col, int row) {
		LayerOption option = new LayerOption();
		option.name = name;
		option.col = col;
		option.row = row;
		
		option.weight_parameter = 0.0;
		option.activity_gradient = null;
		option.cost_loss = null;
		option.softmax = false;
		option.bias = false;
		option.update = null;
		return option;
	}
	
	public static LayerOption layeroption(String name, int col, int row , String activity_gradient , String cost_loss , boolean softmax) {
		LayerOption option = new LayerOption();
		option.name = name;
		option.col = col;
		option.row = row;
		option.activity_gradient = activity_gradient;
		option.cost_loss = cost_loss;
		option.softmax = softmax;
		
		option.weight_parameter = 0.0;
		option.bias = false;
		option.update = null;
		return option;
	}
	
	public static LayerOption layeroption(String name, int col, int row , String activity_gradient ) {
		LayerOption option = new LayerOption();
		option.name = name;
		option.col = col;
		option.row = row;
		option.activity_gradient = activity_gradient;
		
		option.weight_parameter = 0.0;
		option.cost_loss = null;
		option.bias = false;
		option.update = null;
		return option;
	}
	
	public static LayerOption layeroption(String name, int col, int row , String update , boolean bias , double weight_parameter) {
		LayerOption option = new LayerOption();
		option.name = name;
		option.col = col;
		option.row = row;
		option.update = update;
		option.bias = bias;
		option.weight_parameter = weight_parameter;
		
		option.activity_gradient = null;
		option.cost_loss = null;
		option.softmax = false;
		return option;
	}
	
	public static Model model(ModelOption options) {
		Model model = new Model();
		model.layers = new Vector<Layer>();
		model.options = options;
		
		for(int i = 0 ; i < model.options.step.size() ; i++) {
			
		}
		
		return model;
	}


}

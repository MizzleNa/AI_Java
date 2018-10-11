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
		}else if(type.equals("softrand")) {
			for(int size = 0; size < col *row ; size++) {
				matrix.value.add(Math.random());
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
			String randname = "rand";
			
			layer.matrixs.add(Make.matrix("w", randname, option.col, option.row));
			layer.matrixs.add(Make.matrix("dw", "zero", option.col, option.row));
			if(option.bias) {
				layer.matrixs.add(Make.matrix("b", randname, option.col, 1));
				layer.matrixs.add(Make.matrix("db", "zero", option.col, 1));
			}else {
				layer.matrixs.add(Make.matrix("b", "zero", option.col, 1));
				layer.matrixs.add(Make.matrix("db", "zero", option.col, 1));
			}
			layer.matrixs.add(Make.matrix("w_param_1", "zero", option.col, option.row));
			layer.matrixs.add(Make.matrix("b_param_1", "zero", option.col, 1));
			
			layer.matrixs.add(Make.matrix("w_param_2", "zero", option.col, option.row));
			layer.matrixs.add(Make.matrix("b_param_2", "zero", option.col, 1));
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
			if(i == 0) {
				LayerOption inputoption = layeroption("input", model.options.step.get(i), 1);
				model.layers.add(layer(inputoption));
			}else if(i == model.options.step.size()-1) {
				LayerOption weightoption = Make.layeroption("weight", model.options.step.get(i), model.options.step.get(i-1), options.update, options.bias, options.weight_parameter);
				model.layers.add(layer(weightoption));
				LayerOption outputoption = layeroption("output", model.options.step.get(i), 1 , options.activity_gradient , options.cost_loss , options.softmax);
				model.layers.add(layer(outputoption));
			}else {
				LayerOption weightoption = Make.layeroption("weight", model.options.step.get(i), model.options.step.get(i-1), options.update, options.bias, options.weight_parameter);
				model.layers.add(layer(weightoption));
				LayerOption hiddenoption = Make.layeroption("hidden", model.options.step.get(i), 1 , options.activity_gradient);
				model.layers.add(layer(hiddenoption));
			}
		}
		LayerOption targetoption = layeroption("target", model.options.step.lastElement(), 1);
		model.layers.add(layer(targetoption));
		
		return model;
	}
	
	public static ModelOption modeloption(int epoch, String activity_gradient, String cost_loss, String update, boolean bias, boolean softmax, double weight_parameter, int... step) {
		ModelOption option = new ModelOption();
		option.step =  new Vector<Integer>();
		for(Integer st : step) {
			option.step.add(st);
		}
		
		option.epoch = epoch;
		option.activity_gradient = activity_gradient;
		option.cost_loss = cost_loss;
		option.update = update;
		option.bias = bias;
		option.softmax = softmax;
		option.weight_parameter = weight_parameter;

		return option;
	}

}

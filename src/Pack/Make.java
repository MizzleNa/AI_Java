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
			layer.matrixs.add(Make.matrix(option.name, 0, option.col, option.row));
		}
		if(option.name.equals("weight")) {
			layer.matrixs.add(Make.matrix("w", "rand", option.col, option.row));
			layer.matrixs.add(Make.matrix("b", "rand", option.col, option.row));
			layer.matrixs.add(Make.matrix("dw", "rand", option.col, option.row));
			layer.matrixs.add(Make.matrix("db", "rand", option.col, option.row));
		}
		if(option.name.equals("hidden") || option.name.equals("output")) {
			layer.matrixs.add(Make.matrix("affine", 0, option.col, option.row));
			layer.matrixs.add(Make.matrix(option.name, 0, option.col, option.row));
			layer.matrixs.add(Make.matrix("loss", 0, option.col, option.row));
			layer.matrixs.add(Make.matrix("gradient", 0, option.col, option.row));
			layer.matrixs.add(Make.matrix("loss*gradient", 0, option.col, option.row));
		}
		if(option.name.equals("target")) {
			layer.matrixs.add(Make.matrix(option.name, 0, option.col, option.row));
		}
		return layer;
	}	
}

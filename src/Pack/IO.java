package Pack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.Vector;

public class IO {
	
	
	public static Matrix fileMatrix(String filepath) {
		
		Matrix matrix = new Matrix();
		matrix.value = new Vector<Double>();
		matrix.col = 0;
		matrix.row = 0;
		
		try {
			File file = new File(filepath);
			FileReader filereader = new FileReader(file);
			BufferedReader bufreader = new BufferedReader(filereader);
			
			String line = "";
			String[] split = null;
			int col = 0;
			int row = 0;
			
			while((line = bufreader.readLine()) != null) {
				split = line.split("\t");
				row = split.length;
				for(String str : split) {
					matrix.value.add(Double.valueOf(str));
				}
				col++;
			}
			
			bufreader.close();
			
			matrix.col = col;
			matrix.row = row;
			
		}catch(IOException e) {
			e.printStackTrace();
			matrix.col = 0;
			matrix.row = 0;
		}finally {
			
		}
		System.gc();
		
		return matrix;
	}

}

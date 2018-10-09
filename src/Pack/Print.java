package Pack;

public class Print {
	public static void  matrix(Matrix matrix) {
		System.out.println("Name : " + matrix.name);
		System.out.println("Col : " + matrix.col + "\tRow : "+ matrix.row);
		for(int c = 0; c < matrix.col ; c++) {
			for(int r = 0; r < matrix.row ; r++) {
				System.out.print(matrix.value.get(c*matrix.row+r)+"\t");
			}	
			System.out.println();
		}
		System.out.println();
	}
	
	public static void valuePrint(Matrix matrix) {
		System.out.print(" : " + matrix.name + "\t");
		for(int i = 0; i < matrix.col*matrix.row ; i++) {
			System.out.print(matrix.value.get(i)+"\t");
		}
		System.out.println();
	}
}

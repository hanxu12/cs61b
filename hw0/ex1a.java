/**Intended to draw a triange*/
public class draw{
	public static void main(String[] args){
	int row = 1;
	while (row < 6){
		int col = 0;
		while (col < row){
			System.out.print("*");
			col++;
		}
		row++;
		System.out.println("");
	}
}
}
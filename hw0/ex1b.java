public class TriangleDrawer {
	public static void main(String[] args) {
		drawTriangle(10);
	}
	public static void drawTriangle(int N){
/**Method intended to draw a triange*/
	int row = 1;
	while (row < N+1){
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



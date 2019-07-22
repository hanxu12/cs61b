public class MaxValue {
	/** Returns the maximum value from m. */
	public static int max(int[] m) {
		int maxvalue = 0;
		int i = 0;
		while (i < m.length){
			if (m[i] > maxvalue){
				maxvalue = m[i];
			}
			i++;
		}
		return maxvalue;
	}

	public static void main(String[] args) {
		int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
		System.out.println(max(numbers));
	}
}
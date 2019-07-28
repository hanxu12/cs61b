public class NBody{
	public static Double readRadius(String fileaddr){
		In in = new In(fileaddr);

		int firstItemInFile = in.readInt();
		double universeradius= in.readDouble();

		return universeradius;
	}
}
public class NBody{
	public static double readRadius(String fileaddr){
		In in = new In(fileaddr);

		int firstItemInFile = in.readInt();
		double radius= in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets(String fileaddr){
		In in = new In(fileaddr);
		/** Create an array of 5 planets*/
		
		int planetno = in.readInt();
		Planet[] planets = new Planet[planetno];
		double radius= in.readDouble();
		for (int i = 0; i < planets.length; i ++){
			double xpos = in.readDouble();
			double ypos = in.readDouble();
			double xvelocity = in.readDouble();
			double yvelocity = in.readDouble();
			double mass = in.readDouble();
			String name = in.readString();
			Planet p = new Planet(xpos, ypos, xvelocity, yvelocity, mass, name);
			planets[i] = p;
		}
		return planets;
	}

	public static void main(String[] args){
		/**Store the 0th and 1st cmd line args as doubles named T and dt */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		double timenow = 0.0;

		while (timenow < T){
			double[] xForces = new double[5];
			double[] yForces = new double[5];
			for (int i = 0; i < planets.length; i ++){
				double xForce = planets[i].calcNetForceExertedByX(planets);
				double yForce = planets[i].calcNetForceExertedByY(planets);
				xForces[i] = xForce;
				yForces[i] = yForce;
				planets[i].update(dt, xForce, yForce);
			}
			StdDraw.enableDoubleBuffering();
			StdDraw.setXscale(-radius, radius);
			StdDraw.setYscale(-radius, radius);
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (int i = 0; i < planets.length; i++){
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			timenow += dt;
			/**Set the scale so that it matches the radius of the universe. */
			/**Draw image starfield.jpg as the background. */
			StdOut.printf("%d\n", planets.length);
			StdOut.printf("%.2e\n", radius);
			for (int i = 0; i < planets.length; i++) {
				StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
							  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
							  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
			}
		}
	}


}
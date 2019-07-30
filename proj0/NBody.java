public class NBody{
	public static Double readRadius(String fileaddr){
		In in = new In(fileaddr);

		int firstItemInFile = in.readInt();
		double radius= in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets(String fileaddr){
		In in = new In(fileaddr);
		/** Create an array of 5 planets*/
		Planet[] planets = new Planet[5];
		int firstItemInFile = in.readInt();
		double universeradius= in.readDouble();
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
		Double T = Double.parseDouble(args[0]);
		Double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		Double timenow = 0.0;

		while (timenow < T){
			Double[] xForces = new Double[5];
			Double[] yForces = new Double[5];
			for (int i = 0; i < planets.length; i ++){
				Double xForce = planets[i].calcNetForceExertedByX(planets);
				Double yForce = planets[i].calcNetForceExertedByY(planets);
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
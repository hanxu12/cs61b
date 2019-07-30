public class Planet{

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	private double G = 0.0000000000667;
/**Your Planet class should NOT have a main method, 
because we’ll never run the Planet class directly (i.e. we will never do java Planet). 
Also, all methods should be non-static. */

//Constructor 1
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

//Constructor 2
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		return Math.sqrt(dx*dx + dy*dy);
	}

	public double calcForceExertedBy(Planet p){
		double forceexerted = G * this.mass * p.mass / (calcDistance(p) * calcDistance(p));
		return forceexerted;

	}

	public double calcForceExertedByX(Planet p){
		double distx = 0;
//		if (p.xxPos > this.xxPos){
			distx = p.xxPos - this.xxPos;
/**		}
		else{
			distx = this.xxPos - p.xxPos;
		}
*/		return this.calcForceExertedBy(p) * distx / this.calcDistance(p);
	}

	public double calcForceExertedByY(Planet p){
		double disty = 0;
//		if (p.yyPos > this.yyPos){
			disty = p.yyPos - this.yyPos;
/**		}
		else{
			disty = this.yyPos - p.yyPos;
		}
*/		return this.calcForceExertedBy(p) * disty / this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] allplanets){
		double netforcex = 0;
		for (int i = 0; i < allplanets.length; i++){
			if (allplanets[i].equals(this)){
				continue;
			}
			netforcex += calcForceExertedByX(allplanets[i]);
		}
		return netforcex;
	}

	public double calcNetForceExertedByY(Planet[] allplanets){
		double netforcey = 0;
		for (int i = 0; i < allplanets.length; i++){
			if (allplanets[i].equals(this)){
				continue;
			}
			netforcey += calcForceExertedByY(allplanets[i]);
		}
		return netforcey;
	}

	public void update(double dt, double fX, double fY){
		double accelx = fX/this.mass;
		double accely = fY/this.mass;
		this.xxVel += dt*accelx;
		this.yyVel += dt*accely;
		this.xxPos += dt*this.xxVel;
		this.yyPos += dt*this.yyVel;

	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
	
/**
	public static void main(String[] args) {

	}
*/
}




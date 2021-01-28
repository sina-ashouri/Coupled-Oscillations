package shapes;

import java.awt.Graphics;

public class Point {

	public int x,y;
	public String Name;
	
	public Point() {
		this(10,10,"point");
	}

	public Point(int X, int Y){
		this.x = X;
		this.y = Y;
	}
	
	public Point(int X, int Y,String N){
		this.x = X;
		this.y = Y;
		this.Name = N;
	}
	
	public Point getposition(){
		return this;
	}
	
	public double getdistance(Point p){
		return Math.sqrt(Math.pow(p.x - this.x,2.00) + Math.pow(p.y - this.y,2.00));
	}
	
	
	
	public void setposition(int X,int Y){
		this.x = X;
		this.y = Y;
	}
	
}

package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Mass extends Shape {
	
	double M; //  mass

	public Mass(){
		super();
		Allshapes.add(this);
	}
	
	public Mass(double m,int X,int Y){
		this.M = m;
		this.setCenter(new Point(X,Y));
		Allshapes.add(this);
	}
	
	public void setMass(double m){
		this.M = m;
	}
	
	public double getMass(){
		return M;
	}
	
	@Override
	public boolean isIn(Point p) {
		// TODO Auto-generated method stub
		if(5 >= (int)(p.getdistance(new Point(this.getCenter().x , this.getCenter().y ))))
			return true;
		return false;
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Mass";
	}

	@Override
	public void Render(Graphics G) {
		// TODO Auto-generated method stub
		G.setColor(Color.BLUE);
		G.fillOval(this.getCenter().x - 5, this.getCenter().y - 5,10,10);
	}

	@Override
	public int getN() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getL() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getR() {
		// TODO Auto-generated method stub
		return 0;
	}

}

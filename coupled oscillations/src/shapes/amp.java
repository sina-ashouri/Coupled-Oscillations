package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class amp extends Shape {

	public amp(){
		super();
		Allshapes.add(this);
	}
	
	public amp(int X,int Y){
		this.setCenter(new Point(X,Y));
		Allshapes.add(this);
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
		return "AMP";
	}

	@Override
	public void Render(Graphics G) {
		// TODO Auto-generated method stub
		G.setColor(Color.RED);
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

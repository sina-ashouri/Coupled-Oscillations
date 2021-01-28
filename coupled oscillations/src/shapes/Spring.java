package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Spring extends Shape {

	private Point P1,P2; 
	double K; // spring constant
	
	public Spring(){
		super();
		Allshapes.add(this);
	}
	
	public Spring(double k,Point p1,Point p2){
		this.P1 = p1;
		this.P2 = p2;
		this.K = k;
		Allshapes.add(this);
	}
	
	public void reSize(Point p1,Point p2){
		this.P1 = p1;
		this.P2 = p2;
	}
	public void setK(double k){
		this.K = k;
	}
	
	public double getK(){
		return K;
	}
	@Override	
	public boolean isIn(Point p) {
		// TODO Auto-generated method stub
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
		return "Spring";
	}

	@Override
	public void Render(Graphics G) {
		// TODO Auto-generated method stub
		G.setColor(Color.BLACK);
		G.drawLine(P1.x, P1.y, P2.x, P2.y);
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

package shapes;

import java.awt.Color;
import java.awt.Graphics;

import main.*;

public class Rectangle extends Shape {

	int width,height;
	Point P1,P2,P3,P4;
	
	public Rectangle() {
		super();
		Allshapes.add(this);
	}
	
	public Rectangle(int w,int h,int x,int y,Color fc,Color bc,String N){
		super(new Point(x,y) , fc,bc , N);
		this.width = w;
		this.height = h;
		this.setCenter(new Point(x,y));
		this.P1 = new Point(this.getCenter().x - (int)width/2, this.getCenter().y - (int)height/2);
		this.P2 = new Point(this.getCenter().x - (int)width/2, this.getCenter().y + (int)height/2);
		this.P3 = new Point(this.getCenter().x + (int)width/2, this.getCenter().y + (int)height/2);
		this.P4 = new Point(this.getCenter().x + (int)width/2, this.getCenter().y - (int)height/2);
		Allshapes.add(this);
	}
	
	public Rectangle(Rectangle R){
		super(R.getCenter(),R.getfillcolor(),R.getbordercolor(),R.getName());
		this.width = R.getwidth();
		this.height = R.getheight();
		this.P1 = new Point(R.getCenter().x - (int)R.getwidth()/2, R.getCenter().y - (int)R.getheight()/2);
		this.P2 = new Point(R.getCenter().x - (int)R.getwidth()/2, R.getCenter().y + (int)R.getheight()/2);
		this.P3 = new Point(R.getCenter().x + (int)R.getwidth()/2, R.getCenter().y + (int)R.getheight()/2);
		this.P4 = new Point(R.getCenter().x + (int)R.getwidth()/2, R.getCenter().y - (int)R.getheight()/2);
		Allshapes.add(this);
	}
	public int getwidth(){
		return width;
	}
	public int getheight(){
		return height;
	}
	public double TriangleArea(Point P1,Point P2,Point P3){
		return Math.abs((P1.x*P2.y)+(P2.x*P3.y)+(P3.x*P1.y)-(P1.y*P2.x)-(P2.y*P3.x)-(P3.y*P1.x))/2;
	}
	@Override
	public boolean isIn(Point p) {
		// TODO Auto-generated method stub
		if(this.getArea()  == TriangleArea(P1,p,P2) + TriangleArea(P2,p,P3) + TriangleArea(P3,p,P4) + TriangleArea(P4,p,P1))
			return true;
		return false;
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return (double)(height*width);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "rectangle";
	}

	@Override
	public void Render(Graphics G) {
		// TODO Auto-generated method stub
		G.setColor(fillcolor);
		G.fillRect(this.getCenter().x - (int)width/2 , this.getCenter().y - (int)height/2 , width, height);
		G.setColor(bordercolor);
		G.drawRect(this.getCenter().x - (int)width/2 , this.getCenter().y - (int)height/2 , width, height);
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

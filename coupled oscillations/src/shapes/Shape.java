package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import main.*;

public abstract class Shape {
	Point center;
	Color fillcolor;
	Color bordercolor;
	Point target;
	String Name;
	double angle = 0.00;
	boolean select = false;
	public static ArrayList<Shape> Allshapes = new ArrayList<>();
	public Shape() {
		this(new Point(),Color.BLACK,Color.BLACK,"shape");
		Allshapes.add(this);
		Iterator <Shape> itt = Allshapes.iterator();
		//select current shape 
		while(itt.hasNext()){
			itt.next().setSelect(false);
		}
		this.setSelect(true);
		this.setTarget(this.getCenter());
	}
	
	public Shape(Point ce,Color fc,Color bc,String N){
		this.center = ce;
		this.fillcolor = fc;
		this.bordercolor = bc;
		this.Name = N;
		this.angle = 0.00;
		this.select = false;
		Allshapes.add(this);
		this.setSelect(true);
		this.setTarget(this.getCenter());
	}
	
	public Shape(Shape s){
		this.center = s.getCenter();
		this.fillcolor = s.getfillcolor();
		this.bordercolor = s.getbordercolor();
		this.Name = s.getName();
		this.angle = s.getangle();
		this.select = false;
		Allshapes.add(this);
		this.setSelect(true);
		this.setTarget(this.getCenter());
	}
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center=center;
	}
	public String getName() {
		return Name;
	}
	public Color getfillcolor(){
		return fillcolor;
	}
	public Color getbordercolor(){
		return bordercolor;
	}
	public void setfillcolor(Color f){
		this.fillcolor = f;
	}
	public void setbordercolor(Color b){
		this.bordercolor = b;
	}
	public void setName(String name) {
		this.Name=name;
	}
	public String toString() {
		return "Shape:"+Name+center;
	}
	public double getangle(){
		return angle;
	}
	public void setangle(double ang){
		this.angle = ang;
	}
	public void setSelect(boolean s){
		if(s == true)
		{
			this.fillcolor = new Color(fillcolor.getRed(), fillcolor.getGreen(), fillcolor.getBlue(), 250);
		}
		if(s == false)
			this.fillcolor = new Color(fillcolor.getRed(), fillcolor.getGreen(), fillcolor.getBlue(), 100);
		select = s;
	}
	public boolean getSelect(){
		return select;
	}
	public void setTarget(Point p){
		this.target = p;
	}
	public Point getTarget(){
		return target;
	}
	public abstract boolean isIn(Point p); // return true if a point is in a shape
	public abstract double getArea();// calculate Area of shape
	public abstract String getType();// return type of shape
	public abstract void Render(Graphics G);// draw shape
	public abstract int getN();// return number of sides of a polygon
	public abstract int getL();// return side length of a polygon
	public abstract int getR();// return radius of circle  
}

package com.mycompany.a3;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObjects{
	protected double locX;
	protected double locY;
	protected int color;
	protected int size;
	Random rand = new Random();
	
	
	
	public double getX() {
		return this.locX;
	}
	public double getY() {
		return this.locY;
	}
	
	public double getSize() {
		return this.size;
	}
	
	
	public void setColor(int r, int g, int b) {
		color = ColorUtil.rgb(r, g, b);
	}
	
	public void setX(double x) {
		this.locX = x;
	}
	public void setY(double y) {
		this.locY = y;
	}
	 
	public void set_location(double x, double y) {
		setX(x);
		setY(y);
	}
	
	
	
	
	public String toString() {
		// added super.toString() to print location from parent class i.e. Objects.java
		return "loc = " + (Math.round(this.getX() *10.0 )/ 10.0) + ", " + (Math.round(this.getY() *10.0 )/ 10.0) + " "
					+ "Color = [" + ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color)+ "]";
	}
	
	
}
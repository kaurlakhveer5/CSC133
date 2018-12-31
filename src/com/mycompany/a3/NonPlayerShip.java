package com.mycompany.a3;
import java.util.Random;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.NpsMissile;
import com.mycompany.a3.Ship;

public class NonPlayerShip extends Ship{ 
	private int size;
	//private NpsMissileLauncher np_ML;
	private NpsMissile npsMissile; //new added
	
	public NonPlayerShip(double x, double y) {
		super.setSpeed(rand.nextInt(8)+1);
		super.setDirection(rand.nextInt(360)+1);
		super.set_location(x, y);
		super.setColor(200, 100, 100);
		size = (rand.nextInt() % 2 == 0) ? 10 : 20;
		
	}
	
	public void NPFireMissile() {
		npsMissile = new NpsMissile();
		npsMissile.setDirection(this.getDirection());
		npsMissile.setSpeed(this.getSpeed() + 10);
		npsMissile.set_location(this.getX(), this.getY());
	} 
	
	public NpsMissile get_missile_obj() {
		return npsMissile;
	}
	
	@Override
	public void draw(Graphics g, Point2D PCmpRelPrnt) {
		// TODO Auto-generated method stub
		int recX = (int) ((int)PCmpRelPrnt.getX() + this.getX());
		int recY = (int) ((int)PCmpRelPrnt.getY() + this.getY());// - size/2);
		int[] xPoints = {recX, recX+size/2, recX-size/2};
		int[] yPoints = {recY+size/2, recY-size/2, recY-size/2};
		
		int nPoints = 3;
		g.setColor(this.color);
		g.fillPolygon(xPoints,  yPoints,  nPoints);
		
	}
	
	
	
	
	public String toString() {
		return "Non Player Ship: " + super.toString() + " size = " + this.size;
	}

	@Override
	public boolean collidesWith(ICollider otherobject) {
		// TODO Auto-generated method stub
		if(otherobject instanceof SpaceStation || otherobject instanceof NpsMissile)
			return false;
		boolean result = false;
		double thisCenterX= this.locX + (size/2); 
		// find centers
		double thisCenterY= this.locY + (size/2);
		GameObjects obj = ((GameObjects) otherobject);
		double otherCenterX = obj.getX() + (size/2);
		double otherCenterY= obj.getY() + (size/2);
		// find dist between centers (use square, to avoid taking roots)
		double dx = thisCenterX - otherCenterX;
		double dy= thisCenterY - otherCenterY;
		double distBetweenCentersSqr= (dx*dx + dy*dy);// find square of sum of radii
		int thisRadius= size/2;
		double otherRadius= obj.getSize()/2;
		double radiiSqr= (thisRadius*thisRadius+ 2*thisRadius*otherRadius+ otherRadius*otherRadius);
		if (distBetweenCentersSqr<= radiiSqr) { 
			result = true ; 
			}
		return result ;
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub
		
	}
	
}
package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.Ship;
import com.mycompany.a3.SteerableML;

public class PlayerShip extends Ship implements ISteerable{
	private SteerableML mslLaunch;
	private Point2D location;
	public PlayerShip(Point2D location) {
		this.location = location;
		super.set_location(location.getX(), location.getY()); 
		//super.set_location(WHValue.getX()/2, WHValue.getY()/2);
		super.setSpeed(0);
		super.setDirection(0);
		super.setColor(250, 150, 150);
		mslLaunch = new SteerableML(location);
		size = 30;
	}
	
	public SteerableML getLauncher() {
		return this.mslLaunch;
	}
	
	public void accelerate() {
		if(super.getSpeed() == 10)
			System.out.println("Max Speed Reached");
		else {
			super.setSpeed(this.getSpeed() + 2);
			mslLaunch.setSpeed(this.getSpeed());
			System.out.println("Speed Increased");
		}
	}
	
	public void decelerate() {
		if(super.getSpeed() == 0)
			System.out.println("Ship is already stopped");
		else {
			super.setSpeed(this.getSpeed() - 2);
			mslLaunch.setSpeed(this.getSpeed());	
			System.out.println("Speed Decreased");
		}
	}
	
	
	@Override
	public void steerLeft() {
		// TODO Auto-generated method stub
		int curr_dir = super.getDirection();
		if(curr_dir == 0) {
			curr_dir = 350; 	
		}
		else curr_dir = curr_dir - 10;
		super.setDirection(curr_dir);
	}

	@Override
	public void steerRight() {
		// TODO Auto-generated method stub
		int curr_dir = super.getDirection();
		if(curr_dir == 350) {
			curr_dir = 0;
		}
		else curr_dir = curr_dir + 10;
		
		super.setDirection(curr_dir);
	}
	
	@Override
	public void move(int time) {
		double theta = 90 - this.getDirection();
		theta = Math.toRadians(theta);
		double distance = this.getSpeed() * (time/1000.0);
		double deltaX = Math.cos(theta) * distance;
		double deltaY = Math.sin(theta) * distance;

		double newX = super.getX() + deltaX;
		double newY = super.getY() + deltaY;	
		set_location(newX, newY);
		mslLaunch.set_location(newX, newY);
	}
	
	
	
	public void DefaultPosition() {
		this.set_location(location.getX(), location.getY());
	}
	
	@Override
	public void draw(Graphics g, Point2D PCmpRelPrnt) {
		// TODO Auto-generated method stub
		int recX = (int) ((int)PCmpRelPrnt.getX() + this.getX());
		int recY = (int) ((int)PCmpRelPrnt.getY() + this.getY()); //- size/2);
		int[] xPoints = {recX, recX+size/2, recX-size/2};
		int[] yPoints = {recY+size/2, recY-size/2, recY-size/2};
		int nPoints = 3;
		g.setColor(this.color);
		g.drawPolygon(xPoints,  yPoints,  nPoints);
		g.fillArc(recX-size/8, recY+size/2, size/5, size/5, 0, 360);	
		//g.fillArc(recX-size/12, recY+size/3, size/5, size/5, 0, 360);
	}

	
	public String toString() {
		return "Player Ship: " + super.toString() +  "\n Launcher dir = " + this.mslLaunch.getDirection() + 
				"  MLAUNCHER DIRECTION: " + (Math.round(mslLaunch.getX() *10.0 )/ 10.0) + ", " + (Math.round(mslLaunch.getY() *10.0 )/ 10.0);
	}

	@Override
	public boolean collidesWith(ICollider otherobject) {
		// TODO Auto-generated method stub
		boolean result = false;
		if(otherobject instanceof SteerableML || otherobject instanceof PsMissile) 
			return false;
		
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
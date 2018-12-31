package com.mycompany.a3;

import com.codename1.ui.geom.Point2D;

public abstract class Movable extends GameObjects implements IMovable {
	private int speed;
	private int direction;
	
	
	
	// using angle theta to decide the new location of a movable object
	@Override
	public void move(int time) {
		double theta = 90 - direction;
		theta = Math.toRadians(theta);
		double distance = speed * (time/1000.0);
		double deltaX = Math.cos(theta) * distance;
		double deltaY = Math.sin(theta) * distance;

		double newX = super.getX() + deltaX;
		double newY = super.getY() + deltaY;	
		set_location(newX, newY);
	}
	
	//getter for speed
	public int getSpeed() {
		return this.speed;
	}
	
	//getter for direction
	public int getDirection() {
		return this.direction;
	}

	//setter for speed
	public void setSpeed(int x) {
		this.speed = x;
	}
	
	public void setDirection(int x) {
		this.direction = x;
	}
	

	//randomly select location if no parameter is passed
		public void set_location() {
			locX = rand.nextDouble() + rand.nextInt(1024)+1;
			locY = rand.nextDouble() + rand.nextInt(768)+1;
		}
		
		//set location based upon passed values
		
	

	public String toString() {
		return super.toString() + " Speed=" + this.speed + " Direction=" + this.direction + " ";
	}
	
}
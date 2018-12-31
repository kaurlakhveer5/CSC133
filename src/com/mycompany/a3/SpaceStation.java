package com.mycompany.a3;
import java.util.Random;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class SpaceStation extends FixedObject implements IDrawable, ICollider{
	Random rand = new Random();
	private int blink_rate;
//	private int blink;

	public SpaceStation(double x, double y) {
		// TODO Auto-generated constructor stub
		//super.set_location(spaceStationLoc.getX(), spaceStationLoc.getY());
		super.set_location(x, y);
		super.setColor(0, 0, 350); // navy blue 
		blink_rate = rand.nextInt(2)+1;
		size = 20;
	}
	
	public int getblink() {
		return rand.nextInt(2)+1;
	}
	
	public void setblink(int blink_rate) {
		 this.blink_rate = blink_rate;
	}
	
	
/*
	public int getBlinkRate() {
		return this.blink_rate;
	}
	
	*/
	
	@Override
	public void draw(Graphics g, Point2D PCmpRelPrnt) {
		// TODO Auto-generated method stub
		int recX = (int) ((int)PCmpRelPrnt.getX() + this.getX()-size/2);
		int recY = (int) ((int)PCmpRelPrnt.getY() + this.getY() - size/2);
		if(blink_rate % 2 == 0)
			g.setColor(this.color);
		else 
			g.setColor(255);
		g.fillArc(recX,  recY, 2*size, 2*size, 0, 360);
		
	}
	

	public String toString() {
		return "Station " + super.toString() + " Blink Rate: "+ this.blink_rate;
	}

	@Override
	public boolean collidesWith(ICollider otherobject) {
		return false;

	}

	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub
		
	}
	

	
	
	
	
	
	
}
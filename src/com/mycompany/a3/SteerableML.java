package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class SteerableML extends Movable implements ISteerable, IDrawable, ICollider{
	private PsMissile ps_missile;
	
	public SteerableML(Point2D location) {
		this.set_location(location.getX(), location.getY()); 
		super.setSpeed(0);
		super.setDirection(0);	
		size = 20;
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
		if(curr_dir == 350)
			curr_dir = 0;
		else curr_dir = curr_dir + 10;
		super.setDirection(curr_dir);
	}
	
	public void FireMissile() {
		ps_missile = new PsMissile();
		ps_missile.set_location(this.getX(), this.getY());
		ps_missile.setDirection(this.getDirection());
		ps_missile.setSpeed(this.getSpeed() + 10);
	}
	
	//return the missile object to use in GameWorld
	public PsMissile get_missile() {
		return this.ps_missile;
	}
	
	public void move(int time) {
		// Move of Missile Launcher is controlled in Player Ship 
	}
	

	@Override
	public void draw(Graphics g, Point2D PCmpRelPrnt) {
		// TODO Auto-generated method stub
		int recX = (int) ((int)PCmpRelPrnt.getX() + this.getX() - size/6);
		int recY = (int) ((int)PCmpRelPrnt.getY() + this.getY() - size/4);
		g.setColor(ColorUtil.GREEN);
		//g.drawRect(recX-size/8, recY-size, size/4, size/3*2);
		g.drawRect(recX, recY, size/3, size);
	}	
	
	public String toString() {
		return "ML LAUNCHER: " + super.toString();
	}

	@Override
	public boolean collidesWith(ICollider otherobject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub
		
	}

	
	
}
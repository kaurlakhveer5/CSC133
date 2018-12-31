package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.Movable;

public abstract class Missile extends Movable implements IDrawable, ICollider{
	private int fuel = 15;
	Point2D myPoint;
	int game_time;
	int size = 5;

	public void move(int time) {	// as move function is invoked, it reduces fuel level by 1 unit
		this.fuel--;
		this.game_time = time;
		super.move(time);
		//super.missileMove(myPoint);
	}
	
	
	public int getFuel(){
		return this.fuel;
	}
	
	public void setFuel() {
		this.fuel = 15;  
	}

	@Override
	public void draw(Graphics g, Point2D PCmpRelPrnt) {
		// TODO Auto-generated method stub
		int recX = (int) ((int)PCmpRelPrnt.getX() + this.getX()- size/2);
		int recY = (int) ((int)PCmpRelPrnt.getY() + this.getY() - size/2);
		g.setColor(ColorUtil.BLUE);
		g.fillRect(recX, recY, size*3/4, size*5/4);
		
		
	}
	
	
	
	
}
package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class NpsMissile extends Missile {
	int size = 5;
	public NpsMissile(){
		this.setColor(210, 110, 110);
	}
	
	
	public String toString() {
		return "NPS's Missile: " + super.toString() + " fuel= " + super.getFuel(); 
	}

	@Override
	public void draw(Graphics g, Point2D PCmpRelPrnt) {
		// TODO Auto-generated method stub
		int recX = (int) ((int)PCmpRelPrnt.getX() + this.getX()- size/2);
		int recY = (int) ((int)PCmpRelPrnt.getY() + this.getY() - size/2);
		g.setColor(ColorUtil.BLUE);
		g.fillRect(recX, recY, size*3/4, size*5/4);
		
		
	}

	@Override
	public boolean collidesWith(ICollider otherobject) {
		// TODO Auto-generated method stub
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

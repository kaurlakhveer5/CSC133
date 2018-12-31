package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class PsMissile extends Missile implements ISelectable{
	private boolean selected;
	int size = 6;
	public PsMissile(){
		super.setColor(57, 255, 20);
	}
	
	public String toString() {
		return "PS's Missile: " + super.toString() + " fuel= " + super.getFuel(); 
	}

	public void draw(Graphics g, Point2D PCmpRelPrnt) {
		// TODO Auto-generated method stub
		int recX = (int) ((int)PCmpRelPrnt.getX() + this.getX()- size/2);
		int recY = (int) ((int)PCmpRelPrnt.getY() + this.getY() - size/2);
		if(selected) g.setColor(ColorUtil.BLUE);
		else g.setColor(color);
		g.fillRect(recX, recY, size*3/4, size*5/4);
		
		
	}
	
	
	@Override
	public boolean collidesWith(ICollider otherobject) {
		// TODO Auto-generated method stub
		if(otherobject instanceof SteerableML || otherobject instanceof PlayerShip)
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
		color = (ColorUtil.rgb(256, 256, 256));
	}

	@Override
	public void setSelected(boolean yesNo) {
		// TODO Auto-generated method stub
		selected = yesNo;
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return selected;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		int px = pPtrRelPrnt.getX(); // pointer location relative to
		int py = pPtrRelPrnt.getY(); // parents origin
		double xLoc = pCmpRelPrnt.getX()+ this.getX();// shape location relative
		double yLoc = pCmpRelPrnt.getY()+ this.getY();// to parents origin
		if ( (px >= xLoc) && (px <= xLoc+size) 
			&& (py >= yLoc) && (py <= yLoc+size) )
			return true; 
		else 
			return false;
	}
	
	public boolean contains(Point pPtrRelPrnt) 
	{
		double deltaX = pPtrRelPrnt.getX() - this.getX();
		double deltaY = pPtrRelPrnt.getY() - this.getY();
		double dist = (deltaX * deltaX) + (deltaY * deltaY);
		if(dist <= size*size)
		{
			return true;
		}
		return false;
	}
	
	
}
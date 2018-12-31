package com.mycompany.a3;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.Movable;
import com.codename1.ui.geom.*;

public class Aestroids extends Movable implements IDrawable, ICollider, ISelectable{
	private int size;
	Random rand = new Random();
	private boolean selected;
	
	public Aestroids(double x, double y) {
		this.size = rand.nextInt(30-6) + 6;
		super.setSpeed(rand.nextInt(10)+1);
		super.setDirection(rand.nextInt(360)+1);
		super.set_location(x,y);
		this.setColor(360, 115, 0);
		
	}
	
	public String toString() {
		return "Asteroids: " + super.toString() + " size = " + this.size;
	}


	@Override
	public void draw(Graphics g, Point2D PCmpRelPrnt) {
		// TODO Auto-generated method stub
		int recX = (int) ((int)PCmpRelPrnt.getX() + this.getX()-size/2);
		int recY = (int) ((int)PCmpRelPrnt.getY() + this.getY() - size/2);
		if(isSelected()) {
			g.setColor(ColorUtil.BLUE);
			System.out.println("Still Selected");
		}
		else
			g.setColor(this.color);
		g.fillRect(recX, recY, size, size);
	}

	@Override
	public boolean collidesWith(ICollider otherobject) {
		// TODO Auto-generated method stub
		if(otherobject instanceof SpaceStation)
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
		// change my color by generating three random color
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
package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.geom.Point2D;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	
	public MapView(GameWorld gw) {
		this.gw = gw;
		this.getStyle().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		repaint();

	}
	
		public void pointerPressed(int x, int y) 
		{
			x = x - getParent().getAbsoluteX() - getX();
			y = y - getParent().getAbsoluteY() - getY();
			Point pPtrRelPrnt = new Point(x, y);
			Point pCmpRelPrnt = new Point(getX(), getY());
			GameObjects currObj;
			IIterator iterator = gw.getGameObjects().getIterator();
	
			while(iterator.hasNext())
			{
				currObj = (GameObjects) iterator.getNext();
				if(currObj instanceof ISelectable)
				{
					ISelectable s = (ISelectable) currObj;
					
					if(s.contains(pPtrRelPrnt))
					{
						gw.setSelectedObj(s);
						repaint();
						return;
					}
				}
			}
			gw.setSelectedObj(null);

			
			
		}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		IIterator gameIterator2 = gw.getGameObjects().getIterator();
		GameObjects currObj;
		while(gameIterator2.hasNext()) {
			currObj = (GameObjects) gameIterator2.getNext();
			Point2D pCmpRelPrnt = new Point2D(getX(), getY());
			if(currObj instanceof IDrawable) {
				((IDrawable) currObj).draw(g, pCmpRelPrnt );
			}
		}
	}
	
	
	
}
	
	
	
	



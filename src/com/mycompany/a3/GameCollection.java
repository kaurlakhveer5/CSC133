package com.mycompany.a3;
import java.util.Vector;

public class GameCollection implements ICollection{
	private Vector theCollection ;
	private IIterator itr;
	
		public GameCollection() {
			theCollection = new Vector(); 
		}
		
		public void add(GameObjects newObject) {
			theCollection.addElement(newObject);
		}
		
		public IIterator getIterator() {
			return new SpaceVectorIterator();
		}
		
		//To support contains in Game-World
		public boolean contains(GameObjects obj) {
			if(theCollection.contains(obj))
				return true;
			else return false;
		}
		
		
		
		//To Support Contains in GameWorld
		public void remove(GameObjects obj) {
			theCollection.remove(obj);
		}
		
		//To support size in Game-World
		public int size() {
			return theCollection.size();
		}
		
		//Continue
		private class SpaceVectorIterator implements IIterator {
			private int currElementIndex;
			
			public SpaceVectorIterator() {
				currElementIndex = -1;
			}
			
			public boolean hasNext() {
				if (theCollection.size ( ) <= 0)  
					return false;
				if (currElementIndex == theCollection.size() - 1 )
					return false;
				return true;
				}
			
			public Object getNext () {
				currElementIndex ++ ;
				return (theCollection.elementAt(currElementIndex));
			} 
			
		}
}



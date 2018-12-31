package com.mycompany.a3;

import com.mycompany.a3.GameObjects;

public class FixedObject extends GameObjects{
	private int ID = 0;
	private static int count = 0;
	
	public FixedObject() {
		locX = 0; //rand.nextDouble() + rand.nextInt(1024)+1;
		locY = 0; //rand.nextDouble() + rand.nextInt(768)+1;
		ID = ++count;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public String toString() {
		return super.toString() + " ID = " + this.ID;
	}




	
}
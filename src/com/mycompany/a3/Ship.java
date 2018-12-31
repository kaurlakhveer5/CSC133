package com.mycompany.a3;

public abstract class Ship extends Movable implements IDrawable, ICollider{
	private int missile = 10;
	
	//private int direction;
	
	public int get_missile() {
		return this.missile;
	}
	
	public void loadMissile() {
		this.missile = 10;
	}
	
	public void dec_missile() {
		if(missile > 0)
			this.missile--;
		else 
			System.out.println("Out of Missiles");
	}
	public String toString() {
		return super.toString() + " Missiles: " + this.missile + " ";
	}
}
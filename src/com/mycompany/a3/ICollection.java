package com.mycompany.a3;

public interface ICollection {
	
	public void add(GameObjects newObject);
	public IIterator getIterator();
	public void remove(GameObjects objects);
}

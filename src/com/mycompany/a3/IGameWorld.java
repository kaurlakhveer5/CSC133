package com.mycompany.a3;

public interface IGameWorld {
	//specifications here for all Game World Methods
	int getPlayerScore();
	//get the other game attributes
	
	int getMissileCount();
	
	int getElapsedTime();
	
	boolean getSound();
	
	int getLives();

	GameCollection getGameObjects();
}

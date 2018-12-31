package com.mycompany.a3;

import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld{
	private GameWorld gw;
	
	public GameWorldProxy(GameWorld gw) {
		this.gw = gw;
	}
	
	public int getPlayerScore() {
		return gw.getPlayerScore();
	}

	@Override
	public int getMissileCount() {
		// TODO Auto-generated method stub
		return gw.getMissileCount() ;
	}

	@Override
	public int getElapsedTime() {
		// TODO Auto-generated method stub
		return gw.getElapsedTime();
	}

	@Override
	public boolean getSound() {
		// TODO Auto-generated method stub
		return gw.getSound();
	}

	@Override
	public int getLives() {
		// TODO Auto-generated method stub
		return gw.getLives();
	}

	@Override
	public GameCollection getGameObjects() {
		// TODO Auto-generated method stub
		return gw.getGameObjects();
	}
	
}

package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FireMissile extends Command{
	private GameWorld gw;
	public FireMissile(GameWorld gw) {
		super("Fire Missile");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getKeyEvent() != -1) 
			gw.fireMissile();
		
	}
}

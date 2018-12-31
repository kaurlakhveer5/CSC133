package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ReloadMissile extends Command{
private GameWorld gw;
	
	public ReloadMissile(GameWorld gw) {
		super("Reload Missile");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getKeyEvent() != -1) 
			gw.newMissileSupply();
		}
}

package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class DefaultPosition extends Command{
	private GameWorld gw;
	
	public DefaultPosition(GameWorld gw) {
		super("Jump back to the Center");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getKeyEvent() != -1) 
			gw.DefaultPosition();
		}

}

package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Refuel_Command extends Command {

	private GameWorld gw;
	public Refuel_Command(GameWorld gw) {
		super("refuel");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getKeyEvent() != -1) 
			gw.refuel();
	}

}

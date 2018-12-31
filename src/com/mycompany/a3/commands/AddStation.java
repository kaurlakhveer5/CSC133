package com.mycompany.a3.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AddStation extends Command {
	private GameWorld gw;
	public AddStation(GameWorld gw) {
		super("Add Space Station");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getKeyEvent() != -1) 
			gw.addSpaceStation();
		
	}
}




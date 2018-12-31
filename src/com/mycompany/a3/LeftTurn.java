package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LeftTurn extends Command{
	private GameWorld gw;
	public LeftTurn(GameWorld gw) {
		super("Left Turn");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getKeyEvent() != -1) 
			gw.turnLeft();
		}
}

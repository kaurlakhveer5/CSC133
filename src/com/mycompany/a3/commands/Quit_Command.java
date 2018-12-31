package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class Quit_Command extends Command {
	private GameWorld gw;
	private boolean gameOver;
	public Quit_Command(GameWorld gw) {
		super("Quit");
		this.gw = gw;
		this.gameOver = gw.getQuitGame();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getKeyEvent() != -1) {
			if (e.getSource().toString().equals("Quit") || e.getKeyEvent() == 81) {  
				if(gameOver == true)
					System.exit(0);
				else
					gw.quit();
			}
		}	
	}
}

package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class Restart extends Command{
		GameWorld gameWorld;
		public Restart(GameWorld gw)
		{
			super("Restart Game");
			gameWorld = gw;
		}
		
		public void actionPerformed(ActionEvent event)
		{ 
			gameWorld.init();	
			gameWorld.set_play();
			gameWorld.setSound(true);
				
		}
}

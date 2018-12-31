package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

	public class TurnLauncherLeft extends Command{
		private GameWorld gw;
		
		public TurnLauncherLeft(GameWorld gw) {
			super("Turn Launcher AntiClockwise");
			this.gw = gw;
		}
		public void actionPerformed(ActionEvent e) {
			if (e.getKeyEvent() != -1) 
				gw.turn_Launcher_Left();
			}
}
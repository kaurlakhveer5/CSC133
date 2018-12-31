
package com.mycompany.a3.commands;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class Play_pause_Command extends Command{
	private GameWorld gw;
	Button b;
	
	public Play_pause_Command(GameWorld gw, Button b) {
		super("Pause");
		this.gw = gw;
		this.b = b;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getKeyEvent() != -1) {
			if(gw.isPlaying()) {
				gw.pause();
				b.setText("Play");
			}
			else {
				gw.play();
				b.setText("Pause");
			}
		
		}
	}
}
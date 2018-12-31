package com.mycompany.a3.commands;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SoundCommand extends Command {
	private GameWorld gw;
	 // private SideMenuItemCheckFormUsingCommand myForm;
	  public SoundCommand (GameWorld gw){
	    super("Sound"); //do not forget to set the "command name
	    this.gw  = gw;
	  }
	@Override
	public void actionPerformed(ActionEvent evt){
	  if (((CheckBox)evt.getComponent()).isSelected()) {//getComponent() returns the component 					       
			gw.setSound(true);
	  }
	  else
	    gw.setSound(false);
	  }//actionPerformed
}// SideMenuItemCheck class

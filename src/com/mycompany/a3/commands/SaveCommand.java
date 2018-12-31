package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SaveCommand extends Command{
	public SaveCommand() 
	{
		super("Save");
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Save Command Invoked.");
	}
}

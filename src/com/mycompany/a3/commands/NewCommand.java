package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class NewCommand extends Command{
	public NewCommand() 
	{
		super("New");
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("New Command Invoked");
	}
}

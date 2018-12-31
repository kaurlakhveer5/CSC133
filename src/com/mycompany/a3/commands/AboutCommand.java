package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command{
	public AboutCommand() 
	{
		super("About");
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Dialog.show("Lakhveer's Project 3", "Csc133", "This is a very Fun Project but extremely hard at the same time", null);
	}
}

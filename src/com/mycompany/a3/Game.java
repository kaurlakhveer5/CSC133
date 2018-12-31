package com.mycompany.a3;
import java.util.Iterator;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import com.mycompany.a3.commands.AboutCommand;
import com.mycompany.a3.commands.Accelerate;
import com.mycompany.a3.commands.AddAestroidCommand;
import com.mycompany.a3.commands.AddNPSCommand;
import com.mycompany.a3.commands.AddPlayerShip;
import com.mycompany.a3.commands.AddStation;
import com.mycompany.a3.commands.NewCommand;
import com.mycompany.a3.commands.Play_pause_Command;
import com.mycompany.a3.commands.Quit_Command;
import com.mycompany.a3.commands.SaveCommand;
import com.mycompany.a3.commands.SoundCommand;
import com.mycompany.a3.commands.UndoCommand;
//import com.mycompany.a2.commands.NewCommand;
//import com.mycompany.a2.commands.SaveCommand;
//import com.mycompany.a2.commands.UndoCommand;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
//import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Point2D;


public class Game extends Form implements Runnable{
	private GameWorld gw;
	private static MapView mv;
	private PointsView pv;
	protected Point2D WHValue;
	
	//Buttons
	Button add_aestroid;
	Button add_nps;
	Button add_station;
	Button add_ps;
	Button accelerate;
	Button decelerate; 
	Button left_turn;
	Button right_turn;
	Button turn_launcher_left;
	Button turn_launcher_right;
	Button fire_missile;
	Button def_position;
	Button quit_button;
	Button new_button;
	Button save_button;
	Button undo_button;
	Button right_quit_button;
	Button about_button;
	
	//Commands
	AddAestroidCommand add_aestroid_command;
	AddNPSCommand add_NPS_command;
	AddStation add_station_command;
	AddPlayerShip add_ps_command;
	Accelerate acc;
	Decelerate dece;
	LeftTurn lt_command;
	RightTurn rt_command;
	TurnLauncherLeft tll;
	TurnLauncherRight tlr;
	FireMissile firemissileCommand;
	DefaultPosition defPos;
	ReloadMissile reloadMissileCommand;
	Toolbar myToolbar;
	CheckBox SoundCheck;
	
	
	public Game() {
		gw = new GameWorld();
		gw.init();
		mv = new MapView(gw);
		pv = new PointsView();
		setLayout(new BorderLayout());
		UITimer timer = new UITimer(this);
		
		timer.schedule(100, true, this);
		gw.addObserver(mv);
		gw.addObserver(pv);
	
		
		WHValue = new Point2D(mv.getWidth(), mv.getHeight());
		
		// code here to create menus, create Command objects for each command,
		Container myContainer = new Container();
		myToolbar = new Toolbar(); 
		setToolbar(myToolbar);				
		myToolbar.setTitleComponent(new Label("Aestroid Game"));
		Label title = new Label("Menu");
		myToolbar.addComponentToSideMenu(title);
		//myContainer.);
		myContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		//Adding Aestroid
		add_aestroid = new Button("Add Aestroid");
		add_aestroid.getAllStyles().setBgTransparency(255);
		add_aestroid.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		add_aestroid.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		add_aestroid.getAllStyles().setPadding(TOP, 2);
		add_aestroid.getAllStyles().setPadding(BOTTOM, 2);
		add_aestroid.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		myContainer.add(add_aestroid);
		
		add_aestroid_command = new AddAestroidCommand(gw); 
		add_aestroid.setCommand(add_aestroid_command);
		addKeyListener('a', add_aestroid_command);
		myToolbar.addCommandToSideMenu(add_aestroid_command);
		
		//Add Non Player Ship commands	
		myContainer.add(add_nps = new Button("Add NPS"));
		add_nps.getAllStyles().setBgTransparency(255);
		add_nps.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		add_nps.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		add_nps.getAllStyles().setPadding(TOP, 2);
		add_nps.getAllStyles().setPadding(BOTTOM, 2);
		add_nps.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		add_NPS_command = new AddNPSCommand(gw); 
		add_nps.setCommand(add_NPS_command);
		addKeyListener('y', add_NPS_command);
		
		//Add Station Commands
		myContainer.add(add_station = new Button("Add Station"));
		add_station.getAllStyles().setBgTransparency(255);
		add_station.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		add_station.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		add_station.getAllStyles().setPadding(TOP, 2);
		add_station.getAllStyles().setPadding(BOTTOM, 2);
		add_station.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		add_station_command = new AddStation(gw);
		add_station.setCommand(add_station_command);
		addKeyListener('b', add_station_command); //do the same thing through keyboard
		myToolbar.addCommandToSideMenu(add_station_command); //to add in the menu
		
		
		
		//Add Playership Commands
		myContainer.add(add_ps = new Button("Add Player Ship"));
		add_ps.getAllStyles().setBgTransparency(255);
		add_ps.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		add_ps.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		add_ps.getAllStyles().setPadding(TOP, 2);
		add_ps.getAllStyles().setPadding(BOTTOM, 2);
		add_ps.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		add_ps_command = new AddPlayerShip(gw);
		add_ps.setCommand(add_ps_command);
		addKeyListener('s', add_ps_command);
		myToolbar.addCommandToSideMenu(add_ps_command);
		
		//Accelerate PlayerShip	
		myContainer.add(accelerate = new Button("Accelerate"));
		accelerate.getAllStyles().setBgTransparency(255);
		accelerate.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		accelerate.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		accelerate.getAllStyles().setPadding(TOP, 2);
		accelerate.getAllStyles().setPadding(BOTTOM, 2);
		accelerate.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		acc = new Accelerate(gw); 
		accelerate.setCommand(acc);
		addKeyListener(-91, acc);
		
		//Decelerate PlayerShip	
		myContainer.add(decelerate = new Button("Decelerate"));
		decelerate.getAllStyles().setBgTransparency(255);
		decelerate.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		decelerate.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		decelerate.getAllStyles().setPadding(TOP, 2);
		decelerate.getAllStyles().setPadding(BOTTOM, 2);
		decelerate.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		dece = new Decelerate(gw);
		decelerate.setCommand(dece);
		addKeyListener(-92, dece);
		
		
		
		//Turn PS Left
		myContainer.add(left_turn = new Button("Left Turn"));
		left_turn.getAllStyles().setBgTransparency(255);
		left_turn.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		left_turn.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		left_turn.getAllStyles().setPadding(TOP, 2);
		left_turn.getAllStyles().setPadding(BOTTOM, 2);
		left_turn.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		lt_command = new LeftTurn(gw);
		left_turn.setCommand(lt_command);
		addKeyListener(-93, lt_command);
		
		
		
		//Turn PS Right
		myContainer.add(right_turn = new Button("Right Turn"));
		right_turn.getAllStyles().setBgTransparency(255);
		right_turn.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		right_turn.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		right_turn.getAllStyles().setPadding(TOP, 2);
		right_turn.getAllStyles().setPadding(BOTTOM, 2);
		right_turn.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		rt_command = new RightTurn(gw);
		right_turn.setCommand(rt_command);
		addKeyListener(-94, rt_command);
		
		
		//Turn Launcher Left	
		myContainer.add(turn_launcher_left = new Button("Turn Launcher Anticlockwise"));
		turn_launcher_left.getAllStyles().setBgTransparency(255);
		turn_launcher_left.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		turn_launcher_left.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		turn_launcher_left.getAllStyles().setPadding(TOP, 2);
		turn_launcher_left.getAllStyles().setPadding(BOTTOM, 2);
		turn_launcher_left.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		tll = new TurnLauncherLeft(gw);
		turn_launcher_left.setCommand(tll);
		addKeyListener(44, tll);
		
		//Turn Launcher Right
		myContainer.add(turn_launcher_right = new Button("Turn Launcher Clockwise"));
		turn_launcher_right.getAllStyles().setBgTransparency(255);
		turn_launcher_right.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		turn_launcher_right.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		turn_launcher_right.getAllStyles().setPadding(TOP, 2);
		turn_launcher_right.getAllStyles().setPadding(BOTTOM, 2);
		turn_launcher_right.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		tlr = new TurnLauncherRight(gw);
		turn_launcher_right.setCommand(tlr);
		addKeyListener(46, tlr);

		//Fire a Missile
		myContainer.add(fire_missile = new Button("Fire Missile"));
		fire_missile.getAllStyles().setBgTransparency(255);
		fire_missile.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		fire_missile.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		fire_missile.getAllStyles().setPadding(TOP, 2);
		fire_missile.getAllStyles().setPadding(BOTTOM, 2);
		fire_missile.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		firemissileCommand = new FireMissile(gw);
		fire_missile.setCommand(firemissileCommand);	
		addKeyListener(-90, firemissileCommand);
		
		//NPS Fire missile
		//FireNPMissile fireNPmissileCommand = new FireNPMissile(gw);	
		//addKeyListener('L', fireNPmissileCommand);
		
		
		//Default Position
		myContainer.add(def_position = new Button("Default Position"));
		def_position.getAllStyles().setBgTransparency(255);
		def_position.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		def_position.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		def_position.getAllStyles().setPadding(TOP, 2);
		def_position.getAllStyles().setPadding(BOTTOM, 2);
		def_position.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		defPos = new DefaultPosition(gw);
		def_position.setCommand(defPos);
		addKeyListener('j', defPos);		
		
		//Reload Missile
		Button reload_missile;
		myContainer.add(reload_missile = new Button("Reload Missiles"));
		reload_missile.getAllStyles().setBgTransparency(255);
		reload_missile.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		reload_missile.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		reload_missile.getAllStyles().setPadding(TOP, 2);
		reload_missile.getAllStyles().setPadding(BOTTOM, 2);
		reload_missile.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		reloadMissileCommand = new ReloadMissile(gw);
		reload_missile.setCommand(reloadMissileCommand);
		addKeyListener('n', reloadMissileCommand);
		myToolbar.addCommandToSideMenu(reloadMissileCommand);
		
		
		myContainer.setScrollableY(true);
		
		
		
		//Sound
		SoundCheck = new CheckBox("Sound");
		SoundCheck.getAllStyles().setBgTransparency(255);
		SoundCheck.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		SoundCheck.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		SoundCheck.getAllStyles().setPadding(TOP, 2);
		SoundCheck.getAllStyles().setPadding(BOTTOM, 2);
		SoundCheck.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		SoundCommand soundCmd = new SoundCommand(gw);
		SoundCheck.setCommand(soundCmd);
		SoundCheck.setSelected(true);
		myToolbar.addComponentToSideMenu(SoundCheck);

		//Refill the Missile
		Button refuel_button;
		myContainer.add(refuel_button = new Button("Refuel"));
		refuel_button.getAllStyles().setBgTransparency(255);
		refuel_button.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		refuel_button.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		refuel_button.getAllStyles().setPadding(TOP, 2);
		refuel_button.getAllStyles().setPadding(BOTTOM, 2);
		refuel_button.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		Refuel_Command refuel_command = new Refuel_Command(gw);
		refuel_button.setCommand(refuel_command);
		addKeyListener('P',refuel_command);
		myToolbar.addCommandToSideMenu(refuel_command);
		
		
		//Play/Pause
		Button play_pause_button;
		myContainer.add(play_pause_button = new Button("Play"));
		play_pause_button.getAllStyles().setBgTransparency(255);
		play_pause_button.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
		play_pause_button.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		play_pause_button.getAllStyles().setPadding(TOP, 2);
		play_pause_button.getAllStyles().setPadding(BOTTOM, 2);
		play_pause_button.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		Play_pause_Command play_pause_command = new Play_pause_Command(gw, play_pause_button);
		play_pause_button.setCommand(play_pause_command);
		addKeyListener('z',play_pause_command);
		myToolbar.addCommandToSideMenu(play_pause_command);	
		

		//Quit
				
				myContainer.add(quit_button = new Button("Quit"));
				quit_button.getAllStyles().setBgTransparency(255);
				quit_button.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,  150, 150));
				quit_button.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
				quit_button.getAllStyles().setPadding(TOP, 2);
				quit_button.getAllStyles().setPadding(BOTTOM, 2);
				quit_button.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
				
				Quit_Command quit_command = new Quit_Command(gw);
				quit_button.setCommand(quit_command);
				addKeyListener('q',quit_command);
				myToolbar.addCommandToSideMenu(quit_command);	
				
				//myToolbar.addCommandToRightSideMenu(quit_command);

				this.add(BorderLayout.WEST, myContainer);
				this.add(BorderLayout.NORTH, pv);
				this.add(BorderLayout.CENTER, mv);
				
			
				
		//Right side menu
		Label title1 = new Label("Commands");	
		
		/*
		Container myContainer2 = new Container();
		
		
		myContainer2.add(new_button = new Button("New"));
		myContainer2.add(save_button = new Button("Save"));
		myContainer2.add(undo_button = new Button("Undo"));
		myContainer2.add(about_button = new Button("About"));
		myContainer2.add(right_quit_button = new Button("Quit"));
		
		NewCommand newCmd = new NewCommand();
		SaveCommand saveCmd = new SaveCommand();
		UndoCommand undoCmd = new UndoCommand();
		AboutCommand aboutCmd = new AboutCommand();
		
		new_button.setCommand(newCmd);
		save_button.setCommand(saveCmd);
		undo_button.setCommand(undoCmd);
		about_button.setCommand(aboutCmd);
		right_quit_button.setCommand(quit_command);
		
		myToolbar.addCommandToOverflowMenu(newCmd);
		myToolbar.addCommandToOverflowMenu(saveCmd);
		myToolbar.addCommandToOverflowMenu(undoCmd);
		myToolbar.addCommandToOverflowMenu(aboutCmd);
		myToolbar.addCommandToOverflowMenu(quit_command);
		*/
		
		
		NewCommand newCmd = new NewCommand();
		myToolbar.addCommandToOverflowMenu(newCmd);
		SaveCommand saveCmd = new SaveCommand();
		myToolbar.addCommandToOverflowMenu(saveCmd);
		UndoCommand undoCmd = new UndoCommand();
		myToolbar.addCommandToOverflowMenu(undoCmd);	
		AboutCommand aboutCmd = new AboutCommand();
		myToolbar.addCommandToOverflowMenu(aboutCmd);
		myToolbar.addCommandToOverflowMenu(quit_command);
		
		this.show();
		
        int myWidth = mv.getWidth();
        int myHeight = mv.getHeight();
		
        WHValue.setX( mv.getWidth());
        WHValue.setY(mv.getHeight());
        
        gw.setWidth(mv.getWidth());
        gw.setHeight(mv.getHeight());
        gw.setSound(true);
        
	}
	
	public static int getMapWidth()
	{
		return mv.getWidth();
	}
	public static int getMapHeight() 
	{
		return mv.getHeight();
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		gw.setWidth(mv.getWidth());
		gw.setHeight(mv.getHeight());
		gw.game_clock(1000);
		//gw.addPlayerShip();
		int roll = GameWorld.genRandInt(0, 50);	
		if(roll> 10 && roll < 13) {
			gw.addNonPlayerShip();
			gw.addNewAestroid();
			if(roll> 10 && roll < 20) 
				gw.fireNPMissile();
		}
			
		
		if(gw.get_play() == false) {
			disableButtons();
			removeKeyListeners();
		}
		else {
			enableButtons();
			addKeyListeners();
		}	
	}	
	
	public void addKeyListeners()
	{
		addKeyListener('a', add_aestroid_command);
		addKeyListener('y', add_NPS_command);
		addKeyListener('b', add_station_command); 
		addKeyListener('s', add_ps_command);
		addKeyListener(-91, acc);
		addKeyListener(-92, dece);
		addKeyListener(-93, lt_command);
		addKeyListener(-94, rt_command);
		addKeyListener(44, tll);
		addKeyListener(46, tlr);
		addKeyListener(-90, firemissileCommand);
		addKeyListener('j', defPos);
	}
	
	// Remove key listeners (pause mode)
	public void removeKeyListeners()
	{
		removeKeyListener('a', add_aestroid_command);
		removeKeyListener('y', add_NPS_command);
		removeKeyListener('b', add_station_command); 
		removeKeyListener('s', add_ps_command);
		removeKeyListener(-91, acc);
		removeKeyListener(-92, dece);
		removeKeyListener(-93, lt_command);
		removeKeyListener(-94, rt_command);
		removeKeyListener(44, tll);
		removeKeyListener(46, tlr);
		removeKeyListener(-90, firemissileCommand);
		removeKeyListener('j', defPos); 	
	}
	
	// Enable buttons (play mode)
	public void enableButtons()
	{
		add_aestroid.setEnabled(true);
		add_nps.setEnabled(true);
		add_station.setEnabled(true);
		add_ps.setEnabled(true);
		accelerate.setEnabled(true);
		decelerate.setEnabled(true); 
		left_turn.setEnabled(true);
		right_turn.setEnabled(true);
		turn_launcher_left.setEnabled(true);
		turn_launcher_right.setEnabled(true);
		fire_missile.setEnabled(true);
		def_position.setEnabled(true);
		//myToolbar.setEnabled(true);
	}
	
	// Disable buttons (pause mode)
	public void disableButtons()
	{
		add_aestroid.setEnabled(false);
		add_nps.setEnabled(false);
		add_station.setEnabled(false);
		add_ps.setEnabled(false);
		accelerate.setEnabled(false);
		decelerate.setEnabled(false); 
		left_turn.setEnabled(false);
		right_turn.setEnabled(false);
		turn_launcher_left.setEnabled(false);
		turn_launcher_right.setEnabled(false);
		fire_missile.setEnabled(false);
		def_position.setEnabled(false);
		//myToolbar.setEnabled(false);
	}
	

	
	
	
	
}

	
	



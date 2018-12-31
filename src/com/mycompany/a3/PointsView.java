
 package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;


public class PointsView extends Container implements Observer{
	private Label pointsValueLabel;
	private Label MissileCountValueLabel;
	private Label ElapsedTimeValueLabel;
	private Label SoundValueLabel;
	private Label LivesValueLabel;
	
	
	public PointsView() {
		//this.gw = gw;
		Label pointsLabel= new Label("Points");
		Label MissileCountLabel = new Label("Missile Count");
		Label ElapsedTimeLabel = new Label("Elapsed Time");
		Label SoundLabel = new Label("Sound");
		Label LivesLabel = new Label("Lives");
	
	
	 pointsValueLabel = new Label("XXX");
	 MissileCountValueLabel = new Label("XXX");
	 ElapsedTimeValueLabel = new Label("XXX");
	 SoundValueLabel = new Label("XXX");
	 LivesValueLabel = new Label("XXX");
	 
	 pointsLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 0));
	 MissileCountLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 0));
	 ElapsedTimeLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 0));
	 SoundLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 0));
	 LivesLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 0));
	 
	 Container pointsContainer = new Container();
	 pointsContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
	 
	 pointsContainer.add(pointsLabel);
	 pointsContainer.add(pointsValueLabel);
	 pointsContainer.add(MissileCountLabel);
	 pointsContainer.add(MissileCountValueLabel);
	 pointsContainer.add(ElapsedTimeLabel);
	 pointsContainer.add(ElapsedTimeValueLabel);
	 pointsContainer.add(SoundLabel);
	 pointsContainer.add(SoundValueLabel);
	 pointsContainer.add(LivesLabel);
	 pointsContainer.add(LivesValueLabel);
	 
	 this.add(pointsContainer);
	}

	@Override
	public void update(Observable o, Object arg) {
		IGameWorld gw = (IGameWorld) arg;
		
		int score = gw.getPlayerScore();
		pointsValueLabel.setText("" + (score > 99? "": "0") + (score > 9? "":0) + score);
		int missileCount = gw.getMissileCount();
		MissileCountValueLabel.setText("" + missileCount);
		int elapsedTime = gw.getElapsedTime();
		ElapsedTimeValueLabel.setText(""+ elapsedTime);
		boolean sound = gw.getSound();
		if(gw.getSound() == true)
			SoundValueLabel.setText("ON");
		else 
			SoundValueLabel.setText("OFF");
		int lives = gw.getLives();
		LivesValueLabel.setText(" " + lives);
		
		this.repaint();	
	}

}


package com.mycompany.a3;

import java.io.Reader;

import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Point2D;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.a3.commands.Quit_Command;
import com.mycompany.a3.commands.Restart;

import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;
import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class GameWorld extends Observable implements IGameWorld{
	
	//code here to hold and manipulate world objects
	//handle observer registration
	//invoke observer call back by passing a GamwWorld Proxy,etc. 
	
	private int life;
	private int score;
	private int time;
	private PlayerShip playerShip;
	private Aestroids aesteroid;
	private NonPlayerShip nps;
	protected SpaceStation spacestation;  
	private PsMissile psMissile;
	private boolean sound;
	private IIterator itr;
	private int width, height;
	private Point2D myPoint = new Point2D(0,0);
	private static boolean flagPlay = true;
	private boolean PsCrashed;
	private ISelectable selectedObj;
	private int blink;
	GameCollection theGameCollection;
	protected boolean quitGame;
	
	Sound backgroundSound;
	Sound ShipcrashSound;
	Sound fireMissileSound;
	Sound endGameSound;
	Sound MissileReloadSound;
	Sound MissileCrashSound;
	Sound EmptyGunSound;
	
	//sound = False;
	Random rand = new Random();
	//private Vector<GameObjects> theGameCollection = new Vector<GameObjects>(); 
	
	
	
	
	public GameWorld() {
		//WHValue = new Point2D(0,0);
		this.init();
	}
	
	public void init() {
		//code to create initial game objects/setup
		life = 3;
		time = 0;
		score = 0;	
		theGameCollection = new GameCollection();
		//blink = spacestation.getblink();
		//sound = false;
		backgroundSound = new Sound("background.mp3", true);
		ShipcrashSound = new Sound("charger_smash.wav", false);
		fireMissileSound = new Sound("Pulse_gun_01.wav", false);
		endGameSound = new Sound("GameEnd.wav", false);
		MissileReloadSound = new Sound("ReloadSound.wav", false);
		MissileCrashSound = new Sound("SmashedSound.wav", false);
		EmptyGunSound = new Sound("EmptyGun.wav", false);
	}
	//methods to manipulate world objects and related game state date
	

	public void addNewAestroid() {
		aesteroid = new Aestroids(rand.nextFloat() * myPoint.getX(), rand.nextFloat()*myPoint.getY());
		//Container gameObjects;
		theGameCollection.add(aesteroid);
		System.out.println("A new Aestriod has been created");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void addSpaceStation() {
		Point2D spaceStationLoc = new Point2D(rand.nextFloat() * myPoint.getX(), rand.nextFloat()*myPoint.getY());
		if(!theGameCollection.contains(spacestation)) {
			spacestation = new SpaceStation(spaceStationLoc.getX(), spaceStationLoc.getY());
			theGameCollection.add(spacestation);
			System.out.println("A new Space Station is added");
		}
		
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}

	public void addPlayerShip() {
		Point2D playerLoc = new Point2D(myPoint.getX()/2, myPoint.getY()/2);
		
        if(!theGameCollection.contains(playerShip) && life > 0) {
        	playerShip = new PlayerShip(playerLoc);
    		theGameCollection.add(playerShip);
    		theGameCollection.add(playerShip.getLauncher());
    		System.out.println("A Player Ship is added");	
    		this.setChanged();
    		this.notifyObservers(new GameWorldProxy(this));
        }
        else {
	        if(theGameCollection.contains(playerShip)) 
	        	System.out.println("ship already exists");
	    } 
	}

	public void addNonPlayerShip() {
		nps = new NonPlayerShip(rand.nextFloat() * myPoint.getX(), rand.nextFloat()*myPoint.getY());
		theGameCollection.add(nps);
		System.out.println("A Non Player Ship is added");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	//Label title = new Label("File");
	public void fireMissile() {	
		if(!theGameCollection.contains(playerShip))
			System.out.println("No player ship is added");
		
		else if(playerShip.get_missile() > 0) {
			playerShip.getLauncher().FireMissile(); 
			theGameCollection.add(playerShip.getLauncher().get_missile());	//add an instance of missile in the theGameCollection.
			playerShip.dec_missile();
			System.out.println("Missile is Fired");
			if(sound == true)
				fireMissileSound.play();
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else{
		System.out.println("Out of Missiles");
		if(sound == true)
			EmptyGunSound.play();
		}
	}
	
	public void fireNPMissile() {
		if(theGameCollection.contains(nps)) {
			nps.NPFireMissile();
			theGameCollection.add(nps.get_missile_obj());
			nps.dec_missile();
			System.out.println("Non Player Ship fired a missile");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else
			System.out.println("No Non PlayerShip is added");
	}

	public void incSpeed(){
		if (theGameCollection.contains(playerShip)) {
			playerShip.accelerate();
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
			}
		else
			System.out.println("No Player Ship exist");	
	}
	
	public void decSpeed(){
		if (theGameCollection.contains(playerShip)) {
			playerShip.decelerate();
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
			
		else
			System.out.println("No Player Ship exist");
		
	}
	
	public void turnLeft() {
		if(theGameCollection.contains(playerShip)) {
			playerShip.steerLeft();
			//playerShip.getLauncher().steerLeft();
			System.out.println("Turned left Slightly");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
			else
				System.out.println("No Player Ship exist");
	}
	
	public void turnRight() {
		if(theGameCollection.contains(playerShip)) {
			playerShip.steerRight();
			//playerShip.getLauncher().steerRight();
			System.out.println("Turned Right Slightly");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else
			System.out.println("No Player Ship exist");	
		
	}
	
	public void turn_Launcher_Left() {
		if(theGameCollection.contains(playerShip)) {
			playerShip.getLauncher().steerLeft();
			System.out.println("Machine Launcher turned anti-clock wise slightly");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else 
			System.out.println("No PlayerShip Exist");
	}
	
	public void turn_Launcher_Right() {
		if(theGameCollection.contains(playerShip)) {
			playerShip.getLauncher().steerRight();
			System.out.println("Machine Launcher turned clock wise slightly");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else 
			System.out.println("No PlayerShip Exist");
	}
	
	public void refuel() {
		if(getSelectedObj() instanceof PsMissile)
			((Missile) getSelectedObj()).setFuel();
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void DefaultPosition() {
		if(theGameCollection.contains(playerShip)) {
			playerShip.DefaultPosition();
			playerShip.getLauncher().set_location(playerShip.locX, playerShip.locY);
			
			System.out.println("Ship is back to its center position");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else 
			System.out.println("No PlayerShip Exist");
	}
	
	public void newMissileSupply() {
		if(theGameCollection.contains(spacestation) && theGameCollection.contains(playerShip)) {
			playerShip.loadMissile();
			System.out.println("Got Missile Supply: 10");
			if(sound == true)
				MissileReloadSound.play();
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else
			System.out.println("Either Space Station or Ship are not Added");
	}
	
	public void currentState() {
		System.out.println("1. Current Score: " + score);
		if(theGameCollection.contains(playerShip))
			System.out.println("2. Number of Missiles: " + playerShip.get_missile()+ " ");
		else
			System.out.println("2. Number of Missiles: No Ship is added");
		System.out.println("3. Current Elapsed Time: " + this.time);
	}
	
	public void shipExploded() {
		life--;
		if(life > 0) 
			System.out.println("Start Agian");
		else {
			System.out.println("You have lost the game.");
			
		}
	}
	
//Game Clock	
	public void game_clock(int other_time) {
		if(flagPlay) {
			this.time++; // increment play time in seconds where 1 unit is 1 second 
			
			//Unselect the object if game is in play motion
			if(selectedObj != null) {
				System.out.println(selectedObj);
				selectedObj.setSelected(false);
			}
		//PsMissile m = null;
		IIterator game_objects_2 = theGameCollection.getIterator();
		while(game_objects_2.hasNext()) {
			Object element =  game_objects_2.getNext();
			if(element instanceof Missile){
				if((((Missile)element).getFuel() == 0 ) || (((Missile)element).getX()) == myPoint.getX()
						|| (((Missile)element).getY()) == myPoint.getY() || (((Missile)element).getX()) == 0 
						|| (((Missile)element).getY()) == 0){
					theGameCollection.remove((Missile)element);
					game_objects_2 = theGameCollection.getIterator();
				}
			}
		}
		//move the movable objects
		IIterator game_objects = theGameCollection.getIterator();
		while(game_objects.hasNext()) {
			Object element =  game_objects.getNext();
			if(element instanceof PsMissile)
				((PsMissile) element).move(other_time);
			else if(element instanceof PlayerShip) {
				playerShip.move(other_time);
			}
			else if(element instanceof IMovable)
				((IMovable) element).move(other_time);
			
			//set blinking for spacestaion
			if(element instanceof SpaceStation)
				spacestation.setblink(spacestation.getblink());
			
		}
		

		
		//Handling Map View , objects don't go out of Map
				IIterator iterator = theGameCollection.getIterator();
				while(iterator.hasNext())
				{
					Object object = iterator.getNext();
					if(object instanceof FixedObject)
					{
						continue;
					}
					Movable movableObject = (Movable) object; 
					double locX = ((GameObjects) object).getX();
					double locY = ((GameObjects) object).getY();
					double mapX = myPoint.getX(); 
					double mapY = myPoint.getY();

					//new location of object
					double ObjX = movableObject.getX();
					double ObjY = movableObject.getY();
					Point2D newLoc = new Point2D(ObjX, ObjY);
						
					
					if(locX > mapX)
					{
						newLoc.setX(0);
					}
					else if( locX < 0)
					{
						newLoc.setX(myPoint.getX());
					}
					
					if(locY > myPoint.getY())
					{
						newLoc.setY(0);
					}
					else if( locY < 0)
					{
						newLoc.setY(myPoint.getY());
					}
					
					movableObject.set_location(newLoc.getX(), newLoc.getY());
				}
				
		//Collission Handling
		Vector<ICollider> CollidableObj = new Vector<ICollider>(); 
		IIterator collIter1 = theGameCollection.getIterator();
				while(collIter1.hasNext()) {
					ICollider curObj = (ICollider)collIter1.getNext(); 
					
					IIterator collIter2 = theGameCollection.getIterator();
					while(collIter2.hasNext()){
						ICollider otherObj = (ICollider) collIter2.getNext();
						if(otherObj!=curObj){// make sure it's not the SAME object
							if(curObj.collidesWith(otherObj)){
								
								//If Playership go to SpaceStation, it will get full misisles
								if((curObj instanceof SpaceStation && otherObj instanceof PlayerShip) ||
										(curObj instanceof PlayerShip && otherObj instanceof SpaceStation)) {
									playerShip.loadMissile();
									System.out.println("In GW: PS reached SpaceStaion");
									if(sound == true)
										MissileReloadSound.play();
								}
								
								//sound if missile crashed an aestroid or NPS
								if(curObj instanceof PsMissile || otherObj instanceof PsMissile) {
									if(sound == true)
										MissileCrashSound.play();
								}
								
								//Score Increase when PS missile explode the aestroid
								if((curObj instanceof Aestroids && otherObj instanceof PsMissile) ||
										(curObj instanceof PsMissile && otherObj instanceof Aestroids)) 
									this.score += 10;
								
								//Score +15 when PS Missile explode NPS
								 if ((curObj instanceof PsMissile && otherObj instanceof NonPlayerShip) ||
											(curObj instanceof NonPlayerShip && otherObj instanceof PsMissile))
										this.score += 15;
						
								//Add the item to remove at last
								 
								 if((curObj instanceof PlayerShip && otherObj instanceof SpaceStation) ||
										(curObj instanceof SpaceStation && otherObj instanceof PlayerShip)) 
									 System.out.println("Do nothing");	 
								 else {
										CollidableObj.add(curObj);
										CollidableObj.add(otherObj);	
									}
								 
								
								
								//theGameCollection.remove((GameObjects)curObj);
								//theGameCollection.remove((GameObjects)otherObj) ;
								//curObj.handleCollision(otherObj);
								//handleCollission(curObj, otherObj);
								 
							}
						}
					}
				}
				//Print the list that we are goign to remove
				for(int i = 0; i< CollidableObj.size(); i++) 
					System.out.println("#" + i + 1 + " " + CollidableObj.elementAt(i));
				// save all the collidable objects in CollidableObj and remove them from list
				
			
				PsCrashed = false;
				for(int i = 0; i< CollidableObj.size(); i++) {
					if(CollidableObj.contains(playerShip)) {
						//to keep track of total lifes
						PsCrashed = true;
						//to remove launcher whenever ps crashed
						theGameCollection.remove(playerShip.getLauncher());
					}
					theGameCollection.remove((GameObjects)CollidableObj.elementAt(i));
				}
				
				if(PsCrashed == true) {
					life--;
					if(sound == true)
						ShipcrashSound.play();
					this.setChanged();
					this.notifyObservers(new GameWorldProxy(this));
					if(life < 1) {
						if(sound == true)
							endGameSound.play();
						flagPlay = false;
						backgroundSound.pause();
						
					//	Dialog.show("Aestroid Game", "Total Score: " + score, null, null);

						quitGame = true;
						Command[] commands = {new Restart(this), new Quit_Command(this)};
						Container gameOver = new Container();
						gameOver.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
						Label label = new Label("Game is Over!");
						label.getAllStyles().setAlignment(Label.CENTER);
						Label label2 = new Label("Total Score: " + score);
						label2.getAllStyles().setAlignment(Label.CENTER);
						gameOver.addComponent(label);
						gameOver.addComponent(label2);
						Dialog.show("Asteroid Game", gameOver, commands);
					}
					
					else addPlayerShip();	
				}
					
		
		
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		}
	}	
	
	public void quit() {
		backgroundSound.pause();
		if(Dialog.show("Confirm", "Do you want to quit?", "OK", "Cancel"))
		{
			System.exit(0);
		}
		else backgroundSound.play();
	}
	
	public boolean getQuitGame() {
		return quitGame;
	}

	@Override
	public int getPlayerScore() {
		return score;
	}
	
	public int getMissileCount() {
		if (playerShip == null) return 0;
			return playerShip.get_missile();
	}


	@Override
	public int getElapsedTime() {
		// TODO Auto-generated method stub
		return this.time;
	}

	
	public void setSound(boolean sound) {
		this.sound = sound;
		if(sound == true && flagPlay == true)
		{
			backgroundSound.play();;
		}
		else
		{
			backgroundSound.pause();
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	
	
	public void pause() {	
		flagPlay = false;
		backgroundSound.pause();
		
	}
	
	public boolean get_play() {
		return this.flagPlay;
	}
	
	//Newly addeds
	//When restat the game
	public void set_play() {
		flagPlay = true;
	}
	
	public void play() {
		flagPlay = true;
		//check if tick is checked, then play music
		if(sound == true)
			backgroundSound.play();	
	}
	
	public boolean isPlaying() {
		if(flagPlay == true)
			return true;
		else return false;
	}
	
	
	@Override
	public boolean getSound() {
		// TODO Auto-generated method stub
		return sound;
	}


	@Override
	public int getLives() {
		return life;
	}

	@Override
	public GameCollection getGameObjects() {
		// TODO Auto-generated method stub
		return theGameCollection;
	}
	

	public void setWidth(int width) {
		myPoint.setX(width);
		// TODO Auto-generated method stub
		
	}

	public void setHeight(int height) {
		// TODO Auto-generated method stub
		myPoint.setY(height);
	}

	public static int genRandInt(int i, int j) {
		// TODO Auto-generated method stub
		Random r = new Random();
		int x = r.nextInt((j - i) + 1) + i;
		if(flagPlay == true)
			return x;
		else return 0;
		
	}
	
	public void setSelectedObj(ISelectable selected)
	{
		if(selectedObj != null)
		{
			selectedObj.setSelected(false);
		}
		
		selectedObj = selected;
		if(selectedObj != null && flagPlay == false)
		{
			selectedObj.setSelected(true);
		}
	}

	
	public ISelectable getSelectedObj()
	{
		return selectedObj;
	}
	
}


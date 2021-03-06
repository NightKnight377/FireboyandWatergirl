import java.util.ArrayList;

public class Level3Universe implements Universe {

	private boolean complete = false;
	private Background background = null;	
	private DisplayableSprite player1 = null;
	private DisplayableSprite player2 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private ArrayList<DisplayableSprite> barriers = new ArrayList<DisplayableSprite>();
	private RedDoor redDoor;
	private BlueDoor blueDoor;
	private YellowDoor yellowDoor;
	private GreenDoor greenDoor;
	private PurpleDoor purpleDoor;
	private PurpleDoor purpleDoor1;
	private FireboyFinish FireboyFinish;
	private WatergirlFinish WatergirlFinish;

	public Level3Universe () {

		this.setXCenter(0);
		this.setYCenter(0);
		player1 = new FireBoy(-450,250);
		sprites.add(player1);
		player2 = new WaterGirl(450,250);
		sprites.add(player2);
		
		//top
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / -2 + 16, true));
		//left
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / -2 + 16, AnimationFrame.SCREEN_HEIGHT / 2, true));
		//right
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / 2 - 16,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / 2, true));
		
		barriers.add(new BarrierSprite(0,-140,0+16, 375, true));
		barriers.add(new BarrierSprite(-500,300,-350, 300+16, true));
		barriers.add(new BarrierSprite(-350,100,-250, 100+16, true));
		barriers.add(new BarrierSprite(-50,150,-0, 150+16, true));
		barriers.add(new BarrierSprite(-500,0,-350, 0+16, true));
		barriers.add(new BarrierSprite(-120,-140,0, -140+16, true));
		barriers.add(new BarrierSprite(380,300,500, 300+16, true));
		barriers.add(new BarrierSprite(60,-375,60+16, 375, true));
		barriers.add(new BarrierSprite(120,-300,120+16, 375, true));
		barriers.add(new BarrierSprite(120,225,120+50, 225+16, true));
		barriers.add(new BarrierSprite(120,125,120+40, 125+16, true));
		barriers.add(new BarrierSprite(120,25,120+30, 25+16, true));
		barriers.add(new BarrierSprite(120,-75,120+20, -75+16, true));
		barriers.add(new BarrierSprite(375,-50,500, -50+16, true));
		barriers.add(new BarrierSprite(400,-150,400+10, -150+16, true));
		barriers.add(new BarrierSprite(250,-250,250+10, -250+16, true));
		barriers.add(new BarrierSprite(120,-300,170, -300+16, true));
		
		//doors
		purpleDoor = new PurpleDoor(-100,250,-25,250+16, true);
		barriers.add(purpleDoor);
		purpleDoor.distanceOpenDoor(75);
		purpleDoor1 = new PurpleDoor(-100,-50,-25,-50+16, true);
		barriers.add(purpleDoor1);
		purpleDoor1.distanceOpenDoor(75);
		//Buttons
		sprites.add(new PurpleButton(-275, 180, -175, 180-30, true));
		sprites.add(new PurpleButton(-275, -120, -175, -120-30, true));
		//Green
		barriers.add(new Green(-500,359,500,375,true));
		//Finish
		sprites.add(new FireboyFinish(10,359-80,10+60,359,true));
		sprites.add(new WatergirlFinish(70,359-80,70+60,359,true));
		
		sprites.addAll(barriers);

	}

	public double getScale() {
		return 1;
	}

	public double getXCenter() {
		return 0;
	}

	public double getYCenter() {
		return 0;
	}

	public void setXCenter(double xCenter) {
	}

	public void setYCenter(double yCenter) {
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		complete = true;
	}

	public Background getBackground() {
		return background;
	}

	public DisplayableSprite getPlayer1() {
		return player1;
	}
	
	public DisplayableSprite getPlayer2() {
		return player2;
	}

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}
	
	public ArrayList<DisplayableSprite> getBarriers() {
		return barriers;
	}

	public boolean centerOnPlayer() {
		return false;
	}		

	public void update(KeyboardInput keyboard, long actual_delta_time) {

		if (keyboard.keyDownOnce(27)) {
			complete = true;
		}
		if ( (((FireBoy) player1).getFinishing() == true) && (((WaterGirl) player2).getFinishing() == true) ) {
			complete = true;
		}
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
		
		
	}

	public String toString() {
		return "Level3Universe";
	}

	@Override
	public void reset() {
		((FireBoy) player1).setCenterX(-450);
		((FireBoy) player1).setCenterY(250);
		((FireBoy) player1).setVelocityY(0);
		((FireBoy) player1).setVelocityX(0);
		((WaterGirl) player2).setCenterX(450);
		((WaterGirl) player2).setCenterY(250);
		((WaterGirl) player2).setVelocityY(0);
		((WaterGirl) player2).setVelocityX(0);
	}
	
	public RedDoor getRedDoor() {
		return redDoor;
	}
	
	public BlueDoor getBlueDoor() {
		return blueDoor;
	}
	
	public YellowDoor getYellowDoor() {
		return yellowDoor;
	}
	
	public GreenDoor getGreenDoor() {
		return greenDoor;
	}
	
	public PurpleDoor getPurpleDoor() {
		return purpleDoor;
	}
	
	public PurpleDoor getPurpleDoor1() {
		return purpleDoor1;
	}
}
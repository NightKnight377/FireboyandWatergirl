import java.util.ArrayList;

public class Level4Universe implements Universe {

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

	public Level4Universe () {

		this.setXCenter(0);
		this.setYCenter(0);
		player1 = new FireBoy(-450,250);
		sprites.add(player1);
		player2 = new WaterGirl(-450,250);
		sprites.add(player2);
		
		//top
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / -2 + 16, true));
		//left
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / -2 + 16, AnimationFrame.SCREEN_HEIGHT / 2, true));
		//right
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / 2 - 16,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / 2, true));
		
		barriers.add(new BarrierSprite(-500,359,-400, 375, true));
		barriers.add(new BarrierSprite(200,359,500, 375, true));
		barriers.add(new BarrierSprite(0,159,400, 175, true));
		barriers.add(new BarrierSprite(-125,309,-75, 325, true));
		barriers.add(new BarrierSprite(450,259,500, 275, true));
		barriers.add(new BarrierSprite(-400,59,100, 75, true));
		barriers.add(new BarrierSprite(200,59,500, 75, true));
		barriers.add(new BarrierSprite(-400,-140,-400+16,59, true));
		barriers.add(new BarrierSprite(-400,-140,-300,-140+16, true));
		barriers.add(new BarrierSprite(-450,-45,-400,-45+16, true));
		barriers.add(new BarrierSprite(-500,59,-450,59+16, true));
		barriers.add(new BarrierSprite(-500,-280,-125,-280+16, true));
		barriers.add(new BarrierSprite(450,-30,500,-30+16, true));
		barriers.add(new BarrierSprite(300,-120,350,-120+16, true));
		
		//Lava
		barriers.add(new Lava(-400,259,-250, 275, true));
		barriers.add(new Lava(0,359,200, 375, true));
		barriers.add(new Lava(-500,159,0, 175, true));
		
		//Water
		barriers.add(new Water(-400,359,-200, 375, true));
		barriers.add(new Water(50,259,200, 275, true));
		
		//Green
		barriers.add(new Green(-200,359,0, 375, true));
		
		//Buttons
		sprites.add(new BlueButton(300,145,350, 145+16, true));
		sprites.add(new BlueButton(-275,-296,-225,-296+16, true));
		sprites.add(new YellowButton(-375,49,-325,49+10,true));
		sprites.add(new YellowButton(-200,-296,-150,-296+16,true));
		
		//Doors
		blueDoor = new BlueDoor(100,-200,200,-200+16,true);
		barriers.add(blueDoor);
		blueDoor.distanceOpenDoor(100);
		yellowDoor = new YellowDoor(100,59,200,59+16,true);
		barriers.add(yellowDoor);
		yellowDoor.distanceOpenDoor(100);
		
		//Finish
		sprites.add(new FireboyFinish(-450,-359,-450+60,-359+80,true));
		sprites.add(new WatergirlFinish(-350,-359,-350+60,-359+80,true));
		
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
		return "Level4Universe";
	}

	@Override
	public void reset() {
		((FireBoy) player1).setCenterX(-450);
		((FireBoy) player1).setCenterY(250);
		((FireBoy) player1).setVelocityY(0);
		((FireBoy) player1).setVelocityX(0);
		((WaterGirl) player2).setCenterX(-450);
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
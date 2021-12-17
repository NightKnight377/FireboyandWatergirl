import java.util.ArrayList;

public class Level2Universe implements Universe {

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
	private FireboyFinish FireboyFinish;
	private WatergirlFinish WatergirlFinish;

	public Level2Universe () {

		this.setXCenter(0);
		this.setYCenter(0);
		player1 = new FireBoy(-50,325);
		sprites.add(player1);
		player2 = new WaterGirl(50,325);
		sprites.add(player2);
		
		//top
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / -2 + 16, true));
		//bottom
		barriers.add(new BarrierSprite(-500,359,500, 375, true));
		barriers.add(new BarrierSprite(-150,-300,-150+16,375, true));
		barriers.add(new BarrierSprite(150,-300,150+16,375, true));
		barriers.add(new BarrierSprite(0,-300,0+16,375, true));
		barriers.add(new BarrierSprite(0,-300,0+16,375, true));
		barriers.add(new BarrierSprite(-150,300-16,-75,300, true));
		barriers.add(new BarrierSprite(0,300-16,75,300, true));
		barriers.add(new BarrierSprite(-75,205-16,0,205, true));
		barriers.add(new BarrierSprite(-150,110-16,-75,110, true));
		barriers.add(new BarrierSprite(0,110-16,75,110, true));
		barriers.add(new BarrierSprite(-75,25-16,0,25, true));
		barriers.add(new BarrierSprite(-150,-65-16,-75,-65, true));
		barriers.add(new BarrierSprite(0,-65-16,75,-65, true));
		barriers.add(new BarrierSprite(-75,-145-16,0,-145, true));
		

		//left
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / -2 + 16, AnimationFrame.SCREEN_HEIGHT / 2, true));
		//right
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / 2 - 16,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / 2, true));
		
		//doors
		blueDoor = new BlueDoor(225,200-16,150,200, true);
		barriers.add(blueDoor);
		blueDoor.distanceOpenDoor(150);
		greenDoor = new GreenDoor(225,25-16,150,25, true);
		barriers.add(greenDoor);
		greenDoor.distanceOpenDoor(150);
		yellowDoor = new YellowDoor(225,-150-16,150,-150, true);
		barriers.add(yellowDoor);
		yellowDoor.distanceOpenDoor(150);
		
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
		return "Level2Universe";
	}

	@Override
	public void reset() {
		((FireBoy) player1).setCenterX(450);
		((FireBoy) player1).setCenterY(325);
		((FireBoy) player1).setVelocityY(0);
		((FireBoy) player1).setVelocityX(0);
		((WaterGirl) player2).setCenterX(450);
		((WaterGirl) player2).setCenterY(325);
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
}
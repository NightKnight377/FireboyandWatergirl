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
		player1 = new FireBoy(-50,315);
		sprites.add(player1);
		player2 = new WaterGirl(100,315);
		sprites.add(player2);
		
		//top
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / -2 + 16, true));
		//bottom
		barriers.add(new BarrierSprite(-166+16,359,166, 375, true));
		barriers.add(new BarrierSprite(350,359,500, 375, true));	
		barriers.add(new BarrierSprite(-500,359,-350+16, 375, true));	
		barriers.add(new BarrierSprite(-150,-250,-150+16,375, true));
		barriers.add(new BarrierSprite(150,-250,150+16,375, true));
		barriers.add(new BarrierSprite(0,-275,0+16,375, true));
		barriers.add(new BarrierSprite(-150,300-16,-75,300, true));
		barriers.add(new BarrierSprite(0,300-16,75,300, true));
		barriers.add(new BarrierSprite(-75,205-16,0,205, true));
		barriers.add(new BarrierSprite(-150,110-16,-75,110, true));
		barriers.add(new BarrierSprite(0,110-16,75,110, true));
		barriers.add(new BarrierSprite(-75,25-16,0,25, true));
		barriers.add(new BarrierSprite(-150,-65-16,-75,-65, true));
		barriers.add(new BarrierSprite(0,-65-16,75,-65, true));
		barriers.add(new BarrierSprite(-75,-145-16,0,-145, true));
		barriers.add(new BarrierSprite(-25,-275,25+16,-275+16, true));
		barriers.add(new BarrierSprite(-175,-250,-100,-250+16, true));
		barriers.add(new BarrierSprite(100,-250,175,-250+16, true));
		
		//Green
		barriers.add(new Green(166,-170,305,-170+16,true));
		barriers.add(new Green(400,-170,500,-170+16,true));
		barriers.add(new Green(166,0,260,0+16,true));
		barriers.add(new Green(350,0,500,0+16,true));
		barriers.add(new Green(166,359,350,359+16,true));
		barriers.add(new Green(-305+16,-170,-166+16,-170+16,true));
		barriers.add(new Green(-500+16,-170,-400+16,-170+16,true));
		barriers.add(new Green(-260+16,0,-166+16,0+16,true));
		barriers.add(new Green(-500+16,0,-350+16,0+16,true));
		barriers.add(new Green(-350+16,359,-166+16,359+16,true));
		
		
		//buttons
		sprites.add(new BlueButton(-60, -170, -10, -160, true));
		sprites.add(new GreenButton(-60, 0, -10, 10, true));
		sprites.add(new YellowButton(-60, 180, -10, 190, true));
		//left
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / -2 + 16, AnimationFrame.SCREEN_HEIGHT / 2, true));
		//right
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / 2 - 16,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / 2, true));
		
		//doors
		blueDoor = new BlueDoor(150,200-16,225,200, true);
		barriers.add(blueDoor);
		blueDoor.distanceOpenDoor(75);
		greenDoor = new GreenDoor(150,25-16,225,25, true);
		barriers.add(greenDoor);
		greenDoor.distanceOpenDoor(75);
		yellowDoor = new YellowDoor(150,-150-16,225,-150, true);
		barriers.add(yellowDoor);
		yellowDoor.distanceOpenDoor(75);
		
		//Finish
		FireboyFinish = new FireboyFinish(430,280,430+50,280+80,true);
		sprites.add(FireboyFinish);
		WatergirlFinish = new WatergirlFinish(-470+50,280,-470,280+80,true);
		sprites.add(WatergirlFinish);
		
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
		((FireBoy) player1).setCenterX(-50);
		((FireBoy) player1).setCenterY(315);
		((FireBoy) player1).setVelocityY(0);
		((FireBoy) player1).setVelocityX(0);
		((WaterGirl) player2).setCenterX(100);
		((WaterGirl) player2).setCenterY(315);
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
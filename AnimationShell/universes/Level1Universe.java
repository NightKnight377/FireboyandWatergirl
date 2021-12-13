import java.util.ArrayList;

public class Level1Universe implements Universe {

	private boolean complete = false;	
	private Background background = null;	
	private DisplayableSprite player1 = null;
	private DisplayableSprite player2 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private ArrayList<DisplayableSprite> barriers = new ArrayList<DisplayableSprite>();
	private Door door;

	public Level1Universe () {

		this.setXCenter(0);
		this.setYCenter(0);
		player1 = new FireBoy(450,325);
		sprites.add(player1);
		player2 = new WaterGirl(450,325);
		sprites.add(player2);
		
		//top
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / -2 + 16, true));
		//bottom
		barriers.add(new BarrierSprite(-500,359,-350, 375, true));
		barriers.add(new BarrierSprite(-200,359,-50, 375, true));
		barriers.add(new BarrierSprite(50,359,200, 375, true));
		barriers.add(new BarrierSprite(350,359,500, 375, true));
		barriers.add(new BarrierSprite(-350,200,500, 200+16, true));
		barriers.add(new BarrierSprite(-500,275,-425, 275+16, true));
		barriers.add(new BarrierSprite(-550,100-16,350, 100, true));
		barriers.add(new BarrierSprite(425,150,500, 150+16, true));
		barriers.add(new BarrierSprite(300,85-16,350, 85, true));

		//left
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / -2 + 16, AnimationFrame.SCREEN_HEIGHT / 2, true));
		//right
		barriers.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / 2 - 16,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / 2, true));	
		//lava
		barriers.add(new Lava(-350, 359, -200, AnimationFrame.SCREEN_HEIGHT / 2, true));
		//Water
		barriers.add(new Water(200, 359, 350, AnimationFrame.SCREEN_HEIGHT / 2, true));
		//Green
		barriers.add(new Green(-50, 359, 50, AnimationFrame.SCREEN_HEIGHT / 2, true));
		barriers.add(new Green(-500+16, 85-16, 300,85, true));
		//Doors
		door = new Door(0,100,16,200, true);
		barriers.add(door);
		door.distanceOpenDoor(100);
		//Buttons
		sprites.add(new Button(-250, 190, -200, 200, true));
		sprites.add(new Button(200, 190, 250, 200, true));
		
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
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
		
		
	}

	public String toString() {
		return "ShellUniverse";
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
	
	public Door getDoor() {
		return door;
	}

}

import java.util.ArrayList;

public interface Universe {

	//STATIC VARIABLES
	
	//INSTANCE VARIABLES

	public double getScale();


	public double getXCenter();
	public double getYCenter();
	public RedDoor getRedDoor();
	public BlueDoor getBlueDoor();
	public YellowDoor getYellowDoor();
	public GreenDoor getGreenDoor();
	public PurpleDoor getPurpleDoor();
	public PurpleDoor getPurpleDoor1();
	
	public void setXCenter(double xCenter);
	public void setYCenter(double yCenter);
	
	public boolean isComplete();
	public void setComplete(boolean complete);
	
	public Background getBackground();
	public DisplayableSprite getPlayer1();
	public boolean centerOnPlayer();

	public ArrayList<DisplayableSprite> getSprites();
	public ArrayList<DisplayableSprite> getBarriers();
	
	public void reset();
	public void update(KeyboardInput keyboard, long actual_delta_time);


	
    
	
}

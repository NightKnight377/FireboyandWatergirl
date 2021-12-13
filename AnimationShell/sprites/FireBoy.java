import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class FireBoy implements DisplayableSprite {

	private static Image image;	
	private static double centerX = 0;
	private static double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;
	boolean jumping = false;
	int timeJumping = 0;
	int totalMove = 0;
	
	private double ACCCELERATION_X = 10;		//PIXELS PER SECOND PER SECOND
	private double ACCCELERATION_Y = 600; 	//PIXELS PER SECOND PER SECOND
	private double MAX_VELOCITY_X = 150;	//PIXELS PER SECOND
	private double FRICTION_FACTOR_X = 0.95;
	private double velocityX = 0;        	//PIXELS PER SECOND
	private double velocityY = 0; 
	
	private boolean isJumping = false;
	private final double INITIAL_JUMP_VELOCITY = 250; //pixels / second
	
	private CollisionDetection collisionDetection;
	TwoDimensionBounce bounce;

	
	public FireBoy(double centerX, double centerY) {

		this.centerX = centerX;
		this.centerY = centerY;
		collisionDetection = new CollisionDetection();
		collisionDetection.setBounceFactorX(0.1);
		collisionDetection.setBounceFactorY(0);
		bounce = new TwoDimensionBounce();
		
		if (image == null) {
			try {
				image = ImageIO.read(new File("res/Fireboy.png"));
			}
			catch (IOException e) {
				System.err.println(e.toString());
			}		
		}
		this.height = 60;
		this.width = 30;	
	}

	public Image getImage() {
		return image;
	}
	
	//DISPLAYABLE
	
	public boolean getVisible() {
		return true;
	}
	
	public double getMinX() {
		return centerX - (width / 2);
	}

	public double getMaxX() {
		return centerX + (width / 2);
	}

	public double getMinY() {
		return centerY - (height / 2);
	}

	public double getMaxY() {
		return centerY + (height / 2);
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getCenterX() {
		return centerX;
	}

	public double getCenterY() {
		return centerY;
	}
	
	public void setCenterX(int value) {
		centerX = value;
	}

	public void setCenterY(int value) {
		centerY = value;
	}
	
	public void setVelocityX(int value) {
		velocityX = value;
	}

	public void setVelocityY(int value) {
		velocityY = value;
	}
	
	public boolean getDispose() {
		return dispose;
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		
		boolean onGround = isOnGround(universe);
		
		if (onGround) {

			if (keyboard.keyDown(38)) {
				isJumping = true;
				this.velocityY -= INITIAL_JUMP_VELOCITY;
				onGround = false;
			} else {
				this.velocityX = this.velocityX * FRICTION_FACTOR_X;
			}
		} else {
			
		}
		// RIGHT
		if (keyboard.keyDown(39)) {
			velocityX += + ACCCELERATION_X;
			if (velocityX > MAX_VELOCITY_X) {
				velocityX = MAX_VELOCITY_X;
			}
		}
		// LEFT
		else if (keyboard.keyDown(37)) {
			velocityX -= ACCCELERATION_X;
			if (velocityX < - MAX_VELOCITY_X) {
				velocityX = - MAX_VELOCITY_X;
			}
		}
		
		collisionDetection.calculate2DBounce(bounce, this, universe.getBarriers(), velocityX, velocityY, actual_delta_time);
		this.centerX = bounce.newX + (width / 2);
		this.centerY = bounce.newY + (height / 2);
		this.velocityX = bounce.newVelocityX;
		this.velocityY = bounce.newVelocityY;
		
		if (onGround == true) {
			this.velocityY = 0;
		} else {
			this.velocityY = this.velocityY + ACCCELERATION_Y * 0.001 * actual_delta_time;
		}
		double deltaX = actual_delta_time * 0.001 * velocityX;
		if (checkCollisionWithBarrier(universe, deltaX, 0) == false) {
			this.centerX += deltaX;
		}
		double deltaY = actual_delta_time * 0.001 * velocityY;
		if (checkCollisionWithBarrier(universe, 0, deltaY) == false) {
			this.centerY += deltaY;
		}

	}
	
	private boolean checkCollisionWithBarrier(Universe universe, double deltaX, double deltaY) {

		boolean colliding = false;
		boolean onButton = false;
		for (DisplayableSprite sprite : universe.getSprites()) {
			if (sprite instanceof BarrierSprite || sprite instanceof Lava || sprite instanceof Door) {
				if (CollisionDetection.overlaps(this.getMinX() + deltaX, this.getMinY() + deltaY, 
						this.getMaxX()  + deltaX, this.getMaxY() + deltaY, 
						sprite.getMinX(),sprite.getMinY(), 
						sprite.getMaxX(), sprite.getMaxY())) {
					colliding = true;	
					break;
				}
			} if (sprite instanceof Water || sprite instanceof Green) {
				if (CollisionDetection.overlaps(this.getMinX() + deltaX, this.getMinY() + deltaY, 
						this.getMaxX()  + deltaX, this.getMaxY() + deltaY, 
						sprite.getMinX(),sprite.getMinY(), 
						sprite.getMaxX(), sprite.getMaxY())) {
					Main.frame.getUniverse().reset();					
				}
			} if (sprite instanceof Button) {
				if (CollisionDetection.overlaps(this.getMinX() + deltaX, this.getMinY() + deltaY, 
						this.getMaxX()  + deltaX, this.getMaxY() + deltaY, 
						sprite.getMinX(),sprite.getMinY(), 
						sprite.getMaxX(), sprite.getMaxY())) {
					onButton = true;
				}
			}
		}		
		if (onButton == true) {
			universe.getDoor().openDoor(-1);
		} else if (onButton == false) {
			universe.getDoor().openDoor(1);
		}
		return colliding;
	}

		
	
	private boolean isOnGround(Universe universe) {
		boolean onGround = false;
		for (DisplayableSprite sprite: universe.getSprites()) {
			boolean bottomColiding = this.getMaxY() >= (sprite.getMinY()) && this.getMaxY() <= sprite.getMinY();
			boolean withinRange = this.getMaxX() > sprite.getMinX() && this.getMinX() < sprite.getMaxX();
			if (bottomColiding && withinRange) {
				onGround = true;
				break;
			}
		}
		return onGround;
	}

}

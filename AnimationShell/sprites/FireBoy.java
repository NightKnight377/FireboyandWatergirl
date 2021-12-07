import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class FireBoy implements DisplayableSprite {

	private static Image image;	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;
	boolean jumping = false;
	int timeJumping = 0;

	double velocityX = 0;
	double velocityY = 0;	
	
	private final double VELOCITY = 150;

	public FireBoy(double centerX, double centerY, double height, double width) {
		this(centerX, centerY);
		
		this.height = height;
		this.width = width;
	}

	
	public FireBoy(double centerX, double centerY) {

		this.centerX = centerX;
		this.centerY = centerY;
		
		if (image == null) {
			try {
				image = ImageIO.read(new File("res/Fireboy.png"));
				this.height = this.image.getHeight(null);
				this.width = this.image.getWidth(null);
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}		
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
		
		double velocityX = 0;
		double velocityY = 0;
		
		if (jumping == true) {
			if (timeJumping <= 30) {
				velocityY = -VELOCITY;
				timeJumping++;
			} else {
				timeJumping = 0;
				jumping = false;
			}
		} else {
			// DOWN
			velocityY += 200;
		}
		
		//LEFT	
		if (keyboard.keyDown(37)) {
			velocityX = -VELOCITY;
		}
		// RIGHT
		if (keyboard.keyDown(39)) {
			velocityX += VELOCITY;
		}			
		//UP
		if (keyboard.keyDown(38)) {
			double deltaY = actual_delta_time * 0.001 * velocityY;
			if (checkCollisionWithBarrier(universe, 0, deltaY) == true) {
				jumping = true;
				velocityY = -VELOCITY;
			}			
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
	
	private boolean checkCollisionWithBarrier(Universe sprites, double deltaX, double deltaY) {

		boolean colliding = false;
//use pixelbased overlaps
		for (DisplayableSprite sprite : sprites.getSprites()) {
			if (sprite instanceof BarrierSprite || sprite instanceof Lava) {
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
					break;					
				}
			}
		}		
		return colliding;		
	}
	
	private boolean isOnGround(Universe universe) {
		boolean onGround = false;
		for (DisplayableSprite sprite: universe.getSprites()) {
			boolean bottomColiding = (this.getMaxY() + 5) >= (sprite.getMinY()) && (this.getMaxY() + 5)<= sprite.getMinY();
			boolean withinRange = this.getMaxX() > sprite.getMinX() && this.getMinX() < sprite.getMaxX();
			if (bottomColiding && withinRange) {
				onGround = true;
				break;
			}
		}
		return onGround;
	}

}
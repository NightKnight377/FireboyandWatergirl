import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Door implements DisplayableSprite {

	private static Image image;
	private boolean visible = true;
	private static double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;
	private int direction = 0;
	private static boolean isMoving = false;
	private double originalY;;
	
	public Door() {

		if (image == null) {
			try {
				image = ImageIO.read(new File("res/Door.png"));
				this.height = this.image.getHeight(null);
				this.width = this.image.getWidth(null);
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}		
	}
	
	public Door(double minX, double minY, double maxX, double maxY, boolean visible) {
		
		if (image == null && visible) {
			try {
				image = ImageIO.read(new File("res/Door.png"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}		
		}
		
		this.centerX = (minX + maxX) / 2;
		this.centerY = (minY + maxY) / 2;
		this.width = maxX - minX;
		this.height = maxY - minY;
		this.visible = visible;
		originalY = centerY;
		
	}
	

	public Image getImage() {
		return image;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	//DISPLAYABLE
	
	public boolean getVisible() {
		return this.visible;
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
	
	public static void addCenterX(int value) {
		centerX += value;
	}

	public void addCenterY(int value) {
		centerY += value;
	}
	
	public boolean getDispose() {
		return dispose;
	}
	public static void openDoor(int direction) {
		this.direction = direction;
		isMoving = true;
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		if (isMoving == true) {
			centerY += direction;
		}
		if (centerY <= originalY -200 && centerY <= originalY) {
			isMoving = false;			
		}
	}

}

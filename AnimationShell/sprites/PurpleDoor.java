import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PurpleDoor implements DisplayableSprite {

	private static Image image;
	private boolean visible = true;
	private static double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;
	private int direction = 0;
	private double originalX = 0;
	private int distance = 0;
	
	public PurpleDoor() {

		if (image == null) {
			try {
				image = ImageIO.read(new File("res/PurpleDoor.png"));
				this.height = this.image.getHeight(null);
				this.width = this.image.getWidth(null);
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}		
	}
	
	public PurpleDoor(double minX, double minY, double maxX, double maxY, boolean visible) {
		
		if (image == null && visible) {
			try {
				image = ImageIO.read(new File("res/PurpleDoor.png"));
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
		originalX = centerX;
		
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
	
	public boolean getDispose() {
		return dispose;
	}
	
	public void distanceOpenDoor(int distance1) {
		distance = distance1;
	}
	
	public void openDoor(int direction1) {
		direction = direction1;
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		if (centerX < originalX && centerX > originalX - distance) {
			centerX += direction;
		} else if (direction == 1 && centerX <= originalX - distance) {
			centerX += direction;
		} else if (direction == -2 && centerX >= originalX) {
			centerX += direction;
		}
	}

}
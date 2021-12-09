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
	private double originalY = 0;
	
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
	
	public boolean getDispose() {
		return dispose;
	}
	public void openDoor(int direction1) {
		direction = direction1;
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		if (centerY < originalY - 200) {
			direction = 0;
			System.out.println("too high");
		}
		if (centerY > originalY) {
			direction = 0;
			System.out.println("too low");
			System.out.println(centerY);
			System.out.println(originalY);
		}
		System.out.println("made it");
		centerY += direction;
	}

}

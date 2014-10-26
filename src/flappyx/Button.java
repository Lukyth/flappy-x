package flappyx;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Button implements Entity {

	public static final int WIDTH = 170;
	public static final int HEIGHT = 80;
	public static final int WIDTH_CHOOSED = 200;
	public static final int HEIGHT_CHOOSED = 90;
	
	private float x;
	private float y;
	public Image image;
	public boolean choosed;

	public Button(int x, int y, String type) throws SlickException {
		this.x = x;
		this.y = y;
		image = new Image("res/button-" + type + ".png");
	}

	@Override
	public void render(Graphics g) {
		image.draw(x, y);
	}

	@Override
	public void update(int delta) {

	}

}

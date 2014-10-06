package flappyx;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background implements Entity{

	private float x;
	private float y;
	private float vy;
	private Image image;

	public Background(float x, float y, float vy) throws SlickException {
		this.x = x;
		this.y = y;
		this.vy = vy;
		image = new Image("res/bg-nyan-cat.jpg");
	}

	@Override
	public void render(Graphics g) {
		image.draw(0, y);
	}

	@Override
	public void update(int delta) {
		y += vy;
	}
	
}

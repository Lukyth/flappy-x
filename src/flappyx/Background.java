package flappyx;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background implements Entity {

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
		image.draw(x, y);
	}

	@Override
	public void update(int delta) {
		updatePosition();
		updateWrapAround();
	}

	protected void updatePosition() {
		y += vy;
	}

	private void updateWrapAround() {
		if (isBelowScene()) {
			moveToAboveScene();
		}
	}

	private void moveToAboveScene() {
		y -= 2 * Setup.GAME_HEIGHT;
	}

	private boolean isBelowScene() {
		return y > Setup.GAME_HEIGHT;
	}

}

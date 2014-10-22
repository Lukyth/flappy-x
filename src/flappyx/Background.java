package flappyx;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background implements Entity {

	private float y;
	private float vy;
	private Image image;

	public Background(float y, float vy) throws SlickException {
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
		y -= (MainGame.GAME_HEIGHT / (MainGame.OBSTACLE_COUNT - 1f))
				* MainGame.OBSTACLE_COUNT;
	}

	private boolean isBelowScene() {
		return y > MainGame.GAME_HEIGHT;
	}

}

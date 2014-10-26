package flappyx;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Obstacle implements Entity {

	static public final int WIDTH = 480;
	static public final int HEIGHT = 80;
	static public final int SPACE = 160;

	private Image leftImage;
	private Image rightImage;

	protected float x;
	protected float y;
	protected float vy;

	public Obstacle(float x, float y, float vy) throws SlickException {
		this.x = x;
		this.y = y;
		this.vy = vy;

		leftImage = new Image("res/obstacle-longcat-left.png");
		rightImage = new Image("res/obstacle-longcat-right.png");

	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public static int randomX() {
		Random random = new Random();
		return (SPACE / 2) + random.nextInt(Setup.GAME_WIDTH - SPACE);
	}

	@Override
	public void render(Graphics g) {
		leftImage.draw(x - (SPACE / 2) - WIDTH, y
				- (HEIGHT / 2));
		rightImage.draw(x + (SPACE / 2), y
				- (HEIGHT / 2));
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
		y -= (Setup.GAME_HEIGHT / (MainGame.OBSTACLE_COUNT - 1f))
				* MainGame.OBSTACLE_COUNT;
		x = randomX();
	}

	private boolean isBelowScene() {
		return y > Setup.GAME_HEIGHT + (HEIGHT / 2);
	}

}

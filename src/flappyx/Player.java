package flappyx;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import flappyx.Entity;

public class Player implements Entity {

	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	
	private static final float DIRECTION_RIGHT = 45;
	private static final float DIRECTION_LEFT = 315;

	private float x;
	private float y;
	private float velocity;
	private float acceleration;
	private float direction;

	private Image image;

	public Player(float x, float y, float v, float a, float dir)
			throws SlickException {

		this.x = x;
		this.y = y;
		this.velocity = v;
		this.acceleration = a;
		this.direction = dir;

		image = new Image("res/player-nyan-cat.png");
	}

	public void render(Graphics g) {
		image.draw(x - (WIDTH / 2), y - (HEIGHT / 2));
	}

	public void update(int delta) {
		updatePosition();
		updateVelocity();
	}

	private void updatePosition() {
		x += Math.sin(Math.toRadians(direction)) * velocity;
		y -= Math.cos(Math.toRadians(direction)) * velocity;
	}

	private void updateVelocity() {
		velocity += acceleration;
	}

	public boolean isCollide(Obstacle obstacle) {
		return CollisionDetector.isCollide(x, y, obstacle.getX(),
				obstacle.getY());
	}

	public void switchDir() {
		if (direction == DIRECTION_RIGHT) {
			direction = DIRECTION_LEFT;
		} else {
			direction = DIRECTION_RIGHT;
		}
		image = image.getFlippedCopy(true, false);
	}

	public void switchAcc() {
		acceleration *= -1;
	}

	public boolean isOutOfScreen() {
		if (x <= 0 || x >= Setup.GAME_WIDTH || y <= 0 || y >= Setup.GAME_HEIGHT) {
			return true;
		}
		return false;
	}

}

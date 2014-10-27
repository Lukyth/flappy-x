package flappyx;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import flappyx.Entity;

public class Player implements Entity {

	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;

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
		if (direction == 45) {
			direction = 315;
		} else {
			direction = 45;
		}
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

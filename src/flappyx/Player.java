package flappyx;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {

	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	
	private float x;
	private float y;
	private float vx;
	private float vy;
	private float ax;
	private float ay;
	private float aTurnRate;
	private float aNormalRate;
	private float aNormal;
	
	private Image image;
	
	public Player(float x, float y, float a, float aTurnRate, float aNormalRate) throws SlickException {
		this.x = x;
		this.y = y;
		this.vx = 0;
		this.vy = 0;
		this.aNormal = a;
		this.ax = aNormal;
		this.ay = aNormal;
		this.aTurnRate = aTurnRate;
		this.aNormalRate = aNormalRate;
		
		image = new Image("res/circle.png");
	}

	public void render() {
		image.draw(x - WIDTH/2, MainGame.GAME_HEIGHT - y - (HEIGHT/2));
	}
	
	public void update() {
		updatePosition();
		updateVelocity();
		updateAcceleration();
	}

	private void updatePosition() {
		x += vx;
		y += vy;
	}
	
	private void updateVelocity() {
		vx += ax;
		vy += ay;
	}

	private void updateAcceleration() {
		ax = turnAccelerationToNormal(ax);
		ay = turnAccelerationToNormal(ay);
	}

	private float turnAccelerationToNormal (float a) {
		if (a > aNormal) {
			a -= aNormalRate;
		}
		else if (a < -aNormal) {
			a += aNormalRate;
		}
		return a;
	}
	
	public void switchX() {
		ax = aTurnRate * ax;
	}
	
	public void switchY() {
		ay = aTurnRate * ay;
	}

}

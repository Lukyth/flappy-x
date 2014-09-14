package jittatx;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Jittat {

	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	
	private float x;
	private float y;
	private float vx;
	private float vy;
	private float ax;
	private float ay;
	
	private Image image;
	
	public Jittat(float x, float y, float v, float a) throws SlickException {
		this.x = x;
		this.y = y;
		this.vx = v;
		this.vy = v;
		this.ax = a;
		this.ay = a;
		image = new Image("res/jittat.png");
	}

	public void render() {
		image.draw(x - WIDTH/2, MainGame.GAME_HEIGHT - y - (HEIGHT/2));
	}
	
	public void update() {
		updatePosition();
		updateVelocity();
	}

	private void updatePosition() {
		x += vx;
		y += vy;
	}
	
	private void updateVelocity() {
		vx += ax;
		vy += ay;
	}

	public void switchX() {
		ax = -ax;
	}
	
	public void switchY() {
		ay = -ay;
	}

}

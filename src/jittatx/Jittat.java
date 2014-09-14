package jittatx;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Jittat {

	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	
	private float x;
	private float y;
	private float dir;
	private float v;
	private float a;
	
	private Image image;
	
	public Jittat(float x, float y, float dir, float v) throws SlickException {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.v = v;
		this.a = 0.2f;
		image = new Image("res/jittat.png");
	}

	public void render() {
		image.draw(x - WIDTH/2, MainGame.GAME_HEIGHT - y - (HEIGHT/2));
	}
	
	public void update() {
		x += Math.sin(Math.toRadians(dir)) * v;
		y -= Math.cos(Math.toRadians(dir)) * v;
		v += a;
	}

}

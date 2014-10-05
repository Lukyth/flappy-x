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
	
	public Obstacle (float x, float y, float vy) throws SlickException {
		this.x = x;
		this.y = y;
		this.vy = vy;
		
		leftImage = new Image("res/obstacle-longcat-left.png");
		rightImage = new Image("res/obstacle-longcat-right.png");
		
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
	
	public static int randomX () {
		Random random = new Random();
		return (SPACE / 2) + random.nextInt(MainGame.GAME_WIDTH - SPACE);
	}
	
	@Override
	public void render(Graphics g) {
		leftImage.draw(MainGame.GAME_WIDTH - (x + WIDTH + SPACE/2), y - HEIGHT/2);
		rightImage.draw(MainGame.GAME_WIDTH - (x - SPACE/2), y - HEIGHT/2);
	}
	
	@Override
	public void update(int delta) {
	
	}
	
}

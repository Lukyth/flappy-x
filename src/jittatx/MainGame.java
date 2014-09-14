package jittatx;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MainGame extends BasicGame {

	private Jittat jittat;
	public static final int GAME_WIDTH = 640;
	public static final int GAME_HEIGHT = 480;
	
	public MainGame(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		jittat.render();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		jittat = new Jittat(GAME_WIDTH/2, GAME_HEIGHT/2, 135, 2);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		jittat.update();
	}
	
	public static void main(String[] args) {
		try {
			MainGame game = new MainGame("Super Ship Game");
			AppGameContainer container = new AppGameContainer(game);
			container.setDisplayMode(640, 480, false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}

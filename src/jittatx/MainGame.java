package jittatx;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MainGame extends BasicGame {

	public MainGame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub

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

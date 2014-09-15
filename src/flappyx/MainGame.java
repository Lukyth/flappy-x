package flappyx;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MainGame extends BasicGame {

	private Player player;
	public static final int GAME_WIDTH = 640;
	public static final int GAME_HEIGHT = 480;
	
	public MainGame(String title) {
		super(title);
	}

	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_SLASH) {
			System.out.println("slash");
			player.switchY();
		}
		if (key == Input.KEY_Z) {
			player.switchX();
		}
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		player.render();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		player = new Player(GAME_WIDTH/2, GAME_HEIGHT/2, 0f, 0.1f);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		player.update();
	}
	
	public static void main(String[] args) {
		try {
			MainGame game = new MainGame("Flappy X"); 
			AppGameContainer container = new AppGameContainer(game);
			container.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, false);
			container.setMinimumLogicUpdateInterval(1000 / 60);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}

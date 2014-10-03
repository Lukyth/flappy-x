package flappyx;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class MainGame extends BasicGame {

	private Player player;
	private Audio wavEffect;
	
	public static final int GAME_WIDTH = 960;
	public static final int GAME_HEIGHT = 640;
	
	public MainGame(String title) {
		super(title);
	}

	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_SLASH) {
			player.switchY();
			wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
		}
		if (key == Input.KEY_Z) {
			player.switchX();
			wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
		}
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		player.render();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		player = new Player(GAME_WIDTH/2, GAME_HEIGHT/2, 0.2f, -2f, 0.04f);
	    try {
			wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/bading.wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			container.setMinimumLogicUpdateInterval(1000/60);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}

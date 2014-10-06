package flappyx;

import java.io.IOException;
import java.util.ArrayList;

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
	
	public static final int GAME_WIDTH = 480;
	public static final int GAME_HEIGHT = 640;
	public static final int OBSTACLE_COUNT = 4;
	public static final int BACKGROUND_COUNT = 2;
	
	private Player player;
	private Obstacle[] obstacles;
	private Background[] backgrounds;
	private Audio wavEffect;
	private ArrayList<Entity> entities;
	
	public MainGame(String title) {
		super(title);
		entities = new ArrayList<Entity>();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		initBackground();
		initObstacle();
		initPlayer();
		initSound();
	}

	private void initBackground() throws SlickException {
		backgrounds = new Background[BACKGROUND_COUNT];
		for(int i = 0; i < BACKGROUND_COUNT; i++) {
			backgrounds[i] = new Background(-GAME_HEIGHT * i, 1f);
			entities.add(backgrounds[i]);
		}
	}
	
	private void initObstacle() throws SlickException {
		obstacles = new Obstacle[OBSTACLE_COUNT];
		for (int i = 0; i < OBSTACLE_COUNT; i++) {
			obstacles[i] = new Obstacle(Obstacle.randomX(), -MainGame.GAME_HEIGHT + Obstacle.HEIGHT + (GAME_HEIGHT / (OBSTACLE_COUNT-1f)) * i - (GAME_HEIGHT / 2f), 4f);
			entities.add(obstacles[i]);
		}
	}
	
	private void initPlayer() throws SlickException{
		player = new Player(GAME_WIDTH/2, GAME_HEIGHT/4, 0.2f, -2f, 0.04f);
		entities.add(player);
	}

	private void initSound() {
		try {
			wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/bading.wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		for (Entity entity : entities) {
			entity.render(g);
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		for (Entity entity : entities) {
			entity.update(delta);
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
			case Input.KEY_SLASH:
				player.switchY();
				wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
				break;
			case Input.KEY_Z:
				player.switchX();
				wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
				break;
		}
	}

	public static void main(String[] args) throws SlickException {
		MainGame game = new MainGame("Flappy X"); 
		AppGameContainer container = new AppGameContainer(game);
		container.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, false);
		container.setMinimumLogicUpdateInterval(1000/60);
		container.start();
	}

}

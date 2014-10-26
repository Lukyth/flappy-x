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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class MainGame extends BasicGameState {

	public static final int OBSTACLE_COUNT = 4;
	public static final int BACKGROUND_COUNT = 2;

	private Player player;
	private Obstacle[] obstacles;
	private Background[] backgrounds;
	private Audio wavEffect;
	private ArrayList<Entity> entities;
	private boolean isGameOver;

	public MainGame() {
		super();
		entities = new ArrayList<Entity>();
	}

	private void initBackground() throws SlickException {
		backgrounds = new Background[BACKGROUND_COUNT];
		for (int i = 0; i < BACKGROUND_COUNT; i++) {
			backgrounds[i] = new Background(-Setup.GAME_HEIGHT * i, 1f);
			entities.add(backgrounds[i]);
		}
	}

	private void initObstacle() throws SlickException {
		obstacles = new Obstacle[OBSTACLE_COUNT];
		for (int i = 0; i < OBSTACLE_COUNT; i++) {
			obstacles[i] = new Obstacle(Obstacle.randomX(),
					-Setup.GAME_HEIGHT + Obstacle.HEIGHT
							+ ((Setup.GAME_HEIGHT / (OBSTACLE_COUNT - 1f)) * i)
							- (Setup.GAME_HEIGHT / 2f), 4f);
			entities.add(obstacles[i]);
		}
	}

	private void initPlayer() throws SlickException {
		player = new Player(Setup.GAME_WIDTH / 2, Setup.GAME_HEIGHT *0.8f, 0, 0.3f, 45f);
		entities.add(player);
	}

	private void initSound() {
		try {
			wavEffect = AudioLoader.getAudio("WAV",
					ResourceLoader.getResourceAsStream("res/bading.wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean checkCollision() {
		for (Obstacle obstacle: obstacles) {
			if (player.isCollide(obstacle)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_SLASH:
			player.switchDir();
			wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
			break;

		case Input.KEY_Z:
			player.switchAcc();
			wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
			break;
		}
	}

	@Override
	public void init(GameContainer container, StateBasedGame arg1)
			throws SlickException {
		isGameOver = false;
		initBackground();
		initObstacle();
		initPlayer();
		initSound();
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g)
			throws SlickException {
		for (Entity entity : entities) {
			entity.render(g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame arg1, int delta)
			throws SlickException {
		for (Entity entity : entities) {
			entity.update(delta);
		}
		if (checkCollision()) {
			isGameOver = true;
		}
	}

	@Override
	public int getID() {
		return 1;
	}

}

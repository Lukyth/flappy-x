package flappyx;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

public class MainGame extends BasicGameState {

	public static final int OBSTACLE_COUNT = 3;
	public static final int BACKGROUND_COUNT = 2;
	public static final int BUTTON_COUNT = 2;
	private static final int POPUP_WIDTH = 400;
	private static final int POPUP_HEIGHT = 480;
	private static final int BUTTON_MARGIN = 100;

	private Player player;
	private Obstacle[] obstacles;
	private Background[] backgrounds;
	private Audio wavEffect;
	private ArrayList<Entity> entities;
	private ArrayList<Entity> entitiesGameOver;
	public static int score;
	public static boolean scored;
	private boolean isGameOver;

	private Button[] buttons;
	private TrueTypeFont font;

	private int choice;

	public MainGame() {
		super();
		entities = new ArrayList<Entity>();
		entitiesGameOver = new ArrayList<Entity>();
	}

	private void initGame() throws SlickException {
		if (!isGameOver) {
			initButton();
		}
		choice = 1;
		buttons[choice].choosed = true;
		Button.clearButtonChoice(buttons, choice);
		isGameOver = false;
		score = 0;
		scored = false;
		initBackground();
		initObstacle();
		initPlayer();
		initSound();
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
			obstacles[i] = new Obstacle(Obstacle.randomX(), -Setup.GAME_HEIGHT
					+ Obstacle.HEIGHT
					+ ((Setup.GAME_HEIGHT / (OBSTACLE_COUNT - 1f)) * i)
					- (Setup.GAME_HEIGHT / 2f), 4f);
			entities.add(obstacles[i]);
		}
	}

	private void initPlayer() throws SlickException {
		player = new Player(Setup.GAME_WIDTH / 2, Setup.GAME_HEIGHT * 0.8f, 0,
				0.3f, 45f);
		entities.add(player);
	}

	private void initButton() throws SlickException {
		buttons = new Button[BUTTON_COUNT];
		for (int i = 0; i < BUTTON_COUNT; i++) {
			String type = null;
			if (i == 0) {
				type = "menu";
			} else if (i == 1) {
				type = "retry";
			}
			buttons[i] = new Button(Setup.GAME_WIDTH / 2f, Setup.GAME_HEIGHT
					* 0.75f - (BUTTON_MARGIN * i), type);
			entitiesGameOver.add(buttons[i]);
		}
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
		if (hitObstacle() || hitEdge()) {
			return true;
		}
		return false;
	}

	private boolean hitObstacle() {
		for (Obstacle obstacle : obstacles) {
			if (player.isCollide(obstacle)) {
				return true;
			}
		}
		return false;
	}

	private boolean hitEdge() {
		if (player.isOutOfScreen()) {
			return true;
		}
		return false;
	}

	@Override
	public void keyPressed(int key, char c) {

		if (!isGameOver) {
			switch (key) {
			case Input.KEY_LEFT:
			case Input.KEY_RIGHT:
				player.switchDir();
				// wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
				break;

			case Input.KEY_UP:
			case Input.KEY_DOWN:
				player.switchAcc();
				// wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
				break;
			}
		} else {
			switch (key) {
			case Input.KEY_UP:
				if (choice != 1) {
					choice = 1;
				}
				break;
			case Input.KEY_DOWN:
				if (choice != 0) {
					choice = 0;
				}
				break;
			}
			buttons[choice].choosed = true;
			Button.clearButtonChoice(buttons, choice);
		}
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		initGame();
		try {
			InputStream inputStream = ResourceLoader
					.getResourceAsStream("res/Hanzipen.ttc");
			Font scoreFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			scoreFont = scoreFont.deriveFont(48f);
			font = new TrueTypeFont(scoreFont, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		for (Entity entity : entities) {
			entity.render(g);
		}
		if (isGameOver) {
			renderPopup(g);
		} else {
			font.drawString(110f, 140f, "Your Score : " + score, Color.darkGray);
		}

	}

	private void renderPopup(Graphics g) throws SlickException {
		g.setColor(Color.lightGray);
		g.drawImage(new Image("res/popup-gameover.png"),
				(Setup.GAME_WIDTH - POPUP_WIDTH) / 2,
				(Setup.GAME_HEIGHT - POPUP_HEIGHT) / 2);
		for (Entity entity : entitiesGameOver) {
			entity.render(g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (!isGameOver) {
			for (Entity entity : entities) {
				entity.update(delta);
			}
		} else {
			if (container.getInput().isKeyPressed(Input.KEY_ENTER)) {
				game.enterState(choice, new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
				initGame();
			}
			updatePopup(delta);
		}
		if (checkCollision()) {
			isGameOver = true;
		}
	}

	private void updatePopup(int delta) throws SlickException {
		for (Entity entity : entitiesGameOver) {
			entity.update(delta);
		}
	}

	@Override
	public int getID() {
		return 1;
	}

}

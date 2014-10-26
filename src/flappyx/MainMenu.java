package flappyx;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends BasicGameState {

	public static final int BACKGROUND_COUNT = 2;
	public static final int BUTTON_COUNT = 2;

	private int choice;
	private Background[] backgrounds;
	private Button[] buttons;
	private ArrayList<Entity> entities;

	private void initBackground() throws SlickException {
		backgrounds = new Background[BACKGROUND_COUNT];
		for (int i = 0; i < BACKGROUND_COUNT; i++) {
			backgrounds[i] = new Background(-Setup.GAME_HEIGHT * i, 0.5f);
			entities.add(backgrounds[i]);
		}
	}

	private void initButton() throws SlickException {
		buttons = new Button[BUTTON_COUNT];
		for (int i = 0; i < BUTTON_COUNT; i++) {
			buttons[i] = new Button(Setup.GAME_WIDTH / 2,
					Setup.GAME_HEIGHT / 2, "play");
			entities.add(buttons[i]);
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_DOWN:
			if (choice != 0) {
				choice = 0;
			}
			break;
		case Input.KEY_UP:
			if (choice != 1) {
				choice = 1;
			}
			break;
		}
		buttons[choice].choosed = true;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		entities = new ArrayList<Entity>();
		initBackground();
		initButton();
		choice = 1;
		buttons[choice].choosed = true;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		for (Entity entity : entities) {
			entity.render(g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ENTER)) {
			if (choice == 0) {
				System.exit(0);
			}
			game.enterState(choice, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
		for (Entity entity : entities) {
			entity.update(delta);
		}
	}

	@Override
	public int getID() {
		return 0;
	}

}

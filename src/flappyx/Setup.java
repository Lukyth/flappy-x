package flappyx;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Setup extends StateBasedGame {

	public static final int GAME_WIDTH = 480;
	public static final int GAME_HEIGHT = 640;
	public static final int FRAME_DELAY = 1000 / 60;

	public Setup(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new MainGame());
	}

	public static void main(String[] args) throws SlickException {
		Setup game = new Setup("Flappy X");
		AppGameContainer container = new AppGameContainer(game);
		container.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, false);
		container.setMinimumLogicUpdateInterval(FRAME_DELAY);
		container.start();
	}

}

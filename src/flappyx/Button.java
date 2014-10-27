package flappyx;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Button implements Entity {

	public static final int WIDTH = 170;
	public static final int HEIGHT = 80;
	public static final int WIDTH_CHOOSED = 200;
	public static final int HEIGHT_CHOOSED = 90;

	private float x;
	private float y;
	private Image image;
	private String type;
	
	public boolean choosed;

	public Button(float f, float g, String type) throws SlickException {
		this.x = f;
		this.y = g;
		this.type = type;
		image = new Image("res/button-" + type + ".png");
	}

	@Override
	public void render(Graphics g) {
		if (choosed) {
			image.draw(x - (WIDTH_CHOOSED / 2), y - (HEIGHT_CHOOSED / 2));
		} else {
			image.draw(x - (WIDTH / 2), y - (HEIGHT / 2));
		}
	}

	@Override
	public void update(int delta) throws SlickException {
		if (choosed) {
			image = new Image("res/button-" + type + "-choosed.png");

		} else {
			image = new Image("res/button-" + type + ".png");
		}
	}

	public static void clearButtonChoice(Button[] buttons, int choice) {
		for (int i = 0; i < buttons.length; i++) {
			if (i != choice) {
				buttons[i].choosed = false;
			}
		}
		buttons[choice].choosed = true;
	}

	public static void setButtonChoice(Button[] buttons, int choice) {
		clearButtonChoice(buttons, choice);
	}

}

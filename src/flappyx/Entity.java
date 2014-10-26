package flappyx;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Entity {
	void render(Graphics g);

	void update(int delta) throws SlickException;
}

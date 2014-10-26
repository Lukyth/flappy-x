package flappyx;

public class CollisionDetector {
	public static boolean isCollide(float pX, float pY, float oX, float oY) {
		if (pY - (Player.HEIGHT / 2) <= oY + (Obstacle.HEIGHT / 2)
				&& pY - (Player.HEIGHT / 2) >= oY
						- ((Obstacle.HEIGHT / 2) + Player.HEIGHT)) {
			MainGame.scored = false;
			if (pX - (Player.WIDTH / 2) > oX - (Obstacle.SPACE / 2)
					&& pX + (Player.WIDTH / 2) < oX + (Obstacle.SPACE / 2)) {
				return false;
			} else {
				return true;
			}
		}
		if (!MainGame.scored
				&& pY + (Player.HEIGHT / 2) <= oY - (Obstacle.HEIGHT / 2)
				&& pY + (Player.HEIGHT / 2) >= oY
						- ((Obstacle.HEIGHT / 2) + Player.HEIGHT)) {
			MainGame.score++;
			MainGame.scored = true;
		}
		return false;
	}
}

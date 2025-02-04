package poi.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import poi.game.Poi;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Poi.WIDTH;
		config.height = Poi.HEIGHT;
		new LwjglApplication(new Poi(new DesktopLeaderboard()), config);
	}
}

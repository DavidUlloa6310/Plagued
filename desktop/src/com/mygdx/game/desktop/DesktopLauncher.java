package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.PlaguedGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PlaguedGame.WIDTH;
		config.height = PlaguedGame.HEIGHT;
		config.title = PlaguedGame.TITLE;
		new LwjglApplication(new PlaguedGame(), config);
	}
}

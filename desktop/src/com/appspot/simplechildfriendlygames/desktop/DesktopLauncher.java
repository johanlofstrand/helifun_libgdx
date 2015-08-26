package com.appspot.simplechildfriendlygames.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.appspot.simplechildfriendlygames.HelifunGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1400;
		config.height=980;
		new LwjglApplication(new HelifunGame(), config);
	}
}

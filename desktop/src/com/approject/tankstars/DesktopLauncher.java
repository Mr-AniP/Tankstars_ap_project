package com.approject.tankstars;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.approject.tankstars.MytankstarsGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(1200, 561);
		config.useVsync(true);
		config.setForegroundFPS(60);
		config.setTitle("Tankstars");
		new Lwjgl3Application(new MytankstarsGame(), config);
	}
}

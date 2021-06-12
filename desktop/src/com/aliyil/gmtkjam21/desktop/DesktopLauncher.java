package com.aliyil.gmtkjam21.desktop;

import com.aliyil.gmtkjam21.OsBridge;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.aliyil.gmtkjam21.Game;

public class DesktopLauncher{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.foregroundFPS = 0;
		config.backgroundFPS = 0;
		config.vSyncEnabled = true;
		config.samples = 8;
		config.title = "GMTK Jam 2021";
		new LwjglApplication(new Game(new OsBridge() {
		}), config);
	}
}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Screens.GameOverScreen;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Screens.StartScreen;

public class Pedes extends Game {
	public static final int V_WIDTH = 1920;
	public static final int V_HEIGHT = 1080;


	public SpriteBatch batch;

	public static AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("music/short.mp3", Music.class);
		manager.load("music/anong kailangan.mp3", Music.class);
		manager.finishLoading();


		setScreen(new StartScreen(this));
	}

	@Override
	public void render () {

		super.render();
		manager.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
	}
}

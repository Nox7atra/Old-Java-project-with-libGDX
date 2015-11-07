package com.simplecatgames.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.simplecatgames.cutlines.MyGame;

public class LoadingScreen implements Screen{
	Texture backgroundTexture = new Texture(Gdx.files.internal("data/img/loading.jpg"), false);
    Sprite backgroundSprite;
    private SpriteBatch spriteBatch = new SpriteBatch();
	MyGame game;
	long startTime;
	int lvlnum; 
	public LoadingScreen(MyGame game, int lvlnum) {
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setSize(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		startTime = System.nanoTime();
		this.lvlnum = lvlnum;
	}
	boolean pauseForSecond(long startTime){
		
		if(System.nanoTime()/10000000 - startTime/10000000 < 10) return false; /// 100 - 1 second
		else {
			return true;
		}
	}
	public void renderBackground() {
		backgroundSprite.draw(spriteBatch);
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
        spriteBatch.begin();
			renderBackground();
		spriteBatch.end();
		if(pauseForSecond(startTime)){
			((MyGame)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game, lvlnum));
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		spriteBatch.begin();
			renderBackground();
		spriteBatch.end();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}

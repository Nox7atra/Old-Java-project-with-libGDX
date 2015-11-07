package com.simplecatgames.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.simplecatgames.cutlines.MyGame;


public class MainScreen implements Screen, InputProcessor{
	private Stage stage = new Stage();
	private Table table = new Table();
	private Skin skin = new Skin(Gdx.files.internal("data/menuSkin.json"), 
			new TextureAtlas(Gdx.files.internal("data/menuAtlas.atlas")));
	private TextButton buttonShop,
	        buttonPlay, buttonTut;
	int lvl;
	public static float CAMERA_WIDTH = 1280f;
	public static  float CAMERA_HEIGHT = 640f;
	float ppuX, ppuY;
	FileHandle from = Gdx.files.internal("data/img/menu.PNG");
	Texture backgroundTexture = new Texture(from, false);
    Sprite backgroundSprite;
    private SpriteBatch spriteBatch = new SpriteBatch();
	MyGame game;
	
	public MainScreen(MyGame game){
		this.game = game;
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		ppuX = Gdx.graphics.getWidth() / CAMERA_WIDTH;
		ppuY = Gdx.graphics.getHeight() / CAMERA_HEIGHT;
		//skin.getFont("default-font").setColor(1f, 1f, 1f, 1f);
		buttonShop = new TextButton("Shop", skin);
		buttonTut = new TextButton("How To Play", skin);
		buttonPlay = new TextButton("Play", skin);
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setSize(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		//skin.getFont("default-font").setScale( (), );
		buttonPlay.getLabel().setFontScale((float)(ppuX/1.7), (float) (ppuY/1.7));
		buttonTut.getLabel().setFontScale((float)(ppuX/1.7), (float) (ppuY/1.7));
		buttonShop.getLabel().setFontScale((float)(ppuX/1.7), (float) (ppuY/1.7));
	}

	public void renderBackground() {
		backgroundSprite.draw(spriteBatch);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
			renderBackground();
		spriteBatch.end();
        stage.act();
        stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		 buttonPlay.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	            //Same way we moved here from the Splash Screen
	            //We set it to new Splash because we got no other screens
	            //otherwise you put the screen there where you want to go
	            ((MyGame)Gdx.app.getApplicationListener()).setScreen(new LevelsScreen(game));
	        }
	    });
	    buttonShop.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	//((MyGame)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game, lvl));
	            // or System.exit(0);
	        }
	    });
	    table.add().size(ppuX*300,ppuY*100).padBottom(60).row();
		table.add(buttonPlay).size(ppuX*250,ppuY*100).padBottom(20);
		table.add(buttonTut).size(ppuX*250,ppuY*100).padBottom(20).padRight(3);
	    table.add(buttonShop).size(ppuX*250,ppuY*100).padBottom(20).padLeft(3).row();
        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
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
		 stage.dispose();
	     skin.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(stage.keyDown(Keys.BACK)){
	           // Do your optional back button handling (show pause menu?)
				System.exit(0);
	     }
		      
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}

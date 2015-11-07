package com.simplecatgames.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.simplecatgames.cutlines.MyGame;


public class LevelsScreen implements Screen, InputProcessor{
	private Stage stage = new Stage();
	private Table table = new Table();
	private Skin skin = new Skin(Gdx.files.internal("data/menuSkin.json"), 
			new TextureAtlas(Gdx.files.internal("data/menuAtlas.atlas")));
	private TextButton buttonLvl12,
	        buttonLvl1,
	        buttonLvl2,
	        buttonLvl3,
    		buttonLvl4,
	        buttonLvl5,
	        buttonLvl6,
    		buttonLvl7,
	        buttonLvl8,
    		buttonLvl9,
	        buttonLvl10,
	        buttonLvl11;
	
	private Label title = new Label("Levels",skin);
	
	public static float CAMERA_WIDTH = 1280f;
	public static  float CAMERA_HEIGHT = 640f;
	int BUTTONLVL_WIDTH = 140;
	int BUTTONLVL_HEIGHT = 140;
	float ppuX, ppuY;
	
	FileHandle from = Gdx.files.internal("data/img/color.png");
	Texture backgroundTexture = new Texture(from, false);
    Sprite backgroundSprite;
    SpriteBatch spriteBatch = new SpriteBatch();
    
	MyGame game;
	
	public LevelsScreen(MyGame game){
		this.game = game;
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		ppuX = Gdx.graphics.getWidth() / CAMERA_WIDTH;
		ppuY = Gdx.graphics.getHeight() / CAMERA_HEIGHT;
		
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setSize(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		
		//skin.getFont("default-font").setScale((float) (ppuX*2), (float) (ppuY));
	
		title.setFontScale((float) (ppuX), ppuY);
		
		buttonInit();
	}
	public void renderBackground() {
		backgroundSprite.draw(spriteBatch);
	}
	private void buttonInit(){
		buttonLvl1 = new TextButton("1", skin);
		buttonLvl2 = new TextButton("2", skin);
        buttonLvl3 = new TextButton("3", skin);
        buttonLvl4 = new TextButton("4", skin);
		buttonLvl5 = new TextButton("5", skin);
        buttonLvl6 = new TextButton("6", skin);
        buttonLvl7 = new TextButton("7", skin);
		buttonLvl8 = new TextButton("8", skin);
        buttonLvl9 = new TextButton("9", skin);
		buttonLvl10 = new TextButton("10", skin);
        buttonLvl11 = new TextButton("11", skin);
        buttonLvl12 = new TextButton("12", skin);
        buttonLvl1.getLabel().setFontScale((float) (ppuX), ppuY);
        buttonLvl2.getLabel().setFontScale((float) (ppuX), ppuY);
        buttonLvl3.getLabel().setFontScale((float) (ppuX), ppuY);
        buttonLvl4.getLabel().setFontScale((float) (ppuX), ppuY);
        buttonLvl5.getLabel().setFontScale((float) (ppuX), ppuY);
        buttonLvl6.getLabel().setFontScale((float) (ppuX), ppuY);
        buttonLvl7.getLabel().setFontScale((float) (ppuX), ppuY);
        buttonLvl8.getLabel().setFontScale((float) (ppuX), ppuY);
        buttonLvl9.getLabel().setFontScale((float) (ppuX), ppuY);
        buttonLvl10.getLabel().setFontScale((float) (ppuX), ppuY);
        buttonLvl11.getLabel().setFontScale((float) (ppuX), ppuY);
        buttonLvl12.getLabel().setFontScale((float) (ppuX), ppuY);
	}
	void onClick(){
		buttonLvl1.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	            //Same way we moved here from the Splash Screen
	            //We set it to new Splash because we got no other screens
	            //otherwise you put the screen there where you want to go
	            ((MyGame)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game, 1));
	        }
	    });
		buttonLvl2.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	((MyGame)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game, 2));
	            // or System.exit(0);
	        }
	    });
		buttonLvl3.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	((MyGame)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game, 3));
	            // or System.exit(0);
	        }
	    });
		buttonLvl4.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	((MyGame)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game, 4));
	            // or System.exit(0);
	        }
	    });
		buttonLvl5.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	((MyGame)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game, 5));
	            // or System.exit(0);
	        }
	    });
		buttonLvl6.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	((MyGame)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game, 6));
	            // or System.exit(0);
	        }
	    });
		buttonLvl7.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	((MyGame)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game, 7));
	            // or System.exit(0);
	        }
	    });
		buttonLvl8.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	((MyGame)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game, 8));
	            // or System.exit(0);
	        }
	    });
		buttonLvl9.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	((MyGame)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game, 9));
	            // or System.exit(0);
	        }
	    });
		buttonLvl10.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	((MyGame)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game, 10));
	            // or System.exit(0);
	        }
	    });
		buttonLvl11.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	((MyGame)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game, 11));
	            // or System.exit(0);
	        }
	    });
		buttonLvl12.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	((MyGame)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game, 12));
	            // or System.exit(0);
	        }
	    });
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
      table.setSize(width, height);
		  
	}

	@Override
	public void show() {
		
		
		onClick();
		table.row();
	    table.add(title).padBottom(20).colspan(4);
	    table.row();
		
	    table.add(buttonLvl1).size(ppuX * BUTTONLVL_WIDTH,ppuY * BUTTONLVL_HEIGHT).padBottom(20).padLeft(10).padRight(10);
	    table.add(buttonLvl2).size(ppuX * BUTTONLVL_WIDTH,ppuY * BUTTONLVL_HEIGHT).padBottom(20).padLeft(10).padRight(10);
	    table.add(buttonLvl3).size(ppuX * BUTTONLVL_WIDTH,ppuY * BUTTONLVL_HEIGHT).padBottom(20).padLeft(10).padRight(10);
	    table.add(buttonLvl4).size(ppuX * BUTTONLVL_WIDTH,ppuY * BUTTONLVL_HEIGHT).padBottom(20).padLeft(10).padRight(10);
	    table.row();
	    table.add(buttonLvl5).size(ppuX * BUTTONLVL_WIDTH,ppuY * BUTTONLVL_HEIGHT).padBottom(20).padLeft(10).padRight(10);
	    table.add(buttonLvl6).size(ppuX * BUTTONLVL_WIDTH,ppuY * BUTTONLVL_HEIGHT).padBottom(20).padLeft(10).padRight(10);
	    table.add(buttonLvl7).size(ppuX * BUTTONLVL_WIDTH,ppuY * BUTTONLVL_HEIGHT).padBottom(20).padLeft(10).padRight(10);
	    table.add(buttonLvl8).size(ppuX * BUTTONLVL_WIDTH,ppuY * BUTTONLVL_HEIGHT).padBottom(20).padLeft(10).padRight(10);
	    table.row(); 
	    table.add(buttonLvl9).size(ppuX * BUTTONLVL_WIDTH,ppuY * BUTTONLVL_HEIGHT).padBottom(20).padLeft(10).padRight(10);
	    table.add(buttonLvl10).size(ppuX * BUTTONLVL_WIDTH,ppuY * BUTTONLVL_HEIGHT).padBottom(20).padLeft(10).padRight(10);
	    table.add(buttonLvl11).size(ppuX * BUTTONLVL_WIDTH,ppuY * BUTTONLVL_HEIGHT).padBottom(20).padLeft(10).padRight(10);
	    table.add(buttonLvl12).size(ppuX * BUTTONLVL_WIDTH,ppuY * BUTTONLVL_HEIGHT).padBottom(20).padLeft(10).padRight(10);
        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
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
		if(keyDown(Keys.BACK)){
	           // Do your optional back button handling (show pause menu?)
				((MyGame)Gdx.app.getApplicationListener()).setScreen(new MainScreen(game));
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

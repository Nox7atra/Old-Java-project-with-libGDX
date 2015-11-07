package com.simplecatgames.screen;

import com.badlogic.gdx.Gdx;
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


public class EndGameScreen implements Screen{
		private Stage stage = new Stage();
		private Table table = new Table();
		String str;
		FileHandle bS;
		private Skin skin = new Skin(Gdx.files.internal("data/menuSkin.json"), 
				new TextureAtlas(Gdx.files.internal("data/menuAtlas.atlas")));
		private TextButton Restart,
		       NewLevel;
		private Label title, points, bestScore;
		int lvl, winner, yourScore, bonus;
		
		public static float CAMERA_WIDTH = 1280f;
		public static  float CAMERA_HEIGHT = 640f;
		float ppuX, ppuY;
		
		FileHandle from = Gdx.files.internal("data/img/color.png");
		Texture backgroundTexture = new Texture(from, false);
	    Sprite backgroundSprite;
	    SpriteBatch spriteBatch = new SpriteBatch();
	    
		
		MyGame game;
		
		public EndGameScreen(MyGame game, int lvl, int winner, int score){
			this.game = game;
			this.lvl = lvl;
			ppuX = Gdx.graphics.getWidth() / CAMERA_WIDTH;
			ppuY = Gdx.graphics.getHeight() / CAMERA_HEIGHT;
			table.setFillParent(true);
			table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			this.yourScore = score;
			this.winner = winner;
			if(winner == 1){
				str = "You win!";
				writeOpenedLvl(lvl);
				bonus = 200;
			}
			else str = "Try again!";
			bS = Gdx.files.local("data/levels/lvl" + String.valueOf(lvl)+ "bestScore.txt");
			title = new Label(str ,skin);
		    points = new Label("Your score: " 
					+ String.valueOf(yourScore) + " (" + String.valueOf(bonus) + " bonus)",skin);
		    bestScore = new Label("Best score: " 
					+ BestScore(bS.readString()),skin);
			skin.getFont("default-font").setScale((float) (ppuX), (float) (ppuY));
			Restart = new TextButton("Restart", skin);
			NewLevel = new TextButton("Levels", skin);
			
			backgroundSprite = new Sprite(backgroundTexture);
			backgroundSprite.setSize(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		}
		

		public void renderBackground() {
			backgroundSprite.draw(spriteBatch);
		}
		
		void writeOpenedLvl(int lvl){
			String lvlName;
			FileHandle EnabledLvl;
			lvlName = "data/levels/lvl" + String.valueOf(lvl)+ "enabled.txt";
			EnabledLvl = Gdx.files.local(lvlName);
			EnabledLvl.writeString("1", false);
		}
		String BestScore(String temp){
			int tmpSum = 0;
			for(int i = 0; i < temp.length(); i++){
				if(Character.isDigit(temp.charAt(i))){
					tmpSum = tmpSum * 10 + Character.getNumericValue(temp.charAt(i));
				}
			}
			
			return String.valueOf(tmpSum);
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
			
			
			Restart.addListener(new ClickListener(){
		        @Override
		        public void clicked(InputEvent event, float x, float y) {
		            //Same way we moved here from the Splash Screen
		            //We set it to new Splash because we got no other screens
		            //otherwise you put the screen there where you want to go
		            ((MyGame)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game, lvl));
		            dispose();
		        }
		    });
		    NewLevel.addListener(new ClickListener(){
		        @Override
		        public void clicked(InputEvent event, float x, float y) {
		        	//((MyGame)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game, lvl));
		            // or System.exit(0);
					((MyGame)Gdx.app.getApplicationListener()).setScreen(new LevelsScreen(game));
					dispose();
		        }
		    });
		    table.row().colspan(2).top();
		    table.add(title).row().padBottom(10).colspan(2);
		    table.add(points).padBottom(10).row().colspan(2);
		    table.add(bestScore).padBottom(30).row().colspan(2);
		    table.row();
		    table.add(NewLevel).size(300 * ppuX,100 * ppuY).padRight(10).padBottom(10);
		    table.add(Restart).size(300 * ppuX,100 * ppuY).padBottom(10);
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
}

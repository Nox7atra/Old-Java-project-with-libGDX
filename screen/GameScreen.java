package com.simplecatgames.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.simplecatgames.cutlines.MyGame;
import com.simplecatgames.model.World;

public class GameScreen implements Screen, InputProcessor {
	long startTime;
	Vector3 p_begin;
	Vector3 p_end;
	World world;
	OrthographicCamera camera;
	int winner = -1;
	int score;
	int lvl;
	int lineWidth = 5;
	boolean YOUPLAY = true;
	boolean setTime = false;
	Vector3 touchPos1 = new Vector3();
	Vector3 touchPos2 = new Vector3();
	ShapeRenderer edge = new ShapeRenderer();
	ShapeRenderer circ = new ShapeRenderer();
	ShapeRenderer line = new ShapeRenderer();
	BitmapFont bonuses = new BitmapFont(Gdx.files.internal("data/fonts/font1.fnt"), false);
	BitmapFont scoreBoard = new BitmapFont(Gdx.files.internal("data/fonts/score.fnt"), false);
	FileHandle bestScore;
	SpriteBatch b_batch = new SpriteBatch();
	SpriteBatch s_batch = new SpriteBatch();
	MyGame myGame;
	boolean clicked;
	FileHandle from ;
	Texture backgroundTexture;
    Sprite backgroundSprite;
    private SpriteBatch spriteBatch = new SpriteBatch();
	//private Skin skin = new Skin(Gdx.files.internal("data/menuSkin.json"), 
			//new TextureAtlas(Gdx.files.internal("data/menuAtlas.atlas")));
	public GameScreen(MyGame game, int levelNum) {
		this.myGame = game;
		this.lvl = levelNum;
		p_begin = new Vector3();
		p_end = new Vector3();
        this.camera = new OrthographicCamera(World.CAMERA_WIDTH, World.CAMERA_HEIGHT);
		SetCamera(World.CAMERA_WIDTH / 2f, World.CAMERA_HEIGHT / 2f);
		world = new World(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, lvl, game);
		Gdx.input.setInputProcessor(world);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.input.setCatchBackKey(true);
		from = Gdx.files.internal("data/img/bglvl" + String.valueOf(lvl)+ ".png");
		backgroundTexture = new Texture(from, false);
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setSize(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
	}
	
	int BestScore(String temp){
		int tmpSum = 0;
		for(int i = 0; i < temp.length(); i++){
			if(Character.isDigit(temp.charAt(i))){
				tmpSum = tmpSum * 10 + Character.getNumericValue(temp.charAt(i));
			}
		}
		return tmpSum;
	}
	
	public void renderBackground() {
		backgroundSprite.draw(spriteBatch);
	}
	
	void drawEdges(){
        edge.setProjectionMatrix(camera.combined);
        edge.begin(ShapeType.Line);
        for(int i = 0; i < world.numberEdges(); i++){
            edge.setColor(world.getEdge(i).getClr());
            edge.line(world.getEdge(i).getBegin(), world.getEdge(i).getEnd());
        }
		edge.end();
	}
	
	void drawScoreBoard(){
		scoreBoard.setScale(0.9f, 0.5f);
		s_batch.setProjectionMatrix(camera.combined);
		s_batch.begin();
		scoreBoard.draw(s_batch, "Score: " + String.valueOf(score), 15, 630);
		s_batch.end();
	}
	
	void drawCircles(){
		circ.setColor(1f,1f, 1f, 1f);
		circ.setProjectionMatrix(camera.combined);
		bonuses.setScale(0.5f, 0.3f);    // Изменение размера шрифта бонусов
		b_batch.setProjectionMatrix(camera.combined);
    	for(int i = 0; i < world.numberNodes(); i++)
    	{	
    		if(!(world.getNodes(i).getLabel() == 0)){	
    			circ.begin(ShapeType.Filled);
    			circ.circle(world.getNodes(i).getPosition().x, 
    				world.getNodes(i).getPosition().y, 25f, 1500);
    			circ.end();
    			b_batch.begin();
    			bonuses.draw(b_batch, String.valueOf(world.getNodes(i).getBonus()), 
    					world.getNodes(i).getPosition().x - 16f,
    					world.getNodes(i).getPosition().y + 13f);
    			b_batch.end();
    		}
    	} 
	}
	void writeBestScore(){
		String lvlName;
		String tmp;
		if(lvl >= 0){	
			lvlName = "data/levels/lvl" + String.valueOf(lvl)+ "bestScore.txt";
			bestScore = Gdx.files.local(lvlName);
			if(bestScore.exists()){
				tmp = bestScore.readString();
				if(BestScore(tmp) < score)
				{
					bestScore.writeString(String.valueOf(score)+ " ", false);
				}
			}
			else
			{
				bestScore.writeString(String.valueOf(score)+ " ", false);
			}
		}
	}
	void boardClear(){
		if(Gdx.input.isTouched(0) && clicked && YOUPLAY){
			if(world.foundIntersect(p_begin, p_end)){
				YOUPLAY = !YOUPLAY; //Смена игрока
				setTime = true;
				clicked = false;
				for(int i = 0; i < world.numberNodes(); i++){
				   for(int j = 0; j < world.numberEdges(); j++){
		 			   if(world.getNodes(i).EdgeIsInside(world.getEdge(j))){
		 				   break;
		 			   }   
		 			   else{
		 				   if(world.numberEdges() - 1 == j){
		 					   score += world.getNodes(i).getBonus();
		 					   world.delNode(i);
		 					   i--;
		 				   }
		 			   }
				  }
				  if(world.numberEdges() == 0){
					  score += world.getNodes(i).getBonus();
					  world.delNode(i);
					  i--;
				  }
			   }
			}	
		}
		
		
	}	
	void turnDraws(){
		if(YOUPLAY){
	    	if(Gdx.input.justTouched()) {
    		   touchPos1.set(Gdx.input.getX(), Gdx.input.getY(), 0);
    		   camera.unproject(touchPos1);
    		   p_begin = touchPos1;
    		   clicked = true;
    		   
	    	}
	    	if(Gdx.input.isTouched(0)) {
    		   touchPos2.set(Gdx.input.getX(), Gdx.input.getY(), 0);
    		   camera.unproject(touchPos2);
    		   p_end = touchPos2;
    		  
	    	}
		}
	}
	
	boolean winnerCheck(){
		
		if(!YOUPLAY && world.findMaxWeight() == -1)
    	{
    		score += 200;  //BONUS SIZE
    		writeBestScore();
    		((MyGame)Gdx.app.getApplicationListener()).setScreen(new EndGameScreen(myGame, lvl, 1, score));
    		return false;
    	}
		else
    	{
			if(world.numberEdges() - world.numOfPlayer2Edges() == 0){
				writeBestScore();  // Player2 - win
				((MyGame)Gdx.app.getApplicationListener()).setScreen(new EndGameScreen(myGame, lvl, 2, score));
	    		return false;
			}
        }
		return true;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
	boolean pauseForSecond(long startTime){
		
		if(System.nanoTime()/10000000 - startTime/10000000 < 100) return false; /// 100 - 1 second
		else {
			world.cutSound.play(0.1f);
			return true;
		}
	}
	public void render(float delta) {
		 
        Gdx.gl20.glLineWidth(lineWidth / camera.zoom);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
			renderBackground();
		spriteBatch.end();
		turnDraws();	
		boardClear();
    	drawScoreBoard();
    	drawEdges();
		drawCircles();
		
		if(!YOUPLAY) {
			if(setTime){
				startTime = System.nanoTime();
				setTime = false;
			}
			if(pauseForSecond(startTime)){
				world.AITurn();
				YOUPLAY = true;
			}
		}   
		winnerCheck();
	}
	

	@Override
	public void show() {
		spriteBatch.begin();
			renderBackground();
		spriteBatch.end();
		drawEdges();
		drawCircles();
		
	}
	
	public void SetCamera(float x, float y){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		this.camera.position.set(x, y, 0);	
		this.camera.update();
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
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
		world.dispose();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
           // Do your optional back button handling (show pause menu?)
			((MyGame)Gdx.app.getApplicationListener()).setScreen(new LevelsScreen(myGame));
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
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resize(int width, int height) {
	
	}
}

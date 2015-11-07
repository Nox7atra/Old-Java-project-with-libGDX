package com.simplecatgames.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.simplecatgames.cutlines.MyGame;
import com.simplecatgames.screen.LevelsScreen;

public class World extends Stage{

	Array<Vector3> coords;
	Array<Node> Nodes;
	Array<Edge> Edges;
	public float ppuX;	
	public float ppuY;
	public static float CAMERA_WIDTH = 1280f;
	public static  float CAMERA_HEIGHT = 640f;
	 public Sound cutSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/cutSound.mp3"));
	//Конструктор класса
	int lvl;
	MyGame game;
	public World(int x, int y, boolean b, int NumLvl, MyGame game) {
		ppuX = getWidth() / CAMERA_WIDTH;
		ppuY = getHeight() / CAMERA_HEIGHT;
		Edges = new Array<Edge>();
		Nodes = new Array<Node>();
		coords = new Array<Vector3>();
		this.lvl = NumLvl;
		this.game = game;
		createWorld();
	}
	
	//Парс файла для узлов
	int fileParse(String temp, Array<Integer> cX, Array<Integer> cY, Array<Integer> bonus){
		int n = 0, tmpSum = 0;
		for(int i = 0; i < temp.length(); i++){
			if(Character.isDigit(temp.charAt(i))){
				if(Character.isDigit(temp.charAt(i + 1))){
					tmpSum = tmpSum * 10 + Character.getNumericValue(temp.charAt(i));
					continue;
				}
				else{
					tmpSum = tmpSum * 10 + Character.getNumericValue(temp.charAt(i));
					if(n % 3 == 0) cX.add(tmpSum);
					if(n % 3 == 1) cY.add(tmpSum);
					if(n % 3 == 2) bonus.add(tmpSum);
					tmpSum = 0;
					n++;
				}
			}
		}
		if(temp.isEmpty()) n = -1;
		return n / 3;
	}
	
	//Парс файла для ребёр
	int fileParse(String temp, Array<Integer> node1, 
		Array<Integer> node2, Array<Integer> colorLabel, Array<Integer> weight){
		int n = 0; 
		float tmpSum = 0;
		for(int i = 0; i < temp.length(); i++){
			if(Character.isDigit(temp.charAt(i))){
				if(Character.isDigit(temp.charAt(i + 1))){
					tmpSum = tmpSum * 10 + Character.getNumericValue(temp.charAt(i));
					continue;
				}
				else{
					tmpSum = tmpSum * 10 + Character.getNumericValue(temp.charAt(i));
					if(n % 4 == 0)
					{
						node1.add((int)tmpSum);
					}
					if(n % 4 == 1) {
						node2.add((int)tmpSum);
					}
					if(n % 4 == 2) {
						colorLabel.add((int)tmpSum);
					}
					if(n % 4 == 3){
						weight.add((int)tmpSum);
					}
					tmpSum = 0;
					n++;
				}
			}
		}
		if(temp.isEmpty()) n = -1;
		return n / 4;
	}
		
	//Создание мира
	
 	public void createWorld() {
 		FileHandle coordNodes = Gdx.files.internal("data/levels/lvl"+ String.valueOf(lvl)+ "/Nodes.txt");
 		FileHandle paramEdges = Gdx.files.internal("data/levels/lvl"+ String.valueOf(lvl)+ "/Edges.txt");
 		String temp = coordNodes.readString();
 		Array<Integer>  cX = new Array<Integer>(), cY = new Array<Integer>(), bonus = new Array<Integer>();
 		Array<Integer>  node1 = new Array<Integer>(), node2 = new Array<Integer>();
 		Array<Integer> colorLabel = new Array<Integer>(), weight = new Array<Integer>();
 		int n;
 		n = fileParse(temp, cX, cY, bonus);
 		if(n != -1)
 		{
	 		for(int i = 0; i < n; i++)
	 		{
				coords.add(new Vector3(cX.get(i), cY.get(i), 0));
	 		}
			for(int i = 0; i < coords.size; i++){			
				if(coords.get(i).y == 0) Nodes.add(new Node(coords.get(i), 0, 0));
				else Nodes.add(new Node(coords.get(i), 1, bonus.get(i)));
			}
			temp = paramEdges.readString();
			n = fileParse(temp, node1, node2, colorLabel, weight);
			if(n != -1){
				for(int i = 0; i < n; i++){
					Edges.add(new Edge(Nodes.get(node1.get(i)).getPosition(), 
						Nodes.get(node2.get(i)).getPosition(), colorLabel.get(i), weight.get(i)));
				}
			}
 		}
	}
 	
 	// Обработка пересечения ребра с отрезком
 	
 	public boolean foundIntersect(Vector3 p_begin, Vector3 p_end){
 		for(int i = 0; i < Edges.size; i++){
 			if(Edges.get(i).IsIntersect(p_begin, p_end) && Edges.get(i).getColorLabel() == 1){
 				cutSound.play(0.1f);
 				Edges.removeIndex(i);
 				for(int j = 0; j < numberNodes(); j++){
					if(getNodes(j).getLabel() == 0){
						coloringBranchAlg(j);
					}
				}
 				clearEdges();
 				return true;
			}
 			
 		}
		return false;
 	}
 	
 	//Алгоритм раскраски дерева связанного с землей
 	
 	public void coloringBranchAlg(int Node_Number){
 		int forSave = 2, edgeNum = -1;
 		for(int i = 0; i < Edges.size; i++){
 			if (Nodes.get(Node_Number).EdgeIsInside(Edges.get(i)) 
 					&& Edges.get(i).getLabel() != forSave){
 				edgeNum = i;
 				Edges.get(edgeNum).setLabel(forSave);
 			}
 			if(edgeNum >= 0){
 		 		for(int k = 0; k < Nodes.size; k++){
 		 			if(Nodes.get(k).EdgeIsInside(Edges.get(edgeNum))){
 		 				if(Nodes.get(k).getLabel() == 1){
 		 					Nodes.get(k).setLabel(forSave);
 		 					Edges.get(edgeNum).setLabel(forSave);
 		 					coloringBranchAlg(k);
 		 				}
 		 			}
 		 		}
 		 		edgeNum = -1;	
 	 		}
 		}
 		
 	}
 	
 	//Удаление висящих в воздухе ребёр
 	
 	public void clearEdges(){
 		
 		for(int i = Edges.size - 1; i >= 0; i--){
 			if(Edges.get(i).getLabel() == 0){
 				Edges.removeIndex(i);
 			}
 		}
 		for(int i = 0; i < Edges.size; i++)
 		{
 			Edges.get(i).setLabel(0);
 		}
 		for(int i = 0; i < Nodes.size; i++)
 		{
 			if(Nodes.get(i).getLabel() != 0) Nodes.get(i).setLabel(1);
 		}
 	}
 	
 	// Удаление узла
 	
 	public void delNode(int i){
 		Nodes.removeIndex(i);
 	}
 	
	public Edge getEdge(int i){
		return Edges.get(i);
	}
	
	public Node getNodes(int i){
		return Nodes.get(i);
	}
	
	public int numberEdges(){
		return Edges.size;
	}
	
	public int numberNodes(){
		return Nodes.size;
	}
	
	public void setPP(float x, float y){
		ppuX = x;
		ppuY = y;
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		super.touchDown(x, y, pointer, button);
		
		return true;
	}
	
	// Искуственный интелект
	public boolean AITurn(){	
		if(findMaxWeight() >= 0){
			Edges.removeIndex(findMaxWeight());
			for(int j = 0; j < numberNodes(); j++){
				if(getNodes(j).getLabel() == 0){
					coloringBranchAlg(j);
				}
			}
			clearEdges();
			for(int i = 0; i < numberNodes(); i++){
			   for(int j = 0; j < numberEdges(); j++){
	 			   if(getNodes(i).EdgeIsInside(getEdge(j))){
	 				   break;
	 			   }   
	 			   else{
	 				   if(numberEdges() - 1 == j){
	 					   delNode(i);
	 					   i--;
	 				   }
	 			   }
			   }
			   if(numberEdges() == 0){
				   if(getNodes(i).getLabel() == 1){
					   delNode(i);
				   }
			   }
		   }
			return true;
		}
		return false;	 
 	}
	public int numOfPlayer2Edges(){
		int count = 0;
		for(int i = 0; i < Edges.size; i++){
			if(Edges.get(i).getColorLabel() == 2) count++;
		}
		return count;
	}
	
	public int findMaxWeight(){
		int max = -1, numMax = - 1;
		for(int i = 0; i < Edges.size; i++){
			if(Edges.get(i).getColorLabel() == 2)
			{
				if(max < Edges.get(i).getWeight()){
					max = Edges.get(i).getWeight();
					numMax = i;
				}
			}
		}
		return numMax;
	}
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
           // Do your optional back button handling (show pause menu?)
			((MyGame)Gdx.app.getApplicationListener()).setScreen(new LevelsScreen(game));
        }
	      
		return false;
	}
	
}

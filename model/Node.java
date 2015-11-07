package com.simplecatgames.model;

import com.badlogic.gdx.math.Vector3;

public class Node{
	Vector3 position;
	int label, bonus; // 0 - начало, 1 - свободный узел, 2 - отмеченный узел;
	
	public Node(Vector3 p, int label, int bonus){
		this.position = p;
		this.label = label;
		this.bonus = bonus;
	}
	public Vector3 getPosition(){
		return position;
	}
	public int getLabel(){
		return label;
	}
	public void setLabel(int label){
		this.label = label;
	}
	public int getBonus(){
		return bonus;
	}
	
	public boolean EdgeIsInside(Edge testEdge){
			if(((testEdge.getBegin().x - position.x)*(testEdge.getBegin().x - position.x)
				 + (testEdge.getBegin().y - position.y)*
					(testEdge.getBegin().y - position.y) <= 0.2f)) return true;
			if(((testEdge.getEnd().x - position.x)*(testEdge.getEnd().x - position.x)
					 + (testEdge.getEnd().y - position.y)*
						(testEdge.getEnd().y - position.y) <= 0.2f)) return true;
		return false;
	}
}

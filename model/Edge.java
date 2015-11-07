package com.simplecatgames.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public class Edge{
	// Класс ребра
	Vector3 first, second; //first - координаты начала ребра
	Edge LEdge, REdge;
	Color EdgClr; //Цвет ребра
	int label = 0;
	int colorL; // 1 - Player, 2 - AI
	int weight; //Вес ребра
	// Конструктор ребра
	public Edge(Vector3 begin, Vector3 end, int colorLable, int weight){
		this.first = begin;
		this.second = end;
		this.colorL = colorLable;
		if(colorLable == 1) this.EdgClr = new Color(1f, 1f,1, 1f); 
		else this.EdgClr = new Color(0, 0f, 0f, 1f);
		this.weight = weight;
	}	
	
	// Метод определения пересечения. Возвращает true,
	// если пересечение есть, и false если пересечения нет.
	public boolean IsIntersect(Vector3 begin, Vector3 end){
		double v1, v2, v3, v4;
		double ax1 = first.x, ax2 = second.x, ay1 = first.y, ay2 = second.y,
				bx1 = begin.x, bx2 = end.x, by1 = begin.y, by2 = end.y;
		v1 = ((bx2-bx1)*(ay1-by1)-(by2-by1)*(ax1-bx1))/1000;
		v2 = ((bx2-bx1)*(ay2-by1)-(by2-by1)*(ax2-bx1))/1000;
		v3 = ((ax2-ax1)*(by1-ay1)-(ay2-ay1)*(bx1-ax1))/1000;
		v4 = ((ax2-ax1)*(by2-ay1)-(ay2-ay1)*(bx2-ax1))/1000;
		if((v1 * v2 / 10000 < 0) && (v3 * v4 / 10000 < 0))
			return true;
		else{
			return false;
		}	
	}
	public Color getClr(){
		return EdgClr;
	}
	
	public Vector3 getBegin(){
		return first;
	}
	public Vector3 getEnd(){
		return second;
	}
	public int getLabel(){
		return this.label;
	}
	public int getWeight(){
		return this.weight;
	}
	public int getColorLabel(){
		return this.colorL;
	}
	public void setLabel(int label){
		this.label = label;
	}
	
}

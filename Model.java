// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import java.util.*;
import java.awt.*;

public class Model extends Observable {	

	// the data in the model, just a counter
	private Color newColor;	
	private int tick;
	private int thickness;
	private int time;
	private int playTime;
	private boolean draw;
	public boolean playing;
	private ArrayList <CanvasHistory> history;

	
	Model() {
		playTime = 0;
		setChanged();
		history = new ArrayList<CanvasHistory>();
	}

	public int getTime() {
		return time;
	}

	public void setTime(int t) {
		time = t;
	}

	public int getPlayTime() {
		return playTime;
	}

	public void setPlayTime(int t) {
		playTime = t;
	}

	public Color getColor() {
		return newColor;
	}

	public void changeColor(Color c) {
		newColor = c;
		System.out.println("Color changed to ");
		System.out.println(c);
	}

	public boolean getDraw() {
		return draw;
	}

	public void setDraw(boolean d) {
		draw = d;
	}
	
	public int getPlayTick() {
		return tick;
	}
	
	public void changePlayTick(int t) {
		tick = t;
	}

	public int getThickness() {
		return thickness;
	}

	public void changeThickness(int t) {
		thickness = t;
	}

	public void addPoint(int x, int y, boolean b) {
		int increment = 0;
		if (history.size() > 0) {
			time = history.get(history.size()-1).time;
		}
		if (playTime < time) {
			time = playTime + 1;
			for (int i = 0; i < history.size(); i++) {
				if (history.get(i).time > time || (history.get(i).blank == false && history.get(i).time == time)) {
					history.remove(i);
					i -= 1;
				}
			}
		}
		if(b) {
			increment = 1;
		}
		CanvasHistory tempCH = new CanvasHistory(x,y,thickness, newColor, time+increment, b);
		history.add(tempCH);
		playTime = time + increment;
	}

	public ArrayList<CanvasHistory> getPointsUpto(int t) {
		ArrayList<CanvasHistory> tempCH = new ArrayList<CanvasHistory>();
		for(int i=0; i<history.size(); i++) {
			if((history.get(i)).time > t) break;
			tempCH.add(history.get(i));
		}
		return tempCH;
	}

	public void loadPoints(ArrayList<CanvasHistory> input) {
		history.clear();
		history.addAll(input);
	}

	public void play() {
		System.out.println("Model: Play clicked");
		setChanged();
		notifyObservers();
	}

	public void start() {
		System.out.println("Model: Start clicked");
		setChanged();
		notifyObservers();
	}

	public void end() {
		System.out.println("Model: End clicked");
		setChanged();
		notifyObservers();
	}

}

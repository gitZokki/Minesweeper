package de.zokki.minesweeper.Utils;

import java.util.ArrayList;

public class Settings {

    private static Settings instance;

    private int mineWidthCount = 10;
    private int mineHeightCount = 10;
    private int bombCount = 10;

    private ArrayList<ArrayList<Mine>> mines = new ArrayList<ArrayList<Mine>>();

    public static synchronized Settings getInstance() {
	if (instance == null) {
	    instance = new Settings();
	}
	return instance;
    }

    public int getMineWidthCount() {
	return mineWidthCount;
    }

    public void setMineWidthCount(int mineWidthCount) {
	this.mineWidthCount = mineWidthCount;
    }

    public int getMineHeightCount() {
	return mineHeightCount;
    }

    public void setMineHeightCount(int mineHeightCount) {
	this.mineHeightCount = mineHeightCount;
    }

    public int getBombCount() {
	return bombCount;
    }

    public void setBombCount(int bombCount) {
	this.bombCount = bombCount;
    }

    public ArrayList<ArrayList<Mine>> getMines() {
	return mines;
    }

    public void setMines(ArrayList<ArrayList<Mine>> mines) {
	this.mines = mines;
    }
}

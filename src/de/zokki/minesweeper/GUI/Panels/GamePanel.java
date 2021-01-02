package de.zokki.minesweeper.GUI.Panels;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.zokki.minesweeper.Utils.GameManager;
import de.zokki.minesweeper.Utils.Images;
import de.zokki.minesweeper.Utils.Mine;
import de.zokki.minesweeper.Utils.Settings;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private ArrayList<ArrayList<Mine>> mines = new ArrayList<ArrayList<Mine>>();

    private Dimension preferedSize;

    private ComponentsPanel componentsPanel;

    private Settings settings = Settings.getInstance();

    private int width, height;

    public GamePanel(int width, int height) {
	this.preferedSize = new Dimension(width, height);
	this.width = width;
	this.height = height;

	setLayout(null);

	componentsPanel = new ComponentsPanel(width, 50);
	add(componentsPanel);

	addListener();
	initMines();
	resizeMinesAndIcons();
	settings.setMines(mines);
	GameManager.setPlaying(true);
    }

    @Override
    public Dimension getPreferredSize() {
	return preferedSize;
    }

    private void addListener() {
	addComponentListener(new ComponentAdapter() {
	    @Override
	    public void componentResized(ComponentEvent e) {
		width = getWidth();
		height = getHeight();
		componentsPanel.setSize(width, 50);
		resizeMinesAndIcons();
		for (ArrayList<Mine> mineArray : mines) {
		    for (Mine mine : mineArray) {
			mine.resizeIcons();
		    }
		}
	    }
	});
    }
    
    private void initMines() {
	for (int x = 0; x < settings.getMineHeightCount(); x++) {
	    ArrayList<Mine> mineArray = new ArrayList<Mine>();
	    for (int y = 0; y < settings.getMineWidthCount(); y++) {
		Mine mine = new Mine(y, x);
		mineArray.add(mine);
		add(mine);
	    }
	    mineArray.trimToSize();
	    mines.add(mineArray);
	}
	mines.trimToSize();
	setBombs(0);
    }

    private void setBombs(int currentCount) {
	int bombCount = settings.getBombCount();
	for (ArrayList<Mine> mineArray : mines) {
	    for (Mine mine : mineArray) {
		if (!mine.hasMine()) {
		    if (currentCount < bombCount) {
			if (Math.random() < 0.001) {
			    mine.setHasMine(true);
			    currentCount++;
			}
		    } else {
			return;
		    }
		}
	    }
	}
	if (currentCount < bombCount) {
	    setBombs(currentCount);
	}
    }

    private void resizeMinesAndIcons() {
	double width = this.width / (double) settings.getMineWidthCount();
	double height = (this.height - 50) / (double) settings.getMineHeightCount();
	Images.scaleMineIcons((int) width, (int) height);
	for (ArrayList<Mine> mineArray : mines) {
	    for (Mine mine : mineArray) {
		mine.setSize((int) width, (int) height);
		mine.setLocation((int) (width * mine.getXIndex()), (int) (height * mine.getYIndex()) + 50);
	    }
	}
    }
}

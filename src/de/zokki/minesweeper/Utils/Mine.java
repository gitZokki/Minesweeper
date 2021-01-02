package de.zokki.minesweeper.Utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JToggleButton;

import de.zokki.minesweeper.GUI.Components.ResetButton;
import de.zokki.minesweeper.GUI.Components.TimerLabel;

public class Mine extends JToggleButton {

    private static final long serialVersionUID = 1L;

    private int xIndex, yIndex;

    private boolean isFlaged, hasMine;

    private Settings settings = Settings.getInstance();

    public Mine(int xIndex, int yIndex) {
	this.xIndex = xIndex;
	this.yIndex = yIndex;

	setFocusable(false);
	addListeners();
    }

    public int getXIndex() {
	return xIndex;
    }

    public int getYIndex() {
	return yIndex;
    }

    public boolean isFlaged() {
	return isFlaged;
    }

    public boolean hasMine() {
	return hasMine;
    }

    public void setHasMine(boolean hasMine) {
	this.hasMine = hasMine;
    }

    public void resizeIcons() {
	if (isFlaged()) {
	    setScaledFlag();
	} else if (hasMine() && !GameManager.isPlaying()) {
	    setScaledBomb();
	}
    }

    private void addListeners() {
	addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (!TimerLabel.isStarted() && GameManager.isPlaying()) {
		    TimerLabel.startTimer();
		}

		if (!isSelected()) {
		    setSelected(true);
		} else if (isFlaged() || !GameManager.isPlaying()) {
		    setSelected(false);
		} else if (hasMine()) {
		    onBombClick();
		} else {
		    checkForNearbyBombs();
		}
	    }
	});

	addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (!GameManager.isPlaying()) {
		    return;
		}

		if (e.getButton() == MouseEvent.BUTTON3 && !isSelected()) {
		    if (isFlaged()) {
			isFlaged = false;
			setIcon(null);
		    } else {
			isFlaged = true;
			setScaledFlag();
		    }
		    return;
		}
	    }
	});
    }

    private void setScaledBomb() {
	setIcon(Images.getBombIcon());
    }

    private void setScaledFlag() {
	setIcon(Images.getFlagIcon());
    }

    private void checkForNearbyBombs() {
	ArrayList<Mine> minesToCheck = getMinesToCheck();

	int nearbyBombs = 0;
	for (Mine mine : minesToCheck) {
	    if (mine.hasMine()) {
		nearbyBombs++;
	    }
	}

	if (nearbyBombs == 0) {
	    for (Mine mine : minesToCheck) {
		if (!mine.hasMine() && !mine.isSelected() && !mine.isFlaged()) {
		    mine.setSelected(true);
		    mine.checkForNearbyBombs();
		}
	    }
	}

	setText(nearbyBombs + "");
	checkForWin();
    }

    private ArrayList<Mine> getMinesToCheck() {
	ArrayList<Mine> minesToCheck = new ArrayList<Mine>();
	ArrayList<ArrayList<Mine>> mines = settings.getMines();

	for (int y = -1; y < 2; y++) {
	    int yIndex = this.yIndex + y;
	    if (yIndex >= mines.size() || yIndex < 0) {
		continue;
	    }

	    ArrayList<Mine> mineArray = mines.get(yIndex);
	    for (int x = -1; x < 2; x++) {
		int xIndex = this.xIndex + x;
		if (xIndex >= mineArray.size() || xIndex < 0) {
		    continue;
		}
		Mine mine = mineArray.get(xIndex);
		if (mine != this) {
		    minesToCheck.add(mine);
		}
	    }
	}
	return minesToCheck;
    }

    private void checkForWin() {
	for (ArrayList<Mine> mines : settings.getMines()) {
	    for (Mine mine : mines) {
		if (!mine.hasMine() && !mine.isSelected()) {
		    return;
		}
	    }
	}
	onBombClick();
	ResetButton.setImageIcon(Images.getWinIcon());
    }

    private void onBombClick() {
	GameManager.setPlaying(false);
	if (TimerLabel.isStarted()) {
	    TimerLabel.stopTimer();
	}
	ResetButton.setImageIcon(Images.getLoseIcon());
	ArrayList<ArrayList<Mine>> mines = settings.getMines();

	for (ArrayList<Mine> mineArray : mines) {
	    for (Mine mine : mineArray) {
		if (mine.hasMine()) {
		    mine.setScaledBomb();
		}
	    }
	}
    }
}

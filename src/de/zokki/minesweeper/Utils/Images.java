package de.zokki.minesweeper.Utils;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import de.zokki.minesweeper.Minesweeper;

public class Images {
    private static Image flagImage, bombImage, winImage, startImage, loseImage, settingsImage;

    private static ImageIcon flag, bomb, win, start, lose, settings;

    static {
	try {
	    flagImage = ImageIO.read(Minesweeper.class.getResource("/flag.png"));
	    bombImage = ImageIO.read(Minesweeper.class.getResource("/bomb.png"));
	    winImage = ImageIO.read(Minesweeper.class.getResource("/win.png"));
	    startImage = ImageIO.read(Minesweeper.class.getResource("/start.png"));
	    loseImage = ImageIO.read(Minesweeper.class.getResource("/lose.png"));
	    settingsImage = ImageIO.read(Minesweeper.class.getResource("/settings.png"));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void scaleMineIcons(int width, int height) {
	flag = new ImageIcon(flagImage.getScaledInstance(width, height, 0));
	bomb = new ImageIcon(bombImage.getScaledInstance(width, height, 0));
    }

    public static void scaleButtonIcons(int width, int height) {
	win = new ImageIcon(winImage.getScaledInstance(width, height, 0));
	start = new ImageIcon(startImage.getScaledInstance(width, height, 0));
	lose = new ImageIcon(loseImage.getScaledInstance(width, height, 0));
	settings = new ImageIcon(settingsImage.getScaledInstance(width, height, 0));
    }

    public static ImageIcon getFlagIcon() {
	return flag;
    }

    public static ImageIcon getBombIcon() {
	return bomb;
    }

    public static ImageIcon getWinIcon() {
	return win;
    }

    public static ImageIcon getStartIcon() {
	return start;
    }

    public static ImageIcon getLoseIcon() {
	return lose;
    }

    public static ImageIcon getSettingsIcon() {
	return settings;
    }
}

package de.zokki.minesweeper;

import de.zokki.minesweeper.GUI.GUI;
import de.zokki.minesweeper.GUI.Panels.SettingsPanel;
import de.zokki.minesweeper.Utils.Settings;

public class Minesweeper {

    public static GUI currentGUI;

    public static void main(String[] args) {
	Settings settings = Settings.getInstance();
	int option = -1;
	do {
	    option = SettingsPanel.showSettings(null);
	    if(option == -1) {
		System.exit(option);
	    }
	} while(settings.getBombCount() == 0 || settings.getMineHeightCount() == 0 || settings.getMineWidthCount() == 0);
	currentGUI = new GUI("Minesweeper", 500, 500);
    }
}

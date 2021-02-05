package de.zokki.minesweeper.GUI.Components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;

public class Timer extends Thread {

    private JLabel label;

    public Timer(JLabel label) {
	this.label = label;
    }

    @Override
    public void run() {
	long startTime = System.currentTimeMillis();
	
	while (true) {
	    DateFormat timeFormat = new SimpleDateFormat("mm:ss.SSS");
	    label.setText(timeFormat.format(System.currentTimeMillis() - startTime));
	}
    }

}

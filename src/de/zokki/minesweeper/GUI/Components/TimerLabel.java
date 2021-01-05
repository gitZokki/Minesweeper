package de.zokki.minesweeper.GUI.Components;

import javax.swing.JLabel;

public class TimerLabel extends JLabel {

    private static final long serialVersionUID = 1L;

    private static TimerLabel label = new TimerLabel();

    private static Timer timer = new Timer(label);

    private static boolean isStarted = false;

    private TimerLabel() {
	setText("00:00.000");
    }

    public static void startTimer() throws RuntimeException {
	if (!isStarted) {
	    isStarted = true;
	    if (timer.getState() == Thread.State.TERMINATED) {
		timer = new Timer(label);
	    }
	    timer.start();
	} else {
	    throw new RuntimeException("Timer already started!");
	}
    }

    @SuppressWarnings("deprecation")
    public static void resetTimer() {
	isStarted = false;
	timer.stop();
	label = new TimerLabel();
    }

    @SuppressWarnings("deprecation")
    public static void stopTimer() {
	if (isStarted) {
	    isStarted = false;
	    timer.stop();
	} else {
	    throw new RuntimeException("Timer has not started yet!");
	}
    }

    public static TimerLabel getLabel() {
	return label;
    }

    public static boolean isStarted() {
	return isStarted;
    }
}

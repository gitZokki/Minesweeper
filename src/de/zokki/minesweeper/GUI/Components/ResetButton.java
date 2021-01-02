package de.zokki.minesweeper.GUI.Components;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import de.zokki.minesweeper.Minesweeper;
import de.zokki.minesweeper.GUI.GUI;
import de.zokki.minesweeper.Utils.Images;

public class ResetButton extends JButton {

    private static final long serialVersionUID = 1L;

    private static ResetButton button = new ResetButton();

    private ResetButton() {
	setSize(35, 35);
	EventQueue.invokeLater(() -> {
	    Images.scaleButtonIcons(35, 35);
	    setImageIcon(Images.getStartIcon());
	});

	addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		onClick();
	    }
	});
    }

    public static ResetButton getButton() {
	return button;
    }

    public static void setImageIcon(ImageIcon icon) {
	button.setIcon(icon);
    }

    public static void onClick() {
	GUI GUI = Minesweeper.currentGUI;
	Rectangle lastBounds = GUI.getBounds();

	GUI.dispose();
	Minesweeper.currentGUI = new GUI("Minesweeper", 500, 500);
	Minesweeper.currentGUI.setBounds(lastBounds);
	TimerLabel.resetTimer();
	setImageIcon(Images.getStartIcon());
    }
}

package de.zokki.minesweeper.GUI.Panels;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.zokki.minesweeper.Minesweeper;
import de.zokki.minesweeper.GUI.Components.ResetButton;
import de.zokki.minesweeper.GUI.Components.TimerLabel;
import de.zokki.minesweeper.Utils.Images;

public class ComponentsPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Dimension preferedSize;

    JButton settingsButton;

    public ComponentsPanel(int width, int height) {
	this.preferedSize = new Dimension(width, height);

	setLayout(null);

	addListeners();

	add(getTimer());
	add(ResetButton.getButton());
	add(getSettings());
    }

    @Override
    public Dimension getPreferredSize() {
	return preferedSize;
    }

    private void addListeners() {
	addComponentListener(new ComponentAdapter() {
	    @Override
	    public void componentResized(ComponentEvent e) {
		ResetButton.getButton().setLocation(getWidth() / 2, 7);
		settingsButton.setLocation(getWidth() - 50, 7);
	    }
	});
    }

    private JLabel getTimer() {
	JLabel timerLabel = TimerLabel.getLabel();
	timerLabel.setBorder(BorderFactory.createTitledBorder("TIMER"));
	timerLabel.setFont(new Font(timerLabel.getFont().getName(), Font.PLAIN, 30));
	timerLabel.setSize(160, 50);
	return timerLabel;
    }

    private JButton getSettings() {
	settingsButton = new JButton();
	EventQueue.invokeLater(() -> settingsButton.setIcon(Images.getSettingsIcon()));
	settingsButton.setSize(35, 35);
	settingsButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (SettingsPanel.showSettings(Minesweeper.currentGUI) != -1) {
		    ResetButton.onClick();
		}
	    }
	});
	return settingsButton;
    }
}

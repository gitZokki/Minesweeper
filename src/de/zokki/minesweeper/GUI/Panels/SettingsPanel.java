package de.zokki.minesweeper.GUI.Panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.zokki.minesweeper.Utils.Settings;

public class SettingsPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    Settings settings = Settings.getInstance();
    
    public SettingsPanel() {
	JTextField mineWidthCount = new JTextField("" + settings.getMineWidthCount());
	JTextField mineHeightCount = new JTextField("" + settings.getMineHeightCount());
	JTextField bombCount = new JTextField("" + settings.getBombCount());
	JLabel label = new JLabel("*");
	JLabel mineCountLabel = new JLabel("= " + (settings.getMineWidthCount() * settings.getMineHeightCount()));	
	
	mineWidthCount.setBorder(BorderFactory.createTitledBorder("Mine Width Count"));
	mineWidthCount.setPreferredSize(new Dimension(125, 40));
	mineWidthCount.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyTyped(KeyEvent e) {
		EventQueue
			.invokeLater(() -> {
			    String text = mineWidthCount.getText().replaceAll("[^0-9]+", "");
			    int textInt = Integer.parseInt(text.isEmpty() ? "0" : text);
			    textInt = Math.min(textInt, 75);
			    mineWidthCount.setText(textInt + "");
			    
			    int newBombCount = textInt * Integer.parseInt(mineHeightCount.getText());
			    updateBombCount(newBombCount, bombCount);
			    mineCountLabel.setText("= " + newBombCount);
			    settings.setMineWidthCount(textInt);
			});
	    }
	});
	add(mineWidthCount);

	label.setFont(new Font(label.getFont().getFontName(), Font.BOLD, 25));
	add(label);
	
	mineHeightCount.setBorder(BorderFactory.createTitledBorder("Mine Height Count"));
	mineHeightCount.setPreferredSize(new Dimension(125, 40));
	mineHeightCount.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyTyped(KeyEvent e) {
		EventQueue
			.invokeLater(() -> {
			    String text = mineHeightCount.getText().replaceAll("[^0-9]+", "");
			    int textInt = Integer.parseInt(text.isEmpty() ? "0" : text);
			    textInt = Math.min(textInt, 75);
			    mineHeightCount.setText(textInt + "");
			    
			    int newBombCount = textInt * Integer.parseInt(mineWidthCount.getText());
			    updateBombCount(newBombCount, bombCount);
			    mineCountLabel.setText("= " + newBombCount);
			    settings.setMineWidthCount(textInt);
			});
	    }
	});
	add(mineHeightCount);

	mineCountLabel.setPreferredSize(new Dimension(50, 40));
	add(mineCountLabel);
	
	bombCount.setBorder(BorderFactory.createTitledBorder("Bomb Count"));
	bombCount.setPreferredSize(new Dimension(350, 40));
	bombCount.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyTyped(KeyEvent e) {
		EventQueue.invokeLater(() -> {
		    String text = bombCount.getText().replaceAll("[^0-9]+", "");
		    int textInt = Integer.parseInt(text.isEmpty() ? "0" : text);
		    textInt = Math.min(textInt, settings.getMineHeightCount() * settings.getMineWidthCount() - 1);
		    settings.setBombCount(textInt);
		    bombCount.setText(textInt + "");
		});
	    }
	});
	add(bombCount);
    }

    @Override
    public Dimension getPreferredSize() {
	return new Dimension(350, 150);
    }
    
    public static int showSettings(Component parent) {
	return JOptionPane.showConfirmDialog(parent, new SettingsPanel(), "Settings", -1, JOptionPane.PLAIN_MESSAGE);
    }
    
    private void updateBombCount(int newCount, JTextField update) {
	newCount = Math.min(newCount, settings.getBombCount());
	update.setText(newCount + "");
	settings.setBombCount(newCount);
    }
}

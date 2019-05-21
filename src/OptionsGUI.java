//Martin Lee
//GUI for the options menu

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class OptionsGUI extends JFrame implements ActionListener {
	
	//fields
	private JLabel title = new JLabel("Options");
	private JLabel difficultyText = new JLabel("Difficulté:");
	private JLabel volumeText = new JLabel("Volume:");
	private JRadioButton[] difficultyButtons = new JRadioButton[3];
	private JButton muteButton = new JButton();
	private JButton menuButton = new JButton("Retour au menu");
	
	Font titleFont = new Font("Serif", Font.BOLD, 24);
	Font otherTextFont = new Font("Serif", Font.PLAIN, 20);
	private static String[] diffs = {"Easy","Normal","Hard"};
	private static String[] vol = {"On","Off"};
	
	//default options
	private static String difficulty = diffs[1]; 
	private static String volume = vol[0];
	
	public OptionsGUI() {
		
		//adjust screen size based on resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pack();
		
		//for JFrame
		setSize(1000, 700);
		setResizable(false);
		setContentPane(new JLabel(new ImageIcon()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		
		setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/optionsBackground.jpeg")
				.getImage().getScaledInstance(screenSize.width - 100, screenSize.height - 100,
					Image.SCALE_SMOOTH))));
		
		//title
		title.setFont(titleFont);
		title.setBounds(100, 110, 150, 150);
		title.setVisible(true);
//		title.setForeground(Color.WHITE);
		add(title);
		
		//difficulty JLabel
		difficultyText.setFont(otherTextFont);
		difficultyText.setBounds(100, 275, 100, 100);
		difficultyText.setVisible(true);
		add(difficultyText);
		
		//group radio buttons together	
		ButtonGroup group = new ButtonGroup();
		
		for (int x = 0; x < 3; x++) {
			difficultyButtons[x] = new JRadioButton(diffs[x]);
			difficultyButtons[x].setBounds(difficultyText.getX() + 125 * (x+1), 300, 100, 50);
			difficultyButtons[x].setOpaque(false);
			difficultyButtons[x].setVisible(true);
			difficultyButtons[x].addActionListener(this);
			add(difficultyButtons[x]);
			group.add(difficultyButtons[x]);
			
			if(difficultyButtons[x].getText()==difficulty)
				difficultyButtons[x].setSelected(true);
		}
		
		//volume JLabel
		volumeText.setFont(otherTextFont);
		volumeText.setBounds(100, 425, 100, 40);
		volumeText.setVisible(true);
		add(volumeText);
		
		//mute button
		muteButton.setBounds(volumeText.getX() + 125, volumeText.getY(), 100, 50);
		muteButton.setVisible(true);
		muteButton.addActionListener(this);
		add(muteButton);
		
		if(volume == vol[0])
			muteButton.setText("Mute");
		else
			muteButton.setText("Unmute");
			
		
		//menu button
		menuButton.setBounds(getWidth()-150-25, getHeight()-100, 150, 50);
		menuButton.setVisible(true);
		menuButton.addActionListener(this);
		add(menuButton);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		//changes difficulty based on radio buttons clicked
		for (int x = 0; x < 3; x++) {
			
			if (e.getSource() == difficultyButtons[x]) 
				difficulty = difficultyButtons[x].getText();
			
		}
		
		//mutes or unmutes music
		if (e.getSource() == muteButton) {

			if (muteButton.getText() == "Mute") {
				muteButton.setText("Unmute");
				volume = vol[1];
			} else if (muteButton.getText() == "Unmute") {
				muteButton.setText("Mute");
				volume = vol[0];
			}

			Music.update();
			
		}
		
		//goes back to main menu
		if (e.getSource() == menuButton) {
			new MainMenuGUI();
			this.dispose();
		}
			
	}

	//getters for difficulty and volume
	public static String getDifficulty() {
		return difficulty;
	}
	
	public static String getVolume() {
		return volume;
	}
	
}

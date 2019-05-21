//Martin Lee
//GUI for the main menu

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainMenuGUI extends JFrame implements ActionListener {
	
	//fields
	private JLabel title = new JLabel("HERO");
	private JButton playButton = new JButton("Debut");
	private JButton optionsButton = new JButton("Options");
	private JButton instructionsButton = new JButton("Comment jouer");
	
	Font titleFont = new Font("Serif", Font.BOLD, 24);
	
	public MainMenuGUI() {
		
		//adjust screen size based on resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pack();
		
		//for JFrame
		setSize(screenSize.width - 100, screenSize.height - 100);
		setResizable(false);
		setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/mainmenubackground.jpg")
			.getImage().getScaledInstance(getWidth(), getHeight(),
				Image.SCALE_SMOOTH))));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		
		//title
		title.setFont(titleFont);
		title.setBounds(100, 100, 500, 150);
		title.setVisible(true);
		add(title);
		
		//play button
		playButton.setBounds(100, screenSize.height / 5 + 100, 150, 50);
		playButton.setVisible(true);
		playButton.addActionListener(this);
		add(playButton);
		
		//options button
		optionsButton.setBounds(100, 2 * screenSize.height / 5 + 50, 150, 50);
		optionsButton.setVisible(true);
		optionsButton.addActionListener(this);
		add(optionsButton);
		
		//instructions button
		instructionsButton.setBounds(100, 3 * screenSize.height / 5, 150, 50);
		instructionsButton.setVisible(true);
		instructionsButton.addActionListener(this);
		add(instructionsButton);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		//opens character selection screen
		if (e.getSource() == playButton) {
			new CharacterSelectGUI();
			this.dispose();
		}

		//opens options screen
		if (e.getSource() == optionsButton) {
			new OptionsGUI();
			this.dispose();
		}
		
		//opens instructions screen
		if (e.getSource() == instructionsButton) {
			new InstructionsGUI();
			this.dispose();
		}
		
	}

}

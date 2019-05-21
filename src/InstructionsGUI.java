//Martin Lee
//GUI for the instructions menu

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InstructionsGUI extends JFrame implements ActionListener {

	//fields
	private JLabel title = new JLabel("Comment jouer");
	private JTextArea instructions = new JTextArea();
	private JButton menuButton = new JButton("Back to Menu");

	Font titleFont = new Font("Serif", Font.BOLD, 24);

	public InstructionsGUI() {

		//changes screen size based on resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pack();

		//for JFrame
		setSize(screenSize.width - 100, screenSize.height - 100);
		setResizable(false);
		setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/mainmenubackground.jpg")
		.getImage().getScaledInstance(screenSize.width - 100, screenSize.height - 100,
				Image.SCALE_SMOOTH))));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

		//title
		title.setFont(titleFont);
		title.setBounds(100, 100, 200, 150);
		title.setVisible(true);
		add(title);

		//instructions
		Font instructionsFont = new Font("Serif", Font.PLAIN, 24);
		instructions.setFont(instructionsFont);
		instructions.setBounds(100, 200, 800, 400);
		instructions.setVisible(true);
		instructions.setText("1. Runner: Press 'Space' to JUMP. / Coureur: Appuyez "
				+ "sur 'Espace' pour sauter. \n 2. Collect the correct vocabulary to "
				+ "gain points. / Recueillir le vocabulaire \n correct pour gagner des points."
				+ "\n 3. Have fun! / S'amuser!");
		instructions.setEditable(false);
		add(instructions);

		//menu button
		menuButton.setBounds(screenSize.width - 300, screenSize.height - 200, 150, 50);
		menuButton.setVisible(true);
		menuButton.addActionListener(this);
		add(menuButton);

	}

	public void actionPerformed(ActionEvent e) {

		//goes back to main menu
		if (e.getSource() == menuButton) {
			new MainMenuGUI();
			this.dispose();
		}

	}

}

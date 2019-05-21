import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EndScreen extends JFrame implements ActionListener{

	private JLabel scoreLabel = new JLabel();
	private JButton back = new JButton("Retour au menu");
	
	Font font = new Font("Serif", Font.BOLD, 24);
	
	public EndScreen(int score){
		
		//frame
		setSize(500, 500);
		setResizable(false);
		setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/end"+CharacterSelectGUI.getSelected()+".jpg")
			.getImage().getScaledInstance(getWidth(), getHeight(),
				Image.SCALE_SMOOTH))));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		
		//display score
		scoreLabel.setText("Score: "+score);
		scoreLabel.setFont(font);
		scoreLabel.setBounds(100, 100, 500, 50);
		scoreLabel.setVisible(true);
		add(scoreLabel);
		
		//menu button
		back.setBounds(100, 200, 150, 50);
		back.setVisible(true);
		back.addActionListener(this);
		add(back);
		
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==back){
			new MainMenuGUI();
			dispose();
		}
	}

	
}

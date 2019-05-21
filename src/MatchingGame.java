import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class MatchingGame extends JFrame implements ActionListener {

	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 550;

	private static final int IMAGE_X = 300;
	private static final int IMAGE_Y = 300;

	private final int MATCHES = 10;
	private ArrayList<Integer> noRep = new ArrayList<Integer>();
	private String[] opt;
	private Random r = new Random();

	private JLabel instructions = new JLabel("Correspondance: sélectionnez la bonne"
			+ " réponse pour obtenir plus de points.", SwingConstants.CENTER);

	private JLabel timerLabel = new JLabel();
	private JLabel scoreLabel = new JLabel();

	private int score = 0;
	private int timeRemaining;
	private int lives;
	private int matchesRemaining = 10;
	private int multiplier = 1;

	private JLabel[] livesLabel;
	private JLabel multiplierLabel = new JLabel();

	private Timer countDown = new Timer(1000, this);

	private Font font = new Font("Serif", Font.PLAIN, 24);

	private static final ImageIcon[] pictures =
		{
				(new ImageIcon(new ImageIcon("images/picture0.gif").getImage().getScaledInstance(IMAGE_X, IMAGE_Y,Image.SCALE_SMOOTH))),
				(new ImageIcon(new ImageIcon("images/picture1.gif").getImage().getScaledInstance(IMAGE_X, IMAGE_Y,Image.SCALE_SMOOTH))),	
				(new ImageIcon(new ImageIcon("images/picture2.gif").getImage().getScaledInstance(IMAGE_X, IMAGE_Y,Image.SCALE_SMOOTH))),
				(new ImageIcon(new ImageIcon("images/picture3.gif").getImage().getScaledInstance(IMAGE_X, IMAGE_Y,Image.SCALE_SMOOTH))),
				(new ImageIcon(new ImageIcon("images/picture4.gif").getImage().getScaledInstance(IMAGE_X, IMAGE_Y,Image.SCALE_SMOOTH))),
				(new ImageIcon(new ImageIcon("images/picture5.gif").getImage().getScaledInstance(IMAGE_X, IMAGE_Y,Image.SCALE_SMOOTH))),
				(new ImageIcon(new ImageIcon("images/picture6.gif").getImage().getScaledInstance(IMAGE_X, IMAGE_Y,Image.SCALE_SMOOTH))),
				(new ImageIcon(new ImageIcon("images/picture7.gif").getImage().getScaledInstance(IMAGE_X, IMAGE_Y,Image.SCALE_SMOOTH))),
				(new ImageIcon(new ImageIcon("images/picture8.gif").getImage().getScaledInstance(IMAGE_X, IMAGE_Y,Image.SCALE_SMOOTH))),
				(new ImageIcon(new ImageIcon("images/picture9.gif").getImage().getScaledInstance(IMAGE_X, IMAGE_Y,Image.SCALE_SMOOTH))),
		};

	private JButton[] words = new JButton[10];
	private JLabel image = new JLabel();

	public MatchingGame() {

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		setContentPane(new JLabel(new ImageIcon()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

		setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/GameBack1.jpg")
				.getImage().getScaledInstance(getWidth(), getHeight(),
						Image.SCALE_SMOOTH))));

		//read words through .csv file
		FrenchFileInput f = new FrenchFileInput();
		opt = f.recieveMatches();

		Font instructionsFont = new Font("Serif", Font.PLAIN, 24);
		instructions.setFont(instructionsFont);
		instructions.setBounds(25, 25, FRAME_WIDTH - 50, 50);
		instructions.setForeground(Color.WHITE);
		instructions.setVisible(true);
		add(instructions);

		//no repeats for text
		for(int i = 0; i < MATCHES; i++)
			noRep.add(i);

		for (int x = 0; x < 5; x++) {

			words[x] = new JButton();
			words[x].setText(opt[x]);
			words[x].setBounds(FRAME_WIDTH / 2 - 75, 100 + 75 * x, 150, 50);
			words[x].setVisible(true);
			words[x].addActionListener(this);
			add(words[x]);

		}

		for (int x = 5; x < 10; x++) {

			words[x] = new JButton();
			words[x].setText(opt[x]);
			words[x].setBounds(FRAME_WIDTH / 2 + 125, 100 + 75 * (x - 5), 150, 50);
			words[x].setVisible(true);
			words[x].addActionListener(this);
			add(words[x]);

		}

		image.setBounds(25, 125, IMAGE_X, IMAGE_Y);
		image.setIcon(pictures[selectRandomNumber()]);
		image.setVisible(true);
		add(image);

		this.repaint();

		if (OptionsGUI.getDifficulty() == "Easy") {
			timeRemaining = 60;
			lives = 5;
		} else if (OptionsGUI.getDifficulty() == "Normal") {
			timeRemaining = 45;
			lives = 4;
		} else if (OptionsGUI.getDifficulty() == "Hard") {
			timeRemaining = 30;
			lives = 3;
		}

		timerLabel.setBounds(25, FRAME_HEIGHT - 75, 50, 50);
		timerLabel.setText(Integer.toString(timeRemaining));
		timerLabel.setForeground(Color.WHITE);
		timerLabel.setFont(font);
		timerLabel.setVisible(true);
		add(timerLabel);

		livesLabel = new JLabel[lives];

		for(int x = 0; x < lives; x++) {

			//change image
			livesLabel[x] = new JLabel(new ImageIcon(new ImageIcon("images/face" + 
					CharacterSelectGUI.getSelected() + ".jpg")
					.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			livesLabel[x].setBounds(100 + 75 * x, FRAME_HEIGHT - 75, 50, 50);
			livesLabel[x].setVisible(true);
			add(livesLabel[x]);

		}

		countDown.start();

		scoreLabel.setBounds(FRAME_WIDTH - 125, FRAME_HEIGHT - 75, 100, 50);
		scoreLabel.setText(Integer.toString(score));
		scoreLabel.setFont(font);
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setVisible(true);
		add(scoreLabel);

		multiplierLabel.setBounds(FRAME_WIDTH - 125, 100, 50, 50);
		multiplierLabel.setText(Integer.toString(multiplier) + "x");
		multiplierLabel.setFont(font);
		multiplierLabel.setForeground(Color.WHITE);
		multiplierLabel.setVisible(true);
		add(multiplierLabel);

		this.repaint();

	}

	//generate random number to show image and know the word to match with it
	public int selectRandomNumber() {

		int x = r.nextInt(noRep.size());

		int y = noRep.get(x);
		noRep.remove(x);
		return y;

	}

	private void endGame(){

		countDown.stop();

		if(lives>0)
			new FillInTheBlank(lives, score, multiplier);
		else
			new MainMenuGUI();
		
		dispose();


	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == countDown) {
			timeRemaining--;
			timerLabel.setText(Integer.toString(timeRemaining));
		}

		//adjust game based on difficulty
		if (matchesRemaining > 0 && timeRemaining > 0) {

			for(int x = 0; x < 10; x++) {

				if (e.getSource() == words[x]) {

					if (image.getIcon() == pictures[x]) {
						score += 100 * multiplier;
						scoreLabel.setText(Integer.toString(score));
						multiplier++;

						if(OptionsGUI.getDifficulty() == "Easy")
							words[x].setVisible(false);

					} else {

						--lives;
						livesLabel[lives].setIcon(null);

						if (lives == 0) {
							JOptionPane.showMessageDialog(null, "No lives left. Game over."
									+ " / Il ne reste plus aucune vie. Jeu terminé.",
									null, JOptionPane.WARNING_MESSAGE);
							//end screen
							endGame();
						}

						multiplier = 1;

					}

					multiplierLabel.setText(Integer.toString(multiplier) + "x");

					if (noRep.size() > 0)
						image.setIcon(pictures[selectRandomNumber()]);	

					matchesRemaining--;

				}

			}		

		} else if(timeRemaining == 0){


			multiplier = 1;
			multiplierLabel.setText(Integer.toString(multiplier) + "x");
			countDown.stop();
			JOptionPane.showMessageDialog(null, "Times up! Proceeding to next screen."
					+ " / Le temps est écoulé! Passant à l'écran suivant.", 
					null, JOptionPane.WARNING_MESSAGE);

			//next screen
			endGame();

		}else if(matchesRemaining == 0){
			JOptionPane.showMessageDialog(null, "You completed all the questions!"
					+ " Proceeding to next screen. / Vous avez rempli toutes les questions!"
					+ " Passant à l'écran suivant.", 
					null, JOptionPane.WARNING_MESSAGE);

			//next screen
			endGame();

		}



	}



}



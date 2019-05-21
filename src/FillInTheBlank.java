import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class FillInTheBlank extends JFrame implements ActionListener {

	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 450;

	private static final int QUESTIONS = 5;
	private String[] answer = new String[5];

	private ArrayList<Integer> noRep = new ArrayList<Integer>();
	private String[] opt;
	private Random r = new Random();

	private JLabel question = new JLabel();

	//change
	private JLabel instructions = new JLabel("Fill in the blank/Remplissez le champ vide", SwingConstants.CENTER);

	private JLabel timerLabel = new JLabel();
	private JLabel scoreLabel = new JLabel();

	private int score;
	private int timeRemaining;
	private int lives;
	private int questionsRemaining = 5;
	private int multiplier = 1;

	private JLabel[] livesLabel;
	private JLabel multiplierLabel = new JLabel();

	private Timer countDown = new Timer(1000, this);

	private Font font = new Font("Serif", Font.PLAIN, 24);

	private JButton[] words = new JButton[5];

	public FillInTheBlank(int liv, int scor, int multi) {

		lives = liv;
		score = scor;
		multiplier = multi;

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		setContentPane(new JLabel(new ImageIcon()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

		setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/GameBack2.jpg")
				.getImage().getScaledInstance(getWidth(), getHeight(),
						Image.SCALE_SMOOTH))));

		//read words through .csv file
		FrenchFileInput f = new FrenchFileInput();
		opt = f.receiveFillQuestions();
		answer = f.receiveFillBlanks();

		Font instructionsFont = new Font("Serif", Font.PLAIN, 24);
		instructions.setFont(instructionsFont);
		instructions.setBounds(25, 25, FRAME_WIDTH - 50, 50);
		instructions.setForeground(Color.WHITE);
		instructions.setVisible(true);
		add(instructions);

		for(int i = 0; i < QUESTIONS; i++)
			noRep.add(i);

		for (int x = 0; x < 5; x++) {

			words[x] = new JButton();
			words[x].setText(answer[x]);
			words[x].setBounds(50 + 150 * x, 300, 125, 50);
			words[x].setVisible(true);
			words[x].addActionListener(this);
			add(words[x]);

		}

		//get lives from previous game

		if (OptionsGUI.getDifficulty() == "Easy") {
			timeRemaining = 15;
			//			lives = 5; 
		} else if (OptionsGUI.getDifficulty() == "Normal") {
			timeRemaining = 10;
			//			lives = 4;
		} else if (OptionsGUI.getDifficulty() == "Hard") {
			timeRemaining = 5;
			//			lives = 3;
		}

		timerLabel.setBounds(25, FRAME_HEIGHT - 75, 50, 50);
		timerLabel.setText(Integer.toString(timeRemaining));
		timerLabel.setFont(font);
		timerLabel.setForeground(Color.WHITE);
		timerLabel.setVisible(true);
		add(timerLabel);

		question.setBounds(25, 200, 800, 50);
		question.setText(opt[selectRandomNumber()]);
		question.setHorizontalAlignment(SwingConstants.CENTER);
		question.setForeground(Color.WHITE);
		question.setFont(font);
		question.setVisible(true);
		add(question);

		livesLabel = new JLabel[lives];

		for(int x = 0; x < lives; x++) {

			//			livesLabel[x] = new JLabel(new ImageIcon(new ImageIcon("images/face" + 
			//					CharacterSelectGUI.getSelected() + ".png").getImage()
			//					.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			livesLabel[x] = new JLabel(new ImageIcon(new ImageIcon("images/face"+CharacterSelectGUI.getSelected()+".jpg")
					.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			livesLabel[x].setBounds(100 + 75 * x, FRAME_HEIGHT - 75, 50, 50);
			livesLabel[x].setVisible(true);
			add(livesLabel[x]);

		}

		countDown.start();

		scoreLabel.setBounds(FRAME_WIDTH - 125, FRAME_HEIGHT - 75, 100, 50);
		scoreLabel.setText(Integer.toString(score));
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setFont(font);
		scoreLabel.setVisible(true);
		add(scoreLabel);

		multiplierLabel.setBounds(FRAME_WIDTH - 125, 100, 50, 50);
		multiplierLabel.setText(Integer.toString(multiplier) + "x");
		multiplierLabel.setForeground(Color.WHITE);
		multiplierLabel.setFont(font);
		multiplierLabel.setVisible(true);
		add(multiplierLabel);

		this.repaint();

	}

	public int selectRandomNumber() {

		int x = r.nextInt(noRep.size());

		int y = noRep.get(x);
		noRep.remove(x);
		return y;

	}

	private void endGame(){

		countDown.stop();

		if(lives>0)
			new Runner(lives,score,multiplier);

		else
			new MainMenuGUI();
		
		dispose();

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == countDown) {
			timeRemaining--;
			timerLabel.setText(Integer.toString(timeRemaining));
		}

		if (questionsRemaining > 0 && timeRemaining > 0) {

			for (int x = 0; x < words.length; x++) {

				if (e.getSource() == words[x]) {

					if (question.getText() == opt[x]) {
						score += 100 * multiplier;
						scoreLabel.setText(Integer.toString(score));
						multiplier++;
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

					if (OptionsGUI.getDifficulty() == "Easy")
						timeRemaining = 15;
					else if (OptionsGUI.getDifficulty() == "Normal")
						timeRemaining = 10;
					else if (OptionsGUI.getDifficulty() == "Hard") 
						timeRemaining = 5;

					timerLabel.setText(Integer.toString(timeRemaining));

					multiplierLabel.setText(Integer.toString(multiplier) + "x");

					if (noRep.size() > 0)
						question.setText(opt[selectRandomNumber()]);	

					questionsRemaining--;


				}

			}

		} else {

			if (timeRemaining == 0) {

				countDown.stop();

				multiplier = 1;
				multiplierLabel.setText(Integer.toString(multiplier) + "x");
				lives--;

				livesLabel[lives].setIcon(null);

				if (lives == 0) {
					JOptionPane.showMessageDialog(null, "No lives left. Game over."
							+ " / Il ne reste plus aucune vie. Jeu terminé.",
							null, JOptionPane.WARNING_MESSAGE);
					//end screen
					endGame();

				}

				if (OptionsGUI.getDifficulty() == "Easy")
					timeRemaining = 15;
				else if (OptionsGUI.getDifficulty() == "Normal")
					timeRemaining = 10;
				else if (OptionsGUI.getDifficulty() == "Hard") 
					timeRemaining = 5;

				timerLabel.setText(Integer.toString(timeRemaining));

				if (noRep.size() > 0)
					question.setText(opt[selectRandomNumber()]);	

				questionsRemaining--;

				countDown.start();

			}

			if (questionsRemaining == 0) {

				JOptionPane.showMessageDialog(null, "You completed all the questions!"
						+ " Proceeding to next screen. / Vous avez rempli toutes les questions!"
						+ " Passant à l'écran suivant.", 
						null, JOptionPane.WARNING_MESSAGE);

				endGame();

			}

		}
	}


}





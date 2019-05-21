

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;

public class Runner extends JFrame implements KeyListener, ActionListener{


	private final int SIZE_X = 800, SIZE_Y = 550;
	private Hero hero;
	private Background bg[] = new Background[3];
	private int count = 0;
	private JLabel q = new JLabel();
	private Timer boxMaker = new Timer(1000, this);
	private ArrayList<Phrase> phrases = new ArrayList<Phrase>();
	private Random random = new Random();
	private Timer moveO = new Timer(40,this);
	private ArrayList<JLabel> o = new ArrayList<JLabel>();
	private Font font = new Font("Serif", Font.PLAIN, 48);
	private Font fonti = new Font("Serif", Font.PLAIN, 30);
	private String cor;
	private int score,multiplier,lives;

	private JLabel scoreLabel = new JLabel();
	private JLabel multiplierLabel = new JLabel();
	private JLabel[] livesLabel;

	public Runner(int liv, int scor, int multi) {
		lives = liv;
		score = scor;
		multiplier = multi;
		setTitle("Jimp Jump Slump");
		setSize(SIZE_X, SIZE_Y);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		addKeyListener(this);

		//static background
		setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/GameBack3.jpg")
				.getImage().getScaledInstance(getWidth(), getHeight(),
						Image.SCALE_SMOOTH))));

		//create backgrounds to scroll
		//		for(int i=0;i<bg.length;i++){
		//			bg[i] = new Background(getWidth(),getHeight());
		//			bg[i].setLocation((SIZE_X)*i,0);
		//			add(bg[i]);
		//
		//		}

		//create hero
		String[] hNames = CharacterSelectGUI.getHeroNames();
		int sel = CharacterSelectGUI.getSelected();
		hero = new Hero(hNames[sel], sel);
		hero.setBorder(BorderFactory.createLineBorder(Color.YELLOW,1));
		add(hero);

		scoreLabel.setBounds(getWidth() - 200, getHeight() - 100, 200, 50);
		scoreLabel.setText(Integer.toString(score));
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setFont(font);
		scoreLabel.setVisible(true);
		add(scoreLabel);

		multiplierLabel.setBounds(getWidth() - 200, 50, 100, 45);
		multiplierLabel.setText(Integer.toString(multiplier) + "x");
		multiplierLabel.setForeground(Color.WHITE);
		multiplierLabel.setFont(font);
		multiplierLabel.setVisible(true);
		add(multiplierLabel);

		livesLabel = new JLabel[lives];

		for(int x = 0; x < lives; x++) {

			//			livesLabel[x] = new JLabel(new ImageIcon(new ImageIcon("images/face" + 
			//					CharacterSelectGUI.getSelected() + ".png").getImage()
			//					.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			livesLabel[x] = new JLabel(new ImageIcon(new ImageIcon("images/face"+CharacterSelectGUI.getSelected()+".jpg")
					.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			livesLabel[x].setBounds(100 + 75 * x, getHeight() - 100, 50, 50);
			livesLabel[x].setVisible(true);
			add(livesLabel[x]);

		}

		FrenchFileInput a = new FrenchFileInput();
		phrases = a.recieveQuestions();

		q.setFont(fonti);
		q.setBounds(25, 25, 700, 50);
		q.setForeground(Color.WHITE);
		q.setVisible(true);
		add(q);

		boxMaker.start();
		moveO.start();


		//		System.out.println(Arrays.toString(phrases.toArray()));

		repaint();

	}

	private void endGame(){

		boxMaker.stop();
		moveO.stop();

		//		if(lives>0)
		new EndScreen(score);

		dispose();

	}

	public void keyPressed(KeyEvent e) {

		//arrowkeys
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			hero.jump();

	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void actionPerformed(ActionEvent e) {

		if(phrases.size()>0 && o.size()<3){
			if(e.getSource() == boxMaker){
				//				System.out.println("fff");

				count++;

				int k = random.nextInt(phrases.size());

				q.setText(phrases.get(k).getPhrase());

				ArrayList<Integer> posi = new ArrayList<Integer>(Arrays.asList(1,2,3));
				ArrayList<String> answ = new ArrayList<String>();
				for(int i=0;i<3;i++)
					answ.add(phrases.get(k).getAnsPhr()[i]);

//				System.out.println(Arrays.toString(answ.toArray()));/////ANSWERS
				
				cor = answ.get(0);

				for(int i=0;i<3;i++){
					int j = random.nextInt(posi.size());
					int a = random.nextInt(answ.size());

					JLabel x = new JLabel();
					x.setBounds(SIZE_X, 125*posi.get(j), 250, 50);
					x.setText(answ.get(a));
					x.setFont(font);
					x.setForeground(Color.WHITE);
					x.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
					x.setVisible(true);

					o.add(x);
					add(o.get(o.size()-1));

					answ.remove(a);
					posi.remove(j);

				}

				phrases.remove(k);

			}

		}else if(e.getSource() == moveO){
			//			System.out.println("Move");
			if(o.size()>0){
				for(int i=0;i<o.size();i++){
					o.get(i).setLocation(o.get(i).getX()-2,o.get(i).getY());

					if(o.get(i).getBounds().intersects(hero.getBounds())){

						if(o.get(i).getText() == cor) {
//							System.out.println("Correct");
							score += 100 * multiplier;
							scoreLabel.setText(Integer.toString(score));
							multiplier++;

						}else{//not correct
//							System.out.println("Incorrect");

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

						for(int j=0;j<o.size();j++)
							o.get(j).setLocation(-1000,0);

						hero.reset();
						
					}

				}

				if(o.get(0).getX()<=-40)
					while(o.size()>0){
						o.remove(0);
					}

				if(count>=5)
					endGame();
				//				System.out.println(count);
				//				System.out.println(o.size());


			}
		}//end else if



	}

}//end Runner


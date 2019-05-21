import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CharacterSelectGUI extends JFrame implements ActionListener{

	//adjust screen size based on resolution
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private static final int CIMAGE_X = 400;
	private static final int CIMAGE_Y = 400;

	private JLabel title = new JLabel("Sélection de personnage");
	private JLabel charDisplay = new JLabel();
	private JLabel cLabel = new JLabel("Personnage:");
	private JRadioButton[] cSelect = new JRadioButton[3];//0 is character, 1 is colour, 2 is costume
	private JButton start = new JButton("Start!");
	private JButton menu = new JButton("Menu");
	
	private static String[] heroNames = {"Batman", "Wonder Woman", "Thor"};
	private static int selected = 0;//defaulted selected

	Font titleFont = new Font("Serif", Font.BOLD, 40);
	Font labelFont = new Font("Serif", Font.PLAIN,24);

	public static final ImageIcon[] hero = 
		{//images for display
			(new ImageIcon(new ImageIcon("images/face0.jpg").getImage().getScaledInstance(CIMAGE_X, CIMAGE_Y,Image.SCALE_SMOOTH))),
			(new ImageIcon(new ImageIcon("images/face1.jpg").getImage().getScaledInstance(CIMAGE_X, CIMAGE_Y,Image.SCALE_SMOOTH))),
			(new ImageIcon(new ImageIcon("images/face2.jpg").getImage().getScaledInstance(CIMAGE_X, CIMAGE_Y,Image.SCALE_SMOOTH)))
		};

	public CharacterSelectGUI(){

		setUpFrame();
		setUpLabels();
		setUpRadioButtons();
		setUpButtons();

	}

	private void setUpFrame() {

		//JFrame
		setSize(1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setTitle("Character Select");
		setVisible(true);
		
		setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/charSBack.jpg")
				.getImage().getScaledInstance(getWidth(), getHeight(),
					Image.SCALE_SMOOTH))));

	}

	private void setUpLabels() {

		//displays title
		title.setBounds(getWidth()/2-200,50,500,100);
		title.setFont(titleFont);
		title.setVisible(true);
		title.setForeground(Color.WHITE);
		add(title);

		//displays the image of the character you're playing
		charDisplay.setBounds(20,150,CIMAGE_X,CIMAGE_Y);
		charDisplay.setIcon(hero[selected]);
		//		charDisplay.setOpaque(true);
		//		charDisplay.setBackground(Color.BLUE);
		add(charDisplay);

		//label for radio buttons
		cLabel.setBounds(500,200,180,20);
		cLabel.setFont(labelFont);
		cLabel.setVisible(true);
		cLabel.setForeground(Color.WHITE);
		add(cLabel);

	}

	private void setUpRadioButtons() {

		//character select radio buttons
		ButtonGroup characters = new ButtonGroup();

		for(int x=0;x<cSelect.length;x++){
			cSelect[x] = new JRadioButton(heroNames[x]);
			cSelect[x].setBounds(cLabel.getX()+cLabel.getWidth(),cLabel.getY()+40*x,200,24);
			cSelect[x].setFont(labelFont);
			cSelect[x].setForeground(Color.WHITE);
			cSelect[x].setOpaque(false);
			cSelect[x].setVisible(true);
			cSelect[x].addActionListener(this);
			characters.add(cSelect[x]);
			add(cSelect[x]);
		}

		cSelect[selected].setSelected(true);//default selected(first) radio button

	}

	private void setUpButtons() {

		//start button
		start.setBounds(600,500,150,50);
		start.setFont(labelFont);
		start.setVisible(true);
		start.addActionListener(this);
		add(start);

		menu.setBounds(start.getX()+start.getWidth()+20, start.getY(), 150, 50);
		menu.setFont(labelFont);
		menu.setVisible(true);
		menu.addActionListener(this);
		add(menu);

	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == start){
			new MatchingGame();
			this.dispose();
		} 

		for (int x = 0; x < 3; x++) {

			//changes image if clicks on the radio button
			if(e.getSource() == cSelect[x]){
				charDisplay.setIcon(hero[x]);
				selected = x;
			}
		}

		if (e.getSource() == menu) {
			new MainMenuGUI();
			this.dispose();
		}

	}

	public static int getSelected() {
		return selected;
	}

	public static void setSelected(int selected) {
		CharacterSelectGUI.selected = selected;
	}
	
	public static String[] getHeroNames() {
		return heroNames;
	}

	public static void setHeroNames(String[] heroNames) {
		CharacterSelectGUI.heroNames = heroNames;
	}

}

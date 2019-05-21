import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;


public class Hero extends JLabel implements ActionListener{

	private final int MAX_SPEED = 3;

	private int speed = MAX_SPEED;

	private Timer moveTimer = new Timer(20,this);

	private boolean h;

	private String name;
	private int characterNumber;

	public Hero(String name, int characterNumber) {
		super();
		this.name = name;
		this.characterNumber = characterNumber;
		setBounds(50,400,70,70);
		setIcon(new ImageIcon(new ImageIcon("images/hero"+2+".jpg").getImage().getScaledInstance(getWidth(), getHeight(),Image.SCALE_SMOOTH)));
		setVisible(true);

		moveTimer.start();
		speed = MAX_SPEED;

	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCharacterNumber() {
		return characterNumber;
	}
	public void setCharacterNumber(int characterNumber) {
		this.characterNumber = characterNumber;
	}


	public void jump(){

		h=true;
		speed = MAX_SPEED;

	}

	public void reset(){

		h = false;
		speed = MAX_SPEED;
		setBounds(50,400,70,70);

	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource() == moveTimer){

			if(h)
				this.setLocation(getX()+speed, getY());
			else
				this.setLocation(getX(), getY()-speed);
			
		}

		if(getY()<100){
			speed=-MAX_SPEED;
		}else if(getY()>400)
			speed=MAX_SPEED;



	}

}

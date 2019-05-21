
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Background extends JLabel implements ActionListener{
	
	private Timer scroller = new Timer(20, this);

	private int x;
	private int y;

	public Background(int x, int y) {
		this.x = x+10;
		this.y = y;

		setVisible(true);
		setSize(x,y);
		setIcon(new ImageIcon(new ImageIcon("images/game_background.jpg").getImage().getScaledInstance(x, y, 0)));
		scroller.start();

	}


	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == scroller)
			setLocation(getX()-2,getY());
		
		if(getX() == -getWidth())
			this.setLocation(getWidth(), 0);
		
	}


}
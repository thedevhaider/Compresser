import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Native;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class logo extends JFrame{
	JLabel ras, text;
	public logo() throws IOException{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) dim.getHeight();
		int width = (int) dim.getWidth();
		setUndecorated(true);
		setBounds(width-(400 + width/2), height-(250 + height/2), 850, 800);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		//setAlwaysOnTop(true);
		getContentPane().setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		ras = new JLabel();
		ras.setBounds(220, 20, 357, 366);
		ras.setIcon(new ImageIcon(getClass().getResource("res/rasengan.png")));
		add(ras);
		text = new JLabel();
		text.setBounds(-25, 260, 800, 300);
		text.setIcon(new ImageIcon(getClass().getResource("res/text.png")));
		add(text);
		repaint();
		try{
			Thread.sleep(5000);
		}catch(Exception e){
			e.printStackTrace();
		}
		dispose();
		loadingframe ob = new loadingframe();
		
		
	}
}

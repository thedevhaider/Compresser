import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class loadingframe extends JFrame{
	JProgressBar bar;
	JLabel land;
	public loadingframe(){
		try{
			for(LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
				if("Windows".equals(info.getName())){
					UIManager.setLookAndFeel(info.getClassName());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) dim.getHeight();
		int width = (int) dim.getWidth();
		setUndecorated(true);
		setBounds(width-(400 + width/2), height-(250 + height/2), 800, 500);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		getContentPane().setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		land = new JLabel();
		land.setBounds(220, 50, 357, 366);
		land.setIcon(new ImageIcon(getClass().getResource("res/rasengan.png")));
		add(land);
		repaint();
		bar = new JProgressBar();
		bar.setBounds(200, 400, 400, 20);
		bar.setStringPainted(true);
		bar.setForeground(Color.decode("#82b1ff"));
		for(int i = 1; i <= 100; i++){
			try {
				Thread.sleep(50);
				bar.setValue(i);
				bar.setForeground(Color.decode("#82b1ff"));
				//bar.setValue(100);
				//bar.setBounds(1, 280, i * 5, 20);
				add(bar);
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			}
		}
		add(bar);
		dispose();
		try {
			Menu ob = new Menu();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

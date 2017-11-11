import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Menu extends JFrame{
	JLabel comp, enc, set, clo;
	public Menu() throws InterruptedException{
		repaint();
		try{
			for(LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
				if("Nimbus".equals(info.getName())){
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
		setBounds(width-(300 + width/2), height-(300 + height/2), 600, 600);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		getContentPane().setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		repaint();
		comp = new JLabel();
		comp.setIcon(new ImageIcon(getClass().getResource("res/compresser.png")));
		comp.setBounds(60, 30, 250, 265);
		add(comp);
		enc = new JLabel();
		enc.setIcon(new ImageIcon(getClass().getResource("res/encrypt.png")));
		enc.setBounds(310, 45, 265, 250);
		add(enc);
		set = new JLabel();
		set.setIcon(new ImageIcon(getClass().getResource("res/settings.png")));
		set.setBounds(45, 295, 265, 250);
		add(set);
		clo = new JLabel();
		clo.setIcon(new ImageIcon(getClass().getResource("res/close.png")));
		clo.setBounds(310, 295, 250, 265);
		add(clo);
		repaint();
		
		for(int i = 1; i <= 2; i++){
			Thread.sleep(100);
			comp.setBounds(55, 25, 250, 265);
			add(comp);
			Thread.sleep(100);
			comp.setBounds(60, 30, 250, 265);
			add(comp);
			enc.setBounds(315, 40, 265, 250);
			add(enc);
			Thread.sleep(100);
			enc.setBounds(310, 45, 265, 250);
			add(enc);
			clo.setBounds(315, 300, 250, 265);
			add(clo);
			Thread.sleep(100);
			clo.setBounds(310, 295, 250, 265);
			add(clo);
			set.setBounds(40, 300, 265, 250);
			add(set);
			Thread.sleep(100);
			set.setBounds(45, 295, 265, 250);
			add(set);
		}
		
		Handler handle = new Handler();
		comp.addMouseListener(handle);
		enc.addMouseListener(handle);
		set.addMouseListener(handle);
		clo.addMouseListener(handle);
		
		comp.addMouseMotionListener(handle);
		enc.addMouseMotionListener(handle);
		set.addMouseMotionListener(handle);
		clo.addMouseMotionListener(handle);
		
		
		repaint();
	}
	private class Handler implements MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == comp){
				
				dispose();
				compresser ob = new compresser();
			}else if(e.getSource() == enc){
				
				
			}else if(e.getSource() == set){
				
				
			}else if(e.getSource() == clo){
				
				
				dispose();
				
				
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == comp){
				comp.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
				comp.setBounds(55, 25, 250, 265);
			}else if(e.getSource() == enc){
				enc.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
				enc.setBounds(315, 40, 265, 250);
			}else if(e.getSource() == set){
				set.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
				set.setBounds(40, 300, 265, 250);
			}else if(e.getSource() == clo){
				clo.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
				clo.setBounds(315, 300, 250, 265);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == comp){
				comp.setBorder(null);
				comp.setBounds(60, 30, 250, 265);
			}else if(e.getSource() == enc){
				enc.setBorder(null);
				enc.setBounds(310, 45, 265, 250);
			}else if(e.getSource() == set){
				set.setBorder(null);
				set.setBounds(45, 295, 265, 250);
			}else if(e.getSource() == clo){
				clo.setBorder(null);
				clo.setBounds(310, 295, 250, 265);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == comp){
			
			}else if(e.getSource() == enc){
				
			}else if(e.getSource() == set){
				
			}else if(e.getSource() == clo){
				
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}

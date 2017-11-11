import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.io.File;
import java.util.ArrayList;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import javax.swing.UIManager.LookAndFeelInfo;

public class compresser extends JFrame{
	JPanel header, div, mdiv, wrong;
	JLabel home, close, compresser, arclab, archive, address, lformat, llevel, pass, password, epass, rpass;
	JTextField arcname, arcsave;
	JButton browse, savebro, compress;
	JFileChooser file, forsave;
	File selectedfile, savepath;
	File files[] = new File[100];
	JComboBox format, level;
	JCheckBox cpass;
	JPasswordField enter, reenter;
	String filext[] = {".7z", ".zip", ".rar", ".tar"};
	String complvl[] = {"Fastest", "Fast", "Normal", "Maximum", "Ultra"};
	int deflatelvl[] = {
			Zip4jConstants.DEFLATE_LEVEL_FASTEST,
			Zip4jConstants.DEFLATE_LEVEL_FAST,
			Zip4jConstants.DEFLATE_LEVEL_NORMAL,
			Zip4jConstants.DEFLATE_LEVEL_MAXIMUM,
			Zip4jConstants.DEFLATE_LEVEL_ULTRA
	};
	int lvlindex, passenable = 0;
	String folderpath;
	ZipFile zfile;
	String archivename = new String();
	ZipParameters parameters;
	int filecount = 1, filecount2 = 0;
	public compresser(){
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
		setBounds(width-(300 + width/2), height-(310 + height/2), 600, 620);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#448aff")));
		//getContentPane().setBackground(Color.decode("#bbdefb"));
		//parameters = new ZipParameters();
		//parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		
		
		Handler handle = new Handler();
		ItemHandle Itemhandle = new ItemHandle();
		KeyHandle keyhandle = new KeyHandle();
	    header = new JPanel();
		header.setBounds(0, 0, 600, 50);
		header.setBackground(Color.decode("#42a5f5"));
		header.setLayout(null);
		add(header);
		home = new JLabel();
		home.setBounds(270, 5, 54, 40);
		home.setIcon(new ImageIcon(getClass().getResource("res/home.png")));
		home.addMouseListener(handle);
		home.addMouseMotionListener(handle);
		header.add(home);
		close = new JLabel();
		close.setBounds(555, 5, 40, 40);
		close.setIcon(new ImageIcon(getClass().getResource("res/close1.png")));
		close.addMouseListener(handle);
		close.addMouseMotionListener(handle);
		header.add(close);
		compresser = new JLabel();
		compresser.setBounds(225, 55, 200, 40);
		compresser.setText("  Compresser");
		compresser.setFont(new Font("Arial", Font.BOLD, 20));
		compresser.setForeground(Color.decode("#0F7FDA"));
		add(compresser);
		arclab = new JLabel();
		arclab.setBounds(10, 90, 80, 40);
		arclab.setText("Select:");
		arclab.setFont(new Font("Arial", Font.BOLD, 16));
		arclab.setForeground(Color.decode("#448aff"));
		add(arclab);
		arcname = new JTextField();
		arcname.setBounds(85, 98, 400, 27);
		//arcname.setBackground(Color.decode("#e3f2fd"));
		arcname.setFont(new Font("Arial", Font.PLAIN, 14));
		//arcname.setForeground(Color.decode("#448aff"));
		arcname.setEditable(false);
		add(arcname);
		browse = new JButton();
		browse.setBounds(490, 96, 105, 30);
		browse.setText("Browse");
		browse.setFont(new Font("Arial", Font.BOLD, 14));
		//browse.setBackground(Color.decode("#448aff"));
		//browse.setForeground(Color.decode("#e3f2fd"));
		browse.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int check = file.showOpenDialog(new JFrame());
				if(check==JFileChooser.APPROVE_OPTION){
					try {
						
						files[filecount - 1] = file.getSelectedFile();
						arcname.setText(file.getSelectedFile().getAbsolutePath());
						arclab.setText("Select:(" + filecount + ")");
						arcsave.setText("archive" + filext[1]);
						filecount++;
						if(file.getSelectedFile().exists()){
							savebro.setEnabled(true);
						}
						System.out.println("Okay Browse");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Not ok Browse");
					}
						
				}
					
			}
			
			
		});
		add(browse);
		file = new JFileChooser();
		file.setMultiSelectionEnabled(true);
		file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		file.setCurrentDirectory(new File("res/"));
		archive = new JLabel();
		archive.setBounds(10, 145, 80, 40);
		archive.setText("Archive:");
		archive.setFont(new Font("Arial", Font.BOLD, 16));
		archive.setForeground(Color.decode("#448aff"));
		add(archive);
		address = new JLabel();
		address.setBounds(85, 125, 400, 40);
		address.setText("Press Save Button Select the Directory.");
		address.setFont(new Font("Arial", Font.PLAIN, 11));
		address.setForeground(Color.decode("#448aff"));
		add(address);
		arcsave = new JTextField();
		arcsave.setBounds(85, 153, 400, 27);
		//arcsave.setBackground(Color.decode("#e3f2fd"));
		arcsave.setFont(new Font("Arial", Font.PLAIN, 14));
		//arcsave.setForeground(Color.decode("#448aff"));
		arcsave.setEditable(false);
		add(arcsave);
		savebro = new JButton();
		savebro.setBounds(490, 151, 105, 30);
		savebro.setEnabled(false);
		savebro.setText("Save");
		savebro.setFont(new Font("Arial", Font.BOLD, 14));
		//savebro.setBackground(Color.decode("#448aff"));
		//savebro.setForeground(Color.decode("#e3f2fd"));
		savebro.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int check = file.showSaveDialog(new JFrame());
				if(check==JFileChooser.APPROVE_OPTION){
					savepath = file.getSelectedFile();
					address.setText(savepath.getParent());
					System.out.println(savepath.getParent());
					
				}
			}
			
		});
		add(savebro);
		div = new JPanel();
		div.setBounds(10, 190, 580, 2);
		//div.setBackground(Color.decode("#9FD4FF"));
		div.setLayout(null);
		add(div);
		lformat = new JLabel();
		lformat.setBounds(10, 200, 80, 40);
		lformat.setText("Format:");
		lformat.setFont(new Font("Arial", Font.BOLD, 16));
		lformat.setForeground(Color.decode("#448aff"));
		add(lformat);
		format = new JComboBox(filext);
		format.setBounds(105, 205, 150, 30);
		//format.setBackground(Color.decode("#448aff"));
		format.setFont(new Font("Arial", Font.PLAIN, 14));
		format.setSelectedIndex(1);
		format.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				String original = arcsave.getText();
				String neww = new String();
				if(e.getStateChange()==ItemEvent.SELECTED){
					for(int i = 0; i < original.length(); i++){
						if(original.charAt(i)=='.'){
							break;
						}else{
							neww = neww + original.charAt(i);
						}
					}
					arcsave.setText(neww + filext[format.getSelectedIndex()]);
				}
				
			}
			
		});
		add(format);
		llevel = new JLabel();
		llevel.setBounds(10, 235, 80, 40);
		llevel.setText("Level:");
		llevel.setFont(new Font("Arial", Font.BOLD, 16));
		llevel.setForeground(Color.decode("#448aff"));
		add(llevel);
		level = new JComboBox(complvl);
		level.setBounds(105, 240, 150, 30);
		//level.setBackground(Color.decode("#448aff"));
		level.setFont(new Font("Arial", Font.PLAIN, 14));
		level.setSelectedIndex(2);
		level.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				//parameters.setCompressionLevel(deflatelvl[level.getSelectedIndex()]);
				lvlindex = level.getSelectedIndex();
			}
			
		});
		add(level);
		pass = new JLabel();
		pass.setBounds(10, 270, 90, 40);
		pass.setText("Password:");
		pass.setFont(new Font("Arial", Font.BOLD, 16));
		pass.setForeground(Color.decode("#448aff"));
		add(pass);
		cpass = new JCheckBox();
		cpass.setBounds(105, 280, 20, 20);
		cpass.addItemListener(Itemhandle);
		add(cpass);
		mdiv = new JPanel();
		mdiv.setBounds(300, 205, 2, 100);
		mdiv.setBackground(Color.decode("#9FD4FF"));
		mdiv.setLayout(null);
		add(mdiv);
		password = new JLabel();
		password.setBounds(330, 195, 200, 40);
		password.setText("Enter Password:");
		password.setFont(new Font("Arial", Font.BOLD, 16));
		password.setForeground(Color.decode("#0F7FDA"));
		add(password);
		epass = new JLabel();
		epass.setBounds(330, 230, 80, 40);
		epass.setText("Enter:");
		epass.setFont(new Font("Arial", Font.BOLD, 16));
		epass.setForeground(Color.decode("#448aff"));
		add(epass);
		enter = new JPasswordField();
		enter.setBounds(400, 235, 180, 27);
		//enter.setBackground(Color.decode("#e3f2fd"));
		enter.setFont(new Font("Arial", Font.PLAIN, 14));
		//enter.setForeground(Color.decode("#448aff"));
		enter.setEditable(false);
		enter.addKeyListener(keyhandle);
		add(enter);
		rpass = new JLabel();
		rpass.setBounds(330, 265, 80, 40);
		rpass.setText("Reenter:");
		rpass.setFont(new Font("Arial", Font.BOLD, 16));
		rpass.setForeground(Color.decode("#448aff"));
		add(rpass);
		reenter = new JPasswordField();
		reenter.setBounds(400, 270, 180, 27);
		//reenter.setBackground(Color.decode("#e3f2fd"));
		reenter.setFont(new Font("Arial", Font.PLAIN, 14));
		//reenter.setForeground(Color.decode("#448aff"));
		reenter.setEditable(false);
		reenter.addKeyListener(keyhandle);
		add(reenter);
		wrong = new JPanel();
		wrong.setBounds(582, 272, 5, 22);
		wrong.setBackground(Color.decode("#bbdefb"));
		wrong.setLayout(null);
		add(wrong);
		compress = new JButton();
		compress.setBounds(250, 312, 105, 40);
		compress.setEnabled(true);
		compress.setText("Compress");
		compress.setFont(new Font("Arial", Font.BOLD, 14));
		//compress.setBackground(Color.decode("#448aff"));
		//compress.setForeground(Color.decode("#e3f2fd"));
		compress.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					//ZipFile zipfile = new ZipFile(savepath.getParent() + "\\" + arcsave.getText());
					System.out.println(savepath.getParent() + "\\" + arcsave.getText());
					ZipFile zipfile = new ZipFile("zipfile.zip");
					System.out.println(savepath.getParent() + "\\" + arcsave.getText());
					ArrayList<File> filestoadd = new ArrayList<File>();
					for(int i = 0; i < files.length; i++){
						filestoadd.add(new File(files[i].getAbsolutePath()));
					}
					parameters = new ZipParameters();
					parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
					parameters.setCompressionLevel(deflatelvl[lvlindex]);
					if(passenable == 1){
						parameters.setEncryptFiles(true);
						parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
						parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
						parameters.setPassword(reenter.getPassword());
					}
					//zipfile.addFiles(filestoadd, parameters);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		});
		add(compress);
		
		repaint();
	}
	class Handler implements MouseMotionListener, MouseListener{

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			dispose();
			try {
				if(e.getSource()==home){
					Menu ob = new Menu();
				}else if(e.getSource()==close){
					dispose();
				}
				
			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==home){
				home.setIcon(new ImageIcon(getClass().getResource("res/home2.png")));
			}else if(e.getSource()==close){
				close.setIcon(new ImageIcon(getClass().getResource("res/close2.png")));
			}
			
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==home){
				home.setIcon(new ImageIcon(getClass().getResource("res/home.png")));
			}else if(e.getSource()==close){
				close.setIcon(new ImageIcon(getClass().getResource("res/close1.png")));
			}
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class ItemHandle implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(cpass.isSelected()){
				enter.setEditable(true);
				reenter.setEditable(true);
				passenable = 1;
				//parameters.setEncryptFiles(true);
				//parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
	            //parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
			}else{
				enter.setEditable(false);
				enter.setText("");
				reenter.setEditable(false);
				reenter.setText("");
				wrong.setBackground(Color.decode("#bbdefb"));
				passenable = 0;
				//parameters.setEncryptFiles(false);
			}
		}
		
	}
	class KeyHandle implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if(!(Arrays.equals(enter.getPassword(), reenter.getPassword()))){
				wrong.setBackground(Color.decode("#FF4A4A"));
			}
			if(Arrays.equals(enter.getPassword(), reenter.getPassword())){
				wrong.setBackground(Color.decode("#bbdefb"));
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if(!(Arrays.equals(enter.getPassword(), reenter.getPassword()))){
				wrong.setBackground(Color.decode("#FF4A4A"));
			}
			if(Arrays.equals(enter.getPassword(), reenter.getPassword())){
				wrong.setBackground(Color.decode("#bbdefb"));
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

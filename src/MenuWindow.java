import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.print.DocFlavor.URL;
import javax.swing.*;


public class MenuWindow extends JFrame implements ActionListener {

	private JButton quit;
	private JButton highSc;
	private JButton help;
	private JButton game;
	private ContentPanel contentPanel;
	
			
	
	  MenuWindow() {
		  	contentPanel = new ContentPanel(MenuWindow.class.getResource("/pictures/Yahtzee.jpg"));
		    add(contentPanel);
		    setSize(1024, 800);
		    setLocation(400,100);
		    game = new JButton("Play");
		    help = new JButton("Help");
		    highSc = new JButton("Highscore");
		    quit = new JButton("Quit");
		    
		    highSc.addActionListener(this);
		    highSc.setActionCommand("highscore");
		    quit.addActionListener(this);
		    quit.setActionCommand("quit");
		    help.addActionListener(this);
		    help.setActionCommand("help");
		    game.addActionListener(this);
		    game.setActionCommand("game");
	  }

	class ContentPanel extends JPanel {

		Image bgimage = null;
		  MediaTracker mt;
		  
		  ContentPanel(java.net.URL url) {
			mt = new MediaTracker(this);
		    bgimage = Toolkit.getDefaultToolkit().getImage(url);
		    mt.addImage(bgimage, 0);
		    try {
		      mt.waitForAll();
		    }
		    catch (InterruptedException e) {
		      e.printStackTrace();
		    }
		    
		  }

		 protected void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    g.drawImage(bgimage, 1, 1, null);
		    add(game);
		    add(help);
		    add(highSc);
		    add(quit);
		  }
		  
		  public void setPicture(java.net.URL url){
			  this.mt.removeImage(bgimage);
			  bgimage = Toolkit.getDefaultToolkit().getImage(url);
			  this.mt.addImage(bgimage, 0);
			    try {
				      mt.waitForAll();
				    }
				    catch (InterruptedException e) {
				      e.printStackTrace();
				    }
		  }
	}
	
	@Override
	 public void actionPerformed(ActionEvent ae) {
		        String action = ae.getActionCommand();
		        if (action.equals("game")){
		        	new GameWindow();
		        	setVisible(false);
		        }
		        if (action.equals("highscore")) {
		        	setVisible(false);
		        	try {
						new HighscoreWindow();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
		        }
		        if(action.equals("help")){
		        	setVisible(false);
		        	this.contentPanel.setPicture(MenuWindow.class.getResource("/pictures/help.png"));
		        	this.contentPanel.paintComponent(this.getGraphics());
		        	setVisible(true);
		        }
		        if(action.equals("quit")){
		        	System.exit(0);
		        }
		    }
	}



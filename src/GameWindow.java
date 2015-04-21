import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class GameWindow extends JFrame {
	
	private JCheckBox diceBox[] = new JCheckBox[5];
	private Dice dice;
	private JButton roll = new JButton("ROLL!"), 
			        newGame = new JButton("New Game"),
			        quit = new JButton("Quit"),
			        mw = new JButton("Back");
	private JButton buttons[] = new JButton[6];
	private JButton lowerButtons[] = new JButton[8];
	private JLabel labels[] = new JLabel[6];
	private JLabel lowerLabels[] = new JLabel[8];
	private String names[] = {"Ones", "Twos", "Threes", "Fours", "Fives", "Sixes"};
	private String lowerNames[] = {"Two of a kind", "Three of a kind", "Four of a kind", "Full house", "Small straight", "Large straight", "Chance", "Yahtzee"};
	private JFrame frame = new JFrame("Yahtzee");
	private JLabel sum = new JLabel("Score: "), 
			       bonus = new JLabel("Bonus: "),
			       totalSum = new JLabel("Total Score: ");
	private JPanel east, west, westUpper, eastLower, eastUpper;
	private int diceArray[], counter = 0;
	private Game game;
	private Player newPlayer;
	private String name;
	
	GameWindow() {	
	name = JOptionPane.showInputDialog("Please input your name: ");
	
	if(name == null){
		MenuWindow mw = new MenuWindow();
		mw.setVisible(true);
		frame.setVisible(false);
	}
	
	if(name != null){
		frame.setSize(550,700);
		frame.setLocation(600,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	frame.setLayout(new GridLayout(1,2,1,1));
	
	east = new JPanel(new BorderLayout(1,1));
	west = new JPanel(new BorderLayout(1,0));
	westUpper = new JPanel();
	westUpper.setLayout(null);
	eastLower = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	eastUpper = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
	west.add(westUpper);
	east.add(eastLower, BorderLayout.SOUTH);
	east.add(eastUpper, BorderLayout.NORTH);
	
	frame.add(west);
	frame.add(east);
	
	newPlayer = new Player(name);
	game = new Game();
	diceArray = new int[5];
	dice = new Dice();

	for(int i=0; i<5; i++){
		diceBox[i] = new JCheckBox();
		eastLower.add(diceBox[i]);
	}
	
	for(int i = 0; i < buttons.length; i++){
		buttons[i] = new JButton(names[i]);
		labels[i] = new JLabel("0");
		westUpper.add(buttons[i]);
		westUpper.add(labels[i]);
		buttons[i].setLocation(0,35*i);
		buttons[i].setSize(120,35);
		labels[i].setLocation(125, (35*i)-5);
		labels[i].setSize(70,50);
		buttons[i].setActionCommand(""+(i+1));
		
		buttons[i].addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	int index = Integer.parseInt(e.getActionCommand());
		    	labels[index-1].setText(""+game.checkNumberRules(diceArray, Integer.parseInt(e.getActionCommand())));
		    	resetDice(diceArray, dice);
		    	buttons[index-1].setEnabled(false);
		    	ResetCheckPoints();
		    	counter = 0;
		    	roll.setEnabled(true);
		    	
		    	for(int j = 0; j < diceBox.length; j++){
		    		diceBox[j].setText("");
		    		diceBox[j].setSelected(false);
		    	}
		    	
		    	sum.setText("Score: " + game.countSum(labels));
		    	totalSum.setText("Total Score: " + game.countTotalSum(labels, lowerLabels));
			    	
		    	if(game.checkBonus()){
			    	bonus.setText("Bonus: 50");	
			    }  	
		    }
		   
		});
	}
	
	for(int i = 0; i < lowerButtons.length; i++){
		lowerButtons[i] = new JButton(lowerNames[i]);
		lowerLabels[i] = new JLabel("0");
		westUpper.add(lowerButtons[i]);
		westUpper.add(lowerLabels[i]);
		lowerButtons[i].setLocation(0, 35*i+300 );
		lowerButtons[i].setSize(120,35);
		lowerLabels[i].setLocation(125, 35*i+300);
		lowerLabels[i].setSize(70,50);
	}
	
	lowerButtons[0].addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
    		lowerLabels[0].setText(""+game.checkOfAKindRule(diceArray,2));
    		lowerButtons[0].setEnabled(false);
    		counter = 0;
    		roll.setEnabled(true);
    		
    		for(int i = 0; i < diceBox.length; i++){
	    		diceBox[i].setSelected(false);
	    		diceBox[i].setText("");
	    	}
    		
    		resetDice(diceArray, dice);
    		ResetCheckPoints();
    		totalSum.setText("Total Score: " + game.countTotalSum(labels, lowerLabels));
	    }	    
	});
	
	lowerButtons[1].addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
    		lowerLabels[1].setText(""+game.checkOfAKindRule(diceArray,3));
    		lowerButtons[1].setEnabled(false);
    		counter = 0;
    		roll.setEnabled(true);
    		
    		for(int i = 0; i < diceBox.length; i++){
	    		diceBox[i].setSelected(false);
	    		diceBox[i].setText("");
	    	}
    		
    		resetDice(diceArray, dice);
    		ResetCheckPoints();
    		totalSum.setText("Total Score: " + game.countTotalSum(labels, lowerLabels));
    	}	    
	});
	
	lowerButtons[2].addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
    		lowerLabels[2].setText(""+game.checkOfAKindRule(diceArray,4));
    		lowerButtons[2].setEnabled(false);
    		counter = 0;
    		roll.setEnabled(true);
    		
    		for(int i = 0; i < diceBox.length; i++){
	    		diceBox[i].setSelected(false);
	    		diceBox[i].setText("");
	    	}
    		
    		resetDice(diceArray, dice);
    		ResetCheckPoints();
    		totalSum.setText("Total Score: " + game.countTotalSum(labels, lowerLabels));
    	}   
	});
	
	lowerButtons[3].addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
    		lowerLabels[3].setText(""+game.checkHouseRule(diceArray));
    		lowerButtons[3].setEnabled(false);
    		counter = 0;
    		roll.setEnabled(true);
    		
    		for(int i = 0; i < diceBox.length; i++){
	    		diceBox[i].setSelected(false);
	    		diceBox[i].setText("");
	    	}
    		
    		resetDice(diceArray, dice);
    		ResetCheckPoints();
    		totalSum.setText("Total Score: " + game.countTotalSum(labels, lowerLabels));
    	}    
	});
	
	lowerButtons[4].addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
    		lowerLabels[4].setText(""+game.checkStraightRules(diceArray,1));
    		lowerButtons[4].setEnabled(false);
    		counter = 0;
    		roll.setEnabled(true);
    		
    		for(int i = 0; i < diceBox.length; i++){
	    		diceBox[i].setSelected(false);
	    		diceBox[i].setText("");
	    	}
    		
    		resetDice(diceArray, dice);
    		ResetCheckPoints();
    		totalSum.setText("Total Score: " + game.countTotalSum(labels, lowerLabels));
    	}    
	});
	
	lowerButtons[5].addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
    		lowerLabels[5].setText(""+game.checkStraightRules(diceArray,2));
    		lowerButtons[5].setEnabled(false);
    		counter = 0;
    		roll.setEnabled(true);
    		
    		for(int i = 0; i < diceBox.length; i++){
	    		diceBox[i].setSelected(false);
	    		diceBox[i].setText("");
	    	}
    		
    		resetDice(diceArray, dice);
    		ResetCheckPoints();
    		totalSum.setText("Total Score: " + game.countTotalSum(labels, lowerLabels));
    	}
	});
	
	lowerButtons[6].addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
    		lowerLabels[6].setText(""+game.checkChanceRule(diceArray));
    		lowerButtons[6].setEnabled(false);
    		counter = 0;
    		roll.setEnabled(true);
    		
    		for(int i = 0; i < diceBox.length; i++){
	    		diceBox[i].setSelected(false);
	    		diceBox[i].setText("");
	    	}
    		
    		resetDice(diceArray, dice);
    		ResetCheckPoints();
    		totalSum.setText("Total Score: " + game.countTotalSum(labels, lowerLabels));
    	}
	});

	lowerButtons[7].addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
    		lowerLabels[7].setText(""+game.checkYahtzeeRule(diceArray));
    		lowerButtons[7].setEnabled(false);
    		counter = 0;
    		roll.setEnabled(true);
    		
    		for(int i = 0; i < diceBox.length; i++){
	    		diceBox[i].setSelected(false);
	    		diceBox[i].setText("");
	    	}
    		
    		resetDice(diceArray, dice);
    		ResetCheckPoints();
    		totalSum.setText("Total Score: " + game.countTotalSum(labels, lowerLabels));
    	}
	});
	
	eastLower.add(roll);
	eastUpper.add(newGame);
	eastUpper.add(mw);
	eastUpper.add(quit);
	westUpper.add(bonus);
	westUpper.add(sum);
	westUpper.add(totalSum);
	
	bonus.setLocation(0,210);
	bonus.setSize(70,50);
	sum.setLocation(0,255);
	sum.setSize(70,50);
	totalSum.setLocation(0, 580);
	totalSum.setSize(110,50);
	
	throwDices();
	
	quit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	if(checkIfDone(buttons, lowerButtons)){
	    		newPlayer.setScore(game.countTotalSum(labels, lowerLabels));
	    		try {
					newPlayer.writeToFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    	System.exit(0);
	    }
	});
	
	newGame.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	if(checkIfDone(buttons, lowerButtons)){
	    		newPlayer.setScore(game.countTotalSum(labels, lowerLabels));
	    		try {
					newPlayer.writeToFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    	GameWindow newGame = new GameWindow();
	    	frame.setVisible(false);
	    }
	});
	
	mw.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	MenuWindow mw = new MenuWindow();
	    	frame.setVisible(false);
	    	mw.setVisible(true);
	    	if(checkIfDone(buttons, lowerButtons)){
	    		newPlayer.setScore(game.countTotalSum(labels, lowerLabels));
	    		try {
					newPlayer.writeToFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    }
	});
  }
	
	 private void throwDices(){
		roll.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
	    		for(int i = 0; i < 5; i++){
			    	if ( !diceBox[i].isSelected()){
			    		diceArray[i] = dice.throwDice();
			    		diceBox[i].setText(dice.toString());
			    	}
		    	}
	    	
	    	counter++;
	    	CheckIfPoints();
	    	if(counter == 3)
	    		roll.setEnabled(false);
	
		   }
		});
	 }
	
	private boolean checkIfDone(JButton[] buttons, JButton[] moreButtons){
	
		boolean first = false, 
				second = false, 
				returner = false;
		
		for(int i = 0; i < buttons.length-1; i++){
			if(!buttons[i].isEnabled())
				first = true;
			else
				first = false;
		}
		
		for(int i = 0; i < moreButtons.length; i++){
			if(!moreButtons[i].isEnabled())
				second = true;
			else
				second = false;
		}
		
		if(first == true && second == true)
			returner = true;
		
		return returner;
	}

	private void resetDice(int[] array, Dice dice){
		for(int i = 0; i < array.length; i++)
			array[i] = dice.resetDice();
	}
	
	private void CheckIfPoints(){
		for(int i = 0; i < labels.length; i++){
			if(buttons[i].isEnabled()){
				labels[i].setText("" + game.checkNumberRules(diceArray, (i+1)));
				
				if(game.checkNumberRules(diceArray, (i + 1)) != 0)
					labels[i].setForeground(Color.RED);
				else
					labels[i].setForeground(Color.BLACK);
			}
		}
		if(lowerButtons[0].isEnabled()){
			lowerLabels[0].setText(""+game.checkOfAKindRule(diceArray,2));
			
			if(game.checkOfAKindRule(diceArray,2) != 0)
				lowerLabels[0].setForeground(Color.RED);
			else
				lowerLabels[0].setForeground(Color.BLACK);
		}
		
		if(lowerButtons[1].isEnabled()){
			lowerLabels[1].setText("" + game.checkOfAKindRule(diceArray,3));
			
			if(game.checkOfAKindRule(diceArray,3) != 0)
				lowerLabels[1].setForeground(Color.RED);
			else
				lowerLabels[1].setForeground(Color.BLACK);
		}
		
		if(lowerButtons[2].isEnabled()){
			lowerLabels[2].setText(""+game.checkOfAKindRule(diceArray,4));
			
			if(game.checkOfAKindRule(diceArray,4) != 0)
				lowerLabels[2].setForeground(Color.RED);
			else
				lowerLabels[2].setForeground(Color.BLACK);
		}
		
		if(lowerButtons[3].isEnabled()){
			lowerLabels[3].setText(""+game.checkHouseRule(diceArray));
			
			if(game.checkHouseRule(diceArray) != 0)
				lowerLabels[3].setForeground(Color.RED);
			else
				lowerLabels[3].setForeground(Color.BLACK);
		}
		
		if(lowerButtons[4].isEnabled()){
			lowerLabels[4].setText(""+game.checkStraightRules(diceArray,1));
			
			if(game.checkStraightRules(diceArray,1) != 0)
				lowerLabels[4].setForeground(Color.RED);
			else
				lowerLabels[4].setForeground(Color.BLACK);
		}
		
		if(lowerButtons[5].isEnabled()){
			lowerLabels[5].setText(""+game.checkStraightRules(diceArray,2));
			
			if(game.checkStraightRules(diceArray,2) != 0)
				lowerLabels[5].setForeground(Color.RED);
			else
				lowerLabels[5].setForeground(Color.BLACK);
		}
		
		if(lowerButtons[6].isEnabled()){
			lowerLabels[6].setText(""+game.checkChanceRule(diceArray));
			
			if(game.checkChanceRule(diceArray) != 0)
				lowerLabels[6].setForeground(Color.RED);
			else
				lowerLabels[6].setForeground(Color.BLACK);
		}
		
		if(lowerButtons[7].isEnabled()){
			lowerLabels[7].setText(""+game.checkYahtzeeRule(diceArray));
			
			if(game.checkYahtzeeRule(diceArray) != 0)
				lowerLabels[7].setForeground(Color.RED);
			else
				lowerLabels[7].setForeground(Color.BLACK);
		}
	}
	
	private void ResetCheckPoints(){
		for(int i = 0; i < labels.length; i++){
			labels[i].setForeground(Color.BLACK);
			if(buttons[i].isEnabled()){
				labels[i].setText("0");
			}
		}
		for(int i = 0; i < lowerLabels.length; i++){
			lowerLabels[i].setForeground(Color.BLACK);
			if(lowerButtons[i].isEnabled()){
				lowerLabels[i].setText("0");
			}
		}
	}
}


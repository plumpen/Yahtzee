import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class HighscoreWindow {
	private JFrame frame = new JFrame("Highscore");
	private JPanel east, west, eastUpper;
	private JButton button = new JButton("Back");
	private JTextArea textAreal;
	private ArrayList<Integer> score = new ArrayList<>();
	private ArrayList<String> names = new ArrayList<>();
	private static final String newLine = System.getProperty("line.separator");
	
	HighscoreWindow() throws IOException{
		frame.setSize(600,800);
		frame.setLocation(600,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new GridLayout(1,2,1,1));
		
		east = new JPanel(new BorderLayout());
		west = new JPanel(new BorderLayout());
		eastUpper = new JPanel();	
		
		frame.add(west, BorderLayout.WEST);
		frame.add(east, BorderLayout.EAST);
		east.add(eastUpper, BorderLayout.NORTH);          

		eastUpper.add(button);
		
		east.setBackground(Color.WHITE);
		eastUpper.setBackground(Color.WHITE);

		button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	MenuWindow mw = new MenuWindow();
		    	frame.setVisible(false);
		    	mw.setVisible(true);
		    	}
			});
		
		
		File file = new File("highscore.txt");
		
	    try {
	        Scanner sc = new Scanner(file);
	        while (sc.hasNext()) {
	        		String b = "";
	        		int i = 0;
	            if(sc.hasNextInt())
	            	i = sc.nextInt();
	            else
	            	b = sc.next();
	            
	            if(b != "")
	            	names.add(b);
	            if(i != 0)
	            	score.add(i);
	        }
	        
	        sc.close();
	        BubbleSort();
	        writeToNewFile();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }

		try(BufferedReader br = new BufferedReader(new FileReader("newHighscore.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();    
            }
            
            String everything = sb.toString();
            textAreal = new JTextArea(everything, 100, 100);
            west.add(textAreal);
            
            Font font = new Font("Arial", Font.BOLD, 16);
            textAreal.setFont(font);

        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int compareItems(int x, int y) {
			
		    return score.get(x).compareTo(score.get(y));
		}
			
	private void swapString(ArrayList<String> xs, int x, int y){
	
	    String temp = xs.get(x);
	    xs.set(x, xs.get(y));
	    xs.set(y, temp);
	}
	
	private void swapInt(ArrayList<Integer> xs, int x, int y){

		int temp = xs.get(x);
		    xs.set(x, xs.get(y));
		    xs.set(y, temp);
	}
	 
	private void swapItems(int x, int y){
	
	    swapString(names, x, y);
	    swapInt(score, x, y);
	
	}
	
	private void BubbleSort(){
	
	    for(int x = 0; x < score.size() - 1; x++) {
	        for (int i = 0; i < score.size() - 1; i++) {
	            if (compareItems(i, i + 1) < 0) 
	                swapItems(i, i + 1);
	        }
	    }
	}
	
	private void writeToNewFile(){
	Writer writer = null;
		File file = new File("newHighscore.txt");
		if(file.exists())
			file.delete();
		
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(file, true), "utf-8"));
		    for(int i = 0; i < score.size(); i++){
		    	writer.write(names.get(i).toString() + "'s score: " + score.get(i).toString() + newLine);
		    }
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}	
}

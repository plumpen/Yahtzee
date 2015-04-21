import java.io.*;


public class Player {
	private static final String newLine = System.getProperty("line.separator");
	private String name;
	private int score;
	File file = new File("highscore.txt");
	
	Player(String nName){
		name = nName;
		score = 0;
	}
	
	public void setScore(int totScore){
		score = totScore;
	}
	
	public int getScore(){
		return score;
	}
	
	public void writeToFile() throws IOException{
		Writer writer = null;
		
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(file, true), "utf-8"));
		    writer.write(getScore() + " " + this.name + newLine);
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}
}


import java.util.Random;

public class Dice {
	private int value;
	private Random dice;
	
	Dice(){
		dice = new Random();
	}
	
	public int throwDice(){
		value = dice.nextInt(6) + 1;
		return value;
	}
	
	public String toString(){
		return "" + value;
	}
	
	public int resetDice(){
		value = 0;
		return value;
	}
}


public class NumberRule implements Rule{
	int numberToCheck = -1;
	
	public NumberRule(int number){
		numberToCheck = number;
	}
	
	public int GetNumberToCheck(){
		return numberToCheck;
	}

	@Override
	public int CheckRule(int[] die) {
		int sum = 0;
		
		for(int i = 0; i < die.length; i++){
			if(die[i] == numberToCheck)
				sum = sum + die[i];
		}
		return sum;
	}
}

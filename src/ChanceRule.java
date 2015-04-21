public class ChanceRule implements Rule {
	
	@Override
	public int CheckRule(int[] die) {
		int counter = 0;
		
		for(int i = 0; i < die.length; i++){
			counter += die[i];
		}
		return counter;
	}

}

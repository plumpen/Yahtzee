
public class OfAKindRule extends SortRule{

	private int amount = -1;
	
	public OfAKindRule(int compare){
		amount = compare;
	}
	
	public int GetNumberToCheck(){
		return amount;
	}
	
	@Override
	public int CheckRule(int[] die) {
		int[] sorted = sortDie(die);
		int value = -1;
		int numbersAlike = 1;
		
		for(int i = sorted.length-1; i > 0 ; i--){
			if(sorted[i] == sorted[i-1]){
				if(value == -1)
					value = sorted[i];
			
				if(value == sorted[i]){
					value = sorted[i];
					numbersAlike++;	
				}
			}
			if(numbersAlike >= amount)
				break;
		}
		return numbersAlike >= amount ? value * amount : 0;
	}
}

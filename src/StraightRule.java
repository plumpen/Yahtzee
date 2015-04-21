
public class StraightRule extends SortRule { 
	private int startNumber = -1;
	
	public StraightRule(int number){
		startNumber = number;
	}
	
	public int GetNumberToCheck(){
		return startNumber;
	}
	
	@Override
	public int CheckRule(int[] die) {
		int sorted[] = sortDie(die);
		int nrToCheck = startNumber;
		int sum = 0;
		
		for(int i = 0; i < sorted.length; i++){
			if(sorted[i] == nrToCheck){
				sum += sorted[i];
				nrToCheck++;
			}else{
				sum = 0;
				break;
			}
		}	
		return sum;
	}
}

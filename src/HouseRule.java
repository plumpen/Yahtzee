public class HouseRule extends SortRule{

	@Override
	public int CheckRule(int[] die) {
		
		int[] sorted = sortDie(die);
		int counter = 0;
		int anotherCounter = 0;
		int compare = sorted[0];
		int sum = 0;
		int anotherCompare = sorted[sorted.length-1];
		
		for(int i = 0; i < sorted.length-1; i++){
			if(compare == sorted[i])
				counter++;
		}
		
		for(int i = sorted.length-1; i > 0; i--){
			if(anotherCompare == sorted[i])
				anotherCounter++;
		}
		
		if(counter == 2 && anotherCounter == 3)
			sum = (compare*counter + anotherCompare*anotherCounter);
		else if(counter == 3 && anotherCounter == 2)
			sum = (compare*counter + anotherCompare*anotherCounter);
		
		return sum;
	}

}

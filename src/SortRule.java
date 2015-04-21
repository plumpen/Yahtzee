
public abstract class SortRule implements Rule{

	protected int[] sortDie(int[] die){
		int[] temp = new int[die.length];
		
		for(int i = 0; i < temp.length; i++)
			temp[i] = die[i];
		
		
		for(int i = 0; i < temp.length-1; i++){
			int index = i;
			
			for(int j = i+1; j < temp.length; j++){
				if(temp[j] < temp[index])
					index = j;
			}
			
			int smallerNumber = temp[index];
			temp[index] = temp[i];
			temp[i] = smallerNumber;
		}
		return temp;
	}
	public abstract int CheckRule(int[] die);
}

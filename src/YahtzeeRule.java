
public class YahtzeeRule implements Rule {

	@Override
	public int CheckRule(int[] die) {
		
		boolean bool = true;
		for(int i = 0; i < die.length-1; i++){
			
			if(die[i] != die[i+1]){
				bool = false;
				break;
			}
			
			if(die[i] == 0){
				bool = false;
				break;
			}
		}
		return bool ? 50 : 0;
	}
}

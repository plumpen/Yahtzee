import java.util.ArrayList;

import javax.swing.JLabel;

public class Game {
	int totalSum;
	private ArrayList<Rule> mRules;
	
	Game(){
		mRules = new ArrayList<>();
		
		for(int i = 1; i <= 6; i++){
			mRules.add(new NumberRule(i));
		}
		
		mRules.add(new ChanceRule());
		mRules.add(new YahtzeeRule());
		
		for(int i = 2; i <= 4; i++){
			mRules.add(new OfAKindRule(i));
		}
		
		for(int i = 1; i <=2; i++){
			mRules.add(new StraightRule(i));
		}
		
		mRules.add(new HouseRule());
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Rule> ArrayList<T> getObjectsOfType(Class<T> coolness){
		ArrayList<T> temp = new ArrayList<>();
		
		for(Rule rule : mRules){
			if(rule.getClass().equals(coolness)){
				temp.add((T)rule);
			}
		}
		return temp;
	}
	
	public int checkStraightRules(int[] die, int ruleToCheck){
		ArrayList<StraightRule> temp = getObjectsOfType(StraightRule.class);
		int sum = 0;
		
		for(StraightRule rule : temp){
			if(rule.GetNumberToCheck() == ruleToCheck){
				sum = rule.CheckRule(die);
				break;
			}
		}
		return sum;
	}
	
	public int checkNumberRules(int[] die, int ruleToCheck){
		ArrayList<NumberRule> temp = getObjectsOfType(NumberRule.class);
		int sum = 0;
		
		for(NumberRule rule : temp){
			if(rule.GetNumberToCheck() == ruleToCheck){
				sum = rule.CheckRule(die);
				break;
			}
		}	
		return sum;
	}
	
	public int checkHouseRule(int[] die){
		ArrayList<HouseRule> temp = getObjectsOfType(HouseRule.class);
		
		return temp.get(0).CheckRule(die);
	}
	
	public int checkChanceRule(int[] die){
		ArrayList<ChanceRule> temp = getObjectsOfType(ChanceRule.class);
		
		return temp.get(0).CheckRule(die);
	}
	
	public int checkYahtzeeRule(int[] die){
		ArrayList<YahtzeeRule> temp = getObjectsOfType(YahtzeeRule.class);
		
		return temp.get(0).CheckRule(die);
	}
	
	public int checkOfAKindRule(int[] die, int number)
	{
		int sum = -1;
		ArrayList<OfAKindRule> temp = getObjectsOfType(OfAKindRule.class);
		
		for(OfAKindRule rule : temp){
			if(rule.GetNumberToCheck() == number){
				sum = rule.CheckRule(die);
				break;
			}
		}
		
		return sum;
		
	}
	
	public int countSum(JLabel array[]){
		int sum = 0;
		
		for(int i=0; i<array.length; i++){
			sum += Integer.parseInt( array[i].getText());
		}
		
		if(sum >= 63){
			sum += 50;
		}
		
		this.totalSum = sum;
		return sum;
	}
		
	public int countTotalSum(JLabel array[], JLabel secArray[]){
		int sum = 0;
		int totSum = 0;
		sum = countSum(array);
		
		for(int i = 0; i < secArray.length; i++){
			totSum += Integer.parseInt(secArray[i].getText());
		}
		
		totSum = totSum + sum;
		return totSum;
	}
		
	public boolean checkBonus(){
		boolean retVal = false;
		
		if(this.totalSum >= 63){
			retVal = true;
		}
		return retVal;
	}
}

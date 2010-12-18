
public class TreasureHunter extends Thread {
	
	public static int maxSteps = 50;
	private String name;
	private int steps, consumedTreasures;
	
	public TreasureHunter(String name){
		this.name = name;
		steps = 0;
		consumedTreasures = 0;
	}
	
	public String getTHName(){
		return name;
	}
	
	/*
	 * treasure hunter makes a move
	 */
	public boolean step(){
		if(steps >= maxSteps){
			return false;
		}
		steps++;
		return true;
		
		//TODO: exit aufrufen, falls noMoreWall u !insideLab ODER maxSteps erreicht
	}
	
	public void eatTreasure(int treasure){
		consumedTreasures += treasure;
	}
	
	public int getConsumedTreasures(){
		 return consumedTreasures;
	}
	
}

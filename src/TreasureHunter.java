
public class TreasureHunter extends BasicPlayer {
	
	public static int maxSteps = 50;
	private String name;
	private int steps, consumedTreasures;
	
	public TreasureHunter(Labyrinth lab, int x, int y, String name){
		super(lab, x, y);
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
		
		//TODO: kill aufrufen, falls noMoreWall u !insideLab ODER maxSteps erreicht
	}
	
	public void eatTreasure(int treasure){
		consumedTreasures += treasure;
	}
	
	public int getConsumedTreasures(){
		 return consumedTreasures;
	}
	
}

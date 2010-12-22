
public class TreasureHunter extends BasicPlayer {
	
	public final static int MAX_STEPS = 500;
	public final String name;
	private int steps = 0, purse = 0;
	
	public TreasureHunter(Labyrinth lab, int x, int y, String name){
		super(lab, x, y);
		this.name = name;
	}

	public TreasureHunter(Labyrinth lab, String name) {
		super(lab);
		this.name = name;
	}
	
	/*
	 * post: treasure hunter is killed;
	 * 		 if all treasure hunters are killed, the game is over
	 */
	public void kill() {
		alive = false;
		if(!labyrinth.someAlive()) labyrinth.finish();
	}
	
	/*
	 * pre: value of treasure >= 0
	 * post: the value of the treasure is added to the account of the hunter
	 */
	public void addTreasure(int treasure){
		purse += treasure;
	}
	
	/*
	 * post: returns the name and the final value of a hunters' purse if he's alive
	 * 		 or returns the name and the status "dead"
	 */
	@Override
	public void print() {
		if (alive) {
			System.out.println(String.format("Player `%s` has collected %d", name, purse));
		} else {
			System.out.println(String.format("Player `%s` is DEAD", name));	
		}
	}

	/*
	 * pre: field != null; 
	 * post: player enters another field;
	 * 		 if he's reached the max steps and is alive: print name and status "reached max steps"
	 * 		 if he's reached one of the exits and is still alive: print name and status "reached exit"
	 */
	@Override
	protected void enterTo(Field field) {
		field.enter(this);
		if (alive) {
			if (++steps >= MAX_STEPS) {
				System.out.println(String.format("Player `%s` has reached MAX_STEPS", name));
				labyrinth.finish();
				return;
			}				
			if (labyrinth.isExit(field)) {
				System.out.println(String.format("Player `%s` has reached Labyrinth EXIT", name));
				labyrinth.finish();
				return;
			}	
		}
	}
}

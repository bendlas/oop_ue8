
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
	
	public void kill() {
		alive = false;
	}
	
	public void addTreasure(int treasure){
		purse += treasure;
	}

	@Override
	public void print() {
		if (alive) {
			System.out.println(String.format("Player `%s` has collected %d", name, purse));
		} else {
			System.out.println(String.format("Player `%s` is DEAD", name));	
		}
	}

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

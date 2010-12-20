public class Ghost extends BasicPlayer {

	public Ghost(Labyrinth labyrinth, int x, int y){
		super(labyrinth, x, y);
	}
	
	public Ghost(Labyrinth lab) {
		super(lab);
	}

	@Override
	public void print() {
	}

	@Override
	protected void enterTo(Field field) {
		field.enter(this);
	}
}

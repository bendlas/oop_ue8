
public abstract class BasicPlayer extends Thread {
	protected final Labyrinth labyrinth;
	protected Field field;
	volatile protected boolean alive = true;
	public static final int SLEEP_TIME = 4;
	
	public BasicPlayer(Labyrinth labyrinth, int x, int y){
		labyrinth.addPlayer(this);
		this.labyrinth = labyrinth;
		field = this.labyrinth.fields[x][y];
		enterTo(field);
	}
	public BasicPlayer(Labyrinth labyrinth) {
		labyrinth.addPlayer(this);
		this.labyrinth = labyrinth;
		field = labyrinth.fields[Labyrinth.rand.nextInt(labyrinth.getWidth())][Labyrinth.rand.nextInt(labyrinth.getHeight())];
		enterTo(field);
	}
	public boolean isPlayerAlive(){
		return alive;
	}
	
	
	abstract public void print();
	abstract protected void enterTo(Field field);
	public void run(){
		while (labyrinth.running){
			Field next = labyrinth.randomNext(field);
			synchronized (field) {
				if (alive) {
					field.leave(this);
					field = null;
				} else {
					return;
				}
			}
			// separate synchronized blocks leave a small window in which Hunters can't be eaten
			// which is arguably better than risking deadlock with nested synchronized blocks
			synchronized (next) {
				enterTo(next);
				if (alive){
					field = next;
				} else {
					return;
				}
			}
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException ex){
			}
		}
	}
}
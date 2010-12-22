/*
 * Basic class for ghosts and treasure hunters
 */
public abstract class BasicPlayer extends Thread {
	protected final Labyrinth labyrinth;
	protected Field field;
	//alive will be modified by different threads
	volatile protected boolean alive = true;
	
	public BasicPlayer(Labyrinth labyrinth, int x, int y){
		labyrinth.addPlayer(this);
		this.labyrinth = labyrinth;
		field = this.labyrinth.fields[x][y];
	}
	//if not dedicated the player is set to a random field in the labyrinth
	public BasicPlayer(Labyrinth labyrinth) {
		labyrinth.addPlayer(this);
		this.labyrinth = labyrinth;
		field = labyrinth.fields[Labyrinth.rand.nextInt(labyrinth.getWidth())][Labyrinth.rand.nextInt(labyrinth.getHeight())];
	}
	
	/*
	 * post: returns a random (between 2 & 10) sleepTime for the player thread
	 */
	protected long getSleepTime() {
		return 2 + Labyrinth.rand.nextInt(8);
	}
	
	/*
	 * post: return true if player is still alive
	 */
	public boolean isPlayerAlive(){
		return alive;
	}
	
	
	abstract public void print();
	
	abstract protected void enterTo(Field field);
	
	/*
	 * post: game starts: ghosts and hunters move 
	 * 		 - leave current field, enter new field and stay there for a random time
	 * 		 as long as they are alive
	 */
	public void run(){
		enterTo(field);
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
				Thread.sleep(getSleepTime());
			} catch (InterruptedException ex){
			}
		}
	}
}
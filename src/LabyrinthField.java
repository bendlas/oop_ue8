import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


public class LabyrinthField {
	
	public final boolean northWall, eastWall;
	private int treasure;
	private Set<Ghost> ghosts = new HashSet<Ghost>();
	private Set<TreasureHunter> hunters = new HashSet<TreasureHunter>();
	
	public LabyrinthField(boolean northWall, boolean eastWall, int treasure) {
		this.northWall = northWall;
		this.eastWall = eastWall;
		this.treasure = treasure;
	}
	
	static Random rand = new Random();
	public static LabyrinthField randomField() {
		return new LabyrinthField(rand.nextBoolean(), rand.nextBoolean(), rand.nextInt(1000));
	}
	
	synchronized public void logicTick() {
		if (ghosts.size() > 0) { // call ghostbusters
			for (TreasureHunter hunter : hunters) {
				//TODO hunter.kill();
			}
			hunters.clear();
		} else { // no fear
			Iterator<TreasureHunter> hunterSeq = hunters.iterator();
			if (hunterSeq.hasNext()) {
				hunterSeq.next(); //TODO .addTreasure(treasure)
				treasure = 0;
			}
		}
	}
}

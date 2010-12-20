import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Field {
	public final boolean northWall, eastWall;
	public final int x, y;
	private int treasure;
	private Set<Ghost> ghosts = new HashSet<Ghost>();
	private Set<TreasureHunter> hunters = new HashSet<TreasureHunter>();
	public Field(int x, int y, boolean northWall, boolean eastWall, int treasure) {
		this.northWall = northWall;
		this.eastWall = eastWall;
		this.treasure = treasure;
		this.x = x;
		this.y = y;
	}
	
	
	synchronized public void enter(Ghost player) {
		ghosts.add(player);
		logicTick();
	}
	
	synchronized public void enter(TreasureHunter player) {
		hunters.add(player);
		logicTick();
	}
	
	synchronized public void leave(BasicPlayer player) {
		ghosts.remove(player);
		hunters.remove(player);
	}
	
	private void logicTick() {
		Iterator<TreasureHunter> hunterSeq = hunters.iterator();
		if (ghosts.size() > 0) { // call ghostbusters
			while (hunterSeq.hasNext()) {
				hunterSeq.next().kill();
				hunterSeq.remove();
			}
			hunters.clear();
		} else { // no fear
			if (hunterSeq.hasNext()) {
				hunterSeq.next().addTreasure(treasure);
				treasure = 0;
			}
		}
	}
}
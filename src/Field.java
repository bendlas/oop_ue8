import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Field {
	public final boolean northWall, eastWall;
	public final int x, y;
	private int treasure;
	private Set<Ghost> ghosts = new HashSet<Ghost>();
	private Set<TreasureHunter> hunters = new HashSet<TreasureHunter>();
	
	//each field has a treasure at the beginning
	public Field(int x, int y, boolean northWall, boolean eastWall, int treasure) {
		this.northWall = northWall;
		this.eastWall = eastWall;
		this.treasure = treasure;
		this.x = x;
		this.y = y;
	}
	
	/*
	 * pre: player != null
	 * post: ghost enters field
	 */
	synchronized public void enter(Ghost player) {
		ghosts.add(player);
		logicTick();
	}
	
	/*
	 * pre: player != null
	 * post: hunter enters field
	 */
	synchronized public void enter(TreasureHunter player) {
		hunters.add(player);
		logicTick();
	}
	
	/*
	 * pre: player != null
	 * post: ghost or hunter leave field
	 */
	synchronized public void leave(BasicPlayer player) {
		ghosts.remove(player);
		hunters.remove(player);
	}
	
	/*
	 * post: if >= 1 hunter are on a field with >= 1 ghost, all hunters will be killed
	 * 		 if no ghost is on the field, the treasure is added to the purse of the first hunter entering the field
	 */
	private void logicTick() {
		Iterator<TreasureHunter> hunterSeq = hunters.iterator();
		if (ghosts.size() > 0) { // call ghostbusters
			while (hunterSeq.hasNext()) {
				hunterSeq.next().kill();
				hunterSeq.remove();
			}
		} else { // no fear
			if (hunterSeq.hasNext()) {
				hunterSeq.next().addTreasure(treasure);
				treasure = 0;
			}
		}
	}
}
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Labyrinth {
	public static Random rand = new Random();

	private Set<BasicPlayer> players = new HashSet<BasicPlayer>();
	volatile boolean running = true;
	
	public void addPlayer(BasicPlayer p) {
		players.add(p);
	}
	
	public void run() {
		running = true;
		for (BasicPlayer p : players) {
			p.start();
		}
	}
	
	public void waitFinish() {
		try {
			for (BasicPlayer p : players) {
				p.join();
				p.print();
			}
		} catch (InterruptedException e) {
			
		}	
	}

	public void finish() {
		running = false;
		for (BasicPlayer p : players) {
			p.interrupt();
		}
	}

	public boolean isExit(Field field) {
		return field.x + 1 == fields.length && ! field.eastWall ||
		field.y + 1 == fields[0].length && ! field.northWall;
	}
	
	public Field randomField(int x, int y) {
		return new Field(x, y, rand.nextBoolean(), rand.nextBoolean(), rand.nextInt(1000));
	}

	public int getWidth() {
		return fields.length;
	}
	public int getHeight() {
		return fields[0].length;
	}
	
	public boolean someAlive(){
		for (BasicPlayer p : players) {
			if(p instanceof TreasureHunter){
				if(p.isPlayerAlive()) return true;
			}
		}
		return false;
	}
	
	//inv: fields.length > 0; fields[x].length == fields[y].length; fields[x].length > 0;
	Field[][] fields;
	
	public Labyrinth(int width, int height) {
		assert width > 0;
		assert height > 0;
		fields = new Field[width][height];
		int x = 0;
		for (Field[] col : fields) {
			for (int y=0;y<height;y++) {
				col[y] = randomField(x, y);
			}
			x++;
		}
	}
	
	public Labyrinth(Field[][] fields) {
		assert fields.length > 0;
		Integer h = null;
		int y = 0;
		for (Field[] col : fields) {
			assert h == null || h == col.length;
			h = col.length;
			int x = 0;
			for (Field field : col) {
				assert field != null;
				assert field.x == x && field.y == y;
				x++;
			}
			y++;
		}
		assert h > 0;
		this.fields = fields;
	}
	
	public Field getField(int x, int y) {
		return fields[x][y];
	}
	
	public Field randomNext(Field from) {
		Field[] choice = new Field[5];
		int choices = 0;
		choice[choices] = from;
		choices++;
		if (from.x + 1 < getWidth() && ! from.eastWall) {
			choice[choices] = fields[from.x + 1][from.y];
			choices++;
		}
		if (from.y + 1 < getHeight() && ! from.northWall) {
			choice[choices] = fields[from.x][from.y + 1];
			choices++;
		}
		if (from.x > 0) {
			Field west = fields[from.x - 1][from.y];
			if ( ! west.eastWall) {
				choice[choices] = west;
				choices++;
			}
		}
		if (from.y > 0) {
			Field south = fields[from.x][from.y - 1];
			if ( ! south.northWall) {
				choice[choices] = south;
				choices++;
			}
		}
		
		return choice[rand.nextInt(choices)];
	}
}

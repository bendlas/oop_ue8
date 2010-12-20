public class Test {
	public static void main(String[] args) {
		Labyrinth lab = new Labyrinth(50, 50);
		new TreasureHunter(lab, "Karl");
		new TreasureHunter(lab, "Fritz");
		new TreasureHunter(lab, "Sepp");
		new TreasureHunter(lab, "Franz");
		new TreasureHunter(lab, "Andi");
		
		new Ghost(lab);
		new Ghost(lab);
		new Ghost(lab);
		new Ghost(lab);
		new Ghost(lab);
		new Ghost(lab);
		new Ghost(lab);
		new Ghost(lab);
		new Ghost(lab);
		new Ghost(lab);
		new Ghost(lab);
		new Ghost(lab);
		new Ghost(lab);
		
		
		lab.run();
		lab.waitFinish();
	}
	public static void out(String...out) {
		for (String s : out) {
			System.out.print(s);
		}
		System.out.println();		
	}
	public static void info(String...out) {
		System.out.print("INFO: ");
		Test.out(out);
	}
	public static void error(String...out) {
		System.out.print("ERROR: ");
		Test.out(out);
	}
	
	public static void assert_(boolean cond, String out) {
		while(!cond) {
			error(out);
			System.exit(1);
		}
	}
}

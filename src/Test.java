public class Test {
	public static void main(String[] args) {
		testcase1();
		testcase2();
		testcase3();
		testcase4();
	}

	private static void testcase1(){

		info("--------- Testcase 1 ---------");
		info("a normal run..."  + "\n");

		Labyrinth lab = new Labyrinth(60, 60);
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

		lab.run();
		lab.waitFinish();
	}
	private static void testcase2(){
		info("\n" + "\n" + "--------- Testcase 2 ---------" );
		info(" a lot of Ghosts..."+ "\n");

		Labyrinth lab = new Labyrinth(30, 40);
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
	private static void testcase3(){
		info("\n" + "\n" + "--------- Testcase 3 ---------" );
		info("'Born2Die' is born on a ghost field");
		Labyrinth lab = new Labyrinth(20, 20);

		new TreasureHunter(lab, 5, 5, "Born2Die");
		new Ghost(lab, 5, 5);

		lab.run();
		lab.waitFinish();
	}

	private static void testcase4(){

		info("\n" + "\n" + "--------- Testcase 4 ---------");
		info("small labyrinth"  + "\n");

		Labyrinth lab = new Labyrinth(3, 2);
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

		lab.run();
		lab.waitFinish();

		info(" \n"  + "\n" + " ... Tests finished!");

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

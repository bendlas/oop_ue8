import java.util.Arrays;


public class Test {
	public static void main(String[] args) {
		error("Please define Tests");
		assert_(false, "No tests defined");
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

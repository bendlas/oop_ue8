
//ghost or treasureHunter
public abstract class BasicPlayer extends Thread {

	protected Labyrinth lab;
	protected int xPosition, yPosition;
	protected boolean active;
	public static final int SLEEP_TIME = 40;
	
	public BasicPlayer(Labyrinth lab, int x, int y){
		this.lab = lab;
		xPosition = x;
		yPosition = y;
		active = true;
	}
	
	public void run(){
		while (getActive()){
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException ex){ }
		}
	}
	
	public synchronized boolean getActive(){
		return active;
	}
	
	public synchronized void kill(){
		active = false;
		this.interrupt();
	}
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Window;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame {
	Labyrinth labyrinth;
	private JPanel region = new JPanel(){
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			float w = 600;
			float h = 600;
			float fw = w / labyrinth.getWidth();
			float fh = h / labyrinth.getHeight();
			
			for (int x = labyrinth.getWidth()-1; x >= 0; x--) {
				for (int y = labyrinth.getHeight()-1; y >= 0; y--) {
					int rx = (int) (x * fw);
					int ry = (int) (y * fh);

					g.setColor(Color.BLACK);
					Field here = labyrinth.getField(x, y);
					here.setCb(cb);
					if (here.eastWall) {
						drawEast(g, rx, ry, fw, fh);
					}
					if (here.northWall) {
						drawNorth(g, rx, ry, fw, fh);
					}
					int amt = here.getTreasure();
					drawTreasure(g, rx, ry, fw, fh, amt);
					for(BasicPlayer player : here.getPlayers()){
						drawBP(g, rx, ry, fh, fw, player);
					}
					
				}
			}
		}

		private void drawBP(Graphics g, int x, int y, float fw, float fh, BasicPlayer player) {
			int h = (int) fh - 4;
			int w = (int) fw - 4;
			if(player instanceof TreasureHunter){
				Color c = Color.BLUE;
				g.setColor(c);
			} else {
				Color c = Color.GREEN;
				g.setColor(c);
			}
			g.fillRect(x+2, y+2, w, h);
		}

		private void drawTreasure(Graphics g, int x, int y, float fw, float fh, int amt) {
			int height = (int) (fh-2);
			int width = (int) (fw-2);
			Color c = new Color(
					1.0f, 
					1.0f - (0.16f * amt / 1000), 
					1.0f - ((float) amt / 1000));
			g.setColor(c);
			g.fillRect(x+1, y+1, width, height);			
		}


		private void drawNorth(Graphics g, int x, int y, float fw, float fh) {
			int x1 = (int) x;
			int x2 = (int) (x+fw);
//			System.out.println(String.format("(%d,%d,%f,%f,%d,%d,%d)", x, y, fw, fh, x1, x2, y));
			g.drawLine(x1, y + (int) fh, x2, y + (int) fh);
		}

		private void drawEast(Graphics g, int x, int y, float fw, float fh) {
			x = (int) (x + fw);
			int y1 = (int) y;
			int y2 = (int) (y+fh);
			g.drawLine(x, y1, x, y2);
		}		
	};
	
	private Field.ITickCallback cb = new Field.ITickCallback() {
		@Override
		public void onTick(Field field) {
			repaint();
		}
	};
	
	public MainFrame(Labyrinth lab) {
		super("Labyrinth");
		labyrinth = lab;
		setSize(new Dimension(600,600));
		getContentPane().add(region);
	}
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Window;

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
					g.setColor(Color.BLACK);
					Field here = labyrinth.getField(x, y);
					if (here.eastWall) {
						drawEast(g, x, y, fw, fh);
					}
					if (here.northWall) {
						drawNorth(g, x, y, fw, fh);
					}
					int rx = (int) (x * fw) + 1;
					int ry = (int) (y * fh) + 1;
					int width = (int) (fw-2);
					int height = (int) (fh-2);
					int amt = here.getTreasure();
					Color c = new Color(
							1.0f, 
							1.0f - (0.16f * amt / 1000), 
							1.0f - ((float) amt / 1000));
					g.setColor(c);
					g.fillRect(rx, ry, width, height);
				}
			}
		}

		private void drawNorth(Graphics g, int x, int y, float fw, float fh) {
			int x1 = (int) (x*fw);
			int x2 = (int) ((x+1)*fw);
			y *= fh;
//			System.out.println(String.format("(%d,%d,%f,%f,%d,%d,%d)", x, y, fw, fh, x1, x2, y));
			g.drawLine(x1, y, x2, y);
		}

		private void drawEast(Graphics g, int x, int y, float fw, float fh) {
			x = (int) ((x+1)*fw);
			int y1 = (int) (y*fh);
			int y2 = (int) ((y+1)*fh);
			g.drawLine(x, y1, x, y2);
		}		
	};
	
	public MainFrame(Labyrinth lab) {
		super("Labyrinth");
		labyrinth = lab;
		setSize(new Dimension(600,600));
		getContentPane().add(region);
	}
}

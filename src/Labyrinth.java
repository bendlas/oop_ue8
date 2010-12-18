
public class Labyrinth {

	private LabyrinthField[][] fields;
	
	public Labyrinth(int width, int height) {
		fields = new LabyrinthField[width][height];
		for (LabyrinthField[] col : fields) {
			for (int y=0;y<height;y++) {
				col[y] = LabyrinthField.randomField();
			}
		}
	}
	
	public Labyrinth(LabyrinthField[][] fields) {
		Integer h = null;
		for (LabyrinthField[] col : fields) {
			assert h == null || h == col.length;
			h = col.length;
			for (LabyrinthField field : col) {
				assert field != null;
			}
		}
		this.fields = fields;
	}
	
}

package classes;

public class Decoupe { 

	private int x;
	private int y;
	private int id;
		
	public Decoupe() {
		this.x = 0;
		this.y = 0;
		this.id =0;
		
	}
	
	public Decoupe(int x, int y, int id) {
		super();
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}

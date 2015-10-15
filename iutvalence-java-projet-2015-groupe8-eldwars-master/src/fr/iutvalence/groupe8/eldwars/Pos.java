package fr.iutvalence.groupe8.eldwars;

/** 
 * Position class
 * 
 * @author Nicolas, Clément
 *
 */

public class Pos {

	/**
	 * The x coordinate.
	 */
	private final int x;

	/**
	 * The y coordinate.
	 */
	private final int y;

	/**
	 * The Pos constructor.
	 * 
	 * @param x
	 * @param y
	 */
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the x coordinate.
	 * 
	 * @return An integer
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y coordinate.
	 * 
	 * @return An integer
	 */
	public int getY() {
		return y;
	}

	/**
	 * Create a new Pos.
	 * 
	 * @param dX
	 * @param dY
	 * @return Pos
	 */
	public Pos shift(int dX, int dY) {
		return new Pos(this.x + dX, this.y + dY);
	}

	/**
	 * Gets the distance between two Pos.
	 * 
	 * @param pos2
	 * @return An integer
	 */
	public int distance(Pos pos2) {
		int p2X = this.getX();
		int p2Y = this.getY();
		int distance;

		if (p2X > this.x)
			distance = p2X - this.x;
		else
			distance = this.x - p2X;

		if (p2Y > this.y)
			distance += p2Y - this.y;
		else
			distance += this.y - this.y;

		return distance;
	}

	/**
	 * toString method.
	 */
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
	@Override
	public boolean equals(Object pos2) {
		if(pos2 instanceof Pos){
			Pos tempPos = (Pos)pos2;
			return (this.getX() == tempPos.getX() && this.getY() == tempPos.getY());
		}
		return false;
	}

}

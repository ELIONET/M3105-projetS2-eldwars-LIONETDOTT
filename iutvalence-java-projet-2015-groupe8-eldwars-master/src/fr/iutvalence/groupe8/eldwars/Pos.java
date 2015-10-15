package fr.iutvalence.groupe8.eldwars;

/** 
 * Position class
 * 
 * @author Nicolas, Clément
 *
 */

public class Pos {

	private final int coordX;

	private final int coordY;

	public Pos(int x, int y) {
		this.coordX = x;
		this.coordY = y;
	}

	public int getXCoord() {
		return coordX;
	}

	public int getYCoord() {
		return coordY;
	}

	public Pos newPos(int dX, int dY) {
		return new Pos(this.coordX + dX, this.coordY + dY);
	}

	public int distanceBetweenTwoPos(Pos pos2) {
		int p2X = this.getXCoord();
		int p2Y = this.getYCoord();
		int distance;

		if (p2X > this.coordX)
			distance = p2X - this.coordX;
		else
			distance = this.coordX - p2X;

		if (p2Y > this.coordY)
			distance += p2Y - this.coordY;
		else
			distance += this.coordY - this.coordY;

		return distance;
	}

	public String toString() {
		return "(" + this.coordX + "," + this.coordY + ")";
	}
	
	@Override
	public boolean equals(Object pos2) {
		if(pos2 instanceof Pos){
			Pos tempPos = (Pos)pos2;
			return (this.getXCoord() == tempPos.getXCoord() && this.getYCoord() == tempPos.getYCoord());
		}
		return false;
	}

}

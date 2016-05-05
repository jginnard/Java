/* Developed by Jeremy Ginnard
 * Date: 15 APR 2016
 * COSC 311
 */
public class Cell {
	int x = 0;
	int y = 0;
	int groupNumber = 0;
	
	public Cell(int x, int y){
		this.x = x;
		this.y = y;
	}
	
    public boolean equals(Object other) {
    	 
        // If the object is compared with itself then return true  
        if (other == this) {
            return true;
        }
 
        if (!(other instanceof Cell)) { //
            return false;
        }
        Cell otherCell = (Cell) other;
        // Compare the data members and return result
        return Double.compare(x, otherCell.x) == 0 && Double.compare(y, otherCell.y) == 0;
    }
	
	public boolean adjacent(Cell b){
		if((this.x == b.x && Math.abs(this.y - b.y) == 1) || (this.y == b.y && Math.abs(this.x - b.x) == 1))
			return true;
		else
			return false;
	}
	
	public String toString(){
		return "(" + x + "," + y  + ")";
	}

	public int getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}
	
	
}

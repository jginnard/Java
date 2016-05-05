/* Developed by Jeremy Ginnard
 * Date: 15 APR 2016
 * COSC 311
 */
import java.util.ArrayList;

public class Group {
	private ArrayList<Cell> members = new ArrayList<Cell>();
	int size = 0;
	int number;
	
	public void  add(Cell a){
		members.add(a);
		size++;
	}
	
	
	public String toString(){
		String result = "Group " + number + ": (";
		for(Cell i : members){
			result += i + " ";
		}
		result += ")";
		return result;
	}
	

	public static Group makeGroup(Cell a, ArrayList<Cell> cellList, Group temp, int groupNumber){
		Cell nextCell = null;
		boolean repeat = false;
		for(Cell cell : cellList){
			if(temp.size == 0){ //If it's the first cell
				a.setGroupNumber(groupNumber);
				temp.add(a);
			}
			
			if(cell.getGroupNumber() == 0 && a.adjacent(cell)){ //If the cell is adjacent and unassigned
				cell.setGroupNumber(groupNumber);
				temp.add(cell);
				nextCell = cell;
				repeat = true;
			}
//			else {
//				for(Cell i: temp.members){
//					if(a.adjacent(i) && a.getGroupNumber() == 0){
//						a.setGroupNumber(groupNumber);
//						temp.add(a);
//						nextCell = cell;
//						repeat = true;
//						break;
//					}
//				}
//			}
		}
		if(repeat){
			makeGroup(nextCell, cellList, temp, groupNumber);
		}
		return temp;
	}
	
	public static ArrayList<Group> makeAllGroups(ArrayList<Cell> cellList){
		int remaining = cellList.size();
		ArrayList<Group> allGroups = new ArrayList<Group>();
		Cell nextCell = null;
		int groupNumber = 1;
		Group temp = null;
		remaining = cellList.size();
		while(remaining != 0){ //Continue while there are unassigned cells
		temp = new Group();
		for(Cell cell : cellList){
			if(cell.getGroupNumber() == 0){ //Finds the first cell of the next group
				nextCell = cell;
				break;
			}
		}
		temp = makeGroup(nextCell, cellList, temp, groupNumber);
		temp.setNumber(groupNumber);
		allGroups.add(temp);
		groupNumber ++;
		remaining -= temp.size;
		}
		return allGroups;
	}

	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}

	public ArrayList<Cell> getMembers() {
		return members;
	}

	public int getSize() {
		return size;
	}	
}

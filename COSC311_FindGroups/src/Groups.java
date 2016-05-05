/* Developed by Jeremy Ginnard
 * Date: 15 APR 2016
 * COSC 311
 */
import java.util.ArrayList;

public class Groups {
	static ArrayList<Group> members = null;
	public Groups(ArrayList<Cell> cellList){
		members = Group.makeAllGroups(cellList);
	}
	
	public int sizeGroup(int col, int row){
		for(Group group: members){
			if(group.getMembers().contains(new Cell(col, row))){
				return group.getSize();
			}
		}
		return 0;
	}

	public static int numGroups(){
		return members.size();
	}

	public static void printGroups(){
		for(Group group : members)
		System.out.println(group);
	}
}

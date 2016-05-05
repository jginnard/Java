/* Developed by Jeremy Ginnard
 * Date: 15 APR 2016
 * COSC 311
 */
import java.util.ArrayList;

public class FindGroup {
	static ArrayList<Group> groups = null;

	public static void main(String[] args) {
		Table table = new Table();
		table.printBoard();
		Groups groups = new Groups(Table.getCells());
		groups.printGroups();
		System.out.println("Number of groups is: " + groups.numGroups() + " and size of group at 3,0 is " + groups.sizeGroup(3, 0));
	}
}

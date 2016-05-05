/*Author: Jeremy Ginnard
 * Course: COSC 311
 * Date: 11 APR 2016
 */
import java.util.Comparator;

public class BoxComparator implements Comparator<Box>{

	@Override
	public int compare(Box a, Box b) {
		if (a.weight < b.weight)
        {
            return 1;
        }
        if (a.weight > b.weight)
        {
            return -1;
        }
		return 0;
	}
}

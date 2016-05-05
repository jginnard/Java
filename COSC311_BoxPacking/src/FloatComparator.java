/*Author: Jeremy Ginnard
 * Course: COSC 311
 * Date: 11 APR 2016
 */
import java.util.Comparator;

public class FloatComparator implements Comparator<Float>{

	@Override
	public int compare(Float a, Float b) {
		if (a < b)
        {
            return 1;
        }
        if (a > b)
        {
            return -1;
        }
		return 0;
	}
}
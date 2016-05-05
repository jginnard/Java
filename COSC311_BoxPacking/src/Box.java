/*Author: Jeremy Ginnard
 * Course: COSC 311
 * Date: 11 APR 2016
 */
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Box {

	float weight;
	ArrayList<Float> contents = new ArrayList<Float>();
	
	//Constructor, Box always has at least one item
	public Box(Float a){
		contents.add(a);
		weight = a;
	}
	
	//For adding items to a box
	public void add(float a){
		weight += a;
		contents.add(a);
	}
	
	//Outputs string representation of a box
	public String toString(){
		DecimalFormat df = new DecimalFormat("###.###"); 
		String result = "";
		int i = 1;
		for (float a : contents){
			result += (i>1? ", " : "") + "Item" + i +" (" + a + ")";
			i++;
		}
		result += " : Weight = " + df.format(weight);
		return result;
	}
}
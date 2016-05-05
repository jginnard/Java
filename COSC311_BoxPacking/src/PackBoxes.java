/*Author: Jeremy Ginnard
 * Course: COSC 311
 * Date: 11 APR 2016
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PackBoxes {
	final static float CAPACITY = 1.0f;
	public static void main(String[] args) throws IOException {
		ArrayList<Float> L = importFile();
		printInventory(L);
		System.out.println("=========");
		strategyA(L);
		System.out.println("=========");
		strategyB(L);
	}
	
	private static void strategyA(ArrayList<Float> L){
		PriorityQueue<Box> A = new PriorityQueue<Box>(10, new BoxComparator());
		PriorityQueue<Box> tempA = new PriorityQueue<Box>(10, new BoxComparator());
		
		for(float i : L){ //Iterates through all items in L
			while(true){
				if(A.peek() == null){ //If there are no boxes, put item in a new box
					A.add(new Box(i));
					break;
				}
				else if(A.peek().weight + i <= CAPACITY){ //If it fits, it ships
					Box currentBox = A.poll();
					currentBox.add(i);
					A.add(currentBox);
					break;
				}
				else { //Doesn't fit in this box, so set it aside
					tempA.add(A.poll());
					continue;
				}
			}
			while(tempA.peek() != null){ //Adds tempA to A
				A.add(tempA.poll());
			}
		}
		
		
		int i = 0;
		float total = 0;
		System.out.println("Packing via most-filled strategy:");
		while(A.peek() != null){
			Box currentBox = A.poll();
			i++;
			System.out.println("Box " + i + ": " + currentBox);
			total += currentBox.weight;
		}
		System.out.printf("In total, " + i + " boxes were needed. On average, the boxes were %.1f%% full.\n", (total/i)*100);
	}

	private static void strategyB(ArrayList<Float> L){
		PriorityQueue<Box> B = new PriorityQueue<Box>(10, new BoxComparator());
		PriorityQueue<Box> tempB = new PriorityQueue<Box>(10, new BoxComparator());
		PriorityQueue<Float> LB = new PriorityQueue<Float>(10, new FloatComparator());
		
		//Adds All Items from ArrayList to a Max Priority Queue
		for(float i : L){
			LB.add(i);
		}
		
		while(LB.peek() != null){
			float i = LB.poll();
			while(true){
				if(B.peek() == null){ //If there are no boxes, put item in a new box
					B.add(new Box(i));
					break;
				}
				else if(B.peek().weight + i <= CAPACITY){ //If it fits, it ships
					Box currentBox = B.poll();
					currentBox.add(i);
					B.add(currentBox);
					break;
				}
				else { //Doesn't fit in this box, so set it aside
					tempB.add(B.poll());
					continue;
				}
			}
			while(tempB.peek() != null){ //Adds tempA to A
				B.add(tempB.poll());
			}
		}
		
		
		int i = 0;
		float total = 0;
		System.out.println("Packing via heaviest-item first strategy:");
		while(B.peek() != null){
			Box currentBox = B.poll();
			i++;
			System.out.println("Box " + i + ": " + currentBox);
			total += currentBox.weight;
		}
		System.out.printf("In total, " + i + " boxes were needed. On average, the boxes were %.1f%% full.\n", (total/i)*100);
		
	}

	
	static String printInventory(ArrayList<Float> L){
		System.out.println("Inventory:");
		int i = 1;
		for(float a : L){
			System.out.println("Item" + i + ": " + a);
			i++;
		}
		return null;
	}
	
	//Load File to ArrayList
	private static ArrayList importFile() throws IOException{
	String inputFileName ="";
	ArrayList<Float> L = new ArrayList<Float>();
	BufferedReader inFile = null;
	
	//Input File Chooser
	JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File","txt");
    chooser.setFileFilter(filter);
    chooser.setDialogTitle("Select Input File");
    int returnVal = chooser.showOpenDialog(null);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
//       System.out.println("You chose to import this file: " +
//            chooser.getSelectedFile().getName());
       inputFileName = chooser.getSelectedFile().getPath();
    }
	try {
		// open the file using an absolute path name
		inFile = new BufferedReader(new FileReader(inputFileName));
	} catch (FileNotFoundException e) {
		System.out.println("Can't find (or open) the input file \"" + inputFileName + "\"\nTry again!\n");
	}
	
	// read as long as there is more data to read
		String line;
			while ((line = inFile.readLine()) != null){ //Read line-by-line, until end of file
			    String[] result = line.split(" ");
			    for(int i=0; i < result.length; i++){
			    	try{
			    	L.add(Float.parseFloat(result[i]));
			    	}
			    	catch (Exception e){
			    		continue;
			    	}
			    }
			}
			inFile.close();
			return L;
	}
}

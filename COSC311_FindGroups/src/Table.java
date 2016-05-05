/* Developed by Jeremy Ginnard
 * Date: 15 APR 2016
 * COSC 311
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Table implements Cloneable{
	static ArrayList<Cell> cells = new ArrayList<Cell>();
	static int height = 0;
	static int width = 0;

	public Table() {
		try {
			importFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printBoard(){
		String result = "   "; // 3 spaces
		for(int i = 0; i < width; i++){ //Print horizontal axis
			result += i % 10;
		}
		result += "\n";
		for(int row = 0; row < height; row++){
			result += (row % 10) + ": ";
			for(int column = 0; column < width; column++){
				result += (cells.contains(new Cell(column, row))? "X" : "_");
			}
			result += "\n";
		}
		System.out.println(result);
		
	}
	
	
	public static ArrayList<Cell> getCells() {
		return cells;
	}

	//Load File to ArrayList
	private static void importFile() throws IOException{
	String inputFileName ="";
	BufferedReader inFile = null;
	
	//Input File Chooser
	JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File","txt");
    chooser.setFileFilter(filter);
    chooser.setDialogTitle("Select Table File");
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
		int y = 0;
			while ((line = inFile.readLine()) != null){ //Read line-by-line, until end of file
				height++; //Sets table height
			    String[] result = line.split("");
			
			    if(width == 0){
					width = result.length; //Sets table width
				}
					
			    for(int x=0; x < result.length; x++){
			    	try{
			    		if(result[x].equals("X"))
			    			cells.add(new Cell(x, y));
			    	}
			    	catch (Exception e){
			    		continue;
			    	}
			    }
			    y++;
			}
			inFile.close();
	}
}

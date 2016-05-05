import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImportFile {

public static ArrayList importFile() throws IOException{
	String inputFileName ="";
	ArrayList<Float> L = new ArrayList<Float>();
	
	//File Input
	BufferedReader inFile = null;
	
	//Input File Chooser
	JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File","txt");
    chooser.setFileFilter(filter);
    chooser.setDialogTitle("Select Input File");
    int returnVal = chooser.showOpenDialog(null);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
       System.out.println("You chose to import this file: " +
            chooser.getSelectedFile().getName());
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
				
				
			    while ((line = inFile.readLine()) != null) //Read line-by-line, until end of file
			    {
			    	String[] result = line.split(" ");
			    	for(int i=0; i < result.length; i++){
			    		//System.out.println(result[i]);
			    	L.add(Float.parseFloat(result[i]));
			    	}
			    }
				
			inFile.close();
	return L;
}
}

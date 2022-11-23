import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
//driver class
//im thinking we make a loop that goes through every file and puts each element into an array. then writes them into the files? idk fam
//look up use delimeter it miht be useful for this not sure
public class BibliographyFactory {

    public static void processFilesForValidation(Scanner s, PrintWriter p)
	{
		// Read line by line from input file and copy it to output file
		String str;
		
		while(s.hasNextLine())
		{
			str = s.nextLine();		
			p.println(str);
			
		}
        s.close();
        p.close();

	}

    public static void displayFileContents(BufferedReader b) throws IOException
	{
		String x;
		
		x = b.readLine();
		while(x != null) 
		{
			System.out.print(x);		// MUST CAST; otherwise all what is read will be shown as integers
			x = b.readLine();		
		}
		// Must close the file 
		b.close();
	}
    public static void main(String[] args) 
	{
			
		String s1, s2;
		PrintWriter pw = null;		
		Scanner kb = new Scanner(System.in);
		Scanner sc = null;
        BufferedReader br = null;

		// See if we can establish the two streams
        for (int i = 1; i < 11; i++){
            try
            {
                s1 = "Latex" + i + ".bib";
                s2 = "IEEE" + i + ".json";
                sc = new Scanner(new FileInputStream(s1));	
                pw = new PrintWriter(new FileOutputStream(s2));
            }
            catch(FileNotFoundException e) 
            {							   
                System.out.println("Problem opening files. Cannot proceed to copy.");
                System.out.println("Program will terminate.");
                System.exit(0);			   
            }
            
            // At this moment, both streams exist, so call the method to create the fomatted files
            processFilesForValidation(sc, pw);
            System.out.println("File has been copied ");
        }

        System.out.println("Enter file name that you'd like to be printed: ");
        String fileName = kb.nextLine();

        // Attempt to open the requested file
        try
		{
			br = new BufferedReader(new FileReader(fileName));		
		}
		catch(FileNotFoundException e) 
		{							   
			System.out.println("Problem opening files. Cannot proceed to copy.");
			System.out.println("Program will terminate.");
			System.exit(0);			   
		}

        // Print out contents of requested file
        try
		{
			displayFileContents(br);
		}
		catch(IOException e)
		{
			System.out.println("Error: An error has occurred while reading from the " + fileName + " file. ");
			System.out.println("Program will terminate.");
			System.exit(0);		
		}

        kb.close();
	}
}

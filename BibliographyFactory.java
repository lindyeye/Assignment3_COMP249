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

    public static void processFilesForValidation(Scanner s, PrintWriter p, String fileType)
	{
		// Read line by line from input file and copy it to output file
		String str;
		String type;
		String data;
		int startIndex;
		int endIndex;
		boolean noData = false;
		int counter = 0;
		int articleCounter = 1;

		String author = null;
		String journal = null;
		String title = null;
		String year = null;
		String volume = null;
		String number = null;
		String pages = null;
		String keywords = null;
		String doi = null;
		String ISSN = null;
		String month = null;
		int articleIndex = 1;

		// yeah good luck figuring this one out
		while(s.hasNextLine())
		{
			counter = 0;
			noData = false;
			str = s.nextLine();		
			if(str.indexOf("@ARTICLE{") != -1){
				//System.out.println("Article found!");
				articleIndex = articleCounter;
				while (counter < 11){	// there are always 11
					str = s.nextLine();
					//System.out.println("Line: " + str);
					if(str.indexOf("={") != -1){
						startIndex = str.indexOf("={");
						if (str.charAt(startIndex + 2) == '}'){
							System.out.println("No data");
							noData = true;
							break;
						}
						endIndex = str.indexOf("},");
						data = str.substring(startIndex + 2, endIndex);
						//System.out.println("Data: " + data);
						type = str.substring(0, startIndex);
						//System.out.println("Type: " + type);
						
						if (type.equals("author")){
							author = data;
						}
						else if (type.equals("journal")){
							journal = data;
						}
						else if (type.equals("title")){
							title = data;
						}
						else if (type.equals("year")){
							year = data;
						}
						else if (type.equals("volume")){
							volume = data;
						}
						else if (type.equals("number")){
							number = data;
						}
						else if (type.equals("pages")){
							pages = data;
						}
						else if (type.equals("keywords")){
							keywords = data;
						}
						else if (type.equals("doi")){
							doi = data;
						}
						else if (type.equals("ISSN")){
							ISSN = data;
						}
						else if (type.equals("month")){
							month = data;
						}
						
						counter++;
					}
				}
				if (!noData){
					Article article = new Article(author, journal, title, year, volume, number, pages, keywords, doi, ISSN, month, articleIndex);
					//System.out.println("Article object created!");
					if (fileType.equals("IEEE")){
						p.println(article.toIEEE());
					}
					else if(fileType.equals("ACM")){
						p.println(article.toACM());
					}
					else if(fileType.equals("NJ")){
						p.println(article.toNJ());
					}
					p.println();
					articleCounter++;
				}
				//System.out.println();
			}
			
		}
		System.out.println("File done!");
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
		
		//IEEE output files
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
            processFilesForValidation(sc, pw, "IEEE");
            System.out.println("File has been copied ");
        }

		// ACM output files
		for (int i = 1; i < 11; i++){
            try
            {
                s1 = "Latex" + i + ".bib";
                s2 = "ACM" + i + ".json";
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
            processFilesForValidation(sc, pw, "ACM");
            System.out.println("File has been copied ");
        }

		// NJ output files
		for (int i = 1; i < 11; i++){
            try
            {
                s1 = "Latex" + i + ".bib";
                s2 = "NJ" + i + ".json";
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
            processFilesForValidation(sc, pw, "NJ");
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


import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;

public class BibliographyFactory{

	private static int invalidFileCount = 0;	// keeping track of number files with empty information fields

	public static void processFilesForValidation(Scanner s, PrintWriter p, int fileNumber, File[] outputFiles)
	{
		// Read line by line from input file and copy it to output file
		String str;		// line of latex file
		String type = "No type";		// type of field
		String data;		// field data
		int startIndex;		// start index of field data string
		int endIndex;		// end index of field data string
		boolean noData = false;		// changed to true when an empty field is found
		int counter = 0;	// there are 11 data fields, so this counter will loop 11 times to retrieve info
		int articleCounter = 1;

		Article[] article = new Article[30];		// used to hold all article objects created from latex file
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

		while(s.hasNextLine())
		{
			counter = 0;
			str = s.nextLine();		
			if(str.indexOf("@ARTICLE{") != -1){
				articleIndex = articleCounter;
				while (counter < 11){	// there are always 11 fields
					str = s.nextLine();
					if(str.indexOf("={") != -1){		// if line has ={
						startIndex = str.indexOf("={");
						type = str.substring(0, startIndex);
						if (str.charAt(startIndex + 2) == '}'){		// if line has ={}
							noData = true;
							break;
						}
						endIndex = str.indexOf("},");
						data = str.substring(startIndex + 2, endIndex);						
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
						counter++;		// find next data field as long as counter < 11
					}
				}
				try{
					if (!noData){		// if no empty fields were found
						article[articleCounter - 1] = new Article(author, journal, title, year, volume, number, pages, keywords, doi, ISSN, month, articleIndex);
						articleCounter++;
					}
					else {
						throw new FileInvalidException("Error: Detected Empty Field!\n============================\n");
					}
				}
				catch (FileInvalidException e){
					System.out.println(e.getMessage());
					System.out.println("Problem detected with input file: Latex" + fileNumber + ".bib");
					System.out.println("File is invalid: Field \"" + type + "\" is empty. Processing stopped at this point."
					+  " Other empty fields may be present as well!\n");
					outputFiles[fileNumber-1].delete();		// delete IEEE, ACM, and NJ files for given Latex file
					outputFiles[fileNumber+10-1].delete();
					outputFiles[fileNumber+20-1].delete();
					invalidFileCount++;
					s.close();
					return;
				}
			}
			
		}
		s.close();
		try{		// for latex file 1, print to file in outputFiles[] array at index 0...
			p = new PrintWriter(outputFiles[fileNumber-1]);
		}
		catch (Exception e){
			System.out.println("Problem in processing method");
		}
		for (Article a : article){
			if (a != null){		// if file was deleted because of an empty field, 'a' will be null
				p.println(a.toIEEE());
				p.println();
			}
		}
		p.close();

		try{		// for latex file 1, print to file in outputFiles[] array at index 10 (ACM files start at index 10)
			p = new PrintWriter(outputFiles[fileNumber+10-1]);
		}
		catch (Exception e){
			System.out.println("Problem in processing method");
		}
		for (Article a : article){
			if (a != null){
				p.println(a.toACM());
				p.println();
			}
		}
		p.close();

		try{		// for latex file 1, print to file in outputFiles[] array at index 20 (NJ files start at index 20)
			p = new PrintWriter(outputFiles[fileNumber+20-1]);
		}
		catch (Exception e){
			System.out.println("Problem in processing method");
		}
		for (Article a : article){
			if (a != null){
				p.println(a.toNJ());
				p.println();
			}
		}
        p.close();
	}	// processFilesForValidaton


	public static void main(String args[]){
		Scanner ui = new Scanner(System.in);
		Scanner sc = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		String latexFile, outputFile, fileToRead, fileToDisplay, currentLine;
		File[] outputFiles = new File[30];		// stores IEEE, ACM, and NJ files

		System.out.println("\nWelcome to BibliographyFactory!\n");

		/*
		* Opens each file
		*/
		for (int i = 1; i < 11; i++){
			latexFile = "Latex" + i + ".bib";
			try{ 
				sc = new Scanner(new FileInputStream(latexFile));
			}
			catch(FileNotFoundException e) {							   
				System.out.println("Could not open file " + latexFile + "for reading");
				System.out.println("\nPlease check if file exists! Program will terminate after closing any open files.");
				sc.close();
				System.exit(0);			   
			}
			sc.close();
		}

		/*
		 * Creates a file for each file type, and stores in file array so that they can be deleted if any cannot be made
		 * or deleted once the program finishes
		 */
		for (int i = 1; i < 11; i++){
			outputFile = "IEEE" + i + ".json";
			try{ 
				outputFiles[i-1] = new File(outputFile);
				pw = new PrintWriter(outputFiles[i-1]);
			}
			catch(FileNotFoundException e) {							   
				System.out.println("Could not open and create file " + outputFile);
				System.out.println("\nDeleting all other created files, closing input files, and exiting program");
				pw.close();
				for (File file : outputFiles){
					if (file.exists()){	// deletes all existing files
						file.delete();
					}
				}
				System.exit(0);		   
			}
			pw.close();

			outputFile = "ACM" + i + ".json";
			try{ 
				outputFiles[i+10-1] = new File(outputFile);
				pw = new PrintWriter(outputFiles[i+10-1]);
			}
			catch(FileNotFoundException e) {							   
				System.out.println("Could not open and create file " + outputFile);
				System.out.println("\nDeleting all other created files, closing input files, and exiting program");
				pw.close();
				for (File file : outputFiles){ // deletes all existing files
					if (file.exists()){
						file.delete();
					}
				}
				System.exit(0);			   
			}
			pw.close();

			outputFile = "NJ" + i + ".json";
			try{ 
				outputFiles[i+20-1] = new File(outputFile);
				pw = new PrintWriter(outputFiles[i+20-1]);
			}
			catch(FileNotFoundException e) {							   
				System.out.println("Could not open and create file " + outputFile);
				System.out.println("\nDeleting all other created files, closing input files, and exiting program");
				pw.close();
				for (File file : outputFiles){
					if (file.exists()){	// deletes all existing files
						file.delete();
					}
				}
				System.exit(0);			   
			}
			pw.close();
		}


		for (int i = 1; i < 11; i++){
			latexFile = "Latex" + i + ".bib";
			try{ 
				sc = new Scanner(new FileInputStream(latexFile));
			}
			catch(Exception e) {							   
				System.out.println("Trouble writing to file " + outputFiles[i-1].getName());		   
			}
			processFilesForValidation(sc, pw, i, outputFiles);
			sc.close();
			pw.close();
		}
		System.out.println("A total of " + invalidFileCount + " files were invalid, and could not be processed. All other " + (10-invalidFileCount)
		+ " \"Valid\" files have been created.\n");


		int fileToPrintIndex = -1;		// if requested file exists, this becomes the file's index in outputFiles[] array
		int loopCount = 0;		// used to loop once and give user two chances to enter name of file

		while (true){
			System.out.print("Please enter the name of one of the files that you need to review: ");
			fileToRead = ui.nextLine();
			try{
				for (int i = 0; i < outputFiles.length; i++){
					if (outputFiles[i].exists()){		// can't get the name of null
						if (outputFiles[i].getName().equals(fileToRead)){
							fileToPrintIndex = i;
							break;
						}
					}
				}
				if(fileToPrintIndex != -1){
					break;
				}
				else if (fileToPrintIndex == -1 && loopCount == 0){		// file name doesn't exist BUT it's the FIRST try
					throw new FileNotFoundException("Could not open input file. File does not exist; possibly it could not be created!\n\n"
					+ "However, you will be allowed another chance to enter another file name.");
				}
			}
			catch (FileNotFoundException e){
				System.out.println(e.getMessage());
			}
			if (loopCount ==1 && fileToPrintIndex == -1){		// file name doesn't exist AND it's the SECOND try
				System.out.println("\nCould not open input file again! Either file does not exist or could not be created.");
				System.out.println("Sorry! I am unable to display your desired files! Program will exit!\n");
				System.exit(0);
			}
			loopCount++;		// loopCount = 1 now
		}

		fileToDisplay = outputFiles[fileToPrintIndex].getName();
		System.out.println("Here are the contents of the successfully created Jason file: " + fileToDisplay);


		// prints out requested file line by line
		try{
			br = new BufferedReader(new FileReader(fileToDisplay));
			currentLine = br.readLine();
			while (currentLine != null){
				System.out.println(currentLine);
				currentLine = br.readLine();
			}
			br.close();
		}
		catch (FileNotFoundException e){
			System.out.println("File not found");
		}
		catch (IOException e){
			System.out.println("IO problem");
		}
		
		System.out.println();


		// This block commented out below is used to delete all output files when testing code
		
		// System.out.println("\nEnter anything to delete output files");
		// ui.nextLine();

		// for (File file : outputFiles){
		// 	if (file.exists()){
		// 		file.delete();
		// 	}
		// }

		// System.out.println("Output files deleted.");
		
		ui.close();

    }	// main




	}	// class
public class FileInvalidException extends Exception {
    public FileInvalidException()
	{
		super("Error: Input file cannot be parsed due to missing information(i.e. month={}, title={}, etc.)");
	}
	
	// A constructor that takes a String parameter
	public FileInvalidException(String s)
	{
		super(s);
	}
	
	public String getMessage()
	{
		return super.getMessage();
	}
}

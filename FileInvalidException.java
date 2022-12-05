public class FileInvalidException extends Exception {
    public FileInvalidException()
	{
		super("Error: Input file cannot be parsed due to missing information(i.e. month={}, title={}, etc.)");
	}
	
	public FileInvalidException(String s)
	{
		super(s);
	}
	
	/** 
	 * Returns message for exception
	 * @return String message of exception
	 */
	public String getMessage()
	{
		return super.getMessage();
	}
}

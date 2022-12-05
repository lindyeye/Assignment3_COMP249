public class Article {
    //this will have all the getters and setters + toStrings for all articles
    
    private String author;
    private String journal;
    private String title;
    private String year;
    private String volume;
    private String number;
    private String pages;
    private String keywords;
    private String doi;
    private String ISSN;
    private String month;
    private int index;
    /**
	 * Constructor takes in parameters and creates a new Article object
	 * @param author name of author of the article.
	 * @param journal type of journal(IEEE, ACM, NJ)
	 * @param title name of the article
	 * @param year year article was published
	 * @param volume volume of article.
	 * @param number number of article.
	 * @param pages pages of article.
	 * @param keywords keywords of article.
	 * @param doi doi of article.
	 * @param ISSN ISSN of article.
	 * @param month month article was published.
     * @param index index of article in Latex
	 */
    public Article(String author, String journal, String title, String year, String volume, String number, String pages, String keywords, String doi, String ISSN, String month, int index)
    {
        this.author = author;
        this.journal = journal;
        this.title = title;
        this.year = year;
        this.volume = volume;
        this.number = number;
        this.pages = pages;
        this.keywords = keywords;
        this.doi = doi;
        this.ISSN = ISSN;
        this.month = month;
        this.index = index;
    }

    
    /** 
     * returns author of article
     * @return String author name
     */
    public String getAuthor() {
        return author;
    }
    
    /** 
     * sets author of the article
     * @param author author to change to
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    
    /** 
     * returns journal info
     * @return String journal info
     */
    public String getJournal() {
        return journal;
    }
    
    /** 
     * sets journal info
     * @param journal journal info
     */
    public void setJournal(String journal) {
        this.journal = journal;
    }

    
    /** 
     * returns title of the article
     * @return String title of article
     */
    public String getTitle() {
        return title;
    }
    
    /** 
     * sets title of the article
     * @param title title of the article
     */
    public void setTitle(String title) {
        this.title = title;
    }

    
    /** 
     * returns year the article was published
     * @return String year article was published
     */
    public String getYear() {
        return year;
    }
    
    /**
     * sets year the article was published 
     * @param year year the article was published
     */
    public void setYear(String year) {
        this.year = year;
    }

    
    /** 
     * gets volume of article
     * @return String volume
     */
    public String getVolume() {
        return volume;
    }
    
    /** 
     * sets volume of article
     * @param volume volume
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }

    
    /** 
     * gets number of the article
     * @return String number
     */
    public String getNumber() {
        return number;
    }
    
    /** 
     * sets number of article
     * @param number number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    
    /** 
     * gets pages of article
     * @return String pages
     */
    public String getPages() {
        return pages;
    }
    
    /** 
     * sets pages of article
     * @param pages pages of article
     */
    public void setPages(String pages) {
        this.pages = pages;
    }

    
    /** 
     * gets keywords of article
     * @return String keywords of article
     */
    public String getKeywords() {
        return keywords;
    }
    
    /** 
     * sets keywords of article
     * @param keywords keywords
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    
    /** 
     * gets DOI of article
     * @return String DOI of article
     */
    public String getDoi() {
        return doi;
    }
    
    /** 
     * sets DOI of article
     * @param doi DOI
     */
    public void setDoi(String doi) {
        this.doi = doi;
    }

    
    /** 
     * gets ISSN of article
     * @return String ISSN
     */
    public String getISSN() {
        return ISSN;
    }
    
    /** 
     * sets ISSN of article
     * @param iSSN ISSN
     */
    public void setISSN(String iSSN) {
        ISSN = iSSN;
    }
    
    
    /** 
     * gets month article was published
     * @return String month
     */
    public String getMonth() {
        return month;
    }
    
    /** 
     * sets month article was published
     * @param month month article was published
     */
    public void setMonth(String month) {
        this.month = month;
    }

    
    /** 
     * gets index of article
     * @return int index
     */
    public int getIndex() {
        return index;
    }
    
    /** 
     * sets index of article
     * @param index index of article
     */
    public void setIndex(int index) {
        this.index = index;
    }

    
    /** 
     * outputs the citation for IEEE
     * @return String IEEE citation
     */
    public String toIEEE()
    {
        int index = author.indexOf(" and ");
        String authorIEEE = author;
        while(index != -1)
        {
            authorIEEE = authorIEEE.substring(0, index)+ ", " + authorIEEE.substring(index + 5);
            index = authorIEEE.indexOf(" and ");
        }
        authorIEEE += ".";
        return authorIEEE + " " +  "\"" + title + "\"" + ", " + journal + ", vol. " + volume + ", no. " + number + ", p. " + pages + ", " + month + " " + year + ".";  
                
    }

    
    /** 
     * outputs the citation for ACM
     * @return String ACM citation
     */
    public String toACM()
    {
        int endIndex = author.indexOf(" and ");
        String authorACM = author;
        if (endIndex != -1){
            authorACM = author.substring(0, endIndex) + " et al";
        }
        return "[" + index + "]" + "\t" + authorACM + ". " + year + ". " + title + ". " + journal + ". " + volume + ", " + number +
        " (" + year + "), " + pages + ". DOI:https://doi.org/" + doi + ".";
    }

    
    /**
     * outputs the citation for NJ
     * @return String NJ citation
     */
    public String toNJ(){
        int index = author.indexOf(" and ");
        String authorNJ = author;
        while(index != -1)
        {
            authorNJ = authorNJ.substring(0, index)+ " & " + authorNJ.substring(index + 5);
            index = authorNJ.indexOf(" and ");
        }
        return authorNJ + ". " + title + ". " + journal + ". " + volume + ", " + pages + "(" + year + ").";
    }

}

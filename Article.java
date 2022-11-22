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
	 */
    public Article(String author, String journal, String title, String year, String volume, String number, String pages, String keywords, String doi, String ISSN, String month)
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
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getJournal() {
        return journal;
    }
    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    public String getVolume() {
        return volume;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getPages() {
        return pages;
    }
    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDoi() {
        return doi;
    }
    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getISSN() {
        return ISSN;
    }
    public void setISSN(String iSSN) {
        ISSN = iSSN;
    }
    
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    //idea here is that it seperates the author names and puts them into a new string so they're properly formatted. idk if it works this is just a theory
    public String toIEEE()
    {
        int index = author.indexOf(" and ");
        String authorIEEE = author;
        while(index != -1)
        {
            authorIEEE = authorIEEE.substring(0,index)+", "+ authorIEEE.substring(index + 5);
            index = authorIEEE.indexOf(" and ");
        }
        authorIEEE += ".";
        return authorIEEE + title + "," + journal + "," + volume + ", p." + pages + "," + month + " " + year + ".";  
                
    }

}

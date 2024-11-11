public class Book{
    private String title;
    private String author;
    private String content;
    private int edition;

    /**
     * Makes a new book object with title, author, content, and edition
     * @param t Title of the book
     * @param a Author of the book
     * @param c Content of the book
     * @param e Edition number of the book
     */
    public Book(String t, String a, String c, int e){
        title = t;
        author = a;
        content = c;
        edition = e;
    }

    /**
     * Gets the title of book
     * @return The title of the book
     */
    public String getTitle(){
        return title;
    }

    /**
     * Gets the author of the book
     * @return The author of the book
     */
    public String getAuthor(){
        return author;
    }

    /**
     * Gets the content of the book
     * @return The content of the book
     */
    public String getContent(){
        return content;
    }

    /**
     * Gets the edition of the book
     * @return The edition of the book
     */
    public int getEdition(){
        return edition;
    }

    /**
     * Calculates the number of pages in the book
     * @return The number of pages in the book
     */
    public int getPages(){
        return (int) Math.ceil((double) content.length() / 750);
    }

    /**
     * Returns a string of the book's title, author, and edition
     * @return A string for the details of the book
     */
    public String toString(){
        return "Title: " + title + "\nAuthor: " + author + "\nEdition: " + edition + "\n";
    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Press{
    private Map<String, List<Book>> shelf;
    private Map<String, Integer> edition;
    private int shelfSize;

    /**
     * Press machine to get specific books
     * @param pathToBookDir Path to the directory containing book files
     * @param shelfSize Max number of books each shelf can hold
     */
    public Press(String pathToBookDir, int shelfSize){
        this.shelfSize = shelfSize;
        this.shelf = new HashMap<>();
        this.edition = new HashMap<>();

        try{
            //initialise shelf
            Files.list(Paths.get(pathToBookDir))
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        String bookID = file.getFileName().toString();
                        shelf.put(bookID, new ArrayList<>());
                        edition.put(bookID, 0);
                    });
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the list of book IDs available in the press
     * @return List of book IDs
     */
    public List<String> getCatalogue(){
        return new ArrayList<>(shelf.keySet());
    }

    /**
     * Requests books with the ID
     * If more books are requested than available in stock, additional copies are printed
     * @param bookID ID of book to request
     * @param amount Number of books to request
     * @return List of requested books
     * @throws IllegalArgumentException For if book ID is invalid
     */
    public List<Book> request(String bookID, int amount){
        List<Book> requestedBooks = new ArrayList<>();
        if(!shelf.containsKey(bookID)){
            throw new IllegalArgumentException("Invalid book ID");
        }

        List<Book> bookList = shelf.get(bookID);
        int remainingAmount = amount;

        //fill requested books
        while(remainingAmount > 0 && !bookList.isEmpty()){
            requestedBooks.add(bookList.remove(0));
            remainingAmount--;
        }

        //print new books if needed
        if(remainingAmount > 0){
            int currentEdition = edition.get(bookID);
            for(int i = 0; i < remainingAmount; i++){
                Book newBook = print(bookID, currentEdition + 1);
                requestedBooks.add(newBook);
                bookList.add(newBook);
            }
            edition.put(bookID, currentEdition + 1);
        }

        return requestedBooks;
    }


    /**
     * Prints a new book for book ID and edition number
     * @param bookID ID of the book to print
     * @param edition Edition number of book to print
     * @return The printed book
     * @throws IllegalArgumentException If the book file cannot be read
     */
    protected Book print(String bookID, int edition){
        String filePath = bookID;
        String title = "";
        String author = "";
        StringBuilder content = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            boolean headerPassed = false;
            while ((line = br.readLine()) != null){
                if(!headerPassed && (line.contains("The Project Gutenberg") || line.contains("Project Gutenberg's"))){
                    headerPassed = true;
                    continue;
                }

                if(headerPassed && line.startsWith("*** START OF")){
                    break;
                }

                if(headerPassed && line.startsWith("Title:")){
                    title = line.substring("Title:".length()).trim();
                }

                if(headerPassed && line.startsWith("Author:")){
                    author = line.substring("Author:".length()).trim();
                }

                if(headerPassed){
                    content.append(line).append("\n");
                }
            }

        }catch(IOException e){
            throw new IllegalArgumentException("Error reading file: " + bookID);
        }

        return new Book(title, author, content.toString(), edition);
    }
}
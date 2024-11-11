import java.util.ArrayList;
import java.util.List;

/**
 * A vending machine that stores and gives books
 */
public class VendingMachine{
    private List<Book> shelf;
    private double locationFactor;
    private int cassette;
    private int safe;
    private String password;


    /**
     * Makes vending machine with a location factor and password
     * @param locationFactor Location factor affecting book prices
     * @param password The password required to access vending machine 
     */
    public VendingMachine(double locationFactor, String password){
        this.locationFactor = locationFactor;
        this.password = password;
        this.cassette = 0;
        this.safe = 0;
        this.shelf = new ArrayList<>();
    }

    /**
     * Returns how much money is currently in the cassette
     * @return Amount of money in the cassette
     */
    public int getCassette(){
        return cassette;
    }

    /**
     * Inserts coin into the cassette
     * @param coin The coin to insert
     * @throws IllegalArgumentException If coin is invalid
     */
    public void insertCoin(int coin){
        if(coin != 1 && coin != 2 && coin != 5 && coin != 10 && coin != 20 && coin != 50 && coin != 100 && coin != 200){
            throw new IllegalArgumentException("Invalid coin denomination");
        }
        cassette += coin;
    }

    /**
     * Cancels transaction and returns money
     * @return Amount of money returned after cancel
     */
    public int cancel(){
        int canceledAmount = cassette;
        cassette = 0;
        return canceledAmount;
    }

    /**
     * Restocks vending machine's shelf
     * @param books List of books to restock
     * @param password Password required for restocking
     * @throws InvalidPasswordException If password inputted is wrong
     */
    public void restock(List<Book> books, String password){
        if(!this.password.equals(password)){
            throw new InvalidPasswordException("Invalid password for restocking");
        }
        shelf.addAll(books);
    }

    /**
     * Empties the safe and returns the total amount of money
     * @param password Password required for emptying the safe
     * @return Total amount of money emptied from the safe
     * @throws InvalidPasswordException If the password is invalid
     */
    public int emptySafe(String password){
        if(!this.password.equals(password)){
            throw new InvalidPasswordException("Invalid password for emptying safe");
        }

        int previousSafeAmount = safe;
        safe = 0;
        return previousSafeAmount;
    }

    /**
     * Retrieves the books available on the shelf
     * @return List of book descriptions
     */
    public List<String> getCatalogue(){
        List<String> catalogue = new ArrayList<>();
        for(Book book : shelf){
            catalogue.add(book.toString());
        }
        return catalogue;
    }

    /**
     * Calculates and gets price of the book on the shelf
     * @param index Index of the book on shelf
     * @return Price of the book
     * @throws IndexOutOfBoundsException If the index is out of bounds
     */
    public int getPrice(int index){
        if(index < 0 || index >= shelf.size()){
            throw new IndexOutOfBoundsException("Invalid book index");
        }

        Book book = shelf.get(index);
        int price = (int) Math.ceil(book.getPages() * locationFactor);
        return price;
    }

    /**
     * Sells the book and gives it to the customer
     * @param index Index of the book to sell
     * @return The book sold to the customer
     * @throws IndexOutOfBoundsException If index is out of bounds
     * @throws CassetteException If there is not enough money in the cassette to buy the book
     */
    public Book buyBook(int index){
        if(index < 0 || index >= shelf.size()){
            throw new IndexOutOfBoundsException("Invalid book index");
        }

        Book book = shelf.get(index);
        int price = getPrice(index);
        if(price > cassette){
            throw new CassetteException("Not enough money in cassette to buy the book");
        }
        
        shelf.remove(index);
        cassette -= price;
        safe += price;
        return book;
    }
}
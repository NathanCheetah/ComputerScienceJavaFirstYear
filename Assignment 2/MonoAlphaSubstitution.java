import java.util.HashMap; //import for hash map as suggested in assignment page

public class MonoAlphaSubstitution extends Substitution{
    private HashMap<Character, Character> mapping;

    /**
     * Default constructor
     */
    public MonoAlphaSubstitution(){
        mapping = new HashMap<>();
        for(char c = 'a'; c <= 'z'; c++){
            mapping.put(c, c);
        }
        for(char c = 'A'; c <= 'Z'; c++){
            mapping.put(c, c);
        }
    }

    /**
     * Constructor that takes in the given mapping string.
     *
     * @param mappingString the string for translation
     */
    public MonoAlphaSubstitution(String mappingString){
        mapping = new HashMap<>();
        for(int i = 0; i < mappingString.length(); i += 2){
            char from = mappingString.charAt(i);
            char to = mappingString.charAt(i + 1);
            mapping.put(from, to);
        }
    }

    /**
     * Encrypts a single character from table.
     *
     * @param c the character to encrypt
     * @return the encrypted character
     */
    public char encrypt(char c){
        return mapping.getOrDefault(c, c);
    }

    /**
     * Decrypts a single character from table.
     *
     * @param c the character to decrypt
     * @return the decrypted character
     */
    public char decrypt(char c){
        for(char key : mapping.keySet()){
            if(mapping.get(key) == c){
                return key;
            }
        }
        return c;
    }

    public static void main(String[] args){
        if(args.length < 3){
            System.out.println("Too few parameters!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
            return;
        }

        String mode = args[0];
        String mappingString = args[1];
        String text = args[2];

        if(!mode.equals("encrypt") && !mode.equals("decrypt")){
            System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
            return;
        }

        MonoAlphaSubstitution cipher = new MonoAlphaSubstitution(mappingString);

        if(mode.equals("encrypt")){
            System.out.println(cipher.encrypt(text));
        }else if(mode.equals("decrypt")){
            System.out.println(cipher.decrypt(text));
        }
    }

}

public class Vigenere extends Substitution{
    private int position;
    private final String key;
    private final Caesar[] ciphers;

    /**
     * Default constructor creating objects with identity behavior.
     */
    public Vigenere(){
        this.key = "";
        this.ciphers = new Caesar[0];
    }

    /**
     * Constructor that takes a key word for encryption/decryption.
     *
     * @param key the key word to be used
     */
    public Vigenere(String key){
        this.key = key;
        this.position = 0;
        this.ciphers = new Caesar[key.length()];
        for(int i = 0; i < key.length(); i++){
            int shift = key.charAt(i) - 'A';
            this.ciphers[i] = new Caesar(shift);
        }
    }

    /**
     * Encrypts a single character using the Vigenere cipher with the current position's Caesar cipher.
     *
     * @param c the character to encrypt
     * @return the encrypted character
     */
    @Override
    public char encrypt(char c){
        if(Character.isLetter(c)){
            char encrypted = ciphers[position].encrypt(c);
            position = (position + 1) % ciphers.length;
            return encrypted;
        }else{
            return c;
        }
    }

    /**
     * Decrypts a single character using the Vigenere cipher with the current position's Caesar cipher.
     *
     * @param c the character to decrypt
     * @return the decrypted character
     */
    @Override
    public char decrypt(char c){
        if(Character.isLetter(c)){
            char decrypted = ciphers[position].decrypt(c);
            position = (position + 1) % ciphers.length;
            return decrypted;
        }else{
            return c;
        }
    }

    public static void main(String[] args){
        if(args.length != 3 || !args[0].equals("encrypt") && !args[0].equals("decrypt")){
            System.out.println("Usage: java Vigenere <encrypt|decrypt> key \"text\"");
            return;
        }

        String mode = args[0];
        String key = args[1].toUpperCase();
        String text = args[2];

        Vigenere cipher = new Vigenere(key);

        if(mode.equals("encrypt")){
            System.out.println(cipher.encrypt(text));
        }else if(mode.equals("decrypt")){
            System.out.println(cipher.decrypt(text));
        }
    }
}

public class Caesar extends MonoAlphaSubstitution{
    private int shift;

    /**
     * Default constructor
     */
    public Caesar(){
        super(); //default constructor of superclass
        this.shift = 0;
    }

    /**
     * Constructor that takes an integer argument determining the shift.
     *
     * @param shift shift for encryption/decryption
     */
    public Caesar(int shift){
        super();
        this.shift = (shift + 12224) % 26;
    }

    /**
     * Encrypts a single character according to the Caesar cipher with the specified shift.
     *
     * @param c the character to encrypt
     * @return the encrypted character
     */
    @Override
    public char encrypt(char c){
        if(Character.isLowerCase(c)){
            return (char)((c - 'a' + shift) % 26 + 'a');
        }else if(Character.isUpperCase(c)){
            return (char)((c - 'A' + shift) % 26 + 'A');
        }else{
            return c;
        }
    }

    /**
     * Decrypts a single character according to the Caesar cipher with the specified shift.
     *
     * @param c the character to decrypt
     * @return the decrypted character
     */
    @Override
    public char decrypt(char c){
        if(Character.isLowerCase(c)){
            return (char)((c - 'a' - shift + 26) % 26 + 'a');
        }else if(Character.isUpperCase(c)) {
            return (char)((c - 'A' - shift + 26) % 26 + 'A');
        }else{
            return c;
        }
    }

    public static void main(String[] args){
        if(args.length != 3){
            System.out.println("Usage: java Caesar <encrypt|decrypt> <shift> \"text\"");
            return;
        }

        String mode = args[0];
        int shift;

        if(!args[1].matches("^-?\\d+$")){
            System.out.println("Invalid shift value. Please provide an integer.");
            return;
        }

        shift = Integer.parseInt(args[1]);
        
        String text = args[2];

        Caesar cipher = new Caesar(shift);

        if(mode.equals("encrypt")){
            System.out.println(cipher.encrypt(text));
        }else if(mode.equals("decrypt")){
            System.out.println(cipher.decrypt(text));
        }else{
            System.out.println("Invalid mode. Use 'encrypt' or 'decrypt'.");
        }
    }
}

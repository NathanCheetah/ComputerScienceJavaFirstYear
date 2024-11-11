public abstract class Substitution implements Cipher{

    /**
     * Encodes a single character using the implemented substitution algorithm.
     *
     * @param c the character to encode
     * @return the encoded character
     */
    public abstract char encrypt(char c);

    /**
     * Decodes a single character using the implemented substitution algorithm.
     *
     * @param c the character to decode
     * @return the decoded character
     */
    public abstract char decrypt(char c);

    /**
     * Encodes the given plain text into a secret cipher text.
     *
     * @param plainText the plain text to encode
     * @return the Cipher text
     */
    public String encrypt(String plainText){
        StringBuilder cipherText = new StringBuilder();
        for(int i = 0; i < plainText.length(); i++){
            char c = plainText.charAt(i);
            cipherText.append(encrypt(c));
        }
        return cipherText.toString();
    }

    /**
     * Determines the plain text string for a given cipher text.
     *
     * @param cipherText the cipher text to decode
     * @return the plain text original
     */
    public String decrypt(String cipherText){
        StringBuilder plainText = new StringBuilder();
        for(int i = 0; i < cipherText.length(); i++){
            char c = cipherText.charAt(i);
            plainText.append(decrypt(c));
        }
        return plainText.toString();
    }
}

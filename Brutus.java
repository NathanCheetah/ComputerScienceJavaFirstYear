/**
 * A program to find what the caesar cipher shifts are and decodes the input.
 *
 * @version 1.0
 */

public class Brutus {
    public static final double[] english = {

        0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733,
        0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633,
        0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011
    };

    /**
     * Checks how much of each letter is in the inputted string.
     *
     * Cycles through every single letter in the string and increments the counter to the alphabet array if found.
     *
     * @param  str string that will be used to count number of each letter in string.
     * @return the number of each letter as int array.
     */
    public static int[] count(String str){
        int[] letterCount = new int[26];
        str = str.toLowerCase();

        for(int i=0; i<str.length(); i++){ //loops through characters
            char curntChar = str.charAt(i);

            if(Character.isLetter(curntChar)){ //check letter then add to count
                letterCount[curntChar - 'a']++;
            }
        }
        return letterCount;
    }

    /**
     * Calculates the decimal frequency of each letter in the string depending the number of letters.
     *
     * Cycles through every single letter in the string's frequency and divides it by the total number of letters.
     *
     * @param  str2 string that will be used in count function and divided.
     * @return the frequency of letters in string depending on the total number of letters.
     */
    public static double[] frequency(String str2){
        double[] letrsFreq = new double[26];
        int[] letterCount = count(str2);
        int totalCount = str2.length(); //total letter count
        str2 = str2.toLowerCase();

        for (int i=0; i<26; i++){ //checks frequency for each letter in alphabet and divides by total
            letrsFreq[i] = (double) letterCount[i] / totalCount;
        }

        return letrsFreq;
    }

    /**
     * Calculates the Chi Squared Score.
     *
     * Cycles through every single letter's frequency score and the expected scores and compares.
     *
     * @param  calculated double array that holds the calculated scores for the string's letter frequency.
     * @param  expected double array that holds the expected scores for letter frequency.
     * @return the chi score depending on the different calculated scores.
     */
    public static double chiSquared(double[] calculated, double[] expected){
        double chiScore = 0;

        // go through each element in array
        for (int i=0; i<calculated.length; i++){
            double letterCalc = Math.pow(calculated[i] - expected[i], 2) / expected[i];
            chiScore += letterCalc;
        }
        return chiScore;
    }

    /**
     * Takes in the encrypted text and finds the best decryption it can.
     *
     * Takes the input, runs it through the chiSquared method trying every single possible shift it can to see which gives the best score.
     *
     * @param  args Single input string that gives the encrypted text.
     * @return the decrypted text.
     */
    public static void main(String[] args){
        //checking if just 1 input
        if(args.length > 1){
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Brutus \"cipher text\"");
        } else if(args.length < 1){
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Brutus \"cipher text\"");
        } else{

        String inputTxt = args[0]; //input txt

        //letter frequencies
        double[] observedFreq = frequency(inputTxt);

        //find best key
        int bestKey = 0;
        double minChiSquared = chiSquared(observedFreq, english);

        //try keys
        for(int key=1; key<26; key++){
            double[] shiftedArray = new double[26];
            int length = english.length;

            for(int i=0; i<length; i++){
                int newIndex = (i + key) % length;
                shiftedArray[newIndex] = english[i];
            }

            double chiScore = chiSquared(observedFreq, shiftedArray);
            if(chiScore < minChiSquared){
                minChiSquared = chiScore;
                bestKey = key;
            }
        }

        String decryptedText = "";
        for(char c : inputTxt.toCharArray()){ //decrypts using best key found
            if(Character.isLetter(c)){
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char decryptedChar = (char) ((c - base - bestKey + 26) % 26 + base);
                decryptedText += decryptedChar;
            } else {
                decryptedText += c;
            }
        }

        System.out.println(decryptedText);
        }
    }
}

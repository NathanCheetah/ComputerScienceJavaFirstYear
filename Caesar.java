/**
 * A program to decode caesar cipher.
 *
 * @version 1.0
 */

public class Caesar {

  /**
   * Checks input args for length and calls other methods
   *
   * Checks if input arguments are greater than or less than 2.
   *
   * @param  args the string array to consider.
   * @return the results of the methods of deciphering.
   */  
  public static void main(String[] args){
    if(args.length > 2){
      System.out.println("Too many parameters!");
      System.out.println("Usage: java Caesar n \"cipher text\"");
    } else if(args.length < 2){
      System.out.println("Too few parameters!");
      System.out.println("Usage: java Caesar n \"cipher text\"");
    } else{
      int shift = Integer.parseInt(args[0]);
      String plainText = args[1];

      String changedTxt = rotate(shift, plainText); //calls function
      System.out.println(changedTxt);
    }
  }

  /**
   * Deciphers the individual character that is inputted by the number that is inputted into the method.
   *
   * Does a character shift and checks if that shift is done whether it will stay in the same character base.
   *
   * @param  shift integer for how many shifts for the caeser cipher to be decoded.
   * @param  chara input of character to be shifted.
   * @return the deciphered character.
   */
  public static char rotate(int shift, char chara){ //rotate for just characters
    char base;
    if (!Character.isLetter(chara)){ //checks if letter 
    return chara;
    }

    if(Character.isLowerCase(chara)){ //checks for lower or upper case
      base = 'a';
    } else {
      base = 'A';
    }

    char outputChar = (char) ((chara - base + shift + 26) % 26 + base); //shifts depending on the base and if it needs to loop back round
    return outputChar;
  }

  /**
   * Deciphers the whole input string.
   *
   * Splits the string into characters and runs those characters through the other rotate method and adding the characters back together afterwards.
   *
   * @param  shift integer for how many shifts for the caeser cipher to be decoded.
   * @param  str input of string to be shifted.
   * @return the deciphered string.
   */
  public static String rotate(int shift, String str){ //rotate for strings
    String result = "";
    for(char letter : str.toCharArray()){ //turns string into char array and uses other rotate function
      result += rotate(shift, letter);
    }
    return result;
  }
}

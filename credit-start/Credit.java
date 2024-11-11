public class Credit {
  public static void main(String[] args) {
    long number = Comp122.getLong("Number: ");
    double totalSum = 0;
    String bank="INVALID";

    // do stuff here with the number
    String numStr = Long.toString(number);
    for (int i = numStr.length() - 1; i >= 0; i--) {

      //convert digits back to numbers from string
      int digit = Character.getNumericValue(numStr.charAt(i));

      //every second digit * 2
      if ((numStr.length() - i) % 2 == 0) {
        digit *= 2;

        if (digit > 9) { //add if greater than 9
          digit = digit % 10 + digit / 10;
        }
      }

      totalSum += digit;
    }

    while(number > 100)
    {
      number=number/10;
    }
    int first2Digits = (int)number;

    //final check if valid (if divisible by 10) + choosing a bank
    if(totalSum % 10 == 0)
    {
      if(first2Digits==34 || first2Digits==37)
      {
        bank = "AMEX";
      } else if(first2Digits>50 && first2Digits<56)
      {
        bank = "MASTERCARD";
      } else if(first2Digits>39 && first2Digits<50)
      {
        bank = "VISA";
      } else
      {
        bank = "INVALID";
      }
    }

    System.out.println(bank);
  }
}

public class StringApp {

    // Part 1
    public static String pow(String s, int n) {
        // your code here
        String word = "";
        for (int i = 0; i < n; i++){ //outputs concatenated string for amount of times wanted
            word += s;
        }
        return word;
    }


    // Part 2
    public static int factorCount(String a, String f){
        int count = 0;
        int pos = 0;

        while ((pos = a.indexOf(f, pos)) != -1){ //loops through string to check for copies
            count++;
            pos += f.length();
        }
        return count;
    }
    
    public static int factorCount(String a, String f, boolean caseSensitive){
        int count = 0;
        int pos = 0;

        if (caseSensitive == false){ //checks if needs to be changed to all same case
            a = a.toLowerCase();
            f = f.toLowerCase();
        }

        while ((pos = a.indexOf(f, pos)) != -1){ //loops through string to check for copies
            count++;
            pos += f.length();
        }
        return count;
    }
    

    // Part 3
    public static void main(String[] args) {
        String lowCase = args[0].toLowerCase(); //sets all letters to lower case

        for (char letter = 'a'; letter <= 'z'; letter++){ //checks for each letter in alphabet
            int count = 0;

            for (int x = 0; x < lowCase.length(); x++){ //goes over each char in string 'lowCase'
                char ch = lowCase.charAt(x);
                if (ch == letter){ //compares char in string to letter in alphabet in current loop
                    count++;
                }
            }

            System.out.println(letter + ": " + count); //prints letter with count
        }  
    }  
}

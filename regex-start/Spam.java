import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.file.Files;

public class Spam {
    public static void main(String[] args) throws IOException {
        // First read in the file
        String spam = getSpam();    

        Matcher matcher = null;
            
        // Part 6
        if(args[0].equals("0")){
            matcher = matchFrom(spam);
            while(matcher.find()){
                System.out.println(matcher.group());
            }
        }

        // Part 7
        if (args[0].equals("1")) {
            // Define our pattern and matches
            matcher = matchEmails(spam);

            // Loop through our matches
            while(matcher.find()){
                System.out.println(matcher.group());
            }
        }
        
        // Part 8
        if (args[0].equals("2")) {
            // Define our pattern and matches
            matcher = matchSenders(spam);

            // Loop through our matches
            while(matcher.find()){
                System.out.println(matcher.group(1));
            }
        }
    }

    public static String getSpam() throws IOException {
        File file = new File("./Spam.txt");
        String fileContent = Files.readString(file.toPath());

        return fileContent;
    }

    // Part 6
    public static Matcher matchFrom(String input) {
        Pattern pattern = Pattern.compile("From:.*"); // CHANGE ME

        // Instantiate our pattern matcher object
        Matcher matcher = pattern.matcher(input);

        return matcher;
    }

    // Part 7
    public static Matcher matchEmails(String input) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");

        // Instantiate our pattern matcher object
        Matcher matcher = pattern.matcher(input);

        return matcher;
    }

    // Part 8
    public static Matcher matchSenders(String input) {
        Pattern pattern = Pattern.compile("From:.*?<([^<>]+)>"); // CHANGE ME

        // Instantiate our pattern matcher object
        Matcher matcher = pattern.matcher(input);
 
        return matcher;
    }
}
